package org.pickup.backend.server.utils.stats;

import org.pickup.backend.server.models.stats.CommunityStats;
import org.pickup.backend.server.utils.MonthlyStatsQueryBuilder;
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
        try {
//            System.out.println(getCountEventsByMonthForCommunity(communityId).toString());
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
                "WITH months (month) AS (" +
                    "SELECT * " +
                    "FROM db_months " +
                    "WHERE month BETWEEN (SELECT create_date FROM communities WHERE id = ?) AND NOW() " +
                ") " +

                "SELECT months.month, count(distinct e.id) as count " +
                    "FROM events e " +
                    "RIGHT JOIN months " +
                        "ON months.month = make_date(" +
                                                "EXTRACT(YEAR FROM e.event_date_time_start)::INTEGER, " +
                                                "EXTRACT(MONTH FROM e.event_date_time_start)::INTEGER, " +
                                                "1) " +
                    "WHERE e.is_active = true " +
                        "AND e.community_id = ? " +
                        "AND e.event_date_time_end < NOW() " +
                    "GROUP BY months.month " +
                    "ORDER BY months.month DESC " +
                    "LIMIT 12";

        return MonthlyStatsQueryBuilder.processResults(
                jdbcTemplate.queryForList(sql, communityId, communityId)
        );
    }

    private Map<String, Long> getCountUsersByMonthForCommunity(Long communityId) {
        String sql =
                "WITH months (month) AS (" +
                        "SELECT * " +
                        "FROM db_months " +
                        "WHERE month BETWEEN (SELECT create_date FROM communities WHERE id = ?) AND NOW() " +
                        ") " +

                        "SELECT months.month, count(distinct u.id) as count " +
                        "FROM users u " +
                        "RIGHT JOIN months " +
                        "ON months.month = make_date(" +
                                                "EXTRACT(YEAR FROM u.create_date)::INTEGER, " +
                                                "EXTRACT(MONTH FROM u.create_date)::INTEGER, " +
                                                "1) " +
                        "WHERE u.is_active = true " +
                        "AND u.community_id = ? " +
                        "GROUP BY months.month " +
                        "ORDER BY months.month DESC " +
                        "LIMIT 12 ";

        return MonthlyStatsQueryBuilder.processResults(
                jdbcTemplate.queryForList(sql, communityId, communityId)
        );
    }

    private Map<String, Long> getLitterCountByMonthForCommunity(Long communityId) {
        String sql =
                "WITH months (month) AS (" +
                        "SELECT * " +
                        "FROM db_months " +
                        "WHERE month BETWEEN (SELECT create_date FROM communities WHERE id = ?) AND NOW() " +
                        ") " +

                        "SELECT months.month, count(distinct l.id) as count " +
                        "FROM litter l " +
                        "RIGHT JOIN months " +
                        "ON months.month = make_date(" +
                                                "EXTRACT(YEAR FROM l.collection_date_time)::INTEGER, " +
                                                "EXTRACT(MONTH FROM l.collection_date_time)::INTEGER, " +
                                                "1) " +
                        "WHERE l.is_active = true " +
                        "AND l.community_id = ? " +
                        "GROUP BY months.month " +
                        "ORDER BY months.month DESC " +
                        "LIMIT 12";

        return MonthlyStatsQueryBuilder.processResults(
                jdbcTemplate.queryForList(sql, communityId, communityId)
        );
    }

}
