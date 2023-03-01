package org.pickup.backend.server.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.models.User;
import org.pickup.backend.server.repositories.UserRepository;
import org.pickup.backend.server.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @JsonView(UserView.Summary.class)
    @GetMapping(value = "/users")
    public ResponseEntity getCommunityUsers(
            @RequestParam long communityId
    ) {
        return new ResponseEntity<>(
                userRepository.findByCommunityId(communityId),
                HttpStatus.OK
        );
    }

    @JsonView(UserView.Detail.class)
    @GetMapping(value = "/users/{id}")
    public ResponseEntity getSingleUserDetail(
            @PathVariable long id
    ) {
        return new ResponseEntity<>(
                userRepository.findById(id),
                HttpStatus.OK
        );
    }

    @JsonView(UserView.Detail.class)
    @PutMapping(value = "/users/{id}")
    public ResponseEntity updateUserDetail(
            @PathVariable long id,
            @RequestBody User user
    ) {
        Optional<User> existing = userRepository.findById(id);
        ResponseEntity<User> resEntity;
        resEntity = existing.map(u -> {
            u = user;
            userRepository.save(u);
            return new ResponseEntity<>(u, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
        return resEntity;
    }

    @JsonView(UserView.Detail.class)
    @PatchMapping(value = "/users/{id}")
    public ResponseEntity patchIsUserActive(
            @PathVariable long id,
            @RequestBody Map<String, Object> user
    ) {
        Optional<User> existing = userRepository.findById(id);
        ResponseEntity<User> resEntity;
        resEntity = existing.map(u -> {
            u.setActive((boolean)user.get("is_active"));
            userRepository.save(u);
            return new ResponseEntity<>(u, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
        return resEntity;
    }
}
