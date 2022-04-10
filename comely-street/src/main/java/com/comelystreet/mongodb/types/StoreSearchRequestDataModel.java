package com.comelystreet.mongodb.types;

import com.comelystreet.dao.mongodb.model.StoreFacilitiesDetail;

public class StoreSearchRequestDataModel {
    private String city;
    private String area;
    private String serviceCategory;
    private String freeTextServiceItem;
    private int rating;
    private StoreType storeType;
    private StoreFacilitiesDetail storeFacilitiesDetail;
    private Pagination pagination;

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

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    @Override
    public String toString() {
        return "StoreSearchRequestDataModel [city=" + city + ", area=" + area + ", serviceCategory=" + serviceCategory
                + ", freeTextServiceItem=" + freeTextServiceItem + ", pagination=" + pagination + "]";
    }

    public String getFreeTextServiceItem() {
        return freeTextServiceItem;
    }

    public void setFreeTextServiceItem(String freeTextServiceItem) {
        this.freeTextServiceItem = freeTextServiceItem;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public StoreType getStoreType() {
        return storeType;
    }

    public void setStoreType(StoreType storeType) {
        this.storeType = storeType;
    }

    public StoreFacilitiesDetail getStoreFacilitiesDetail() {
        return storeFacilitiesDetail;
    }

    public void setStoreFacilitiesDetail(StoreFacilitiesDetail storeFacilitiesDetail) {
        this.storeFacilitiesDetail = storeFacilitiesDetail;
    }
}
