package org.pickup.backend.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "event_comments")
public class EventComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne()
    @JoinColumn(name="event_id", nullable=false)
    @JsonIgnoreProperties({"comments"})
    private Event event;

    @ManyToOne()
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnoreProperties({"comments"})
    private User user;
    @Column(name = "comment_date_time")
    private String commentDateTime;
    @Column(name = "text_body")
    private String textBody;

    public EventComment(
            Event event,
            User user,
            String commentDateTime,
            String textBody) {
        this.event = event;
        this.user = user;
        this.commentDateTime = commentDateTime;
        this.textBody = textBody;
    }
    public EventComment() {}
    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCommentDateTime() {
        return commentDateTime;
    }

    public void setCommentDateTime(String commentDateTime) {
        this.commentDateTime = commentDateTime;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }
}
