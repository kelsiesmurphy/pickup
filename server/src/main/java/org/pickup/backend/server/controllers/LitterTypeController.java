package org.pickup.backend.server.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.models.LitterType;
import org.pickup.backend.server.repositories.LitterTypeRepository;
import org.pickup.backend.server.views.LitterTypeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LitterTypeController {

    @Autowired
    LitterTypeRepository litterTypeRepository;

    @JsonView(LitterTypeView.Summary.class)
    @GetMapping(value = "/litter-types")
    public ResponseEntity getLitterTypes() {
        return new ResponseEntity<>(
                litterTypeRepository.findAll(),
                HttpStatus.OK
        );
    }

    @JsonView(LitterTypeView.Summary.class)
    @PostMapping(value = "/litter-types")
    public ResponseEntity createLitterType(
            @RequestBody LitterType litterType
    ){
        try {
            litterTypeRepository.save(litterType);
            return new ResponseEntity<>(
                    litterType,
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
}
