package org.pickup.backend.server.models.stats;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.pickup.backend.server.utils.RawJsonDeserializer;
import org.pickup.backend.server.views.CommunityView;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({
        "users",
        "events",
        "litter",

})
public class CommunityStats {

    @JsonProperty("users")
    private Map<String, Object> users;

    @JsonProperty("events")
    private Map<String, Object> events;

    @JsonProperty
    private Map<String, Object> litter;

    Long total;

    Map<String, Long> monthlyData;

    @JsonView(CommunityView.Summary.class)
    @JsonProperty("events")
    private void unpackEventStats(Map<String, Object> nestedStat) {
        total = (Long)nestedStat.get("total");
        monthlyData = (Map<String, Long>)nestedStat.get("monthly_data");
    }

    @JsonView(CommunityView.Summary.class)
    @JsonProperty("users")
    private void unpackUsersStats(Map<String, Object> nestedStat) {
        total = (Long)nestedStat.get("total");
        monthlyData = (Map<String, Long>)nestedStat.get("monthly_data");
    }

    @JsonView(CommunityView.Summary.class)
    @JsonProperty("litter")
    private void unpackLitterStats(Map<String, Object> nestedStat) {
        total = (Long)nestedStat.get("total");
        monthlyData = (Map<String, Long>)nestedStat.get("monthly_data");
    }

    public CommunityStats() {
        this.users = new HashMap<>();
        this.events = new HashMap<>();
        this.litter = new HashMap<>();
    }

    public void setUsersTotal(Long count) {
        this.users.put("total", count);
    }
    public void setCompletedEventsTotal(Long count) {
        this.events.put("total", count);
    }
    public void setLitterPickedTotal(Long count) {
        this.litter.put("total", count);
    }

    public void setUsersMonthlyData(Map<String, Long> monthlyData) {
        this.users.put("monthly_data", monthlyData);
    }
    public void setCompletedEventsMonthlyData(Map<String, Long> monthlyData) {
        this.events.put("monthly_data", monthlyData);
    }
    public void setLitterPickedMonthlyData(Map<String, Long> monthlyData) {
        this.litter.put("monthly_data:", monthlyData);
    }

    public Map<String, Object> getUsers() {
        return users;
    }

    public Map<String, Object> getEvents() {
        return events;
    }

    public Map<String, Object> getLitter() {
        return litter;
    }

}
