package org.pickup.backend.server.models.stats;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.views.UserView;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({
        "litter",
        "events"
})
public class UserStats {

    @JsonView(UserView.Summary.class)
    @JsonProperty("events")
    private Map<String, Object> events;

    @JsonView(UserView.Summary.class)
    @JsonProperty("litter")
    private Map<String, Object> litter;

    Long total;

    Long thisMonth;

    Long lastMonth;

    @JsonView(UserView.Summary.class)
    @JsonProperty("events")
    private void unpackEventStats(Map<String, Object> nestedStat) {
        total = (Long)nestedStat.get("total");
    }

    @JsonView(UserView.Summary.class)
    @JsonProperty("litter")
    private void unpackLitterStats(Map<String, Object> nestedStat) {
        total = (Long)nestedStat.get("total");
        thisMonth = (Long)nestedStat.get("this_month");
        lastMonth = (Long)nestedStat.get("last_month");
    }

    public UserStats() {
        this.events = new HashMap<>();
        this.litter = new HashMap<>();
    }

    public void setEventsTotal(Long count) {
        this.events.put("total", count);
    }
    public void setLitterTotal(Long count) {
        this.litter.put("total", count);
    }
    public void setLitterThisMonth(Long count) {
        this.litter.put("this_month", count);
    }
    public void setLitterLastMonth(Long count) {
        this.litter.put("last_month", count);
    }

    public Map<String, Object> getEvents() {
        return events;
    }

    public Map<String, Object> getLitter() {
        return litter;
    }
}
