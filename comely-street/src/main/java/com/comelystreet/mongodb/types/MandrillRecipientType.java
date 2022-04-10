package com.comelystreet.mongodb.types;

public enum MandrillRecipientType {

    TO, CC, BCC;

    public String getTypeName() {
        return this.name().toLowerCase();
    }
}
