package com.comelystreet.services.mongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.comelystreet.dao.mongodb.model.ComelyStreetAvailableAreaDetails;
import com.comelystreet.dao.mongodb.model.ServiceCategoryDetail;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class CommonGeneralPurposeService {

    private Logger log = LoggerFactory.getLogger(CommonGeneralPurposeService.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    private Map<String, ServiceCategoryDetail> serviceCategoryMap;

    public List<ComelyStreetAvailableAreaDetails> getSiteAvailabilityAreas() {
        return mongoTemplate.find(new BasicQuery("{}"), ComelyStreetAvailableAreaDetails.class);
    }

    public List<ServiceCategoryDetail> getServiceCategories() {
        return mongoTemplate.find(new BasicQuery("{}"), ServiceCategoryDetail.class);
    }

    public ComelyStreetAvailableAreaDetails addNewCity(final String cityName) {
        final ComelyStreetAvailableAreaDetails areaDetails = new ComelyStreetAvailableAreaDetails();
        areaDetails.setCity(cityName);
        this.mongoTemplate.save(areaDetails);
        return areaDetails;
    }

    public ComelyStreetAvailableAreaDetails renameExistingCity(final String siteAvailabilityAreasId,
            final String cityNewName) {
        ComelyStreetAvailableAreaDetails areaDetails = this.mongoTemplate.findById(siteAvailabilityAreasId,
                ComelyStreetAvailableAreaDetails.class);
        areaDetails.setCity(cityNewName);
        this.mongoTemplate.save(areaDetails);
        return areaDetails;
    }

    public ComelyStreetAvailableAreaDetails deactivateCity(final String siteAvailabilityAreasId) {
        ComelyStreetAvailableAreaDetails areaDetails = this.mongoTemplate.findById(siteAvailabilityAreasId,
                ComelyStreetAvailableAreaDetails.class);
        areaDetails.setActive(false);
        this.mongoTemplate.save(areaDetails);
        return areaDetails;
    }

    public Map<String, ServiceCategoryDetail> getServiceCategoryMapFromDB() {
        if (null == serviceCategoryMap || serviceCategoryMap.isEmpty()) {
            final Map<String, ServiceCategoryDetail> finalMap = Maps.newConcurrentMap();
            this.getServiceCategories().stream().forEach(x -> {
                finalMap.put(x.getId(), x);
            });
            this.serviceCategoryMap = finalMap;
        }
        return serviceCategoryMap;
    }

    public List<String> getServiceCategoriesAsString() {
        final List<String> categories = Lists.newArrayList();
        this.getServiceCategories().forEach(x -> categories.add(x.getServiceCategoryName()));
        return categories;
    }

    public void initializeServiceCategory() {

        List<ServiceCategoryDetail> serviceCategories = mongoTemplate.find(new BasicQuery("{}"),
                ServiceCategoryDetail.class);

        if (serviceCategories == null || serviceCategories.isEmpty()) {

            for (Map.Entry<Integer, String> entry : getServiceCategoryMap().entrySet()) {
                final ServiceCategoryDetail serviceCategory = new ServiceCategoryDetail();
                serviceCategory.setServiceCategoryName(entry.getValue());
                mongoTemplate.save(serviceCategory);
            }

        } else {

            log.debug("Already initialized");
        }

    }

    public void initializeAvailableAreas() {

        List<ComelyStreetAvailableAreaDetails> siteAvailabilityAreas = mongoTemplate.find(new BasicQuery("{}"),
                ComelyStreetAvailableAreaDetails.class);

        if (siteAvailabilityAreas == null || siteAvailabilityAreas.isEmpty()) {

            for (Map.Entry<String, List<String>> entry : getAvailableCityAndAreas().entrySet()) {

                final ComelyStreetAvailableAreaDetails areas = new ComelyStreetAvailableAreaDetails();
                areas.setCity(entry.getKey());
                areas.setAreas(entry.getValue());

                mongoTemplate.save(areas);
            }

        } else {

            log.debug("Already initialized");
        }

    }

    private Map<Integer, String> getServiceCategoryMap() {

        final Map<Integer, String> serviceCategoryMap = new HashMap<Integer, String>() {
            private static final long serialVersionUID = 1L;
            {
                put(1, "Facial");
                put(2, "Body");
                // put(3, "Nails");
                // put(4, "Hair");
                // put(5, "Makeup");
                // put(6, "Bridal");
                // put(7, "Massage");
            }
        };
        return serviceCategoryMap;
    }

    private Map<String, List<String>> getAvailableCityAndAreas() {

        final Map<String, List<String>> map = Maps.newHashMap();

        final List<String> bangaloreAreas = Lists.newArrayList();
        bangaloreAreas.add("Murugeshpalya");
        bangaloreAreas.add("Old Airport Road");
        bangaloreAreas.add("HAL");
        bangaloreAreas.add("Domlur");
        bangaloreAreas.add("Indiranagar");
        map.put("Bangalore", bangaloreAreas);

        return map;
    }
}
