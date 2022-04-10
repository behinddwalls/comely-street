package com.comelystreet.mongodb.types;

public class CustomerProfileDataModel {

    private CustomerDataModel customerDataModel;
    private String updateType;

    public CustomerDataModel getCustomerDataModel() {
        return customerDataModel;
    }

    public void setCustomerDataModel(CustomerDataModel customerDataModel) {
        this.customerDataModel = customerDataModel;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }
}
