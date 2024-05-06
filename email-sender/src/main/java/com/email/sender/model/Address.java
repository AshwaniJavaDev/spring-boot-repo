package com.email.sender.model;

public class Address {

    private String Address;
    private String city;
    private String pincode;
    private String state;

    public Address(String address, String city, String pincode, String state) {
        Address = address;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
