package org.pickup.backend.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne()
    @JoinColumn(name="community_id", nullable=false)
    @JsonIgnoreProperties({"users"})
    private Community community;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "email")
    private String email;
    @Column(name = "img_profile_link")
    private String imgProfileLink;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "is_admin")
    private boolean isAdmin;

    @OneToMany(mappedBy="user", fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"user"})
    private List<Litter> litter;

    @OneToMany(mappedBy="user", fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"user"})
    private List<EventComment> comments;

    public User(Community community,
                String userName,
                String email,
                String imgProfileLink,
                boolean isAdmin) {
        this.community = community;
        this.userName = userName;
        this.email = email;
        this.imgProfileLink = imgProfileLink;
        this.isAdmin = isAdmin;
        this.isActive = true;
        this.litter = new ArrayList<>();
        this.comments = new ArrayList<>();
    }
    public User() {}
    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgProfileLink() {
        return imgProfileLink;
    }

    public void setImgProfileLink(String imgProfileLink) {
        this.imgProfileLink = imgProfileLink;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
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
