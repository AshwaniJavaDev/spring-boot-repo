package com.email.sender.model;

public class EmailSenderModel {

    private String name;
    private String email;
    private String phone;
    private Address address;
    private Charges charges;

    public EmailSenderModel(String name, String email, String phone, Address address, Charges charges) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.charges = charges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Charges getCharges() {
        return charges;
    }

    public void setCharges(Charges charges) {
        this.charges = charges;
    }
}
