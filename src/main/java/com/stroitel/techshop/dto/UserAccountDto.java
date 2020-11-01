package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.UserAccount;

public class UserAccountDto {
    private Long id;
    private String username;
    private String email;

    public UserAccount toUser(){
        UserAccount user = new UserAccount();
        user.setId(id);
        user.setName(username);
        user.setEmail(email);

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}