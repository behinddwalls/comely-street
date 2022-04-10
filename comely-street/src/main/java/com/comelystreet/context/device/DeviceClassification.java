package com.comelystreet.context.device;

public class DeviceClassification {

    private DevicePlatform devicePlatform;
    private DeviceRequestEnvironment deviceRequestEnvironment;
    private DeviceType deviceType;

    public DevicePlatform getDevicePlatform() {
        return devicePlatform;
    }

    public void setDevicePlatform(DevicePlatform devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

    public DeviceRequestEnvironment getDeviceRequestEnvironment() {
        return deviceRequestEnvironment;
    }

    public void setDeviceRequestEnvironment(DeviceRequestEnvironment deviceRequestEnvironment) {
        this.deviceRequestEnvironment = deviceRequestEnvironment;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public String toString() {
        return "DeviceClassification [devicePlatform=" + devicePlatform + ", deviceRequestEnvironment="
                + deviceRequestEnvironment + ", deviceType=" + deviceType + "]";
    }
}
