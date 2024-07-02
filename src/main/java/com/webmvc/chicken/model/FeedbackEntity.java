package com.webmvc.chicken.model;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "feedback", schema = "public", catalog = "testabc")
public class FeedbackEntity {

    @Id
    @Column(name = "feedback_id")
    @GeneratedValue(strategy = AUTO)
    private int feedbackId;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "subject")
    private String subject;

    @Basic
    @Column(name = "message")
    private String message;

    public int getFeedbackId() {
        return this.feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeedbackEntity feedback = (FeedbackEntity) o;

        if (this.feedbackId != feedback.feedbackId) return false;
        if (!Objects.equals(this.email, feedback.email)) return false;
        if (!Objects.equals(this.name, feedback.name)) return false;
        if (!Objects.equals(this.subject, feedback.subject)) return false;
        return Objects.equals(this.message, feedback.message);
    }

    @Override
    public int hashCode() {
        int result = this.feedbackId;
        result = 31 * result + (this.email != null ? this.email.hashCode() : 0);
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.subject != null ? this.subject.hashCode() : 0);
        result = 31 * result + (this.message != null ? this.message.hashCode() : 0);
        return result;
    }

}
