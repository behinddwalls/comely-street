package com.comelystreet.controller.store;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.comelystreet.controller.BaseController;
import com.comelystreet.dao.mongodb.model.ServiceItemDetail;
import com.comelystreet.dao.mongodb.model.StoreDailyScheduleDetail;
import com.comelystreet.dao.mongodb.model.StoreDetail;
import com.comelystreet.mongodb.mapper.StoreDetailMapper;
import com.comelystreet.mongodb.mapper.StoreOnboardingInputToDBDataModelMapper;
import com.comelystreet.mongodb.types.StoreOnboardingInputDataModel;
import com.comelystreet.services.mongo.BookingDetailService;
import com.comelystreet.services.mongo.CommonGeneralPurposeService;
import com.comelystreet.services.mongo.StoreDailyScheduleDetailService;
import com.comelystreet.services.mongo.StoreDetailService;
import com.comelystreet.services.util.DateTimeUtility;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping("store")
public class StoreRegistrationController extends BaseController {

    @Autowired
    private StoreDetailService clientDetailServiceMongo;
    @Autowired
    private BookingDetailService bookingDetailService;
    @Autowired
    private CommonGeneralPurposeService commonGeneralPurposeService;
    @Autowired
    private StoreDailyScheduleDetailService clientDailySchedulingDetailServiceMongo;
    @Autowired
    private StoreOnboardingInputToDBDataModelMapper clientOnboardingInputToDBDataModelMapper;

    @Autowired
    private StoreDetailMapper storeDetailMapper;

    @RequestMapping("")
    public ModelAndView storeLandingPage(final Model model) {

        return getModelAndView("client/landing", model);
    }

    @RequestMapping("onboard")
    public ModelAndView onboardClient(final Model model) {
        model.addAttribute("clientOnboardingInputDataModel", new StoreOnboardingInputDataModel());
        model.addAttribute("siteAvailabilityAreas", this.commonGeneralPurposeService.getSiteAvailabilityAreas());
        return getModelAndView("client/onboard", model);
    }

    @RequestMapping("register")
    public ModelAndView registerClient(final Model model,
            final StoreOnboardingInputDataModel clientOnboardingInputDataModel) throws JsonProcessingException {
        // validate input using validator

        List<ServiceItemDetail> serviceItemDataModels = clientOnboardingInputToDBDataModelMapper
                .createDbEntityforServiceItemDataModel();
        // Map UI input model to DB model
        StoreDetail clientDataModel = clientOnboardingInputToDBDataModelMapper
                .createDBEntityFromInputModel(clientOnboardingInputDataModel);
        clientDataModel.setServiceItemDetails(serviceItemDataModels);

        // Save data to DB
        clientDataModel = clientDetailServiceMongo.saveOrUpdate(clientDataModel);

        // create daily scheduling
        clientDailySchedulingDetailServiceMongo.mockDailyStoreScheduling(clientDataModel, new Date());

        model.addAttribute("clientDataModel", this.storeDetailMapper.getModelFromEntity(clientDataModel));
        model.addAttribute("siteAvailabilityAreas", this.commonGeneralPurposeService.getSiteAvailabilityAreas());
        return getModelAndView("client/onboard-confirm", model);

    }

    /**
     * Only for testing
     * 
     * @param clientId
     * @return
     * @throws ParseException
     */
    @RequestMapping("dailySchedule")
    public @ResponseBody StoreDailyScheduleDetail mockdailySchedule(@RequestParam final String clientId,
            @RequestParam final String date) throws ParseException {
        System.out.println(DateTimeUtility.getDateFromString(date, DateTimeUtility.DD_MMM_YYYY_SHORT_STRING));
        return clientDailySchedulingDetailServiceMongo.mockDailyStoreScheduling(
                clientDetailServiceMongo.findByStoreId(clientId),
                DateTimeUtility.getDateFromString(date, DateTimeUtility.DD_MMM_YYYY_SHORT_STRING));
    }

}
