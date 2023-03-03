package org.pickup.backend.server.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MonthlyStatsQueryBuilder {

    public static final String makeDate =
            "make_date(" +
                "EXTRACT(YEAR FROM event_date_time_start)::INTEGER, " +
                "EXTRACT(MONTH FROM event_date_time_start)::INTEGER, " +
                "1" +
            ") ";

    public static Map<String, Long> processResults(
            List<Map<String, Object>> results
    ) {
        return results
                .stream()
                .collect(Collectors
                        .toMap(k -> k.get("month").toString(), v -> (Long)v.get("count")));
    }

    public static String makeMonth(String columnName) {
        String result =
                "make_date(" +
                    "EXTRACT(YEAR FROM " + columnName + ")::INTEGER, " +
                    "EXTRACT(MONTH FROM " + columnName + ")::INTEGER, " +
                    "1) ";
        return result;
    }
}
