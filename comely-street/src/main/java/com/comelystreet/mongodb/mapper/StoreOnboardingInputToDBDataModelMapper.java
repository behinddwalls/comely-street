package com.comelystreet.mongodb.mapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comelystreet.dao.mongodb.model.BreakTimeDetail;
import com.comelystreet.dao.mongodb.model.ServiceCategoryDetail;
import com.comelystreet.dao.mongodb.model.ServiceItemDetail;
import com.comelystreet.dao.mongodb.model.StoreDetail;
import com.comelystreet.dao.mongodb.model.StoreOperationalTimeDetail;
import com.comelystreet.mongodb.types.StoreOnboardingInputDataModel;
import com.comelystreet.services.mongo.CommonGeneralPurposeService;
import com.google.common.collect.Lists;

@Component
public class StoreOnboardingInputToDBDataModelMapper {

    @Autowired
    private CommonGeneralPurposeService commonGeneralPurposeService;

    public StoreDetail createDBEntityFromInputModel(final StoreOnboardingInputDataModel clientOnboardingInputDataModel) {

        // prepare Operation time
        final BreakTimeDetail breakTimeDataModel = new BreakTimeDetail(1300, 1400);
        List<BreakTimeDetail> breakTimeDataModels = Lists.newArrayList(breakTimeDataModel);
        StoreOperationalTimeDetail storeOperationalTimeDataModel = new StoreOperationalTimeDetail();
        storeOperationalTimeDataModel.setBreakTimes(breakTimeDataModels);
        storeOperationalTimeDataModel.setOpenTime(900);
        storeOperationalTimeDataModel.setCloseTime(2000);

        StoreDetail storeDataModel = new StoreDetail();
        storeDataModel.setName(clientOnboardingInputDataModel.getStoreName());
        storeDataModel.setCity(clientOnboardingInputDataModel.getCity());
        storeDataModel.setArea(clientOnboardingInputDataModel.getArea());
        storeDataModel.setAddress(clientOnboardingInputDataModel.getAddress());
        storeDataModel.setMobileNumber(clientOnboardingInputDataModel.getContactNumber());
        storeDataModel.setEmailId(clientOnboardingInputDataModel.getEmail());
        storeDataModel.setOperationalTimeDetail(storeOperationalTimeDataModel);
        storeDataModel.setZipCode(clientOnboardingInputDataModel.getZipCode());
        return storeDataModel;
    }

    public List<ServiceItemDetail> createDbEntityforServiceItemDataModel() {

        List<ServiceCategoryDetail> serviceCategories = commonGeneralPurposeService.getServiceCategories();

        final List<ServiceItemDetail> serviceItemDataModels = Lists.newArrayList();

        ServiceItemDetail serv1 = new ServiceItemDetail();
        serv1.setId(ObjectId.get().toString());
        serv1.setCurrency("INR");
        serv1.setPrice(99);
        serv1.setTime(20);
        serv1.setTimeUnit(TimeUnit.MINUTES);
        serv1.setName("Masking");
        serv1.setServiceCategory(serviceCategories.get(0));
        serv1.setActive(true);

        ServiceItemDetail serv2 = new ServiceItemDetail();
        serv2.setId(ObjectId.get().toString());
        serv2.setCurrency("INR");
        serv2.setPrice(99);
        serv2.setTime(20);
        serv2.setTimeUnit(TimeUnit.MINUTES);
        serv2.setName("Threading");
        serv2.setServiceCategory(serviceCategories.get(1));
        serv2.setActive(true);

        serviceItemDataModels.add(serv1);
        serviceItemDataModels.add(serv2);

        return serviceItemDataModels;

    }
}
