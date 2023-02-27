package org.pickup.backend.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private long id;

    @ManyToOne()
    @JoinColumn(name="community_id", nullable=false)
    @JsonIgnoreProperties({"events"})
    private Community community;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "text_body")
    private String textBody;
    @Column(name = "location")
    private String location;
    @Column(name = "event_date_time")
    private String eventDateTime;
    @Column(name = "img_before_link")
    private String imgBeforeLink;
    @Column(name = "img_after_link")
    private String imgAfterLink;
    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy="event", fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"event"})
    private List<Litter> litter;

    @OneToMany(mappedBy="event", fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"event"})
    private List<EventComment> comments;

    public Event(
            Community community,
            String name,
            String description,
            String textBody,
            String location,
            String eventDateTime,
            String imgBeforeLink
    ) {
        this.community = community;
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

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
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
