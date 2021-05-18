package com.portal.springrest.model;

import com.portal.springrest.security.Role;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "account")
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Role role;


    public Account(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public Account(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}