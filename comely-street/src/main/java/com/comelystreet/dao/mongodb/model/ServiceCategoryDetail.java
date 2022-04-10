package com.comelystreet.dao.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ServiceCategoryDetail")
public class ServiceCategoryDetail {

    @Id
    private String id;
    private String serviceCategoryName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceCategoryName() {
        return serviceCategoryName;
    }

    public void setServiceCategoryName(String serviceCategoryName) {
        this.serviceCategoryName = serviceCategoryName;
    }

    @Override
    public String toString() {
        return "ServiceCategoryDetail [id=" + id + ", serviceCategoryName=" + serviceCategoryName + "]";
    }

}
