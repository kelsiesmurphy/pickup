package org.pickup.backend.server.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.views.EmailSignupView;

import javax.persistence.*;

@Entity
@Table(name = "email_signups")
public class EmailSignup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(EmailSignupView.PostReturn.class)
    private long id;

    @Column(name = "email", nullable=false)
    @JsonView(EmailSignupView.PostReturn.class)
    private String email;
    @Column(name = "signup_date_time", nullable=false)
    @JsonView(EmailSignupView.PostReturn.class)
    private String signupDateTime;
    @Column(name = "is_active", nullable=false)
    private boolean is_active = true;

    public EmailSignup(String email, String signupDateTime) {
        this.email = email;
        this.signupDateTime = signupDateTime;
    }
    public EmailSignup() {}

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("signup_date_time")
    public String getSignupDateTime() {
        return signupDateTime;
    }

    @JsonProperty("signup_date_time")
    public void setSignupDateTime(String signupDateTime) {
        this.signupDateTime = signupDateTime;
    }

    @JsonProperty("is_active")
    public boolean isActive() {
        return is_active;
    }

    @JsonProperty("is_active")
    public void setActive(boolean active) {
        is_active = active;
    }
}
