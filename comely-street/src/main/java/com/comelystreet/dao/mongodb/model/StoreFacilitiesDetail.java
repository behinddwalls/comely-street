package com.comelystreet.dao.mongodb.model;

public class StoreFacilitiesDetail {

    private boolean tvAvailable;
    private boolean parkingAvailable;
    private boolean acAvailable;
    private boolean acceptsCreditCards;
    private boolean wifiAvailable;

    public boolean isTvAvailable() {
        return tvAvailable;
    }

    public void setTvAvailable(boolean tvAvailable) {
        this.tvAvailable = tvAvailable;
    }

    public boolean isParkingAvailable() {
        return parkingAvailable;
    }

    public void setParkingAvailable(boolean parkingAvailable) {
        this.parkingAvailable = parkingAvailable;
    }

    public boolean isAcAvailable() {
        return acAvailable;
    }

    public void setAcAvailable(boolean acAvailable) {
        this.acAvailable = acAvailable;
    }

    public boolean isAcceptsCreditCards() {
        return acceptsCreditCards;
    }

    public void setAcceptsCreditCards(boolean acceptsCreditCards) {
        this.acceptsCreditCards = acceptsCreditCards;
    }

    public boolean isWifiAvailable() {
        return wifiAvailable;
    }

    public void setWifiAvailable(boolean wifiAvailable) {
        this.wifiAvailable = wifiAvailable;
    }

    @Override
    public String toString() {
        return "StoreFacilitiesDetail [tvAvailable=" + tvAvailable + ", parkingAvailable=" + parkingAvailable
                + ", acAvailable=" + acAvailable + ", acceptsCreditCards=" + acceptsCreditCards + ", wifiAvailable="
                + wifiAvailable + "]";
    }

}
