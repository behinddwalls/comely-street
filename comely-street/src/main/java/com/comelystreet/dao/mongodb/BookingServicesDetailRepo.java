package com.comelystreet.dao.mongodb;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.comelystreet.dao.mongodb.model.BookingServicesDetail;

public interface BookingServicesDetailRepo extends MongoRepository<BookingServicesDetail, Serializable> {

    BookingServicesDetail findById(String id);

    public List<BookingServicesDetail> findByStoreDataModel_Id(final String storeId);

}
