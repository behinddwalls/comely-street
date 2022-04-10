package com.comelystreet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.comelystreet.context.RequestContext;
import com.comelystreet.context.RequestContextContainer;
import com.comelystreet.context.device.DeviceClassification;
import com.comelystreet.context.device.DeviceClassifier;
import com.comelystreet.dao.mongodb.model.CustomerDetail;
import com.comelystreet.dao.mongodb.model.StoreDetail;
import com.comelystreet.mongodb.types.SessionConstants;

public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        RequestContext.Builder requestContextBuilder = new RequestContext.Builder();

        HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
        final Cookie[] cookies = httpServletRequest.getCookies();
        final String userAgent = httpServletRequest.getHeader("User-Agent");

        final DeviceClassification deviceClassification = DeviceClassifier.classify(userAgent, cookies);
        HttpSession httpSession = httpServletRequest.getSession(false);

        requestContextBuilder.deviceClassification(deviceClassification);

        if (null != httpSession) {
            if (null != httpSession.getAttribute(SessionConstants.CUSTOMER_DETAIL)) {
                requestContextBuilder.customerDetail((CustomerDetail) httpSession
                        .getAttribute(SessionConstants.CUSTOMER_DETAIL));
            }
            if (null != httpSession.getAttribute(SessionConstants.STORE_DETAIL)) {
                requestContextBuilder
                        .storeDetail((StoreDetail) httpSession.getAttribute(SessionConstants.STORE_DETAIL));
            }
        }

        RequestContextContainer.setRequestContext(requestContextBuilder.build());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
