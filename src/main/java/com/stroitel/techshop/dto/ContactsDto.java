package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.Contacts;

public class ContactsDto {

    private String phone;

    private String address;

    private String cityAndRegion;

    public ContactsDto(Contacts contacts) {

        this.phone = contacts.getPhone();
        this.address = contacts.getAddress();
        this.cityAndRegion = contacts.getCityAndRegion();
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
