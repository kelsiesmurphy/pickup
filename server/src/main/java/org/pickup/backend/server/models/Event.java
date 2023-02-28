package org.pickup.backend.server.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.pickup.backend.server.helpers.RawJsonDeserializer;
import org.pickup.backend.server.repositories.CommunityRepository;
import org.pickup.backend.server.views.CommunityView;
import org.pickup.backend.server.views.EventView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "events")
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

    @Column(name = "name")
    @JsonView(EventView.Summary.class)
    private String name;

    @Column(name = "description")
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

    @Column(name = "event_date_time")
    @JsonView(EventView.Summary.class)
    @JsonProperty("event_date_time")
    private String eventDateTime;

    @Column(name = "img_before_link")
    @JsonView(EventView.Summary.class)
    @JsonProperty("img_before_link")
    private String imgBeforeLink;

    @Column(name = "img_after_link")
    @JsonView(EventView.Summary.class)
    @JsonProperty("img_after_link")
    private String imgAfterLink;

    @Column(name = "is_active")
    @JsonView(EventView.Detail.class)
    @JsonProperty("is_active")
    private boolean isActive = true;

    @OneToMany(mappedBy="event", fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"event"})
    private List<Litter> litter;

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
            String eventDateTime,
            String imgBeforeLink
    ) {
        this.communityId = communityId;
        this.name = name;
        this.description = description;
        this.textBody = textBody;
        this.location = location;
        this.eventDateTime = eventDateTime;
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

//    public Community getCommunity() {
//        return community;
//    }
//
//    public void setCommunity(Community community) {
//        this.community = community;
//    }

    public long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(long communityId) {
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

    public String getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
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
}
