package com.comelystreet.dao.mongodb;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.comelystreet.dao.mongodb.model.StoreDailyScheduleDetail;

public interface StoreDailySchedulingDetailRepo extends MongoRepository<StoreDailyScheduleDetail, Serializable> {

    List<StoreDailyScheduleDetail> findByStoreDetail_IdAndScheduleDate(String id, long scheduleDate);

}
