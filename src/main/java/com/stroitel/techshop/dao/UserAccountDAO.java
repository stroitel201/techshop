package com.stroitel.techshop.dao;

import com.stroitel.techshop.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountDAO extends CrudRepository<UserAccount, Long>, JpaRepository<UserAccount, Long> {

    UserAccount findByEmail(String email);
}