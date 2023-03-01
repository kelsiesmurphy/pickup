package org.pickup.backend.server.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.models.Community;
import org.pickup.backend.server.repositories.CommunityRepository;
import org.pickup.backend.server.utils.stats.CommunityStatBuilder;
import org.pickup.backend.server.views.CommunityView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CommunityController {

    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    CommunityStatBuilder communityStatBuilder;

    @JsonView(CommunityView.Summary.class)
    @GetMapping(value = "/communities")
    public ResponseEntity getCommunitiesSummary() {
        try {
            List<Community> result = communityRepository.findByIsPrivate(false);
            for (Community community : result) {
                community.setStats(communityStatBuilder.build(community.getId()));
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @JsonView(CommunityView.Detail.class)
    @GetMapping(value = "/communities/{id}")
    public ResponseEntity getCommunityDetail(
            @PathVariable long id
    ) {
        try {
            Optional<Community> result = communityRepository.findById(id);
            return result.map(community -> {
                community.setStats(communityStatBuilder.build(community.getId()));
                return new ResponseEntity<>(community, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @JsonView(CommunityView.Detail.class)
    @PostMapping(value = "/communities")
    public ResponseEntity createCommunity(
            @RequestBody Community community
    ) {
        try {
            communityRepository.save(community);
            community.setStats(communityStatBuilder.build(community.getId()));
            return new ResponseEntity<>(community, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @JsonView(CommunityView.Detail.class)
    @PutMapping(value = "/communities/{id}")
    public ResponseEntity updateCommunity(
            @RequestBody Community updatedCommunity,
            @PathVariable long id
    ) {
        try {
            Optional<Community> result = communityRepository.findById(id);
            return result.map(community -> {
                community = updatedCommunity;
                communityRepository.save(community);
                community.setStats(communityStatBuilder.build(community.getId()));
                return new ResponseEntity<>(community, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
