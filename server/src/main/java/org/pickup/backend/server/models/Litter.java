package org.pickup.backend.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.utils.DateTimeParse;
import org.pickup.backend.server.views.LitterView;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "litter")
@JsonPropertyOrder({
        "id",
        "community_id",
        "event_id",
        "user_id",
        "litter_type_id",
        "collection_date_time"
})
public class Litter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(LitterView.Summary.class)
    private long id;

    @ManyToOne(targetEntity = Community.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "community_id", insertable=false, updatable=false)
//    @JsonIgnoreProperties({"litter"})
    private Community community;

    @Column(name = "community_id", nullable = false)
    @JsonView(LitterView.Summary.class)
    @JsonProperty("community_id")
    private Long communityId;

    @ManyToOne(targetEntity = Event.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", insertable=false, updatable=false)
//    @JsonIgnoreProperties({"litter"})
    private Event event;

    @Column(name = "event_id", nullable=true)
    @JsonView(LitterView.Summary.class)
    @JsonProperty("event_id")
    private Long eventId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
//    @JsonIgnoreProperties({"litter"})
    private User user;

    @Column(name = "user_id", nullable=false)
    @JsonView(LitterView.Summary.class)
    @JsonProperty("user_id")
    private Long userId;

    @ManyToOne(targetEntity = LitterType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "litter_type_id", insertable=false, updatable=false)
//    @JsonIgnoreProperties({"litter"})
    private LitterType litterType;

    @Column(name = "litter_type_id", nullable=false)
    @JsonView(LitterView.Summary.class)
    @JsonProperty("litter_type_id")
    private Long litterTypeId;

    @Column(name = "collection_date_time")
    @JsonView(LitterView.Summary.class)
    @JsonProperty("collection_date_time")
    private LocalDateTime collectionDateTime;

    @Column(name = "is_active")
    private Boolean isActive = true;

    public Litter(
            Long communityId,
            Long eventId,
            Long userId,
            Long litterTypeId,
            String collectionDateTime) {
        this.communityId = communityId;
        this.eventId = eventId;
        this.userId = userId;
        this.litterTypeId = litterTypeId;
        this.collectionDateTime = DateTimeParse.fromString(collectionDateTime);
        this.isActive = true;
    }
    public Litter() {}
    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Community getCommunity() {
        return community;
    }

    public Event getEvent() {
        return event;
    }

    public User getUser() {
        return user;
    }

    public LitterType getLitterType() {
        return litterType;
    }

    public String getCollectionDateTime() {
        return DateTimeParse.toString(collectionDateTime);
    }

    public void setCollectionDateTime(String collectionDateTime) {
        this.collectionDateTime = DateTimeParse.fromString(collectionDateTime);
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLitterTypeId() {
        return litterTypeId;
    }

    public void setLitterTypeId(Long litterTypeId) {
        this.litterTypeId = litterTypeId;
    }
}
