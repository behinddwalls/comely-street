package com.comelystreet.mongodb.types;

import java.util.List;

import com.comelystreet.dao.mongodb.model.ServiceItemDetail;
import com.comelystreet.dao.mongodb.model.StoreFacilitiesDetail;
import com.comelystreet.dao.mongodb.model.StoreOperationalTimeDetail;

public class StoreDataModel {

    private String id;
    private String name;
    private String address;
    private String zipCode;
    private String city;
    private String area;
    private StoreType storeType;
    private int numberOfEmployees;
    private int numberOfDaysAllowedForAdvancedBooking;
    private int minimumTimeRequiredForService;
    private StoreOperationalTimeDetail operationalTimeDetail;
    private List<ServiceItemDetail> serviceItemDetails;
    private boolean isVerified;

    private int rating;
    private StoreFacilitiesDetail storeFacilitiesDetail;

    // client credentials
    private String emailId;
    private String mobileNumber;
    private String otherPhoneNumbers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

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

    public StoreType getStoreType() {
        return storeType;
    }

    public void setStoreType(StoreType storeType) {
        this.storeType = storeType;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public int getNumberOfDaysAllowedForAdvancedBooking() {
        return numberOfDaysAllowedForAdvancedBooking;
    }

    public void setNumberOfDaysAllowedForAdvancedBooking(int numberOfDaysAllowedForAdvancedBooking) {
        this.numberOfDaysAllowedForAdvancedBooking = numberOfDaysAllowedForAdvancedBooking;
    }

    public int getMinimumTimeRequiredForService() {
        return minimumTimeRequiredForService;
    }

    public void setMinimumTimeRequiredForService(int minimumTimeRequiredForService) {
        this.minimumTimeRequiredForService = minimumTimeRequiredForService;
    }

    public StoreOperationalTimeDetail getOperationalTimeDetail() {
        return operationalTimeDetail;
    }

    public void setOperationalTimeDetail(StoreOperationalTimeDetail operationalTimeDetail) {
        this.operationalTimeDetail = operationalTimeDetail;
    }

    public List<ServiceItemDetail> getServiceItemDetails() {
        return serviceItemDetails;
    }

    public void setServiceItemDetails(List<ServiceItemDetail> serviceItemDetails) {
        this.serviceItemDetails = serviceItemDetails;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtherPhoneNumbers() {
        return otherPhoneNumbers;
    }

    public void setOtherPhoneNumbers(String otherPhoneNumbers) {
        this.otherPhoneNumbers = otherPhoneNumbers;
    }

    public StoreDataModel(String id, String name, String address, String zipCode, String city, String area,
            StoreType storeType, int numberOfEmployees, int numberOfDaysAllowedForAdvancedBooking,
            int minimumTimeRequiredForService, StoreOperationalTimeDetail operationalTimeDetail,
            List<ServiceItemDetail> serviceItemDetails, boolean isVerified, String emailId, String mobileNumber,
            String otherPhoneNumbers, int rating, StoreFacilitiesDetail storeFacilitiesDetail) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.area = area;
        this.storeType = storeType;
        this.numberOfEmployees = numberOfEmployees;
        this.numberOfDaysAllowedForAdvancedBooking = numberOfDaysAllowedForAdvancedBooking;
        this.minimumTimeRequiredForService = minimumTimeRequiredForService;
        this.operationalTimeDetail = operationalTimeDetail;
        this.serviceItemDetails = serviceItemDetails;
        this.isVerified = isVerified;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.otherPhoneNumbers = otherPhoneNumbers;
        this.rating = rating;
        this.storeFacilitiesDetail = storeFacilitiesDetail;
    }

    public StoreDataModel() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public StoreFacilitiesDetail getStoreFacilitiesDetail() {
        return storeFacilitiesDetail;
    }

    public void setStoreFacilitiesDetail(StoreFacilitiesDetail storeFacilitiesDetail) {
        this.storeFacilitiesDetail = storeFacilitiesDetail;
    }
}
