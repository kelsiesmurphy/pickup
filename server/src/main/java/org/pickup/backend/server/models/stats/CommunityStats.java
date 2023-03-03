package org.pickup.backend.server.models.stats;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.pickup.backend.server.utils.RawJsonDeserializer;
import org.pickup.backend.server.views.CommunityView;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({
        "users",
        "events",
        "litter"
})
public class CommunityStats {

    @JsonView(CommunityView.Summary.class)
    @JsonIncludeProperties({"total", "monthly_data"})
    private Map<String, Object> users;

    @JsonView(CommunityView.Summary.class)
    @JsonIncludeProperties({"total", "monthly_data"})
    @JsonDeserialize(using = RawJsonDeserializer.class)
    private Map<String, Object> events;

    @JsonView(CommunityView.Summary.class)
    @JsonIncludeProperties({"total", "monthly_data"})
    private Map<String, Object> litter;

//    @JsonView(CommunityView.Summary.class)
//    @JsonInclude
//    private Map<String, Long> monthlyData;

    public CommunityStats() {
        this.users = new HashMap<>();
//        this.users.put("total", 0);
//        this.users.put("monthly_data", new HashMap<String, Long>());
        this.events = new HashMap<>();
//        this.events.put("total", 0);
//        this.events.put("monthly_data", new HashMap<String, Long>());
        this.litter = new HashMap<>();
//        this.litter.put("total", 0);
//        this.litter.put("monthly_data", new HashMap<String, Long>());
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
