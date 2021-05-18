package com.portal.springrest.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "files")
public class FileBD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public FileBD() {
    }

    public FileBD(int id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public FileBD(String name, User user) {
        this.name = name;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FileBD{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileBD fileBD = (FileBD) o;
        return id == fileBD.id && Objects.equals(name, fileBD.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
