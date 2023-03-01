package org.pickup.backend.server.utils.stats;

import org.pickup.backend.server.models.Community;
import org.pickup.backend.server.models.stats.CommunityStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommunityStatBuilder {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public CommunityStats build(Long community_id) {
        CommunityStats stats = new CommunityStats();
        stats.setUsersTotal(getCountUsersForCommunity(community_id));
        stats.setCompletedEventsTotal(getCountEventsForCommunity(community_id));
        stats.setLitterPickedTotal(getCountLitterForCommunity(community_id));
        return stats;
    }

    private Long getCountUsersForCommunity(Long community_id) {
        String sql =
                "SELECT count(*) " +
                "FROM users u " +
                "WHERE u.is_active = true AND u.community_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, community_id);
    }

    private Long getCountEventsForCommunity(Long community_id) {
        String sql =
                "SELECT count(*) " +
                "FROM events e " +
                "WHERE e.is_active = true AND e.community_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, community_id);
    }

    private Long getCountLitterForCommunity(Long community_id) {
        String sql =
                "SELECT count(*) " +
                "FROM litter l " +
                "WHERE l.is_active = true AND l.community_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, community_id);
    }
}
