package org.pickup.backend.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class UserContext {

    @JsonProperty("community_id")
    private Long communityId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("img_profile_link")
    private String imgProfileLink;

    @JsonProperty("create_date")
    private String createDate;

    @JsonProperty("auth0id")
    private String auth0Id;

    public UserContext(Long communityId,
                       String userName,
                       String email,
                       String imgProfileLink,
                       String createDate,
                       String auth0Id) {
        this.communityId = communityId;
        this.userName = userName;
        this.email = email;
        this.imgProfileLink = imgProfileLink;
        this.createDate = createDate;
        this.auth0Id = auth0Id;
    }

    // Getters and setters

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAuth0Id() {
        return auth0Id;
    }

    public void setAuth0Id(String auth0Id) {
        this.auth0Id = auth0Id;
    }
}
