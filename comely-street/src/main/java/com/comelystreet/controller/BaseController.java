package com.comelystreet.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.comelystreet.context.RequestContextContainer;
import com.comelystreet.context.device.DeviceClassification;

public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    protected ModelAndView getModelAndView(final String viewName, final Model model) {
        if (model != null) {
            model.addAttribute("isCustomerAuthenticated", isCustomerAuthenticated());
            model.addAttribute("customerId", getCustomerId());
            model.addAttribute("customerName", getCustomerFullName());
            model.addAttribute("deviceClassification", getDeviceClassification());
        }
        return new ModelAndView(viewName, "commands", model);
    }

    protected String getCustomerId() {
        if (null != RequestContextContainer.getRequestContext().getCustomerDetail()) {
            return RequestContextContainer.getRequestContext().getCustomerDetail().getId();
        }
        return null;
    }

    protected String getCustomerFullName() {
        if (null != RequestContextContainer.getRequestContext().getCustomerDetail()) {
            return RequestContextContainer.getRequestContext().getCustomerDetail().getFullName();
        }
        return null;
    }

    protected boolean isCustomerAuthenticated() {
        return RequestContextContainer.getRequestContext().isCustomerAuthenticated();
    }

    protected DeviceClassification getDeviceClassification() {
        return RequestContextContainer.getRequestContext().getDeviceClassification();
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error/500");
        log.error("Some error", exception);
        return mav;
    }
}
