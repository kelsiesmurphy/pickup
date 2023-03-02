package org.pickup.backend.server.models.stats;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.views.EventView;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({
        "users",
        "litter"
})
public class EventStats {

    @JsonView(EventView.Summary.class)
    @JsonIncludeProperties({"total"})
    private Map<String, Object> users;

    @JsonView(EventView.Summary.class)
    @JsonIncludeProperties({"total"})
    private Map<String, Object> litter;

    public EventStats() {
        this.users = new HashMap<>();
        this.litter = new HashMap<>();
    }

    public void setUsersTotal(Long count) {
        this.users.put("total", count);
    }
    public void setLitterTotal(Long count) {
        this.litter.put("total", count);
    }

    public Map<String, Object> getUsers() {return users;}
    public Map<String, Object> getLitter() {return litter;}
}
