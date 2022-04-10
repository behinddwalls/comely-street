package com.comelystreet.dao.mongodb.model;

public class ClientCommunicationStatus {
    private String emailStatus;
    private String smsStatus;
    private String appNotificationStatus;
    
    public String getEmailStatus() {
        return emailStatus;
    }
    
    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }
    
    public String getSmsStatus() {
        return smsStatus;
    }
    
    public void setSmsStatus(String smsStatus) {
        this.smsStatus = smsStatus;
    }
    
    public String getAppNotificationStatus() {
        return appNotificationStatus;
    }
    
    public void setAppNotificationStatus(String appNotificationStatus) {
        this.appNotificationStatus = appNotificationStatus;
    }
    
}
