package org.pickup.backend.server.utils.stats;

import org.pickup.backend.server.models.stats.UserStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserStatsBuilder {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserStats build(Long userId) {
        UserStats stats = new UserStats();
        stats.setEventsTotal(getEventsCountByUser(userId));
        stats.setLitterTotal(getLitterCountByUser(userId));
        return stats;
    }

    private Long getEventsCountByUser(Long userId) {
        String sql =
                "SELECT count(distinct event_id) " +
                "FROM litter l " +
                "WHERE l.is_active = true AND l.user_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, userId);
    }

    private Long getLitterCountByUser(Long userId) {
        String sql =
                "SELECT count(*) " +
                "FROM litter l " +
                "WHERE l.is_active = true and l.user_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, userId);
    }
}
