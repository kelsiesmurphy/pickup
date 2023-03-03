package org.pickup.backend.server.utils.stats;

import org.pickup.backend.server.models.stats.CommunityStats;
import org.pickup.backend.server.utils.MonthlyStatsQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommunityStatsBuilder {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public CommunityStats build(Long communityId) {
        CommunityStats stats = new CommunityStats();
        stats.setUsersTotal(getCountUsersForCommunity(communityId));
        stats.setCompletedEventsTotal(getCountEventsForCommunity(communityId));
        stats.setLitterPickedTotal(getCountLitterForCommunity(communityId));
        try {
//            System.out.println(getCountEventsByMonthForCommunity2(communityId).toString());
            stats.setCompletedEventsMonthlyData(
                    getCountEventsByMonthForCommunity(communityId)
            );
            stats.setUsersMonthlyData(
                    getCountUsersByMonthForCommunity(communityId)
            );

            stats.setLitterPickedMonthlyData(
                    getLitterCountByMonthForCommunity(communityId)
            );

        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(stats.getEvents());
        System.out.println(stats.getEvents().get("monthly_data"));
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
                "SELECT " + MonthlyStatsQueryBuilder.makeDate +
                        "as month, count(*) as count " +
                        "FROM events e " +
                        "WHERE e.is_active = true " +
                        "AND e.community_id = ? " +
                        "AND e.event_date_time_end < NOW() " +
                        "GROUP BY " + MonthlyStatsQueryBuilder.makeDate;

        return MonthlyStatsQueryBuilder.processResults(
                jdbcTemplate.queryForList(sql, communityId)
        );
    }

    private Map<String, Long> getCountUsersByMonthForCommunity(Long communityId) {
        String sql =
                "SELECT " + MonthlyStatsQueryBuilder.makeMonth("collection_date_time") +
                        "as month, count(distinct user_id) as count " +
                        "FROM litter l " +
                        "WHERE l.is_active = true " +
                        "AND l.community_id = ? " +
                        "GROUP BY " + MonthlyStatsQueryBuilder.makeMonth("collection_date_time");

        return MonthlyStatsQueryBuilder.processResults(
                jdbcTemplate.queryForList(sql, communityId)
        );
    }

    private Map<String, Long> getLitterCountByMonthForCommunity(Long communityId) {
        String sql =
                "SELECT " + MonthlyStatsQueryBuilder.makeMonth("collection_date_time") +
                        "as month, count(*) as count " +
                        "FROM litter l " +
                        "WHERE l.is_active = true " +
                        "AND l.community_id = ? " +
                        "GROUP BY " + MonthlyStatsQueryBuilder.makeMonth("collection_date_time");

        return MonthlyStatsQueryBuilder.processResults(
                jdbcTemplate.queryForList(sql, communityId)
        );
    }

}
