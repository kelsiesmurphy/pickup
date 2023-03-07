package org.pickup.backend.server.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.models.User;
import org.pickup.backend.server.models.UserContext;
import org.pickup.backend.server.repositories.UserRepository;
import org.pickup.backend.server.utils.stats.UserStatsBuilder;
import org.pickup.backend.server.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserContextController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserStatsBuilder userStatsBuilder;

    @JsonView(UserView.Detail.class)
    @GetMapping(value = "/user-context")
    public ResponseEntity getUserContext(
            @RequestParam String auth0Id
    ) {
        try {
            Optional<User> result = userRepository.findByAuth0Id(auth0Id);
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
    @PostMapping(value = "/user-context")
    public ResponseEntity createUserContext(
            @RequestBody UserContext userAuth
    ) {
        try {
            System.out.println(userAuth);
            Optional<User> result = userRepository.findByAuth0Id(userAuth.getAuth0Id());
            return result.map(user -> {
                user.setStats(userStatsBuilder.build(user.getId()));
                return new ResponseEntity<>(user, HttpStatus.ALREADY_REPORTED);
            }).orElseGet(() -> {
                User user = new User(
                        userAuth.getCommunityId(),
                        userAuth.getUserName(),
                        userAuth.getEmail(),
                        userAuth.getImgProfileLink(),
                        false,
                        userAuth.getCreateDate()
                );

                user.setAuth0Id(userAuth.getAuth0Id());
                userRepository.save(user);
                userRepository.refresh(user);
                user.setStats(userStatsBuilder.build(user.getId()));
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            });
        }
        catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
