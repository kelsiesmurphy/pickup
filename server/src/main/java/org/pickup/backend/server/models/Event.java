package org.pickup.backend.server.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.pickup.backend.server.models.stats.EventStats;
import org.pickup.backend.server.utils.DateTimeParse;
import org.pickup.backend.server.utils.RawJsonDeserializer;
import org.pickup.backend.server.views.EventView;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
@JsonPropertyOrder({
        "id",
        "community_id",
        "name",
        "description",
        "text_body",
        "location",
        "event_date_time_start",
        "event_date_time_end",
        "img_before_link",
        "img_after_link",
        "is_active",
        "stats",
        "comments"
})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(EventView.Summary.class)
    private long id;

    @ManyToOne(targetEntity = Community.class, fetch = FetchType.EAGER)
    @JoinColumn(name="community_id", insertable=false, updatable=false)
//    @JsonIdentityReference(alwaysAsId = true)
    private Community community;

    @Column(name = "community_id", nullable=false)
    @JsonView(EventView.Summary.class)
    @JsonProperty("community_id")
    private Long communityId;

    @Column(name = "name", nullable=false)
    @JsonView(EventView.Summary.class)
    private String name;

    @Column(name = "description", nullable=false)
    @JsonView(EventView.Summary.class)
    private String description;

    @Column(name = "text_body")
    @JsonView(EventView.Detail.class)
    @JsonProperty("text_body")
    @JsonRawValue
    @JsonDeserialize(using = RawJsonDeserializer.class)
    private String textBody;

    @Column(name = "location")
    @JsonView(EventView.Summary.class)
    private String location;

    @Column(name = "event_date_time_start", nullable=false)
    @JsonView(EventView.Summary.class)
    @JsonProperty("event_date_time_start")
    private LocalDateTime eventDateTimeStart;

    @Column(name = "event_date_time_end", nullable=true)
    @JsonView(EventView.Summary.class)
    @JsonProperty("event_date_time_end")
    private LocalDateTime eventDateTimeEnd;

    @Column(name = "img_before_link", nullable=false)
    @JsonView(EventView.Summary.class)
    @JsonProperty("img_before_link")
    private String imgBeforeLink;

    @Column(name = "img_after_link")
    @JsonView(EventView.Summary.class)
    @JsonProperty("img_after_link")
    private String imgAfterLink;

    @Column(name = "is_active", nullable=false)
    @JsonView(EventView.Detail.class)
    @JsonProperty("is_active")
    private boolean isActive = true;

    @OneToMany(mappedBy="event", fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"event"})
    private List<Litter> litter;

    @JsonView(EventView.Summary.class)
    @Transient
    @JsonProperty("stats")
    private EventStats stats;

    @OneToMany(mappedBy="event", fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"event"})
    @JsonView(EventView.Detail.class)
    private List<EventComment> comments;

    public Event(
            long communityId,
            String name,
            String description,
            String textBody,
            String location,
            String eventDateTimeStart,
            String imgBeforeLink
    ) {
        this.communityId = communityId;
        this.name = name;
        this.description = description;
        this.textBody = textBody;
        this.location = location;
        this.eventDateTimeStart = DateTimeParse.fromString(eventDateTimeStart);
        this.imgBeforeLink = imgBeforeLink;
        this.litter = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Event() {}
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

    public long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventDateTimeStart() {
        return DateTimeParse.toString(eventDateTimeStart);
    }

    public void setEventDateTimeStart(String eventDateTimeStart) {
        this.eventDateTimeStart = DateTimeParse.fromString(eventDateTimeStart);
    }

    public String getEventDateTimeEnd() {
        return DateTimeParse.toString(eventDateTimeEnd);
    }

    public void setEventDateTimeEnd(String eventDateTimeEnd) {
        this.eventDateTimeEnd = DateTimeParse.fromString(eventDateTimeEnd);
    }

    public String getImgBeforeLink() {
        return imgBeforeLink;
    }

    public void setImgBeforeLink(String imgBeforeLink) {
        this.imgBeforeLink = imgBeforeLink;
    }

    public String getImgAfterLink() {
        return imgAfterLink;
    }

    public void setImgAfterLink(String imgAfterLink) {
        this.imgAfterLink = imgAfterLink;
    }

    public boolean isIs_active() {
        return isActive;
    }

    public void setIs_active(boolean is_active) {
        this.isActive = is_active;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Litter> getLitter() {
        return litter;
    }

    public void setLitter(List<Litter> litter) {
        this.litter = litter;
    }

    public List<EventComment> getComments() {
        return comments;
    }

    public void setComments(List<EventComment> comments) {
        this.comments = comments;
    }

    public Object getStats() {
        return stats;
    }

    public void setStats(EventStats stats) {
        this.stats = stats;
    }
}
