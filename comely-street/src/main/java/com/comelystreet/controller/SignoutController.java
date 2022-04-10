package com.comelystreet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/signout")
public class SignoutController extends BaseController {

    @RequestMapping("customer")
    public ModelAndView signoutCustomer(final HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        return getModelAndView("redirect:/signin/customer", null);
    }
}
