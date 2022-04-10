package com.comelystreet.mongodb.mapper;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comelystreet.context.RequestContextContainer;
import com.comelystreet.dao.mongodb.model.BookingServicesDetail;
import com.comelystreet.dao.mongodb.model.CustomerCommunicationStatus;
import com.comelystreet.dao.mongodb.model.CustomerDetail;
import com.comelystreet.dao.mongodb.model.DateTimeSlotDetail;
import com.comelystreet.dao.mongodb.model.ServiceItemDetail;
import com.comelystreet.dao.mongodb.model.StoreDetail;
import com.comelystreet.mongodb.types.BookingServicesDataModelOutput;
import com.comelystreet.mongodb.types.BookingServicesInputDataModel;
import com.comelystreet.mongodb.types.CustomerBookingOutputDataModel;
import com.comelystreet.services.mongo.StoreDetailService;
import com.comelystreet.services.util.DateTimeUtility;
import com.google.common.collect.Lists;

@Component
public class BookingDataModelUIToDBMapper {

    @Autowired
    private StoreDetailService storeDetailService;
    @Autowired
    private StoreDetailMapper storeDetailMapper;

    public BookingServicesDetail createDBEntityFromInputModel(
            final BookingServicesInputDataModel bookingServicesDataModel) throws Exception {

        BookingServicesDetail bookingServicesDetail = new BookingServicesDetail();

        if (RequestContextContainer.getRequestContext().isCustomerAuthenticated()) {
            CustomerDetail customerDetail = RequestContextContainer.getRequestContext().getCustomerDetail();
            System.out.println("CustomerID: " + customerDetail.getId() + "  ; customer name: "
                    + customerDetail.getFullName());
            bookingServicesDetail.setCustomerDataModel(customerDetail);
            bookingServicesDetail.setCustomerEmail(customerDetail.getEmailId());
            bookingServicesDetail.setCustomerMobileNumber(customerDetail.getMobileNumber());
            bookingServicesDetail.setCustomerName(customerDetail.getFullName());
        } else {
            bookingServicesDetail.setCustomerEmail(bookingServicesDataModel.getEmail());
            bookingServicesDetail.setCustomerMobileNumber(bookingServicesDataModel.getMobileNumber());
            bookingServicesDetail.setCustomerName(bookingServicesDataModel.getName());
        }

        StoreDetail storeDetail = storeDetailService.findByStoreId(bookingServicesDataModel.getStoreId());

        if (storeDetail == null) {
            throw new Exception("Store not found with given storeID: " + bookingServicesDataModel.getStoreId());
        }

        bookingServicesDetail.setStoreDataModel(storeDetail);
        bookingServicesDetail.setProposedStartTime(new DateTimeSlotDetail(DateTimeUtility
                .convertFromUIDateStringToDBDateLong(bookingServicesDataModel.getBookingDate()),
                bookingServicesDataModel.getBookingTimeSlot()));

        List<String> selectedServices = bookingServicesDataModel.getSelectedServices();
        System.out.println("Booked services before: " + selectedServices);
        selectedServices = selectedServices.stream().filter(x -> x != null).collect(Collectors.toList());
        System.out.println("Booked services after: " + selectedServices);
        List<ServiceItemDetail> serviceItemDetails = storeDetailService.findServiceItemsByIds(selectedServices,
                bookingServicesDataModel.getStoreId());

        long totalPrice = 0;
        int totalTime = 0;
        for (ServiceItemDetail serviceItemDetail : serviceItemDetails) {
            totalPrice += serviceItemDetail.getPrice();
            totalTime += serviceItemDetail.getTime();
        }
        long endTimeLong = calculateProposedEndTime(bookingServicesDataModel.getBookingTimeSlot(), totalTime);
        bookingServicesDetail.setProposedEndTime(new DateTimeSlotDetail(DateTimeUtility
                .convertFromUIDateStringToDBDateLong(bookingServicesDataModel.getBookingDate()),
                endTimeLong));
        bookingServicesDetail.setTotalPrice(totalPrice);
        bookingServicesDetail.setCurrency(serviceItemDetails.get(0).getCurrency());
        bookingServicesDetail.setServiceItemDetails(serviceItemDetails);
        bookingServicesDetail.setBookingStatus("BOOKED");
        //// Communication Status
        // Customer
        String defaultCommunicationStatus = "TOBEINITIATED";
        bookingServicesDetail.setCustomerCommunicationStatus(new CustomerCommunicationStatus(
                defaultCommunicationStatus, defaultCommunicationStatus, defaultCommunicationStatus));
        // Client
        bookingServicesDetail.setCustomerCommunicationStatus(new CustomerCommunicationStatus(
                defaultCommunicationStatus, defaultCommunicationStatus, defaultCommunicationStatus));
        
        return bookingServicesDetail;
    }

    private long calculateProposedEndTime(long bookingTimeSlot, int totalTime) {
        return DateTimeUtility.addMinutesToTime(bookingTimeSlot, totalTime);
    }

    public BookingServicesDataModelOutput createUIOutputModelFromDBEntity(
            final BookingServicesDetail bookingServicesDetail) throws ParseException {
        BookingServicesDataModelOutput bookingServicesDataModelOutput = new BookingServicesDataModelOutput();
        bookingServicesDataModelOutput.setBookingDate(DateTimeUtility
                .convertLongDateToUIStringDate(bookingServicesDetail.getProposedStartTime().getDate()));
        bookingServicesDataModelOutput.setBookingTimeSlot(DateTimeUtility.getTimeFromLong(bookingServicesDetail
                .getProposedStartTime().getTimeSlot()));
        bookingServicesDataModelOutput.setCustomerEmail(bookingServicesDetail.getCustomerEmail());
        bookingServicesDataModelOutput.setCustomerMobileNumber(bookingServicesDetail.getCustomerMobileNumber());
        bookingServicesDataModelOutput.setCustomerName(bookingServicesDetail.getCustomerName());
        bookingServicesDataModelOutput.setSelectedServices(bookingServicesDetail.getServiceItemDetails());
        bookingServicesDataModelOutput.setBookingId(bookingServicesDetail.getId());
        bookingServicesDataModelOutput.setBookingStatus(bookingServicesDetail.getBookingStatus());
        bookingServicesDataModelOutput.setTotalPrice(bookingServicesDetail.getTotalPrice());
        bookingServicesDataModelOutput.setCurrency(bookingServicesDetail.getCurrency());
        
        bookingServicesDataModelOutput.setStoreId(bookingServicesDetail.getStoreDataModel().getId());
        bookingServicesDataModelOutput.setStoreName(bookingServicesDetail.getStoreDataModel().getName());
        bookingServicesDataModelOutput.setStoreArea(bookingServicesDetail.getStoreDataModel().getArea());
        bookingServicesDataModelOutput.setStoreAddress(bookingServicesDetail.getStoreDataModel().getAddress());
        bookingServicesDataModelOutput.setStoreMobileNumber(bookingServicesDetail.getStoreDataModel().getMobileNumber());
        bookingServicesDataModelOutput.setStoreOtherContactNumbers(bookingServicesDetail.getStoreDataModel().getOtherPhoneNumbers());
        return bookingServicesDataModelOutput;
    }

    /**
     * Being used for listing the bookings for customer.
     * 
     * @param bookingServicesDetail
     * @return
     * @throws ParseException
     */
    public CustomerBookingOutputDataModel getCustomerBookingOutputDataModelFromEntity(
            final BookingServicesDetail bookingServicesDetail) {

        final CustomerBookingOutputDataModel customerBookingOutputDataModel = new CustomerBookingOutputDataModel();
        customerBookingOutputDataModel.setBookingId(bookingServicesDetail.getId());
        customerBookingOutputDataModel.setBookingStatus(bookingServicesDetail.getBookingStatus());
        customerBookingOutputDataModel.setServiceItemDetails(bookingServicesDetail.getServiceItemDetails());
        customerBookingOutputDataModel.setStoreDataModel(this.storeDetailMapper
                .getModelFromEntity(bookingServicesDetail.getStoreDataModel()));
        customerBookingOutputDataModel.setTotalPrice(bookingServicesDetail.getTotalPrice());

        try {
            customerBookingOutputDataModel.setDate(DateTimeUtility.convertLongDateToUIStringDate(bookingServicesDetail
                    .getProposedStartTime().getDate()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        customerBookingOutputDataModel.setTime(DateTimeUtility.getTimeFromLong(bookingServicesDetail
                .getProposedStartTime().getTimeSlot()));

        return customerBookingOutputDataModel;

    }

    public List<CustomerBookingOutputDataModel> getCustomerBookingOutputDataModelsFromEntities(
            @NotNull final List<BookingServicesDetail> bookingServicesDetails) {
        final List<CustomerBookingOutputDataModel> customerBookingOutputDataModels = Lists.newArrayList();

        bookingServicesDetails.forEach(x -> customerBookingOutputDataModels.add(this
                .getCustomerBookingOutputDataModelFromEntity(x)));
        return customerBookingOutputDataModels;

    }
}
