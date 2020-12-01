package com.stroitel.techshop.service;

import com.stroitel.techshop.domain.Role;
import com.stroitel.techshop.domain.UserAccount;

import java.util.List;

public interface UserAccountService {

    UserAccount findByEmail(String email);

    UserAccount create(UserAccount userAccount) throws Exception;

    UserAccount findByUsername(String name);

    UserAccount findById(Long id);

    List<UserAccount> getAllUsers();

    UserAccount deactivate(UserAccount userAccount);

    UserAccount activate(UserAccount userAccount);

}