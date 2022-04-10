package com.comelystreet.mongodb.mapper;

import org.springframework.stereotype.Component;

import com.comelystreet.dao.mongodb.model.CustomerDetail;
import com.comelystreet.mongodb.types.CustomerDataModel;

@Component
public class CustomerDetailMapper {

    public CustomerDataModel getModelFromEntity(final CustomerDetail customerDetail) {
        final CustomerDataModel customerDataModel = new CustomerDataModel(customerDetail.getId(),
                customerDetail.getFullName(), customerDetail.getEmailId(), customerDetail.getMobileNumber(),
                customerDetail.getOtherPhoneNumbers(), "", customerDetail.getResetPasswordKey(),
                customerDetail.getEmailVerificationCode(), customerDetail.getMobileVerificationCode(),
                customerDetail.isEmailVerified(), customerDetail.isMobileVerifid());
        return customerDataModel;
    }

    public CustomerDetail getEntityForProfileSaveFromModel(final CustomerDetail customerDetail,
            final CustomerDataModel customerDataModel) {
        customerDetail.setFullName(customerDataModel.getFullName());
        customerDetail.setMobileNumber(customerDataModel.getMobileNumber());
        return customerDetail;
    }

}
