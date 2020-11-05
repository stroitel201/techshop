package com.stroitel.techshop.dto;

import com.stroitel.techshop.domain.Contacts;

import javax.validation.constraints.NotNull;

public class ContactsDto {

    @NotNull
    private String phone;

    @NotNull
    private String address;

    @NotNull
    private String cityAndRegion;

    public ContactsDto(){}

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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCityAndRegion(String cityAndRegion) {
        this.cityAndRegion = cityAndRegion;
    }
}
