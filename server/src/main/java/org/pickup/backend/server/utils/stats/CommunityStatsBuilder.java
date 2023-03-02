package org.pickup.backend.server.utils.stats;

import org.pickup.backend.server.models.stats.CommunityStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CommunityStatsBuilder {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public CommunityStats build(Long communityId) {
        CommunityStats stats = new CommunityStats();
        stats.setUsersTotal(getCountUsersForCommunity(communityId));
        stats.setCompletedEventsTotal(getCountEventsForCommunity(communityId));
        stats.setLitterPickedTotal(getCountLitterForCommunity(communityId));
        return stats;
    }

    private Long getCountUsersForCommunity(Long communityId) {
        String sql =
                "SELECT count(*) " +
                "FROM users u " +
                "WHERE u.is_active = true AND u.community_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, communityId);
    }

    private Long getCountEventsForCommunity(Long communityId) {
        String sql =
                "SELECT count(*) " +
                "FROM events e " +
                "WHERE e.is_active = true " +
                    "AND e.community_id = ? " +
                    "AND e.event_date_time_end <= NOW()";
        return jdbcTemplate.queryForObject(sql, Long.class, communityId);
    }

    private Long getCountLitterForCommunity(Long communityId) {
        String sql =
                "SELECT count(*) " +
                "FROM litter l " +
                "WHERE l.is_active = true AND l.community_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, communityId);
    }

    private Map<String, Long> getCountEventsByMonthForCommunity(Long communityId) {
        String sql =
                "SELECT make_date(year(event_date_time_start), month(event_date_time_start), 1) month, count(*) " +
                "FROM events e " +
                "WHERE e.is_active = true " +
                    "AND e.community_id = ? " +
                    "AND e.event_date_time_end <= NOW() " +
                "GROUP BY make_date(year(event_date_time_start), month(event_date_time_start), 1) " +
                "ORDER BY 1";
        return jdbcTemplate.queryForObject(sql, Map.class, communityId);
    }
}
