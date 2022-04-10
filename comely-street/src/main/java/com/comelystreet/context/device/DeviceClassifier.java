package com.comelystreet.context.device;

import javax.servlet.http.Cookie;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

public class DeviceClassifier {

    private static final String IPHONE = "iphone";
    private static final String IPAD = "ipad";
    private static final String WINDOWS = "windows";
    private static final String MOBILE = "mobile";

    private static final String APP_COOKIE_NAME = "cmly-app-id";

    public static DeviceClassification classify(final String userAgent, final Cookie[] cookies) {
        String ignoreCaseUserAgent = StringUtils.EMPTY;
        if (null != userAgent) {
            ignoreCaseUserAgent = userAgent.toLowerCase();
        }
        DeviceClassification deviceClassification = new DeviceClassification();
        deviceClassification.setDevicePlatform(classifyPlatform(ignoreCaseUserAgent));
        deviceClassification.setDeviceRequestEnvironment(classifyRequestEnvironment(cookies));
        deviceClassification.setDeviceType(classifyType(ignoreCaseUserAgent));
        return deviceClassification;
    }

    private static DevicePlatform classifyPlatform(@NotNull final String ignoreCaseUserAgent) {
        if (ignoreCaseUserAgent.contains(DevicePlatform.ANDROID.name().toLowerCase())) {
            return DevicePlatform.ANDROID;
        } else if (ignoreCaseUserAgent.contains(IPHONE) || ignoreCaseUserAgent.contains(IPAD)) {
            return DevicePlatform.IOS;
        } else if (ignoreCaseUserAgent.contains(WINDOWS)) {
            return DevicePlatform.WINDOWS;
        }
        return DevicePlatform.NONE;
    }

    private static DeviceRequestEnvironment classifyRequestEnvironment(final Cookie[] cookies) {
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                final String name = cookie.getName();
                if (!StringUtils.isEmpty(name) && APP_COOKIE_NAME.equals(name)) {
                    return DeviceRequestEnvironment.APP;
                }
            }
        }
        return DeviceRequestEnvironment.BROWSER;

    }

    private static DeviceType classifyType(final String ignoreCaseUserAgent) {

        if (ignoreCaseUserAgent.contains(MOBILE))
            return DeviceType.MOBILE;

        return DeviceType.DESKTOP;
    }
}
