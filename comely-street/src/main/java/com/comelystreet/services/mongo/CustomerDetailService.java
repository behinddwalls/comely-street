package com.comelystreet.services.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.comelystreet.dao.mongodb.CustomerDetailRepo;
import com.comelystreet.dao.mongodb.model.CustomerDetail;
import com.comelystreet.mongodb.mapper.CustomerDetailMapper;
import com.comelystreet.mongodb.types.CustomerDataModel;
import com.comelystreet.mongodb.types.CustomerRegisterDataModel;
import com.comelystreet.mongodb.types.SigninDataModel;

/**
 * @author #rustic
 *
 */
@Service
public class CustomerDetailService {

    @Autowired
    private CustomerDetailRepo customerDetailRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomerDetailMapper customerDetailMapper;

    public CustomerDetail registerNewCustomer(final CustomerRegisterDataModel customerRegisterDataModel) {

        CustomerDetail customerDetail = new CustomerDetail();
        customerDetail.setFullName(customerRegisterDataModel.getFirstName() + " "
                + customerRegisterDataModel.getLastName());
        customerDetail.setMobileNumber(customerRegisterDataModel.getMobileNumber());
        customerDetail.setEmailId(customerRegisterDataModel.getEmailId());

        final String encodedPassword = this.bCryptPasswordEncoder.encode(customerRegisterDataModel.getPassword());
        customerDetail.setPassword(encodedPassword);
        return this.customerDetailRepo.save(customerDetail);
    }

    public CustomerDetail signInCustomer(final SigninDataModel signinDataModel) {
        final CustomerDetail customerDetail = this.customerDetailRepo.findOneByEmailId(signinDataModel.getEmailId());
        if (null != customerDetail
                && this.bCryptPasswordEncoder.matches(signinDataModel.getPassword(), customerDetail.getPassword())) {
            return customerDetail;
        }
        return null;
    }

    public CustomerDetail getCustomerDetailById(final String customerId) {
        return this.customerDetailRepo.findOneById(customerId);
    }

    public CustomerDetail saveCustomerProfile(final CustomerDataModel customerDataModel) {
        final CustomerDetail customerDetail = this.customerDetailRepo.findOneById(customerDataModel.getId());
        this.customerDetailMapper.getEntityForProfileSaveFromModel(customerDetail, customerDataModel);
        this.customerDetailRepo.save(customerDetail);
        return customerDetail;
    }
}
