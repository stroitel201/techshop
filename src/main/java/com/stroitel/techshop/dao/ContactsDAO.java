package com.stroitel.techshop.dao;

import com.stroitel.techshop.domain.Contacts;
import com.stroitel.techshop.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ContactsDAO extends CrudRepository<Contacts, Long>, JpaRepository<Contacts, Long> {

    Contacts findByUserAccount(UserAccount userAccount);
}
