package com.comelystreet.services.mongo;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.comelystreet.dao.mongodb.StoreDetailRepo;
import com.comelystreet.dao.mongodb.model.ServiceItemDetail;
import com.comelystreet.dao.mongodb.model.StoreDetail;
import com.comelystreet.mongodb.types.InternalStoreSearchRequestDataModel;
import com.comelystreet.mongodb.types.Pagination;
import com.comelystreet.mongodb.types.SigninDataModel;
import com.comelystreet.mongodb.types.StoreSearchRequestDataModel;
import com.comelystreet.mongodb.types.StoreType;
import com.comelystreet.services.util.PaginationUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class StoreDetailService {

    @Autowired
    private StoreDetailRepo clientDataRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public StoreDetail signInStore(final SigninDataModel signinDataModel) {
        final StoreDetail storeDetail = this.clientDataRepo.findOneByEmailId(signinDataModel.getEmailId());
        System.out.println(storeDetail.getPassword());
        System.out
                .println(this.bCryptPasswordEncoder.matches(signinDataModel.getPassword(), storeDetail.getPassword()));
        if (null != storeDetail
                && this.bCryptPasswordEncoder.matches(signinDataModel.getPassword(), storeDetail.getPassword())) {
            return storeDetail;
        }
        return null;
    }

    /**
     * @param clientDataModel
     * @return
     */
    public StoreDetail saveOrUpdate(final StoreDetail clientDataModel) {
        clientDataRepo.save(clientDataModel);
        return clientDataModel;
    }

    /**
     * @param clientName
     * @return
     */
    public List<StoreDetail> findByClientName(final String clientName) {
        return clientDataRepo.findByName(clientName);
    }

    /**
     * @param clientId
     * @return
     */
    public StoreDetail findByStoreId(final String clientId) {
        StoreDetail clientDataModel = clientDataRepo.findById(clientId);
        clientDataModel.setPassword("");
        return clientDataModel;
    }

    /**
     * @param city
     * @param area
     * @return
     */
    public List<StoreDetail> findByCityAndArea(final String city, final String area) {
        return clientDataRepo.findByCityAndArea(city, area);
    }

    /**
     * @param city
     * @return
     */
    public List<StoreDetail> findByCity(final String city) {
        return this.clientDataRepo.findByCity(city);
    }

    /**
     * @param storeSearchRequestDataModel
     * @return
     */
    public List<StoreDetail> findStoresBySearchCriteria(
            @NotNull final StoreSearchRequestDataModel storeSearchRequestDataModel) {

        if (StringUtils.isEmpty(storeSearchRequestDataModel.getCity())) {
            return Lists.newArrayList();
        }
        final Criteria criteriaDefinition = Criteria.where("city").is(storeSearchRequestDataModel.getCity());

        if (!StringUtils.isEmpty(storeSearchRequestDataModel.getArea())) {
            criteriaDefinition.and("area").is(storeSearchRequestDataModel.getArea());
        }

        if (!StringUtils.isEmpty(storeSearchRequestDataModel.getServiceCategory())) {
            criteriaDefinition.and("serviceItemDetails.serviceCategory.serviceCategoryName").regex(
                    storeSearchRequestDataModel.getServiceCategory(), "i");
        }

        if (!StringUtils.isEmpty(storeSearchRequestDataModel.getFreeTextServiceItem())) {
            criteriaDefinition.and("serviceItemDetails.name").regex(
                    storeSearchRequestDataModel.getFreeTextServiceItem(), "i");

        }
        if (null != storeSearchRequestDataModel.getStoreType()
                && !StoreType.NONE.equals(storeSearchRequestDataModel.getStoreType())) {
            criteriaDefinition.and("storeType").is(storeSearchRequestDataModel.getStoreType());
        }
        criteriaDefinition.and("isVerified").is(true);
        criteriaDefinition.and("serviceItemDetails.active").is(true);
        criteriaDefinition.and("rating").gte(storeSearchRequestDataModel.getRating());

        Query query = new Query(criteriaDefinition);
        query.with(new Sort(Sort.Direction.DESC, "rating"));

        Pagination pagination = storeSearchRequestDataModel.getPagination();

        if (pagination == null) {
            System.out.println("Paginatio null");
            pagination = new Pagination();
            storeSearchRequestDataModel.setPagination(pagination);
        }

        long totalCount = this.mongoTemplate.count(query, StoreDetail.class);
        pagination.setTotalCount(totalCount);
        PaginationUtility.getOptimizedPagination(pagination);
        query.skip(pagination.getOffSet());
        query.limit(pagination.getPageSize());

        System.out.println(query.toString());
        System.out.println("Pagination " + pagination);
        return mongoTemplate.find(query, StoreDetail.class);
    }

    /**
     * @param clientDataModel
     * @return
     */
    public Map<String, List<ServiceItemDetail>> getMapOfServiceCategoryToServiceItemsFromClientDataModel(
            @NotNull final StoreDetail clientDataModel) {

        final Map<String, List<ServiceItemDetail>> resultMap = Maps.newHashMap();

        final List<ServiceItemDetail> serviceItemDataModels = clientDataModel.getServiceItemDetails();

        serviceItemDataModels.stream().filter(x -> x.isActive()).forEach(x -> {
            if (resultMap.containsKey(x.getServiceCategory().getServiceCategoryName())) {
                resultMap.get(x.getServiceCategory().getServiceCategoryName()).add(x);
            } else {
                final List<ServiceItemDetail> list = Lists.newArrayList();
                list.add(x);
                resultMap.put(x.getServiceCategory().getServiceCategoryName(), list);
            }
        });

        try {
            System.out.println(mappingJackson2HttpMessageConverter.getObjectMapper().writeValueAsString(resultMap));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resultMap;
    }

    public List<ServiceItemDetail> findServiceItemsByIds(@NotNull final List<String> ids, final String storeId) {
        final StoreDetail storeDetail = mongoTemplate.findById(storeId, StoreDetail.class);
        final List<ServiceItemDetail> serviceItemDetails = storeDetail.getServiceItemDetails();
        System.out.println("<<<<<<<<<<" + serviceItemDetails);
        final List<ServiceItemDetail> finalServiceItemDetails = Lists.newArrayList();
        System.out.println("Before trim<<<<<<<<<<" + ids);
        ids.forEach(x -> x.trim());

        if (null != serviceItemDetails) {
            serviceItemDetails.stream().filter(x -> ids.contains(x.getId()))
                    .forEach(x -> finalServiceItemDetails.add(x));
        }
        System.out.println("<<<<<<<<<<" + finalServiceItemDetails);
        System.out.println("After trim<<<<<<<<<<" + ids);
        return finalServiceItemDetails;

    }

    public List<StoreDetail> findStoresFromInternalStoreManager(
            @NotNull final InternalStoreSearchRequestDataModel requestDataModel) {

        System.out.println(requestDataModel);
        final Criteria criteriaDefinition = Criteria.where("city").is(requestDataModel.getCity());

        if (!StringUtils.isEmpty(requestDataModel.getArea())) {
            criteriaDefinition.and("area").is(requestDataModel.getArea());
        }
        if (!StringUtils.isEmpty(requestDataModel.getStoreName())) {
            criteriaDefinition.and("name").regex(requestDataModel.getStoreName(), "i");
        }
        if (!requestDataModel.isExclude()) {
            criteriaDefinition.and("isVerified").is(requestDataModel.isStoreVerified());
        }

        Query query = new Query(criteriaDefinition);
        return this.mongoTemplate.find(query, StoreDetail.class);
    }

    public void updateStorePassoword(final String storeId, final String password) {
        final StoreDetail storeDetail = this.clientDataRepo.findById(storeId);
        storeDetail.setPassword(this.bCryptPasswordEncoder.encode(password));
        this.clientDataRepo.save(storeDetail);
    }
}
