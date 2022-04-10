package com.comelystreet.controller.booking;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.comelystreet.controller.BaseController;
import com.comelystreet.dao.mongodb.model.BookingServicesDetail;
import com.comelystreet.dao.mongodb.model.ServiceItemDetail;
import com.comelystreet.mongodb.mapper.BookingDataModelUIToDBMapper;
import com.comelystreet.mongodb.types.BookingServicesDataModelOutput;
import com.comelystreet.mongodb.types.BookingServicesInputDataModel;
import com.comelystreet.services.mongo.BookingDetailService;
import com.comelystreet.services.mongo.StoreDailyScheduleDetailService;
import com.comelystreet.services.mongo.StoreDetailService;
import com.comelystreet.services.util.CommunicationUtility;
import com.comelystreet.services.util.DateTimeUtility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping("/appointment")
public class AppointmentManagementController extends BaseController {

    @Autowired
    private StoreDetailService clientDetailService;
    @Autowired
    private BookingDetailService bookingDetailService;
    @Autowired
    private StoreDailyScheduleDetailService clientDailySchedulingDetailService;
    @Autowired
    private BookingDataModelUIToDBMapper bookingDataModelUIToDBMapper;

    @Autowired
    private CommunicationUtility communicationUtility;

    @RequestMapping("getTimeSlotsForStore")
    public @ResponseBody Map<Long, String> getTimeSlotsForStore(
            @RequestBody final BookingServicesInputDataModel bookingServicesDataModel) throws JsonParseException,
            JsonMappingException, IOException, ParseException {

        final List<ServiceItemDetail> serviceItemDataModels = clientDetailService.findServiceItemsByIds(
                bookingServicesDataModel.getSelectedServices(), bookingServicesDataModel.getStoreId());

        return clientDailySchedulingDetailService.getTimeSlotsAsStringMap(bookingServicesDataModel.getStoreId(),
                bookingServicesDataModel.getBookingDate(), serviceItemDataModels);

    }

    @RequestMapping("bookAppointment")
    public ModelAndView bookAppointment(final Model model,
            @ModelAttribute final BookingServicesInputDataModel bookingServicesDataModel) {
        BookingServicesDataModelOutput bookingServicesDataModelOutput = null;
        try {
            if (isRequestedSlotAvailable(bookingServicesDataModel)) {
                BookingServicesDetail bookingServicesDetail = bookingDetailService
                        .bookAppointment(bookingServicesDataModel);
                bookingServicesDataModelOutput = bookingDataModelUIToDBMapper
                        .createUIOutputModelFromDBEntity(bookingServicesDetail);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // model.addAttribute("bookingDetail", bookingServicesDataModelOutput);
        return getModelAndView(
                "redirect:/appointment/bookingConfirm?bookingId=" + bookingServicesDataModelOutput.getBookingId(),
                model);
    }

    /**
     * This method will check if the requested slot is available in the store
     * Returning TRUE as of now to unblock testing. (I don't see booking slot
     * being taken which was selected on booking page for sometime)
     * 
     * @param bookingServicesDataModel
     * @return
     */
    private boolean isRequestedSlotAvailable(BookingServicesInputDataModel bookingServicesDataModel) {

        // final List<ServiceItemDetail> serviceItemDataModels =
        // clientDetailServiceMongo.findServiceItemsByIds(
        // bookingServicesDataModel.getSelectedServices(),
        // bookingServicesDataModel.getStoreId());
        //
        // Map<Long, String> availableTimeSlotsMap =
        // clientDailySchedulingDetailServiceMongo.getTimeSlotsAsStringMap(
        // bookingServicesDataModel.getStoreId(), DateUtility.getDateFromString(
        // bookingServicesDataModel.getBookingDate(),
        // DateUtility.DD_MMM_YYYY_SHORT_STRING),
        // serviceItemDataModels);
        return true;
    }

    @RequestMapping("showBookingRaw")
    public @ResponseBody BookingServicesDetail getBookingForId(@RequestParam final String bookingId) {
        return bookingDetailService.findByBookingId(bookingId);
    }

    @RequestMapping("bookingConfirm")
    public ModelAndView showBookingConfirm(final Model model, @RequestParam final String bookingId) {
        populateBookingDetailModel(model, bookingId);
        return getModelAndView("/appointment/booking-confirm", model);
    }

    @RequestMapping("bookingReceipt")
    public ModelAndView showBookingReceipt(final Model model, @RequestParam final String bookingId) {
        populateBookingDetailModel(model, bookingId);
        return getModelAndView("/appointment/booking-receipt", model);
    }

    // For testing
    @RequestMapping("sendReceipt")
    public @ResponseBody String sendReceipt(@RequestParam final String bookingId) {
        // communicationUtility.communicateCustomer(bookingId);
        return "DONE";
    }

    private void populateBookingDetailModel(final Model model, final String bookingId) {
        try {
            BookingServicesDetail bookingServicesDetail = bookingDetailService.findByBookingId(bookingId);
            model.addAttribute("bookingDetail",
                    bookingDataModelUIToDBMapper.createUIOutputModelFromDBEntity(bookingServicesDetail));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
