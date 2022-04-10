package com.comelystreet.dao.mongodb;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.comelystreet.dao.mongodb.model.CustomerDetail;

@Repository
public interface CustomerDetailRepo extends MongoRepository<CustomerDetail, Serializable> {

    public CustomerDetail findOneByEmailId(final String emailId);

    public CustomerDetail findOneById(final String id);
}
