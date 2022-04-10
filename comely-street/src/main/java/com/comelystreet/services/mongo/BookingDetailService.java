package com.comelystreet.services.mongo;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.comelystreet.dao.mongodb.BookingServicesDetailRepo;
import com.comelystreet.dao.mongodb.StoreDailySchedulingDetailRepo;
import com.comelystreet.dao.mongodb.model.BookingServicesDetail;
import com.comelystreet.dao.mongodb.model.ReviewDetail;
import com.comelystreet.dao.mongodb.model.StoreDailyScheduleDetail;
import com.comelystreet.dao.mongodb.model.TimeSlotDetail;
import com.comelystreet.mongodb.mapper.BookingDataModelUIToDBMapper;
import com.comelystreet.mongodb.types.BookingServicesInputDataModel;
import com.comelystreet.mongodb.types.CustomerListBookingPageRequestDataModel;
import com.comelystreet.mongodb.types.Pagination;
import com.comelystreet.mongodb.types.ReviewInputDataModel;
import com.comelystreet.mongodb.types.StoreBookingListPageRequestDataModel;
import com.comelystreet.services.util.CommunicationUtility;
import com.comelystreet.services.util.DateTimeUtility;
import com.comelystreet.services.util.PaginationUtility;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

@Service
public class BookingDetailService {
    @Autowired
    private BookingServicesDetailRepo bookingServicesRepo;
    @Autowired
    private BookingDataModelUIToDBMapper bookingDataModelUIToDBMapper;
    @Autowired
    private CommunicationUtility communicationUtility;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private StoreDailySchedulingDetailRepo clientDailySchedulingDetailRepo;

    public BookingServicesDetail findByBookingId(final String bookingId) {
        return bookingServicesRepo.findById(bookingId);
    }

    public BookingServicesDetail bookAppointment(BookingServicesInputDataModel bookingServicesDataModel)
            throws Exception {
        BookingServicesDetail bookingServicesDetail = bookingDataModelUIToDBMapper
                .createDBEntityFromInputModel(bookingServicesDataModel);
        // Reduce available slot by 1
        reduceAvailableSlot(bookingServicesDetail);
        bookingServicesRepo.save(bookingServicesDetail);
        // inform customer
        communicationUtility.communicateCustomer(this, bookingServicesDetail.getId());
        // communicateClient(bookingServicesDetail);

        return bookingServicesDetail;
    }

    private void reduceAvailableSlot(BookingServicesDetail bookingServicesDetail) throws Exception {
        // get all the time slots for booked date
        List<StoreDailyScheduleDetail> clientDailySchedulingDetails = clientDailySchedulingDetailRepo
                .findByStoreDetail_IdAndScheduleDate(bookingServicesDetail.getStoreDataModel().getId(),
                        bookingServicesDetail.getProposedStartTime().getDate());
        StoreDailyScheduleDetail clientDailySchedulingDetail = clientDailySchedulingDetails.get(0);
        // get the slots affected by this booking
        LinkedHashSet<TimeSlotDetail> updateTimeSlots = getAffectedTimeSlotsByBooking(clientDailySchedulingDetail, bookingServicesDetail);
        clientDailySchedulingDetail.setTimeSlotDetails(updateTimeSlots);
        clientDailySchedulingDetails.clear();
        clientDailySchedulingDetails.add(clientDailySchedulingDetail);
        System.out.println("clientDailySchedulingDetails length: " + clientDailySchedulingDetails.size());
        System.out.println("clientDailySchedulingDetail: " + clientDailySchedulingDetails.get(0).getTimeSlotDetails());
        clientDailySchedulingDetailRepo.save(clientDailySchedulingDetails);
    }

    private LinkedHashSet<TimeSlotDetail> getAffectedTimeSlotsByBooking(StoreDailyScheduleDetail clientDailySchedulingDetail,
            BookingServicesDetail bookingServicesDetail) throws Exception {
        
        long startTimeLong = bookingServicesDetail.getProposedStartTime().getTimeSlot();
        long endTimeLong = bookingServicesDetail.getProposedEndTime().getTimeSlot();
        System.out.println("StartTime: " + startTimeLong + " ; EndTime: " + endTimeLong);
        LinkedHashSet<TimeSlotDetail> timeSlots = clientDailySchedulingDetail.getTimeSlotDetails();
        System.out.println("Timslots before update: " + timeSlots);
        LinkedHashSet<TimeSlotDetail> updatedTimeSlots = Sets.newLinkedHashSet();
        Iterator<TimeSlotDetail> itr = timeSlots.iterator();
        while(itr.hasNext()) {
            TimeSlotDetail timeSlot = itr.next();
            if ((timeSlot.getTimeSlot() >= startTimeLong) && (timeSlot.getTimeSlot() <= endTimeLong)) {
                int getAvailableSlot = timeSlot.getSlotsAvailable();
                if (getAvailableSlot <= 0) {
                    System.out.println("No slot available. How did we reach here. Interleaving?");
                    throw new Exception("No slot available. How did we reach here. Interleaving?");
                }
                timeSlot.setSlotsAvailable(getAvailableSlot-1);
            }
            updatedTimeSlots.add(timeSlot);
        }
        System.out.println("Timslots after update: " + updatedTimeSlots);
        return updatedTimeSlots;
    }

    public BookingServicesDetail updateBookingDetail(BookingServicesDetail bookingServicesDetail) {
        return bookingServicesRepo.save(bookingServicesDetail);
    }

    public List<BookingServicesDetail> findBookingsByStoreId(final String storeId) {
        return bookingServicesRepo.findByStoreDataModel_Id(storeId);
    }

    public List<BookingServicesDetail> findBookingForDate(
            StoreBookingListPageRequestDataModel storeBookingListPageRequestDataModel) throws ParseException {
        if (storeBookingListPageRequestDataModel.getStartDate() == null) {
            System.out.println("Start date is null");
            storeBookingListPageRequestDataModel.setStartDate(DateTimeUtility.getCurrentDateAsUIDateString());
        }
        Date startDate = DateTimeUtility.getDateFromString(storeBookingListPageRequestDataModel.getStartDate(),
                DateTimeUtility.DD_MMM_YYYY_SHORT_STRING);
        DateTime jodaStartDate = new DateTime(startDate);
        DateTime jodaEndDate = jodaStartDate.plusDays(1);
        Date endDate = jodaEndDate.toDate();

        System.out.println("Start date: " + startDate.toString());
        System.out.println("JODA start date: " + jodaStartDate.toString());
        System.out.println("end date: " + endDate.toString());
        System.out.println("JODA end date: " + jodaEndDate.toString());

        long startDateLong = DateTimeUtility.convertDateToLong(startDate, DateTimeUtility.YYYYMMDD);

        Pagination pagination = null;
        if (null == storeBookingListPageRequestDataModel.getPagination()) {
            System.out.println("NULL PAGINATION");
            pagination = new Pagination();
            storeBookingListPageRequestDataModel.setPagination(pagination);
        } else {
            pagination = storeBookingListPageRequestDataModel.getPagination();
        }

        final Criteria criteria = Criteria.where("storeDataModel.id")
                .is(storeBookingListPageRequestDataModel.getStoreId()).and("proposedStartTime.date").is(startDateLong);

        Query query = new Query(criteria);
        setPaginationInBookingDetailsQuery(pagination, query);
        query.with(new Sort(Sort.Direction.ASC, "proposedStartTime.timeSlot"));
        System.out.println(query.toString());
        return mongoTemplate.find(query, BookingServicesDetail.class);
    }

    private void setPaginationInBookingDetailsQuery(Pagination pagination, Query query) {
        long count = this.mongoTemplate.count(query, BookingServicesDetail.class);
        System.out.println(pagination);
        pagination.setTotalCount(count);
        PaginationUtility.getOptimizedPagination(pagination);
        query.skip(pagination.getOffSet());
        query.limit(pagination.getPageSize());
    }

    public BookingServicesDetail addReview(@NotNull final ReviewInputDataModel reviewDataModel,
            @NotNull final String customerId) {
        Preconditions.checkArgument(!StringUtils.isEmpty(reviewDataModel.getBookingId()),
                "Booking Id is required for writing reviews");
        Preconditions.checkArgument(!StringUtils.isEmpty(customerId), "Custoemr Id can not be null");

        final BookingServicesDetail bookingServicesDetail = this.bookingServicesRepo.findById(reviewDataModel
                .getBookingId());
        final ReviewDetail reviewDetail = new ReviewDetail();
        reviewDetail.setRating(reviewDataModel.getRating());
        reviewDetail.setReviewComment(reviewDataModel.getReviewComment());
        bookingServicesDetail.setReviewDetail(reviewDetail);
        bookingServicesDetail.setReviewed(true);
        this.bookingServicesRepo.save(bookingServicesDetail);

        // TODO: POST Processing
        // Mark all bookings absolete before of this date for the store and
        // customer
        final Criteria criteria = Criteria.where("storeDataModel.id")
                .is(bookingServicesDetail.getStoreDataModel().getId()).and("customerDataModel.id").is(customerId)
                .and("reviewed").is(false).and("proposedStartTime.date")
                .lte(bookingServicesDetail.getProposedStartTime().getDate());

        final List<BookingServicesDetail> bookingServicesDetails = this.mongoTemplate.find(new Query(criteria),
                BookingServicesDetail.class);

        if (null != bookingServicesDetails && bookingServicesDetails.isEmpty()) {
            bookingServicesDetails.forEach(x -> {
                x.setObsoleteForReview(true);
                this.bookingServicesRepo.save(x);
            });
        }

        // Calculate the Store Rating and update it in the store Detail

        final Criteria updateRatingCriteria = Criteria.where("storeDataModel.id")
                .is(bookingServicesDetail.getStoreDataModel().getId()).and("reviewed").is(true);

        long totalReviewedBookings = this.mongoTemplate.count(new Query(updateRatingCriteria),
                BookingServicesDetail.class);
        long sumOfAllRatings = 0;
        int pageSize = 500;
        for (int i = 0; i < totalReviewedBookings; i += pageSize) {
            final List<BookingServicesDetail> bookingsToCalculateRating = this.mongoTemplate.find(new Query(
                    updateRatingCriteria).skip(i).limit(pageSize), BookingServicesDetail.class);

            if (bookingsToCalculateRating != null) {
                for (BookingServicesDetail b : bookingsToCalculateRating) {
                    sumOfAllRatings += b.getReviewDetail().getRating();
                }
            } else {
                break;
            }

        }
        final Long rating = sumOfAllRatings / totalReviewedBookings;
        bookingServicesDetail.getStoreDataModel().setRating(rating.intValue());
        this.mongoTemplate.save(bookingServicesDetail.getStoreDataModel());

        return bookingServicesDetail;
    }

    public BookingServicesDetail getLatestBookingByStoreIdAndCustomerId(final String storeId, final String customerId) {
        if (StringUtils.isEmpty(storeId) || StringUtils.isEmpty(customerId))
            return null;

        final BookingServicesDetail bookingServicesDetail = this.mongoTemplate.findOne(
                getQueryForBookingsByLatestDate(storeId, customerId), BookingServicesDetail.class);
        return (bookingServicesDetail != null) ? bookingServicesDetail : null;
    }

    public List<BookingServicesDetail> getPaginatedCustomerBookingsForListBookingPage(
            final CustomerListBookingPageRequestDataModel customerListBookingPageRequestDataModel) {

        Preconditions.checkArgument(!StringUtils.isEmpty(customerListBookingPageRequestDataModel.getCustomerId()),
                "Customer Id can not be null");

        final Criteria criteria = Criteria.where("customerDataModel.id").is(
                customerListBookingPageRequestDataModel.getCustomerId());

        String currentDateDBString = DateTimeUtility.getCurrentDateAsDBDateString();
        switch (customerListBookingPageRequestDataModel.getBookingTimelineTypeEnum()) {

        case UPCOMING:
            criteria.and("proposedStartTime.date").gt(Long.valueOf(currentDateDBString));
            break;
        case PAST:
            criteria.and("proposedStartTime.date").lt(Long.valueOf(currentDateDBString));
            break;
        default:
            // show todays by default
            criteria.and("proposedStartTime.date").is(Long.valueOf(currentDateDBString));
            break;
        }
        Pagination pagination = null;
        if (null == customerListBookingPageRequestDataModel.getPagination()) {
            System.out.println("NULL PAGINATION");
            pagination = new Pagination();
            customerListBookingPageRequestDataModel.setPagination(pagination);
        } else {
            pagination = customerListBookingPageRequestDataModel.getPagination();
        }

        Query query = new Query(criteria);
        setPaginationInBookingDetailsQuery(pagination, query);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "proposedStartTime.date")));
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "proposedStartTime.timeSlot")));
        System.out.println(query);
        return this.mongoTemplate.find(query, BookingServicesDetail.class);
    }

    private Query getQueryForBookingsByLatestDate(final String storeId, final String customerId) {
        final Criteria criteria = Criteria.where("storeDataModel.id").is(storeId).and("customerDataModel.id")
                .is(customerId).and("reviewed").is(false);
        final Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "proposedStartTime.date"));
        return query;
    }
}
