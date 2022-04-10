package com.comelystreet.controller.customer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.comelystreet.controller.BaseController;
import com.comelystreet.mongodb.mapper.BookingDataModelUIToDBMapper;
import com.comelystreet.mongodb.types.BookingTimelineType;
import com.comelystreet.mongodb.types.CustomerBookingOutputDataModel;
import com.comelystreet.mongodb.types.CustomerListBookingPageRequestDataModel;
import com.comelystreet.services.mongo.BookingDetailService;
import com.comelystreet.services.util.CommunicationUtility;

@Controller
@RequestMapping("/customer")
public class CustomerBookingController extends BaseController {

    private Logger log = LoggerFactory.getLogger(CustomerBookingController.class);

    @Autowired
    private BookingDetailService bookingDetailService;
    @Autowired
    private BookingDataModelUIToDBMapper bookingDataModelUIToDBMapper;
    
    @RequestMapping("bookings")
    public ModelAndView customerBookingsLandingPage(final Model model) {
        return getModelAndView("customer/booking-landing", model);
    }

    @RequestMapping("bookings/{bookingTimelineType}")
    public ModelAndView customerBookingsByType(@PathVariable final String bookingTimelineType,
            @ModelAttribute CustomerListBookingPageRequestDataModel customerListBookingPageRequestDataModel,
            final Model model) throws Exception {
        BookingTimelineType bookingTimelineTypeEnum = null;

        try {
            bookingTimelineTypeEnum = BookingTimelineType.valueOf(bookingTimelineType.toUpperCase());
        } catch (Exception e) {
            log.error("Invalid timeline type", e);
            throw e;
        }
        model.addAttribute("customerBookingList",
                getCustomerBookingOutputDataModels(customerListBookingPageRequestDataModel, bookingTimelineTypeEnum));
        model.addAttribute("bookingPageHeading", getBookingPageHeadingFromTimeLineEnum(bookingTimelineTypeEnum));
        model.addAttribute("customerListBookingPageRequestDataModel", customerListBookingPageRequestDataModel);
        return getModelAndView("customer/list-bookings", model);
    } 

    private String getBookingPageHeadingFromTimeLineEnum(final BookingTimelineType bookingTimelineType)
            throws Exception {
        switch (bookingTimelineType) {
        case TODAYS:
            return "Today's";
        case UPCOMING:
            return "Upcoming";
        case PAST:
            return "Past";
        default:
            throw new Exception("No Match found for enum value");
        }
    }

    private List<CustomerBookingOutputDataModel> getCustomerBookingOutputDataModels(
            CustomerListBookingPageRequestDataModel customerListBookingPageRequestDataModel,
            final BookingTimelineType bookingTimelineType) {
        System.out.println(customerListBookingPageRequestDataModel);
        if (customerListBookingPageRequestDataModel == null) {
            customerListBookingPageRequestDataModel = new CustomerListBookingPageRequestDataModel();
        }
        customerListBookingPageRequestDataModel.setBookingTimelineTypeEnum(bookingTimelineType);
        customerListBookingPageRequestDataModel.setCustomerId(getCustomerId());
        return this.bookingDataModelUIToDBMapper
                .getCustomerBookingOutputDataModelsFromEntities(this.bookingDetailService
                        .getPaginatedCustomerBookingsForListBookingPage(customerListBookingPageRequestDataModel));
    }
}
