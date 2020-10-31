package com.stroitel.techshop.domain;


import javax.persistence.*;
import java.io.*;
import java.util.*;

/**
 * Role of a {@link UserAccount}.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<UserAccount> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<UserAccount> getUsers() {
        return users;
    }

    public void setUsers(Set<UserAccount> users) {
        this.users = users;
    }

    public enum Roles {
        ROLE_ADMIN,
        ROLE_STAFF,
        ROLE_USER
    }
}

