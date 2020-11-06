package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.UserAccount;

public class UserAccountDto {

    private Long id;

    private String username;

    private String email;

    public UserAccountDto() {
    }

    public UserAccountDto(UserAccount userAccount) {

        this.id = userAccount.getId();
        this.username = userAccount.getName();
        this.email = userAccount.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}