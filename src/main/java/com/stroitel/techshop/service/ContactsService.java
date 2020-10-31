package com.stroitel.techshop.service;

import com.stroitel.techshop.domain.Contacts;

public interface ContactsService {

    Contacts getContacts(String userLogin);

    void updateUserContacts(Contacts changedContacts, String userLogin);

}
