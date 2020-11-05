package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.Role;
import com.stroitel.techshop.domain.UserAccount;

import java.util.Set;
import java.util.stream.Collectors;

public class AdminUserDto {

    private Long id;

    public Long getId() {
        return id;
    }

    private String username;

    private String password;

    private String email;

    private Boolean active;

    private Set<String> roles;

    public AdminUserDto(UserAccount userAccount) {

        this.id = userAccount.getId();
        this.username = userAccount.getName();
        this.email = userAccount.getEmail();
        this.password = userAccount.getPassword();
        this.active = userAccount.isActive();
        this.roles = userAccount.getRoles().stream().map(Role::getTitle).collect(Collectors.toSet());
    }

    public Boolean isActive() {
        return active;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
