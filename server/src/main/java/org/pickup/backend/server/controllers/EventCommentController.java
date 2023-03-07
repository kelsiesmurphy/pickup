package org.pickup.backend.server.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.models.EventComment;
import org.pickup.backend.server.repositories.EventCommentRepository;
import org.pickup.backend.server.views.EventCommentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventCommentController {

    @Autowired
    EventCommentRepository eventCommentRepository;

    @JsonView(EventCommentView.Summary.class)
    @PostMapping(value = "/comments")
    public ResponseEntity createComment(
            @RequestBody EventComment comment
    ) {
        try {
            eventCommentRepository.save(comment);
            eventCommentRepository.refresh(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

}
