package com.comelystreet.mongodb.types;

public enum StoreType {
    MALE("Gents only"), FEMALE("Ladies Only"), UNISEX("Unisex"), NONE("none");

    private String type;

    private StoreType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
