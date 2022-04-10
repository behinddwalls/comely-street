package com.comelystreet.dao.mongodb.model;

import java.util.concurrent.TimeUnit;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * 
 * 
 * <pre>
 * {
 *    "_id": ObjectId("562fb33f0b14d6036201e71b"),
 *    "_class": "com.comelystreet.dao.mongodb.model.ServiceItemDataModel",
 *    "name": "Masking",
 *    "time": NumberLong(20),
 *    "timeUnit": "MINUTES",
 *    "price": NumberLong(99),
 *    "currency": "INR",
 *    "serviceCategory": DBRef("serviceCategory", ObjectId("562bc6734349177a86c3ae8c"))
 *}
 *</pre>
 * 
 * 
 * 
 * 
 */
public class ServiceItemDetail {

    private String id;
    private String name;
    private long time;
    private TimeUnit timeUnit;
    private long price;
    private String currency;
    private ServiceCategoryDetail serviceCategory;
    private boolean active;

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ServiceCategoryDetail getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(ServiceCategoryDetail serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public ServiceItemDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "ServiceItemDetail [id=" + id + ", name=" + name + ", time=" + time + ", timeUnit=" + timeUnit
                + ", price=" + price + ", currency=" + currency + ", serviceCategory=" + serviceCategory + "]";
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
