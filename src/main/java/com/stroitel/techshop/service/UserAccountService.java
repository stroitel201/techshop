package com.stroitel.techshop.service;

import com.stroitel.techshop.domain.Role;
import com.stroitel.techshop.domain.UserAccount;

public interface UserAccountService {

    /**
     * @return user account associated with the specified email
     */
    UserAccount findByEmail(String email);

    /**
     * Creates new account.
     * @return newly created account
     */
    UserAccount create(UserAccount userAccount);

    UserAccount findByUsername(String name);

    UserAccount findById(Long id);

}