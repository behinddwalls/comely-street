package com.comelystreet.dao.mongodb.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CustomerDetail")
public class CustomerDetail {
    @Id
    private String id;
    private String fullName;
    @Indexed(unique = true)
    private String emailId;
    private String mobileNumber;
    private String otherPhoneNumbers;
    private String password;

    private String resetPasswordKey;

    private String emailVerificationCode;
    private String mobileVerificationCode;

    private boolean isEmailVerified;
    private boolean isMobileVerifid;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtherPhoneNumbers() {
        return otherPhoneNumbers;
    }

    public void setOtherPhoneNumbers(String otherPhoneNumbers) {
        this.otherPhoneNumbers = otherPhoneNumbers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResetPasswordKey() {
        return resetPasswordKey;
    }

    public void setResetPasswordKey(String resetPasswordKey) {
        this.resetPasswordKey = resetPasswordKey;
    }

    public String getEmailVerificationCode() {
        return emailVerificationCode;
    }

    public void setEmailVerificationCode(String emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
    }

    public String getMobileVerificationCode() {
        return mobileVerificationCode;
    }

    public void setMobileVerificationCode(String mobileVerificationCode) {
        this.mobileVerificationCode = mobileVerificationCode;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public boolean isMobileVerifid() {
        return isMobileVerifid;
    }

    public void setMobileVerifid(boolean isMobileVerifid) {
        this.isMobileVerifid = isMobileVerifid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
