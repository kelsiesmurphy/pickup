package org.pickup.backend.server.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.models.Community;
import org.pickup.backend.server.repositories.CommunityRepository;
import org.pickup.backend.server.views.CommunityView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CommunityController {

    @Autowired
    CommunityRepository communityRepository;

    @JsonView(CommunityView.Summary.class)
    @GetMapping(value = "/communities")
    public ResponseEntity getPublicCommunitySummary() {
        return new ResponseEntity<>(
                communityRepository.findByIsPrivate(false),
                HttpStatus.OK
        );
    }

    @JsonView(CommunityView.Detail.class)
    @GetMapping(value = "/communities/{id}")
    public ResponseEntity getCommunityDetail(@PathVariable long id) {
//        Optional<Community> community = communityRepository.findById(id);
        return new ResponseEntity<>(
                communityRepository.findById(id),
                HttpStatus.OK
        );
    }

    @JsonView(CommunityView.Detail.class)
    @PostMapping(value = "/communities")
    public ResponseEntity createCommunity(@RequestBody Community community) {
        try {
            communityRepository.save(community);
            return new ResponseEntity<>(
                    community,
                    HttpStatus.OK
            );
        }
        catch(Exception e) {
            return new ResponseEntity<>(
                    e,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @JsonView(CommunityView.Detail.class)
    @PutMapping(value = "/communities/{id}")
    public ResponseEntity updateCommunity(
            @RequestBody Community community,
            @PathVariable long id
    ) {
        Optional<Community> existing = communityRepository.findById(id);
        ResponseEntity<Community> resEntity;
        resEntity = existing.map(c -> {
            c = community;
            communityRepository.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
        return resEntity;
    }
}
