package com.stroitel.techshop.service;

import com.stroitel.techshop.domain.Role;
import com.stroitel.techshop.domain.UserAccount;

import java.util.List;

public interface UserAccountService {

    /**
     * @return user account associated with the specified email
     */
    UserAccount findByEmail(String email);

    /**
     * Creates new account.
     * @return newly created account
     */
    UserAccount create(UserAccount userAccount) throws Exception;

    UserAccount findByUsername(String name);

    UserAccount findById(Long id);

    List<UserAccount> getAllUsers();

    UserAccount deactivate(UserAccount userAccount);

    UserAccount activate(UserAccount userAccount);

}