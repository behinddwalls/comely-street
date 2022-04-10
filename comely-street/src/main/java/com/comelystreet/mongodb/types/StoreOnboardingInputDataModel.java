package com.comelystreet.mongodb.types;

public class StoreOnboardingInputDataModel {

    private String storeName;
    private String city;
    private String area;
    private String address;
    private String zipCode;
    private String contactNumber;
    private String email;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StoreOnboardingInputDataModel() {
        super();
    }

    public StoreOnboardingInputDataModel(String name, String city, String area, String address, String contactNumber,
            String email) {
        super();
        this.storeName = name;
        this.city = city;
        this.area = area;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

}
