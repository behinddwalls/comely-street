package com.comelystreet.mongodb.types;

public class InternalStoreSearchRequestDataModel {

    private String city;
    private String area;
    private String storeName;
    private boolean storeVerified;
    private boolean exclude;

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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public boolean isStoreVerified() {
        return storeVerified;
    }

    public void setStoreVerified(boolean storeVerified) {
        this.storeVerified = storeVerified;
    }

    @Override
    public String toString() {
        return "InternalStoreSearchRequestDataModel [city=" + city + ", area=" + area + ", storeName=" + storeName
                + ", storeVerified=" + storeVerified + "]";
    }

    public boolean isExclude() {
        return exclude;
    }

    public void setExclude(boolean exclude) {
        this.exclude = exclude;
    }

}
