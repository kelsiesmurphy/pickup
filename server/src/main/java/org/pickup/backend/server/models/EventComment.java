package org.pickup.backend.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import org.pickup.backend.server.views.EventCommentView;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "event_comments")
@JsonPropertyOrder({
        "id",
        "event_id",
        "comment_date_time",
        "text_body",
        "user"
})
public class EventComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(EventCommentView.Summary.class)
    private long id;

    @ManyToOne(targetEntity = Event.class, fetch = FetchType.EAGER)
    @JoinColumn(name="event_id", insertable=false, updatable=false)
    private Event event;

    @Column(name = "event_id", nullable=false)
    @JsonProperty("event_id")
    @JsonView(EventCommentView.Summary.class)
    private Long event_id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", insertable=false, updatable=false)
    @JsonIgnoreProperties({"comments"})
    @JsonView(EventCommentView.Summary.class)
    private User user;

    @Column(name = "user_id", nullable=false)
    @JsonProperty("user_id")
    private Long user_id;

    @Column(name = "comment_date_time", nullable=false)
    @JsonProperty("comment_date_time")
    @JsonView(EventCommentView.Summary.class)
    private LocalDateTime commentDateTime;

    @Column(name = "text_body", nullable=false)
    @JsonProperty("text_body")
    @JsonView(EventCommentView.Summary.class)
    private String textBody;

    @Column(name = "is_active", nullable=false)
    @JsonProperty("is_active")
    private boolean isActive = true;

    public EventComment(
            Long event_id,
            Long user_id,
            String commentDateTime,
            String textBody) {
        this.event_id = event_id;
        this.user_id = user_id;
        this.commentDateTime = LocalDateTime.parse(commentDateTime, DateTimeFormatter.ISO_DATE_TIME);
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

    public User getUser() {
        return user;
    }

    public String getCommentDateTime() {
        return commentDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }
    public void setCommentDateTime(String commentDateTime) {
        this.commentDateTime = LocalDateTime.parse(commentDateTime, DateTimeFormatter.ISO_DATE_TIME);
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Long event_id) {
        this.event_id = event_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
