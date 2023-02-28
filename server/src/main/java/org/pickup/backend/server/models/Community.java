package org.pickup.backend.server.models;

import com.fasterxml.jackson.annotation.*;
import org.pickup.backend.server.views.CommunityView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "communities")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Community  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(CommunityView.Id.class)
    private long id;

    @Column(name = "name", nullable=false)
    @JsonView(CommunityView.Summary.class)
    private String name;

    @Column(name = "description")
    @JsonView(CommunityView.Summary.class)
    private String description;

    @Column(name = "is_private", nullable=false)
    @JsonView(CommunityView.Detail.class)
    @JsonProperty("is_private")
    private boolean isPrivate;

    @Column(name = "img_hero_link")
    @JsonView(CommunityView.Detail.class)
    @JsonProperty("img_hero_link")
    private String imgHeroLink;

    @Column(name = "img_logo_link")
    @JsonView(CommunityView.Summary.class)
    @JsonProperty("img_logo_link")
    private String imgLogoLink;

    @Column(name = "is_active", nullable=false)
    @JsonProperty("is_active")
    private boolean isActive = true;

    @OneToMany(mappedBy="community", fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"community"})
    private List<User> users;

    @OneToMany(mappedBy="community", fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"community"})
    private List<Event> events;

    @OneToMany(mappedBy="community", fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"community"})
    private List<Litter> litter;

    public Community(String name,
                     String description,
                     boolean isPrivate,
                     String imgHeroLink,
                     String imgLogoLink) {
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.imgHeroLink = imgHeroLink;
        this.imgLogoLink = imgLogoLink;
        this.users = new ArrayList<>();
        this.events = new ArrayList<>();
        this.litter = new ArrayList<>();
    }
    public Community() {}
    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isPrivate() {
        return isPrivate;
    }
    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
    public String getImgHeroLink() {
        return imgHeroLink;
    }
    public void setImgHeroLink(String imgHeroLink) {
        this.imgHeroLink = imgHeroLink;
    }
    public String getImgLogoLink() {
        return imgLogoLink;
    }

    public void setImgLogoLink(String imgLogoLink) {
        this.imgLogoLink = imgLogoLink;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Litter> getLitter() {
        return litter;
    }

    public void setLitter(List<Litter> litter) {
        this.litter = litter;
    }
}
