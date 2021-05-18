package com.portal.springrest.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Expose(serialize = false)
    private User user;

    @Column(name = "action")
    @Expose
    private String action;

    public Event() {
    }

    public Event(User user, String action) {
        this.user = user;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Event{" +
                "action='" + action + '\'' +
                '}';
    }
}