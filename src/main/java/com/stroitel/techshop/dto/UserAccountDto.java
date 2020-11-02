package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.UserAccount;

public class UserAccountDto {

    private String username;

    private String email;

    public UserAccountDto(UserAccount userAccount) {
        this.username = userAccount.getName();
        this.email = userAccount.getEmail();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}