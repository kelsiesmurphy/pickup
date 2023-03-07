package org.pickup.backend.server.utils.stats;

import org.pickup.backend.server.models.User;
import org.pickup.backend.server.models.stats.UserStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Service
public class UserStatsBuilder {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserStats build(Long userId) {
        UserStats stats = new UserStats();
        stats.setEventsTotal(getEventsTotalForUser(userId));
        stats.setLitterTotal(getLitterTotalForUser(userId));
        try {
            stats.setLitterThisMonth(getLitterThisMonthForUser(userId));
            stats.setLitterLastMonth(getLitterLastMonthForUser(userId));
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return stats;
    }

    public void build(List<User> users, Long communityId) {
        try {
            Map<Long, Long> userEventsTotal = getEventsTotalByUserForCommunity(communityId);
            Map<Long, Long> userLitterTotal = getLitterTotalByUserForCommunity(communityId);
            Map<Long, Long> userLitterThisMonth = getLitterThisMonthByUserForCommunity(communityId);
            Map<Long, Long> userLitterLastMonth = getLitterLastMonthByUserForCommunity(communityId);
            for (User user : users) {
                UserStats stats = new UserStats();
                stats.setEventsTotal(userEventsTotal.getOrDefault(user.getId(), 0L));
                stats.setLitterTotal(userLitterTotal.getOrDefault(user.getId(), 0L));
                stats.setLitterThisMonth(userLitterThisMonth.getOrDefault(user.getId(), 0L));
                stats.setLitterLastMonth(userLitterLastMonth.getOrDefault(user.getId(), 0L));
                user.setStats(stats);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    private Long getEventsTotalForUser(Long userId) {
        String sql =
                "SELECT count(distinct event_id) " +
                "FROM litter l " +
                "WHERE l.is_active = true AND l.user_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, userId);
    }

    private Map<Long, Long> getEventsTotalByUserForCommunity(Long communityId) {
        String sql =
                "SELECT l.user_id, count(distinct event_id) as count " +
                "FROM litter l " +
                "WHERE l.is_active = true AND l.community_id = ? " +
                "GROUP BY l.user_id";
        return jdbcTemplate.queryForList(sql, communityId)
                .stream()
                .collect(Collectors.toMap(k -> (Long)k.get("user_id"), v -> (Long)v.get("count")));
    }

    private Long getLitterTotalForUser(Long userId) {
        String sql =
                "SELECT count(*) " +
                "FROM litter l " +
                "WHERE l.is_active = true and l.user_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, userId);
    }

    private Long getLitterThisMonthForUser(Long userId) {
        String sql =
                "SELECT count(*) " +
                "FROM litter l " +
                "WHERE l.is_active = true " +
                    "AND l.user_id = ? " +
                    "AND l.collection_date_time >= make_date(" +
                                                        "EXTRACT(YEAR FROM NOW())::INTEGER, " +
                                                        "EXTRACT(MONTH FROM NOW())::INTEGER," +
                                                        "1) ";
        return jdbcTemplate.queryForObject(sql, Long.class, userId);
    }

    private Long getLitterLastMonthForUser(Long userId) {
        String sql =
                "SELECT count(*) " +
                "FROM litter l " +
                "WHERE l.is_active = true " +
                    "AND l.user_id = ? " +
                    "AND l.collection_date_time BETWEEN " +
                        "make_date(" +
                                "EXTRACT(YEAR FROM NOW())::INTEGER, " +
                                "EXTRACT(MONTH FROM NOW())::INTEGER, " +
                                "1) + interval '-1 month' " +
                        "AND " +
                        "make_date(" +
                                "EXTRACT(YEAR FROM NOW())::INTEGER, " +
                                "EXTRACT(MONTH FROM NOW())::INTEGER, " +
                                "1) + interval '-1 day' ";
        return jdbcTemplate.queryForObject(sql, Long.class, userId);
    }

    private Map<Long, Long> getLitterTotalByUserForCommunity(Long communityId) {
        String sql =
                "SELECT l.user_id, count(*) as count " +
                "FROM litter l " +
                "WHERE l.is_active = true " +
                    "AND l.community_id = ? " +
                "GROUP BY l.user_id";
        return jdbcTemplate.queryForList(sql, communityId)
                .stream()
                .collect(Collectors.toMap(k -> (Long)k.get("user_id"), v -> (Long)v.get("count")));
    }

    private Map<Long, Long> getLitterThisMonthByUserForCommunity(Long communityId) {
        String sql =
                "SELECT l.user_id, count(*) as count " +
                "FROM litter l " +
                "WHERE l.is_active = true " +
                    "AND l.community_id = ? "  +
                    "AND l.collection_date_time >= make_date(" +
                            "EXTRACT(YEAR FROM NOW())::INTEGER, " +
                            "EXTRACT(MONTH FROM NOW())::INTEGER," +
                            "1) " +
                "GROUP by l.user_id";
        return jdbcTemplate.queryForList(sql, communityId)
                .stream()
                .collect(Collectors.toMap(k -> (Long)k.get("user_id"), v -> (Long)v.get("count")));
    }

    private Map<Long, Long> getLitterLastMonthByUserForCommunity(Long communityId) {
        String sql =
                "SELECT l.user_id, count(*) as count " +
                "FROM litter l " +
                "WHERE l.is_active = true " +
                    "AND l.community_id = ? " +
                    "AND l.collection_date_time BETWEEN " +
                        "make_date(" +
                                "EXTRACT(YEAR FROM NOW())::INTEGER, " +
                                "EXTRACT(MONTH FROM NOW())::INTEGER, " +
                                "1) + interval '-1 month' " +
                        "AND " +
                        "make_date(" +
                                "EXTRACT(YEAR FROM NOW())::INTEGER, " +
                                "EXTRACT(MONTH FROM NOW())::INTEGER, " +
                                "1) + interval '-1 day' " +
                "GROUP BY l.user_id";
        return jdbcTemplate.queryForList(sql, communityId)
                .stream()
                .collect(Collectors.toMap(k -> (Long)k.get("user_id"), v -> (Long)v.get("count")));
    }
}
