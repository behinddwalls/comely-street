package com.comelystreet.mongodb.types;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NexmoSendSMSResponse {

    @JsonProperty("message-count")
    private String messageCount; 
    @JsonProperty("messages")
    private List<NexmoMessage> messages;

    @Override
    public String toString() {
        return "NexmoSendSMSResponse [messageCount=" + messageCount + ", messages=" + messages + ", getClass()="
                + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

}
