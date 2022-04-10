package com.comelystreet.mongodb.types;

public class SelectedServiceDataModel {
    private String serviceCategoryId;
    private String serviceItemId;

    public String getServiceCategoryId() {
        return serviceCategoryId;
    }

    public void setServiceCategoryId(String serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }

    public String getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(String serviceItemId) {
        this.serviceItemId = serviceItemId;
    }

    public SelectedServiceDataModel() {
        super();
    }
}
