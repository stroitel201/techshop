package com.stroitel.techshop.dto;

import javax.validation.constraints.NotNull;

public class RegisterUserDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String address;

    @NotNull
    private String cityAndRegion;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCityAndRegion() {
        return cityAndRegion;
    }
}
