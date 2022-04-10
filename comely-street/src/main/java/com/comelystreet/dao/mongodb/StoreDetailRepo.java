package com.comelystreet.dao.mongodb;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.comelystreet.dao.mongodb.model.StoreDetail;

public interface StoreDetailRepo extends MongoRepository<StoreDetail, Serializable> {

    List<StoreDetail> findByName(String name);

    StoreDetail findById(String id);

    List<StoreDetail> findByCityAndArea(String city, String area);

    public List<StoreDetail> findByCity(final String city);

    public StoreDetail findOneByEmailId(final String emailId);
}
