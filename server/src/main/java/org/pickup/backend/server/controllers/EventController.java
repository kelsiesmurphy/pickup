package org.pickup.backend.server.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.models.Event;
import org.pickup.backend.server.models.EventComment;
import org.pickup.backend.server.repositories.CommunityRepository;
import org.pickup.backend.server.repositories.EventRepository;
import org.pickup.backend.server.views.EventView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    CommunityRepository communityRepository;

    @JsonView(EventView.Summary.class)
    @GetMapping(value = "/events")
    public ResponseEntity getEventSummary(
            @RequestParam long communityId
    ) {
        return new ResponseEntity<>(
                eventRepository.findByCommunityId(communityId),
                HttpStatus.OK
        );
    }

    @JsonView(EventView.Detail.class)
    @GetMapping(value = "/events/{id}")
    public ResponseEntity getEventDetail(@PathVariable long id) {
        return new ResponseEntity<>(
                eventRepository.findById(id),
                HttpStatus.OK
        );
    }

    @JsonView(EventView.Detail.class)
    @PostMapping(value = "/events")
    public ResponseEntity createEvent(@RequestBody Event event) {
        try {
            event.setComments(new ArrayList<EventComment>());
            eventRepository.save(event);
            return new ResponseEntity<>(
                    event,
                    HttpStatus.OK
            );
        }
        catch (Exception e) {
            return new ResponseEntity<>(
                    e,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @JsonView(EventView.Detail.class)
    @PutMapping(value = "/events/{id}")
    public ResponseEntity updateEvent(
            @PathVariable long id,
            @RequestBody Event event
    ) {
        Optional<Event> existing = eventRepository.findById(id);
        ResponseEntity<Event> resEntity;
        resEntity = existing.map(e -> {
            e = event;
            eventRepository.save(e);
            return new ResponseEntity<>(e, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
        return resEntity;
    }

    @JsonView(EventView.Detail.class)
    @PatchMapping(value = "/events/{id}")
    public ResponseEntity patchIsEventActive(
            @PathVariable long id,
            @RequestBody Map<String, Object> event
    ) {
        Optional<Event> existing = eventRepository.findById(id);
        ResponseEntity<Event> resEntity;
        resEntity = existing.map(e -> {
            e.setActive((boolean)event.get("is_active"));
            eventRepository.save(e);
            return new ResponseEntity<>(e, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
        return resEntity;
    }
}
