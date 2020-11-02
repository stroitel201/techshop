package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.UserAccount;

public class AdminUserDto {

    private String username;

    private String password;

    private String email;

    public AdminUserDto(UserAccount userAccount) {

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
