package com.comelystreet.controller.internal.store;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.comelystreet.controller.BaseController;
import com.comelystreet.dao.mongodb.model.ServiceCategoryDetail;
import com.comelystreet.dao.mongodb.model.ServiceItemDetail;
import com.comelystreet.dao.mongodb.model.StoreDetail;
import com.comelystreet.mongodb.mapper.StoreDetailMapper;
import com.comelystreet.mongodb.types.InternalStoreSearchRequestDataModel;
import com.comelystreet.mongodb.types.StoreDataModel;
import com.comelystreet.services.mongo.CommonGeneralPurposeService;
import com.comelystreet.services.mongo.StoreDetailService;
import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = { "/internal" })
public class InternalSiteManagerController extends BaseController {

    @Autowired
    private StoreDetailService storeDetailService;
    @Autowired
    private CommonGeneralPurposeService commonGeneralPurposeService;

    @Autowired
    private StoreDetailMapper storeDetailMapper;

    @RequestMapping("")
    public ModelAndView internalLandingPage(final Model model) {

        return getModelAndView("internal/index", model);
    }

    @RequestMapping(value = { "store/manager" })
    public ModelAndView storeManagerPage(final Model model) {

        model.addAttribute("siteAvailabilityAreas", this.commonGeneralPurposeService.getSiteAvailabilityAreas());
        model.addAttribute("serviceCategories", this.commonGeneralPurposeService.getServiceCategories());
        model.addAttribute("storeDetails", new ArrayList<StoreDataModel>());
        model.addAttribute("searchRequestDataModel", new InternalStoreSearchRequestDataModel());
        return getModelAndView("internal/store/manager", model);
    }

    @RequestMapping(value = "store/manager", method = RequestMethod.POST)
    public ModelAndView storeManagerSearch(
            @ModelAttribute final InternalStoreSearchRequestDataModel searchRequestDataModel, final Model model) {

        final List<StoreDetail> storeDetails = this.storeDetailService
                .findStoresFromInternalStoreManager(searchRequestDataModel);
        model.addAttribute("siteAvailabilityAreas", this.commonGeneralPurposeService.getSiteAvailabilityAreas());
        model.addAttribute("serviceCategories", this.commonGeneralPurposeService.getServiceCategories());
        model.addAttribute("storeDetails", this.storeDetailMapper.getModelsFromEntities(storeDetails));
        model.addAttribute("searchRequestDataModel", searchRequestDataModel);
        return getModelAndView("internal/store/manager", model);
    }

    @RequestMapping(value = "store/manager/{storeId}")
    public ModelAndView manageStoreById(@PathVariable final String storeId, final Model model) {

        final StoreDetail storeDetail = this.storeDetailService.findByStoreId(storeId);
        model.addAttribute("storeDetail", this.storeDetailMapper.getModelFromEntity(storeDetail));
        model.addAttribute("serviceCategories", this.commonGeneralPurposeService.getServiceCategories());
        model.addAttribute("siteAvailabilityAreas", this.commonGeneralPurposeService.getSiteAvailabilityAreas());
        return getModelAndView("internal/store/store-detail", model);
    }

    @RequestMapping(value = "store/manager/{storeId}", method = RequestMethod.POST)
    public ModelAndView manageStoreByIdSave(@ModelAttribute final StoreDataModel storeDataModel, final Model model) {

        if (storeDataModel.getServiceItemDetails() == null || storeDataModel.getServiceItemDetails().isEmpty()) {
            storeDataModel.setServiceItemDetails(Lists.newArrayList());
        }
        final List<ServiceItemDetail> serviceItemDetails = storeDataModel.getServiceItemDetails().stream()
                .filter(x -> !StringUtils.isEmpty(x.getName())).map(x -> x).collect(Collectors.toList());

        serviceItemDetails.stream().forEach(
                z -> {
                    if (StringUtils.isEmpty(z.getId())) {
                        z.setId(ObjectId.get().toString());
                    }
                    z.setServiceCategory(this.commonGeneralPurposeService.getServiceCategoryMapFromDB().get(
                            z.getServiceCategory().getId()));
                    z.setActive(true);
                });

        // retrieve all the stored serviceItemdetails and check if anything is
        // being removed and mark it inactive

        final List<ServiceItemDetail> storedServiceItemDetails = this.storeDetailService.findByStoreId(
                storeDataModel.getId()).getServiceItemDetails();

        if (storedServiceItemDetails != null) {
            final List<ServiceItemDetail> filteredStoredServiceItemDetails = storedServiceItemDetails.stream()
                    .filter(x -> {
                        boolean exists = false;
                        for (ServiceItemDetail serviceItemDetail : serviceItemDetails) {
                            if (serviceItemDetail.getId().equals(x.getId())) {
                                exists = true;
                            }
                        }
                        return !exists;
                    }).map(x -> x).collect(Collectors.toList());

            filteredStoredServiceItemDetails.forEach(x -> x.setActive(false));
            serviceItemDetails.addAll(filteredStoredServiceItemDetails);
        }

        System.out.println(serviceItemDetails);
        storeDataModel.setServiceItemDetails(serviceItemDetails);
        final StoreDetail storeDetail = this.storeDetailService.saveOrUpdate(this.storeDetailMapper
                .getEntityFromModel(storeDataModel));

        model.addAttribute("storeDetail", this.storeDetailMapper.getModelFromEntity(storeDetail));
        model.addAttribute("serviceCategories", this.commonGeneralPurposeService.getServiceCategories());
        model.addAttribute("siteAvailabilityAreas", this.commonGeneralPurposeService.getSiteAvailabilityAreas());
        return getModelAndView("internal/store/store-detail", model);
    }

    @RequestMapping(value = { "store/manager/{storeId}/password" })
    public ModelAndView managePasswordLandingPage(@PathVariable final String storeId, final Model model) {
        model.addAttribute("storeId", storeId);
        model.addAttribute("password", "");
        return getModelAndView("internal/store/manage-password", model);
    }

    @RequestMapping(value = { "store/manager/{storeId}/password" }, method = RequestMethod.POST)
    public ModelAndView updatePasswordForStore(@PathVariable final String storeId, @RequestParam final String password,
            final Model model) {

        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(storeId)) {
            model.addAttribute("errorMessage", "StoreId/Password can not be empty.");
        }
        try {
            this.storeDetailService.updateStorePassoword(storeId, password);
            model.addAttribute("successMessage", "Password updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Password update failed");
        }
        model.addAttribute("storeId", storeId);
        model.addAttribute("password", "");
        return getModelAndView("internal/store/manage-password", model);
    }

    @RequestMapping("city/manager")
    public ModelAndView cityManagerLandingPage(final Model model) {
        model.addAttribute("siteAvailabilityAreas", this.commonGeneralPurposeService.getSiteAvailabilityAreas());
        return getModelAndView("internal/city/manager", model);
    }

    @RequestMapping(value = "city/manager", method = RequestMethod.POST)
    public @ResponseBody String cityManagerHandler(final String action, final String siteAvailabilityAreaId,
            final String city, final Model model) {
        model.addAttribute("siteAvailabilityAreas", this.commonGeneralPurposeService.getSiteAvailabilityAreas());

        switch (action) {
        case "add":

            break;
        case "rename":

            break;
        case "deactivate":

            break;

        default:
            break;
        }

        return "";
    }

    @RequestMapping("area/manager")
    public ModelAndView areaManagerLandingPage(final Model model) {

        return getModelAndView("internal/area/manager", model);
    }

    @RequestMapping(value = "area/manager", method = RequestMethod.POST)
    public ModelAndView areaManagerHandler(final Model model) {

        return getModelAndView("internal/area/manager", model);
    }

    @RequestMapping("serviceCategory/manager")
    public ModelAndView servicecategoryManagerLandingPage(final Model model) {

        return getModelAndView("internal/serviceCategory/manager", model);
    }

    @RequestMapping(value = "serviceCategory/manager", method = RequestMethod.POST)
    public ModelAndView servicecategoryManagerHandler(final Model model) {

        return getModelAndView("internal/serviceCategory/manager", model);
    }

    @RequestMapping("servicecategories")
    public @ResponseBody List<ServiceCategoryDetail> getServiceCategoryDetails() {
        return this.commonGeneralPurposeService.getServiceCategories();
    }

}
