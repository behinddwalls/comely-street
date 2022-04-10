package com.comelystreet.controller.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.comelystreet.controller.BaseController;
import com.comelystreet.dao.mongodb.model.CustomerDetail;
import com.comelystreet.mongodb.mapper.CustomerDetailMapper;
import com.comelystreet.mongodb.types.CustomerProfileDataModel;
import com.comelystreet.services.mongo.CustomerDetailService;
import com.comelystreet.validator.CustomerProfileDataModelValidator;

@Controller
@RequestMapping("/customer")
public class CustomerProfileController extends BaseController {

    private Logger log = LoggerFactory.getLogger(CustomerProfileController.class);

    @Autowired
    private CustomerDetailService customerDetailService;
    @Autowired
    private CustomerDetailMapper customerDetailMapper;

    @Autowired
    private CustomerProfileDataModelValidator customerProfileDataModelValidator;

    @InitBinder("customerProfileDataModel")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(customerProfileDataModelValidator);
    }

    @RequestMapping("profile")
    public ModelAndView customerProfileLandingPage(final Model model) {
        try {
            final CustomerDetail customerDetail = this.customerDetailService.getCustomerDetailById(getCustomerId());
            final CustomerProfileDataModel customerProfileDataModel = new CustomerProfileDataModel();
            customerProfileDataModel.setCustomerDataModel(this.customerDetailMapper.getModelFromEntity(customerDetail));
            model.addAttribute("customerProfileDataModel", customerProfileDataModel);
            return getModelAndView("customer/profile", model);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "profile", method = RequestMethod.POST)
    public ModelAndView saveCustomerProfile(
            @ModelAttribute @Validated CustomerProfileDataModel customerProfileDataModel,
            final BindingResult bindingResult, final Model model) {
        try {
            if (!bindingResult.hasErrors()) {
                customerProfileDataModel.getCustomerDataModel().setId(getCustomerId());
                final CustomerDetail customerDetail = this.customerDetailService
                        .saveCustomerProfile(customerProfileDataModel.getCustomerDataModel());
                customerProfileDataModel.setCustomerDataModel(this.customerDetailMapper
                        .getModelFromEntity(customerDetail));
                model.addAttribute("successMessage", "Profile has been successfully saved.");
                System.out.println("Success");
            } else {
                model.addAttribute(bindingResult.getModel());
                System.out.println("else");
                System.out.println(bindingResult.getModel());
            }
        } catch (Exception e) {
            log.error("Profile Save Failed ", e);
            model.addAttribute("errorMessage", "Failed to save profile. Please try again.");
        }
        model.addAttribute("customerProfileDataModel", customerProfileDataModel);
        return getModelAndView("customer/profile", model);
    }
}
