package com.comelystreet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.comelystreet.dao.mongodb.model.CustomerDetail;
import com.comelystreet.dao.mongodb.model.StoreDetail;
import com.comelystreet.mongodb.types.SessionConstants;
import com.comelystreet.mongodb.types.SigninDataModel;
import com.comelystreet.services.mongo.CustomerDetailService;
import com.comelystreet.services.mongo.StoreDetailService;
import com.comelystreet.validator.SigninDataModelValidator;

@Controller
@RequestMapping("/signin")
public class SigninController extends BaseController {

    @Autowired
    private CustomerDetailService customerDetailService;
    @Autowired
    private StoreDetailService storeDetailService;

    @Autowired
    private SigninDataModelValidator signinDataModelValidator;

    @InitBinder("signinDataModel")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(signinDataModelValidator);
    }

    @RequestMapping(value = { "store" }, method = RequestMethod.GET)
    public ModelAndView signInStorePage(@RequestParam(required = false) final String returnUrl,
            @RequestParam(required = false) final String searchUrl, final Model model) {
        final SigninDataModel signinDataModel = new SigninDataModel();
        signinDataModel.setReturnUrl(returnUrl);
        signinDataModel.setSearchUrl(searchUrl);
        model.addAttribute("signinDataModel", signinDataModel);
        return getModelAndView("client/signin", model);
    }

    @RequestMapping(value = { "customer" }, method = RequestMethod.GET)
    public ModelAndView signInCustomerPage(@RequestParam(required = false) final String returnUrl,
            @RequestParam(required = false) final String searchUrl, final Model model) {
        final SigninDataModel signinDataModel = new SigninDataModel();
        signinDataModel.setReturnUrl(returnUrl);
        signinDataModel.setSearchUrl(searchUrl);
        model.addAttribute("signinDataModel", signinDataModel);
        return getModelAndView("customer/signin", model);
    }

    @RequestMapping(value = { "customer" }, method = RequestMethod.POST)
    public ModelAndView handleSignInCustomer(
            @ModelAttribute("signinDataModel") @Validated final SigninDataModel signinDataModel,
            final BindingResult bindingResult, final Model model, final HttpServletRequest httpServletRequest) {

        if (!bindingResult.hasErrors()) {
            final CustomerDetail customerDetail = this.customerDetailService.signInCustomer(signinDataModel);
            if (null != customerDetail) {
                customerDetail.setPassword(null);
                final HttpSession session = httpServletRequest.getSession();
                session.setAttribute(SessionConstants.CUSTOMER_ID, customerDetail.getId());
                session.setAttribute(SessionConstants.CUSTOMER_DETAIL, customerDetail);
                // create session and take to next page if available
                if (StringUtils.isEmpty(signinDataModel.getReturnUrl())) {
                    return getModelAndView("redirect:/", null);
                }
                return getModelAndView(
                        "redirect:" + signinDataModel.getReturnUrl() + "&searchUrl=" + signinDataModel.getSearchUrl(),
                        null);
            } else {
                model.addAttribute("errorMessage", "Email/Password is not correct.");
            }
        }
        signinDataModel.setPassword("");
        model.addAttribute("signinDataModel", signinDataModel);
        return getModelAndView("customer/signin", model);
    }

    @RequestMapping(value = { "store" }, method = RequestMethod.POST)
    public ModelAndView handleSignInStore(
            @ModelAttribute("signinDataModel") @Validated final SigninDataModel signinDataModel,
            final BindingResult bindingResult, final Model model, final HttpServletRequest httpServletRequest) {

        if (!bindingResult.hasErrors()) {
            System.out.println(signinDataModel.getPassword());
            final StoreDetail storeDetail = this.storeDetailService.signInStore(signinDataModel);
            if (null != storeDetail) {
                storeDetail.setPassword(null);
                final HttpSession session = httpServletRequest.getSession();
                session.setAttribute(SessionConstants.STORE_ID, storeDetail.getId());
                session.setAttribute(SessionConstants.STORE_DETAIL, storeDetail);
                // take to store
                return getModelAndView("redirect:/store/manage/dashboard?storeId=" + storeDetail.getId(), null);

            } else {
                model.addAttribute("errorMessage", "Email/Password is not correct.");
            }
        }
        signinDataModel.setPassword("");
        model.addAttribute("signinDataModel", signinDataModel);
        return getModelAndView("client/signin", model);
    }
}
