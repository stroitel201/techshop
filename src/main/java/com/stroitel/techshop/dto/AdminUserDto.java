package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.UserAccount;

public class AdminUserDto {

    private Long id;

    public Long getId() {
        return id;
    }

    private String username;

    private String password;

    private String email;

    public AdminUserDto(UserAccount userAccount) {

        this.id = userAccount.getId();
        this.username = userAccount.getName();
        this.email = userAccount.getEmail();
        this.password = userAccount.getPassword();
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
