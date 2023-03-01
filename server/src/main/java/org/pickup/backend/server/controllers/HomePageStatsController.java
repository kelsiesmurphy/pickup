package org.pickup.backend.server.controllers;

import org.pickup.backend.server.helpers.StatsQueries;
import org.pickup.backend.server.models.stats.HomePageStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageStatsController {

    @Autowired
    StatsQueries statsQueries;

    @GetMapping(value = "/home-stats")
    public ResponseEntity getHomeStats() {
        HomePageStats stats = new HomePageStats();
        stats.setTotalCommunities(statsQueries.getTotalCommunitiesCount());
        stats.setTotalUsers(statsQueries.getTotalUserCount());
        stats.setTotalEventsCompleted(statsQueries.getTotalEventsCount());
        stats.setTotalLitterCollected(statsQueries.getTotalLitterCount());

        return new ResponseEntity<>(
                stats,
                HttpStatus.OK
        );
    }
}
