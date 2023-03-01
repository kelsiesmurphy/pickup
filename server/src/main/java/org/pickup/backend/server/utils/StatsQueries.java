package org.pickup.backend.server.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class StatsQueries {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // Communities Stats
    public Long getTotalCommunitiesCount() {
        String sql =
                "SELECT count(*) " +
                "FROM communities c " +
                "WHERE c.is_active = true";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    // User Stats
    public Long getTotalUserCount() {
        String sql =
                "SELECT count(*) " +
                "FROM users u " +
                "WHERE u.is_active = true";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long getUserCountByCommunityId(long community_id) {
        String sql =
                "SELECT count(*) " +
                "FROM users u " +
                "WHERE u.is_active = true AND u.community_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, community_id);
    }

    // Events
    public Long getTotalEventsCount() {
        String sql =
                "SELECT count(*) " +
                "FROM events e " +
                "WHERE e.is_active = true";
        return jdbcTemplate.queryForObject(sql, Long.class);

    }

    public Long getEventCountByCommunityId(long community_id) {
        String sql =
                "SELECT count(*) " +
                "FROM events e " +
                "WHERE e.is_active = true AND community_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, community_id);
    }

    // Litter
    public Long getTotalLitterCount() {
        String sql =
                "SELECT count(*) " +
                "FROM litter l " +
                "WHERE l.is_active = true";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long getLitterCountByCommunityId(long community_id) {
        String sql =
                "SELECT count(*) " +
                "FROM litter l " +
                "WHERE l.is_active = true AND community_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, community_id);
    }

}
