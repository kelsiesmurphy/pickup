package org.pickup.backend.server.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.views.EmailSignupView;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "email_signups")
@JsonPropertyOrder({
        "id",
        "email",
        "signup_date_time"
})
public class EmailSignup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(EmailSignupView.PostReturn.class)
    private long id;

    @Column(name = "email", nullable=false)
    @JsonView(EmailSignupView.PostReturn.class)
    private String email;

    @Column(name = "signup_date_time", nullable=false, columnDefinition="TIMESTAMP")
    @JsonView(EmailSignupView.PostReturn.class)
    @JsonProperty("signup_date_time")
    private LocalDateTime signupDateTime;

    @Column(name = "is_active", nullable=false)
    @JsonProperty("is_active")
    private boolean isActive = true;

    public EmailSignup(String email, String signupDateTime) {
        this.email = email;
        this.signupDateTime = LocalDateTime.parse(signupDateTime, DateTimeFormatter.ISO_DATE_TIME);
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

    public String getSignupDateTime() {
        return signupDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public void setSignupDateTime(String signupDateTime) {
        this.signupDateTime = LocalDateTime.parse(signupDateTime, DateTimeFormatter.ISO_DATE_TIME);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
