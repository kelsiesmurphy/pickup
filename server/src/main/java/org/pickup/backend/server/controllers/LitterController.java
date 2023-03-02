package org.pickup.backend.server.controllers;

import org.pickup.backend.server.models.Litter;
import org.pickup.backend.server.models.stats.LitterSubmitStats;
import org.pickup.backend.server.repositories.LitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LitterController {

    @Autowired
    LitterRepository litterRepository;

    @PostMapping(value = "/litter")
    public ResponseEntity createLitter(
            @RequestBody List<Litter> litter
    ) {
        try {
            litterRepository.saveAll(litter);
            for (Litter litterItem : litter) {
                litterRepository.refresh(litterItem);
            }
            LitterSubmitStats stats = new LitterSubmitStats(litter);
            return new ResponseEntity<>(stats, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
