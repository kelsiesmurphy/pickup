package org.pickup.backend.server.utils.stats;

import org.pickup.backend.server.models.stats.HomePageStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class HomePageStatsBuilder {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public HomePageStats build() {
        HomePageStats stats = new HomePageStats();
        stats.setTotalCommunities(getTotalCommunitiesCount());
        stats.setTotalUsers(getTotalUsersCount());
        stats.setTotalEventsCompleted(getTotalEventsCount());
        stats.setTotalLitterCollected(getTotalLitterCount());
        return stats;
    }

    private Long getTotalCommunitiesCount() {
        String sql =
                "SELECT count(*) " +
                "FROM communities c " +
                "WHERE c.is_active = true";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    private Long getTotalUsersCount() {
        String sql =
                "SELECT count(*) " +
                "FROM users u " +
                "WHERE u.is_active = true";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    private Long getTotalEventsCount() {
        String sql =
                "SELECT count(*) " +
                "FROM events e " +
                "WHERE e.is_active = true";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    private Long getTotalLitterCount() {
        String sql =
                "SELECT count(*) " +
                "FROM litter l " +
                "WHERE l.is_active = true";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
