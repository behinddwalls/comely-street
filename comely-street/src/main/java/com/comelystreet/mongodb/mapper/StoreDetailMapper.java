package com.comelystreet.mongodb.mapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comelystreet.dao.mongodb.model.StoreDetail;
import com.comelystreet.mongodb.types.StoreDataModel;
import com.comelystreet.services.mongo.StoreDetailService;

@Component
public class StoreDetailMapper {

    @Autowired
    private StoreDetailService storeDetailService;

    public StoreDetail getEntityFromModel(final StoreDataModel storeDataModel) {

        StoreDetail storeDetail = null;
        if (!StringUtils.isEmpty(storeDataModel.getId())) {
            storeDetail = this.storeDetailService.findByStoreId(storeDataModel.getId());
        } else {
            storeDetail = new StoreDetail();
        }
        storeDetail.setName(storeDataModel.getName());
        storeDetail.setAddress(storeDataModel.getAddress());
        storeDetail.setZipCode(storeDataModel.getZipCode());
        storeDetail.setCity(storeDataModel.getCity());
        storeDetail.setArea(storeDataModel.getArea());
        storeDetail.setStoreType(storeDataModel.getStoreType());
        storeDetail.setNumberOfEmployees(storeDataModel.getNumberOfEmployees());
        storeDetail.setNumberOfDaysAllowedForAdvancedBooking(storeDataModel.getNumberOfDaysAllowedForAdvancedBooking());
        storeDetail.setMinimumTimeRequiredForService(storeDataModel.getMinimumTimeRequiredForService());
        storeDetail.setOperationalTimeDetail(storeDataModel.getOperationalTimeDetail());
        storeDetail.setServiceItemDetails(storeDataModel.getServiceItemDetails());
        storeDetail.setVerified(storeDataModel.isVerified());
        storeDetail.setEmailId(storeDataModel.getEmailId());
        storeDetail.setMobileNumber(storeDataModel.getMobileNumber());
        storeDetail.setOtherPhoneNumbers(storeDataModel.getOtherPhoneNumbers());
        if ((storeDetail.getRating() == 0 || storeDetail.getRating() != 0) && storeDataModel.getRating() != 0) {
            storeDetail.setRating(storeDataModel.getRating());
        }
        storeDetail.setStoreFacilitiesDetail(storeDataModel.getStoreFacilitiesDetail());
        return storeDetail;

    }

    public StoreDataModel getModelFromEntity(final StoreDetail storeDetail) {

        final StoreDataModel storeDataModel = new StoreDataModel(storeDetail.getId(), storeDetail.getName(),
                storeDetail.getAddress(), storeDetail.getZipCode(), storeDetail.getCity(), storeDetail.getArea(),
                storeDetail.getStoreType(), storeDetail.getNumberOfEmployees(),
                storeDetail.getNumberOfDaysAllowedForAdvancedBooking(), storeDetail.getMinimumTimeRequiredForService(),
                storeDetail.getOperationalTimeDetail(), storeDetail.getServiceItemDetails(), storeDetail.isVerified(),
                storeDetail.getEmailId(), storeDetail.getMobileNumber(), storeDetail.getOtherPhoneNumbers(),
                storeDetail.getRating(), storeDetail.getStoreFacilitiesDetail());

        return storeDataModel;
    }

    public List<StoreDetail> getEntitiesFromModels(@NotNull final List<StoreDataModel> storeDataModels) {
        return storeDataModels.stream().map(x -> getEntityFromModel(x)).collect(Collectors.toList());
    }

    public List<StoreDataModel> getModelsFromEntities(@NotNull final List<StoreDetail> storeDetails) {
        return storeDetails.stream().map(x -> getModelFromEntity(x)).collect(Collectors.toList());
    }
}
