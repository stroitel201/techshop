package com.stroitel.techshop.dto;

import lombok.Data;


public class AuthenticationRequestDto {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
