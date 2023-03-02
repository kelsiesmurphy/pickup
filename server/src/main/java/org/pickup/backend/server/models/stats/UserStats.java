package org.pickup.backend.server.models.stats;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.views.UserView;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({

})
public class UserStats {

    @JsonView(UserView.Summary.class)
    @JsonIncludeProperties({"total"})
    private Map<String, Object> events;

    @JsonView(UserView.Summary.class)
    @JsonIncludeProperties({"total"})
    private Map<String, Object> litter;

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

    public Map<String, Object> getEvents() {
        return events;
    }

    public Map<String, Object> getLitter() {
        return litter;
    }
}
