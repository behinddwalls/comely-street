package com.comelystreet.controller.store;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.comelystreet.controller.BaseController;
import com.comelystreet.dao.mongodb.model.BookingServicesDetail;
import com.comelystreet.mongodb.mapper.BookingDataModelUIToDBMapper;
import com.comelystreet.mongodb.types.BookingServicesDataModelOutput;
import com.comelystreet.mongodb.types.StoreBookingListPageRequestDataModel;
import com.comelystreet.services.mongo.BookingDetailService;
import com.comelystreet.services.util.DateTimeUtility;
import com.google.common.collect.Lists;

@Controller
@RequestMapping("/store/manage")
public class StoreManagementController extends BaseController {

    @Autowired
    private BookingDetailService bookingDetailService;
    @Autowired
    private BookingDataModelUIToDBMapper bookingDataModelUIToDBMapper;

    @RequestMapping("")
    public void manageStoreLandingPage() {

    }

    // Client view
    @RequestMapping("dashboard")
    public ModelAndView showDashboard(final Model model,
            @ModelAttribute StoreBookingListPageRequestDataModel storeBookingListPageRequestDataModel,
            @RequestParam String storeId,
            // @RequestParam String date,
            final HttpServletRequest httpServletRequest) {
        // System.out.println("Date: " + date);
        try {
            if (storeBookingListPageRequestDataModel == null) {
                storeBookingListPageRequestDataModel = new StoreBookingListPageRequestDataModel();
                storeBookingListPageRequestDataModel.setStartDate(DateTimeUtility.getCurrentDateAsUIDateString());
                storeBookingListPageRequestDataModel.setStoreId(storeId);
            }
            System.out.println("storeBookingListPageRequestDataModel: " + storeBookingListPageRequestDataModel);
            List<BookingServicesDetail> bookingServicesDetails = bookingDetailService
                    .findBookingForDate(storeBookingListPageRequestDataModel);
            List<BookingServicesDataModelOutput> bookingServicesDataModelOutputs = Lists.newArrayList();
            for (BookingServicesDetail bookingServicesDetail : bookingServicesDetails) {
                bookingServicesDataModelOutputs.add(bookingDataModelUIToDBMapper
                        .createUIOutputModelFromDBEntity(bookingServicesDetail));
            }

            model.addAttribute("storeBookingListPageRequestDataModel", storeBookingListPageRequestDataModel);
            model.addAttribute("storeBookingList", bookingServicesDataModelOutputs);
            model.addAttribute("storeId", storeId);
            model.addAttribute("startDate", storeBookingListPageRequestDataModel.getStartDate());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return getModelAndView("client/dashboard", model);
    }
}
