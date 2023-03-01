package org.pickup.backend.server.controllers;

import org.pickup.backend.server.models.stats.HomePageStats;
import org.pickup.backend.server.utils.stats.HomePageStatsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageStatsController {

    @Autowired
    HomePageStatsBuilder homePageStatBuilder;

    @GetMapping(value = "/home-stats")
    public ResponseEntity getHomeStats() {
        try {
            HomePageStats stats = homePageStatBuilder.build();
            return new ResponseEntity<>(stats, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
