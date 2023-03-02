package org.pickup.backend.server.models.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({
        "total_communities",
        "total_users",
        "total_events_completed",
        "total_litter_collected"
})
public class HomePageStats {

    @JsonProperty("total_communities")
    private Long totalCommunities;

    @JsonProperty("total_users")
    private Long totalUsers;

    @JsonProperty("total_events_completed")
    private Long totalEventsCompleted;
    @JsonProperty("total_litter_collected")
    private Long totalLitterCollected;

    public HomePageStats() {

    }

    // Getters and setters
    public Long getTotalCommunities() {
        return totalCommunities;
    }

    public void setTotalCommunities(Long totalCommunities) {
        this.totalCommunities = totalCommunities;
    }

    public Long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(Long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public Long getTotalEventsCompleted() {
        return totalEventsCompleted;
    }

    public void setTotalEventsCompleted(Long totalEventsCompleted) {
        this.totalEventsCompleted = totalEventsCompleted;
    }

    public Long getTotalLitterCollected() {
        return totalLitterCollected;
    }

    public void setTotalLitterCollected(Long totalLitterCollected) {
        this.totalLitterCollected = totalLitterCollected;
    }
}
