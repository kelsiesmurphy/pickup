package org.pickup.backend.server.models;

import javax.persistence.*;

@Entity
@Table(name = "email_signups")
public class EmailSignup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "email")
    private String email;
    @Column(name = "signup_date_time")
    private String signupDateTime;
    @Column(name = "is_active")
    private boolean isActive;

    public EmailSignup(String email, String signupDateTime) {
        this.email = email;
        this.signupDateTime = signupDateTime;
        this.isActive = true;
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
        return signupDateTime;
    }

    public void setSignupDateTime(String signupDateTime) {
        this.signupDateTime = signupDateTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
