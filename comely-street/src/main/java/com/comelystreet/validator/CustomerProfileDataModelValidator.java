package com.comelystreet.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.comelystreet.mongodb.types.CustomerProfileDataModel;

/**
 * Customer register data model validator
 * 
 * @author preetam
 *
 */
@Component
public class CustomerProfileDataModelValidator implements Validator {

    @Autowired
    private EmailValidator emailValidator;

    @Override
    public boolean supports(Class<?> arg0) {
        return CustomerProfileDataModel.class.isAssignableFrom(arg0);
    }

    @Override
    public void validate(Object object, Errors errors) {
        CustomerProfileDataModel customerRegisterDataModel = (CustomerProfileDataModel) object;

        ValidationUtils.rejectIfEmpty(errors, "customerDataModel.fullName", null, "First Name can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "customerDataModel.mobileNumber", null, "Mobile Number can not be empty");

        if (!StringUtils.isEmpty(customerRegisterDataModel.getCustomerDataModel().getMobileNumber())
                && customerRegisterDataModel.getCustomerDataModel().getMobileNumber().length() != 10) {
            errors.rejectValue("customerDataModel.mobileNumber", null, "Mobile number should be of 10 digit only.");
        }
    }

}
