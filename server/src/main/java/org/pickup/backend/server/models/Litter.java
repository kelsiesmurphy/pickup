package org.pickup.backend.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "litter")
public class Litter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "community_id", nullable=false)
    @JsonIgnoreProperties({"litter"})
    private Community community;

    @ManyToOne()
    @JoinColumn(name = "event_id", nullable=true)
    @JsonIgnoreProperties({"litter"})
    private Event event;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable=false)
    @JsonIgnoreProperties({"litter"})
    private User user;

    @ManyToOne()
    @JoinColumn(name = "litter_type_id", nullable=false)
    @JsonIgnoreProperties({"litter"})
    private LitterType litterType;
    @Column(name = "collection_date_time")
    private String collectionDateTime;
    @Column(name = "is_active")
    private Boolean isActive;

    public Litter(
            Community community,
            Event event,
            User user,
            LitterType litterType,
            String collectionDateTime) {
        this.community = community;
        this.event = event;
        this.user = user;
        this.litterType = litterType;
        this.collectionDateTime = collectionDateTime;
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

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LitterType getLitterType() {
        return litterType;
    }

    public void setLitterType(LitterType litterType) {
        this.litterType = litterType;
    }

    public String getCollectionDateTime() {
        return collectionDateTime;
    }

    public void setCollectionDateTime(String collectionDateTime) {
        this.collectionDateTime = collectionDateTime;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
