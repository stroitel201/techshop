package com.stroitel.techshop.service.impl;

import com.stroitel.techshop.dao.ContactsDAO;
import com.stroitel.techshop.domain.Contacts;
import com.stroitel.techshop.domain.UserAccount;
import com.stroitel.techshop.service.ContactsService;
import com.stroitel.techshop.service.UserAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactsServiceImpl implements ContactsService {
    private final ContactsDAO contactsDAO;
    private final UserAccountService userAccountService;

    public ContactsServiceImpl(ContactsDAO contactsDAO, UserAccountService userAccountService) {
        this.contactsDAO = contactsDAO;
        this.userAccountService = userAccountService;
    }

    @Transactional(readOnly = true)
    @Override
    public Contacts getContacts(String userLogin) {
        UserAccount account = userAccountService.findByEmail(userLogin);
        return contactsDAO.findByUserAccount(account);
    }

    @Transactional
    @Override
    public Contacts updateUserContacts(Contacts changedContacts, String userLogin) {
        Contacts originalContacts = getContacts(userLogin);
        if (originalContacts != null) {
            originalContacts.setPhone(changedContacts.getPhone());
            originalContacts.setAddress(changedContacts.getAddress());
            originalContacts.setCityAndRegion(changedContacts.getCityAndRegion());
            return contactsDAO.save(originalContacts);
        }
        else return null;
    }
}
