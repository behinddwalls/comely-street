package com.comelystreet.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.comelystreet.mongodb.types.CustomerRegisterDataModel;
import com.comelystreet.services.mongo.CustomerDetailService;
import com.comelystreet.validator.CustomerRegisterDataModelValidator;

/**
 * @author #rustic
 *
 */
@Controller
@RequestMapping("register")
public class RegisterController extends BaseController {

    @Autowired
    private CustomerDetailService customerDetailService;

    @Autowired
    private CustomerRegisterDataModelValidator customerRegisterDataModelValidator;

    @InitBinder("customerRegisterDataModel")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(customerRegisterDataModelValidator);
    }

    @RequestMapping(value = { "customer" }, method = RequestMethod.GET)
    public ModelAndView registerPageCustomer(final Model model) {

        model.addAttribute("customerRegisterDataModel", new CustomerRegisterDataModel());
        return getModelAndView("customer/register", model);
    }

    @RequestMapping(value = { "customer" }, method = RequestMethod.POST)
    public ModelAndView registerCustomer(
            @ModelAttribute("customerRegisterDataModel") @Validated final CustomerRegisterDataModel customerRegisterDataModel,
            final BindingResult bindingResult, final Model model) {
        if (!bindingResult.hasErrors()) {
            this.customerDetailService.registerNewCustomer(customerRegisterDataModel);
            return getModelAndView("redirect:/signin/customer", null);
        }
        model.addAllAttributes(bindingResult.getModel());
        model.addAttribute("customerRegisterDataModel", customerRegisterDataModel);
        return getModelAndView("customer/register", model);
    }
}
