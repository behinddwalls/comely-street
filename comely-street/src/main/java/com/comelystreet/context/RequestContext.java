package com.comelystreet.context;

import com.comelystreet.context.device.DeviceClassification;
import com.comelystreet.dao.mongodb.model.CustomerDetail;
import com.comelystreet.dao.mongodb.model.StoreDetail;

/**
 * @author behinddwalls
 *
 */
public class RequestContext {

    private final CustomerDetail customerDetail;
    private final DeviceClassification deviceClassification;
    private final StoreDetail storeDetail;

    /**
     * Provides a fluent builder to build a request context object.
     * 
     * @author preetam
     * 
     */
    public static class Builder {
        private CustomerDetail customerDetail;
        private DeviceClassification deviceClassification;
        private StoreDetail storeDetail;

        public Builder customerDetail(final CustomerDetail customerDetail) {
            this.customerDetail = customerDetail;
            return this;
        }

        public Builder deviceClassification(final DeviceClassification deviceClassification) {
            this.deviceClassification = deviceClassification;
            return this;
        }

        public Builder storeDetail(final StoreDetail storeDetail) {
            this.storeDetail = storeDetail;
            return this;
        }

        public RequestContext build() {
            return new RequestContext(this);
        }

    }

    private RequestContext(Builder builder) {
        this.customerDetail = builder.customerDetail;
        this.deviceClassification = builder.deviceClassification;
        this.storeDetail = builder.storeDetail;
    }

    public boolean isCustomerAuthenticated() {
        return null != customerDetail;
    }

    public CustomerDetail getCustomerDetail() {
        return customerDetail;
    }

    public DeviceClassification getDeviceClassification() {
        return deviceClassification;
    }

    public StoreDetail getStoreDetail() {
        return storeDetail;
    }

}
