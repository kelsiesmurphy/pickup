package org.pickup.backend.server.utils.stats;

import org.pickup.backend.server.models.stats.EventStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventStatsBuilder {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public EventStats build(Long eventId) {
        EventStats stats = new EventStats();
        stats.setUsersTotal(getCountLitterForEvent(eventId));
        stats.setLitterTotal(getCountLitterForEvent(eventId));
        return stats;
    }

    private Long getCountUsersForEvent(Long eventId) {
        String sql =
                "SELECT count(distinct user_id) " +
                "FROM litter l " +
                "WHERE l.is_active = true AND l.event_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, eventId);
    }

    private Long getCountLitterForEvent(Long eventId) {
        String sql =
                "SELECT count(*) " +
                "FROM litter l " +
                "WHERE l.is_active = true AND l.event_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, eventId);
    }
}
