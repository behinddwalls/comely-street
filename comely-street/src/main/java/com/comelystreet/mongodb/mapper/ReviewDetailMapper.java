package com.comelystreet.mongodb.mapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.comelystreet.dao.mongodb.model.BookingServicesDetail;
import com.comelystreet.dao.mongodb.model.ReviewDetail;
import com.comelystreet.mongodb.types.ReviewInputDataModel;
import com.comelystreet.mongodb.types.ReviewOutputDataModel;
import com.google.common.collect.Lists;

@Component
public class ReviewDetailMapper {

    public ReviewOutputDataModel getReviewModelFromBookingDetailEntity(final BookingServicesDetail bookingServicesDetail) {
        final ReviewOutputDataModel reviewDataModel = new ReviewOutputDataModel();

        reviewDataModel.setBookingId(bookingServicesDetail.getId());
        if (bookingServicesDetail.getReviewDetail() != null) {
            reviewDataModel.setRating(bookingServicesDetail.getReviewDetail().getRating());
            reviewDataModel.setReviewComment(bookingServicesDetail.getReviewDetail().getReviewComment());
            reviewDataModel.setCustomerName(bookingServicesDetail.getCustomerDataModel().getFullName());
        }
        return reviewDataModel;
    }

    public BookingServicesDetail getBookingDetailEntityFromReviewModel(final ReviewInputDataModel dataModel,
            final BookingServicesDetail bookingServicesDetail) {
        final ReviewDetail reviewDetail = new ReviewDetail();
        reviewDetail.setRating(dataModel.getRating());
        reviewDetail.setReviewComment(dataModel.getReviewComment());
        bookingServicesDetail.setReviewDetail(reviewDetail);
        return bookingServicesDetail;
    }

    public List<ReviewOutputDataModel> getReviewModelsFromBookingDetailEntities(
            @NotNull final List<BookingServicesDetail> bookingServicesDetails) {

        final List<ReviewOutputDataModel> reviewDataModels = Lists.newArrayList();

        bookingServicesDetails.stream().filter(x -> x.isReviewed())
                .forEach(x -> reviewDataModels.add(this.getReviewModelFromBookingDetailEntity(x)));

        return reviewDataModels.stream().filter(x -> x.getRating() > 0).collect(Collectors.toList());
    }
}
