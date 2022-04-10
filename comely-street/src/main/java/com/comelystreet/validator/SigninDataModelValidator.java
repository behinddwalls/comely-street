package com.comelystreet.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.comelystreet.mongodb.types.SigninDataModel;

/**
 * Customer register data model validator
 * 
 * @author preetam
 *
 */
@Component
public class SigninDataModelValidator implements Validator {

    @Autowired
    private EmailValidator emailValidator;

    @Override
    public boolean supports(Class<?> arg0) {
        return SigninDataModel.class.isAssignableFrom(arg0);
    }

    @Override
    public void validate(Object object, Errors errors) {
        SigninDataModel CustomerSigninDataModel = (SigninDataModel) object;

        ValidationUtils.rejectIfEmpty(errors, "emailId", null, "Email Id can not be empty");
        if (!StringUtils.isEmpty(CustomerSigninDataModel.getEmailId())
                && !emailValidator.isValid(CustomerSigninDataModel.getEmailId())) {
            errors.rejectValue("emailId", null, "Email Id is not valid");
        }

        ValidationUtils.rejectIfEmpty(errors, "password", null, "Passowrd can not be empty");

    }

}
