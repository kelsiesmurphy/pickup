package org.pickup.backend.server.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.models.EmailSignup;
import org.pickup.backend.server.repositories.EmailSignupRepository;
import org.pickup.backend.server.views.EmailSignupView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailSignupController {

    @Autowired
    EmailSignupRepository emailSignupRepository;

    @JsonView(EmailSignupView.PostReturn.class)
    @GetMapping(value = "/email-signup")
    public ResponseEntity getEmailSigups() {
        try {
            List<EmailSignup> result = emailSignupRepository.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/email-signup")
    @JsonView(EmailSignupView.PostReturn.class)
    public ResponseEntity postEmailSignup(
            @RequestBody EmailSignup emailSignup
    ){
        try {
            emailSignupRepository.save(emailSignup);
            return new ResponseEntity<>(emailSignup, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

}
