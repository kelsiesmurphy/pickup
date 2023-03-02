package org.pickup.backend.server.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.models.Event;
import org.pickup.backend.server.models.EventComment;
import org.pickup.backend.server.repositories.EventRepository;
import org.pickup.backend.server.utils.stats.EventStatsBuilder;
import org.pickup.backend.server.views.EventView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventStatsBuilder eventStatBuilder;

    @JsonView(EventView.Summary.class)
    @GetMapping(value = "/events")
    public ResponseEntity getEventSummary(
            @RequestParam long communityId
    ) {
        try {
            List<Event> result = eventRepository.findByCommunityId(communityId);
            for (Event event : result) {
                event.setStats(eventStatBuilder.build(event.getId()));
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @JsonView(EventView.Detail.class)
    @GetMapping(value = "/events/{id}")
    public ResponseEntity getEventDetail(
            @PathVariable long id
    ) {
        try {
            Optional<Event> result = eventRepository.findById(id);
            return result.map(event -> {
                event.setStats(eventStatBuilder.build(event.getId()));
                return new ResponseEntity<>(event, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @JsonView(EventView.Detail.class)
    @PostMapping(value = "/events")
    public ResponseEntity createEvent(
            @RequestBody Event event
    ) {
        try {
            event.setComments(new ArrayList<EventComment>());
            eventRepository.save(event);
            event.setStats(eventStatBuilder.build(event.getId()));
            return new ResponseEntity<>(event, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @JsonView(EventView.Detail.class)
    @PutMapping(value = "/events/{id}")
    public ResponseEntity updateEvent(
            @PathVariable long id,
            @RequestBody Event updatedEvent
    ) {
        try {
            Optional<Event> result = eventRepository.findById(id);
            return result.map(event -> {
                event = updatedEvent;
                eventRepository.save(event);
                event.setStats(eventStatBuilder.build(event.getId()));
                return new ResponseEntity<>(event, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @JsonView(EventView.Detail.class)
    @PatchMapping(value = "/events/{id}")
    public ResponseEntity patchIsEventActive(
            @PathVariable long id,
            @RequestBody Map<String, Object> eventStatus
    ) {
        try {
            Optional<Event> result = eventRepository.findById(id);
            return result.map(event -> {
                event.setActive((boolean)eventStatus.get("is_active"));
                eventRepository.save(event);
                event.setStats(eventStatBuilder.build(event.getId()));
                return new ResponseEntity<>(event, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
