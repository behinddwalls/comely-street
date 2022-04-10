package com.comelystreet.dao.mongodb.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.comelystreet.mongodb.types.StoreType;

/*
 * 
 * <pre>
 * {
 *    "_id": ObjectId("562fb33f0b14d6036201e71d"),
 *    "_class": "com.comelystreet.dao.mongodb.model.ClientDataModel",
 *    "name": "Preetam Saloon",
 *    "address": "909090909",
 *    "zipCode": "90909090",
 *    "city": "Bangalore",
 *    "area": "Murugeshpalya",
 *    "numberOfEmployees": 0,
 *    "numberOfDaysAllowedForAdvancedBooking": 0,
 *    "minimumTimeRequiredForService": 0,
 *    "operationalTime": {
 *        "openTimeHour": 9,
 *        "openTimeMinute": 0,
 *        "closeTimeHour": 20,
 *        "closeTimeMinute": 0,
 *        "breakTimes": [{
 *            "startTime": 1300,
 *            "endTime": 1400
 *            }]
 *        },
 *    "emailId": "909090909@(0909090",
 *    "mobileNumber": "909090909090909",
 *    "serviceItemDataModels": [DBRef("clientServices", ObjectId("562fb33f0b14d6036201e71b")), DBRef("clientServices", ObjectId("562fb33f0b14d6036201e71c"))]
 *    }
 *</pre>
 * 
 */

@Document(collection = "StoreDetail")
@CompoundIndexes({ @CompoundIndex(name = "unique_client", def = "{'name' : 1, 'city' : 1, 'area' : 1, 'address' : 1}") })
public class StoreDetail {

    @Id
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

    // Store credentials
    @Indexed(unique = true)
    private String emailId;
    private String mobileNumber;
    private String otherPhoneNumbers;
    private String password;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public List<ServiceItemDetail> getServiceItemDetails() {
        return serviceItemDetails;
    }

    public void setServiceItemDetails(List<ServiceItemDetail> serviceItemDetails) {
        this.serviceItemDetails = serviceItemDetails;
    }

    public StoreOperationalTimeDetail getOperationalTimeDetail() {
        return operationalTimeDetail;
    }

    public void setOperationalTimeDetail(StoreOperationalTimeDetail operationalTimeDetail) {
        this.operationalTimeDetail = operationalTimeDetail;
    }

    public StoreType getStoreType() {
        return storeType;
    }

    public void setStoreType(StoreType storeType) {
        this.storeType = storeType;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    @Override
    public String toString() {
        return "StoreDetail [id=" + id + ", name=" + name + ", address=" + address + ", zipCode=" + zipCode + ", city="
                + city + ", area=" + area + ", storeType=" + storeType + ", numberOfEmployees=" + numberOfEmployees
                + ", numberOfDaysAllowedForAdvancedBooking=" + numberOfDaysAllowedForAdvancedBooking
                + ", minimumTimeRequiredForService=" + minimumTimeRequiredForService + ", operationalTimeDetail="
                + operationalTimeDetail + ", serviceItemDetails=" + serviceItemDetails + ", isVerified=" + isVerified
                + ", rating=" + rating + ", storeFacilitiesDetail=" + storeFacilitiesDetail + ", emailId=" + emailId
                + ", mobileNumber=" + mobileNumber + ", otherPhoneNumbers=" + otherPhoneNumbers + ", password="
                + password + "]";
    }

    public StoreDetail() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public StoreDetail(String id, String name, String address, String zipCode, String city, String area,
            StoreType storeType, int numberOfEmployees, int numberOfDaysAllowedForAdvancedBooking,
            int minimumTimeRequiredForService, StoreOperationalTimeDetail operationalTimeDetail,
            List<ServiceItemDetail> serviceItemDetails, boolean isVerified, int rating, String emailId,
            String mobileNumber, String otherPhoneNumbers, String password) {
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
        this.rating = rating;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.otherPhoneNumbers = otherPhoneNumbers;
        this.password = password;
    }

    public StoreFacilitiesDetail getStoreFacilitiesDetail() {
        return storeFacilitiesDetail;
    }

    public void setStoreFacilitiesDetail(StoreFacilitiesDetail storeFacilitiesDetail) {
        this.storeFacilitiesDetail = storeFacilitiesDetail;
    }

}
