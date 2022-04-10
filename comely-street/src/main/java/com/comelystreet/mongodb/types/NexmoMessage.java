package com.comelystreet.mongodb.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NexmoMessage {
    @JsonProperty("to")
    private String to;
    @JsonProperty("message-id")
    private String messageid;
    @JsonProperty("status")
    private String status;
    @JsonProperty("remaining-balance")
    private String remainingBalance;
    @JsonProperty("message-price")
    private String messagePrice;
    @JsonProperty("network")
    private String network;
    @JsonProperty("error-text")
    private String errortext;
    @JsonProperty("client-ref")
    private String clientRef;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(String remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public String getMessagePrice() {
        return messagePrice;
    }

    public void setMessagePrice(String messagePrice) {
        this.messagePrice = messagePrice;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getErrortext() {
        return errortext;
    }

    public void setErrortext(String errortext) {
        this.errortext = errortext;
    }

    public String getClientRef() {
        return clientRef;
    }

    public void setClientRef(String clientRef) {
        this.clientRef = clientRef;
    }

    @Override
    public String toString() {
        return "Message [to=" + to + ", messageid=" + messageid + ", status=" + status + ", remainingBalance="
                + remainingBalance + ", messagePrice=" + messagePrice + ", network=" + network + ", errortext="
                + errortext + ", clientRef=" + clientRef + "]";
    }

}