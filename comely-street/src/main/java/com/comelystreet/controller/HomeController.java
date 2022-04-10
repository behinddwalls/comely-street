package com.comelystreet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.comelystreet.exceptions.InvalidRequestException;
import com.comelystreet.mongodb.types.MandrillSendMailInput;
import com.comelystreet.mongodb.types.NexmoSendSMSInput;
import com.comelystreet.mongodb.types.StoreSearchRequestDataModel;
import com.comelystreet.services.mail.MandrillMailService;
import com.comelystreet.services.mongo.StoreDetailService;
import com.comelystreet.services.mongo.CommonGeneralPurposeService;
import com.comelystreet.services.sms.NexmoSMSService;
import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class HomeController extends BaseController {

    @Autowired
    private StoreDetailService clientDetailServiceMongo;
    @Autowired
    private CommonGeneralPurposeService commonGeneralPurposeService;
    @Autowired
    private MandrillMailService mandrillMailService;
    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private NexmoSMSService nexmoSMSService;

    @RequestMapping(value = { "home", "index", ", ", "/" }, method = { RequestMethod.GET })
    public ModelAndView test(final Model model) {
        model.addAttribute("searchRequestDataModel", new StoreSearchRequestDataModel());
        model.addAttribute("siteAvailabilityAreas", this.commonGeneralPurposeService.getSiteAvailabilityAreas());
        return getModelAndView("home", model);
    }

    @RequestMapping(value = "/", method = { RequestMethod.POST })
    public ModelAndView search(final Model model, final StoreSearchRequestDataModel searchRequestDataModel) {

        System.out.println("Search requested." + searchRequestDataModel.toString());
        // clientDetailServiceMongo.
        return null;
    }

    @RequestMapping("init-db")
    public @ResponseBody String initDb() {
        commonGeneralPurposeService.initializeServiceCategory();
        commonGeneralPurposeService.initializeAvailableAreas();
        return "DONE";
    }

    @RequestMapping("testMail")
    public @ResponseBody String testMail() throws InvalidRequestException, RequestFailedException {

        MandrillSendMailInput input = new MandrillSendMailInput.Builder("Comely Street", "no-reply@comelystreet.com")
                .toEmailId("behinddwalls@gmail.com").toEmailId("sandeep.gond@gmail.com")
                .subject("Test Mail - ComelyStreet").htmlMessage("Test Mail").build();
        mandrillMailService.sendMail(input);
        return "Sent";
    }

    @RequestMapping("testSMS")
    public @ResponseBody String testSMS() throws Exception {

        NexmoSendSMSInput input = new NexmoSendSMSInput.Builder().toNumber("919535829415").text("Hello").build();

        System.out.println(nexmoSMSService.sendSMS(input));

        System.out.println(input.toJSONFormat(mappingJackson2HttpMessageConverter.getObjectMapper()));
        return input.toJSONFormat(mappingJackson2HttpMessageConverter.getObjectMapper());
    }
}