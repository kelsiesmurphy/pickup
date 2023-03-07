package org.pickup.backend.server.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.models.User;
import org.pickup.backend.server.repositories.UserRepository;
import org.pickup.backend.server.utils.stats.UserStatsBuilder;
import org.pickup.backend.server.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserStatsBuilder userStatsBuilder;

    @JsonView(UserView.Summary.class)
    @GetMapping(value = "/users")
    public ResponseEntity getCommunityUsers(
            @RequestParam long communityId
    ) {
        try {
            List<User> result = userRepository.findByCommunityId(communityId);
            userStatsBuilder.build(result, communityId);
//            for (User user : result) {
//                user.setStats(userStatsBuilder.build(user.getId()));
//            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @JsonView(UserView.Detail.class)
    @GetMapping(value = "/users/{id}")
    public ResponseEntity getSingleUserDetail(
            @PathVariable long id
    ) {
        try {
            Optional<User> result = userRepository.findById(id);
            return result.map(user -> {
                user.setStats(userStatsBuilder.build(user.getId()));
                return new ResponseEntity<>(user, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @JsonView(UserView.Detail.class)
    @PutMapping(value = "/users/{id}")
    public ResponseEntity updateUserDetail(
            @PathVariable long id,
            @RequestBody User updatedUser
    ) {
        try {
            Optional<User> result = userRepository.findById(id);
            return result.map(user -> {
                user = updatedUser;
                userRepository.save(user);
                user.setStats(userStatsBuilder.build(user.getId()));
                return new ResponseEntity<>(user, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @JsonView(UserView.Detail.class)
    @PatchMapping(value = "/users/{id}")
    public ResponseEntity patchIsUserActive(
            @PathVariable long id,
            @RequestBody Map<String, Object> userStatus
    ) {
        try {
            Optional<User> result = userRepository.findById(id);
            return result.map(user -> {
                user.setActive((boolean)userStatus.get("is_active"));
                userRepository.save(user);
                user.setStats(userStatsBuilder.build(user.getId()));
                return new ResponseEntity<>(user, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
