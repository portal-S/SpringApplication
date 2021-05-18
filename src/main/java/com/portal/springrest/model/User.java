package com.portal.springrest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<FileBD> fileBDList;

    @OneToOne()
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Expose
    private List<Event> events;

    public User(int id, List<FileBD> fileBDList) {
        this.id = id;
        this.fileBDList = fileBDList;
    }

    public User(Account account) {
        this.account = account;
    }

    public User(int id, Account account) {
        this.id = id;
        this.account = account;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<FileBD> getFileBDList() {
        return fileBDList;
    }

    public void setFileBDList(List<FileBD> fileBDList) {
        this.fileBDList = fileBDList;
    }

    @Override
    public String toString() {
        return "User{" +
                "events=" + events +
                '}';
    }
}
