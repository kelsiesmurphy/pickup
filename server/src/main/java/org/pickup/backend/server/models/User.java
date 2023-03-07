package org.pickup.backend.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.models.stats.UserStats;
import org.pickup.backend.server.views.UserView;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@JsonPropertyOrder({
        "id",
        "community_id",
        "user_name",
        "email",
        "img_profile_link",
        "stats",
        "is_active",
        "is_admin",
        "create_date"
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(UserView.CommentSummary.class)
    private long id;

    @ManyToOne(targetEntity = Community.class, fetch = FetchType.EAGER)
    @JoinColumn(name="community_id", insertable=false, updatable=false)
    private Community community;

    @Column(name = "community_id", nullable=false)
    @JsonView(UserView.Detail.class)
    @JsonProperty("community_id")
    private Long community_id;

    @Column(name = "user_name")
    @JsonView(UserView.CommentSummary.class)
    @JsonProperty("user_name")
    private String userName;

    @Column(name = "email")
    @JsonView(UserView.Summary.class)
    private String email;

    @Column(name = "img_profile_link")
    @JsonView(UserView.CommentSummary.class)
    @JsonProperty("img_profile_link")
    private String imgProfileLink;

    @Column(name = "is_active")
    @JsonView(UserView.Detail.class)
    @JsonProperty("is_active")
    private boolean isActive;

    @Column(name = "is_admin")
    @JsonView(UserView.Detail.class)
    @JsonProperty("is_admin")
    private boolean isAdmin;

    @Column(name = "auth0_id", nullable=true, unique=true)
    @JsonView(UserView.Detail.class)
    @JsonProperty("auth0_id")
    private String auth0Id;

    @Column(name = "create_date")
    @JsonView(UserView.Detail.class)
    @JsonProperty("create_date")
    private LocalDate createDate;

    @JsonView(UserView.Summary.class)
    @Transient
    @JsonProperty("stats")
    private UserStats stats;

    @OneToMany(mappedBy="user", fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"user"})
    private List<Litter> litter;

    @OneToMany(mappedBy="user", fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"user"})
    private List<EventComment> comments;

    public User(Long community_id,
                String userName,
                String email,
                String imgProfileLink,
                boolean isAdmin,
                String createDate) {
        this.community_id = community_id;
        this.userName = userName;
        this.email = email;
        this.imgProfileLink = imgProfileLink;
        this.isAdmin = isAdmin;
        this.isActive = true;
        this.createDate = LocalDate.parse(createDate, DateTimeFormatter.ISO_DATE);
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

    public String getCreateDate() {
        return createDate.format(DateTimeFormatter.ISO_DATE);
    }

    public void setCreateDate(String createDate) {
        this.createDate = LocalDate.parse(createDate, DateTimeFormatter.ISO_DATE);
    }

    public Community getCommunity() {
        return community;
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

    public Long getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(Long community_id) {
        this.community_id = community_id;
    }

    public UserStats getStats() {
        return stats;
    }

    public void setStats(UserStats stats) {
        this.stats = stats;
    }

    public String getAuth0Id() {
        return auth0Id;
    }

    public void setAuth0Id(String auth0Id) {
        this.auth0Id = auth0Id;
    }
}
