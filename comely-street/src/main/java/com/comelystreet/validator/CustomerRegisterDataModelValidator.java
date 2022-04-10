package com.comelystreet.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.comelystreet.mongodb.types.CustomerRegisterDataModel;

/**
 * Customer register data model validator
 * 
 * @author preetam
 *
 */
@Component
public class CustomerRegisterDataModelValidator implements Validator {

    @Autowired
    private EmailValidator emailValidator;

    @Override
    public boolean supports(Class<?> arg0) {
        return CustomerRegisterDataModel.class.isAssignableFrom(arg0);
    }

    @Override
    public void validate(Object object, Errors errors) {
        CustomerRegisterDataModel customerRegisterDataModel = (CustomerRegisterDataModel) object;
        ValidationUtils.rejectIfEmpty(errors, "firstName", null, "First Name can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "emailId", null, "Email Id can not be empty");
        if (!StringUtils.isEmpty(customerRegisterDataModel.getEmailId())
                && !emailValidator.isValid(customerRegisterDataModel.getEmailId())) {
            errors.rejectValue("emailId", null, "Email Id is not valid");
        }

        ValidationUtils.rejectIfEmpty(errors, "password", null, "Passowrd can not be empty");
        if (!StringUtils.isEmpty(customerRegisterDataModel.getPassword())
                && !customerRegisterDataModel.getPassword().equals(customerRegisterDataModel.getVerifyPassword())) {
            errors.rejectValue("verifyPassword", null, "Password doesn't matches");
        }

    }

}
