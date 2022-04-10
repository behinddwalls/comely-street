package com.comelystreet.controller.search;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.comelystreet.dao.mongodb.model.BookingServicesDetail;
import com.comelystreet.dao.mongodb.model.StoreDetail;
import com.comelystreet.mongodb.mapper.ReviewDetailMapper;
import com.comelystreet.mongodb.mapper.StoreDetailMapper;
import com.comelystreet.mongodb.types.ReviewInputDataModel;
import com.comelystreet.mongodb.types.StoreDataModel;
import com.comelystreet.mongodb.types.StoreSearchRequestDataModel;
import com.comelystreet.services.mongo.BookingDetailService;
import com.comelystreet.services.mongo.CommonGeneralPurposeService;
import com.comelystreet.services.mongo.StoreDetailService;

@Controller
@RequestMapping("/search")
public class SearchStoreController extends BaseController {

    @Autowired
    private StoreDetailService clientDetailServiceMongo;
    @Autowired
    private CommonGeneralPurposeService commonGeneralPurposeService;
    @Autowired
    private StoreDetailMapper storeDetailMapper;
    @Autowired
    private BookingDetailService bookingDetailService;
    @Autowired
    private ReviewDetailMapper reviewDetailMapper;

    @RequestMapping("stores")
    public ModelAndView searchStore(final Model model,
            @ModelAttribute final StoreSearchRequestDataModel searchRequestDataModel,
            final HttpServletRequest httpServletRequest) {
        System.out.println(searchRequestDataModel);

        model.addAttribute("searchRequestDataModel", searchRequestDataModel);
        List<StoreDetail> clientDataModels = clientDetailServiceMongo
                .findStoresBySearchCriteria(searchRequestDataModel);
        System.out.println(clientDataModels);
        model.addAttribute("clientDataModels", this.storeDetailMapper.getModelsFromEntities(clientDataModels));
        model.addAttribute("siteAvailabilityAreas", this.commonGeneralPurposeService.getSiteAvailabilityAreas());

        model.addAttribute("serviceCategories", this.commonGeneralPurposeService.getServiceCategories());
        try {
            model.addAttribute(
                    "searchUrl",
                    URLEncoder.encode(
                            "&searchUrl= " + httpServletRequest.getRequestURI() + "?"
                                    + httpServletRequest.getQueryString(), "UTF-8"));
            model.addAttribute("storeUrlPrefix", URLEncoder.encode("/search/store?id=", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return getModelAndView("search/search-store", model);
    }

    @RequestMapping("store")
    public ModelAndView searchStoreById(final Model model, @RequestParam final String id,
            @RequestParam(required = false) final String searchUrl) {
        StoreDetail clientDataModel = clientDetailServiceMongo.findByStoreId(id);

        model.addAttribute("clientServicesMap",
                clientDetailServiceMongo.getMapOfServiceCategoryToServiceItemsFromClientDataModel(clientDataModel));
        model.addAttribute("clientDataModel", this.storeDetailMapper.getModelFromEntity(clientDataModel));
        model.addAttribute("searchUrl", searchUrl);
        model.addAttribute("searchRequestDataModel", new StoreSearchRequestDataModel());
        model.addAttribute("siteAvailabilityAreas", this.commonGeneralPurposeService.getSiteAvailabilityAreas());
        return getModelAndView("client/store-page", model);
    }

    @RequestMapping("store/{id}/json")
    public @ResponseBody StoreDataModel searchStoreByIdAsJsonResponse(final Model model,
            @PathVariable("id") final String id) {
        StoreDetail clientDataModel = clientDetailServiceMongo.findByStoreId(id);
        return this.storeDetailMapper.getModelFromEntity(clientDataModel);
    }

    @RequestMapping("store/signin_intertitial")
    public ModelAndView loginInterstialPage(@RequestParam final String nextUrl, final Model model) {

        if (isCustomerAuthenticated()) {
            return getModelAndView("redirect:" + nextUrl, null);
        }
        return getModelAndView("redirect:/signin/customer?returnUrl=" + nextUrl, null);
    }

    @RequestMapping(value = "store/{storeId}/reviews", method = RequestMethod.GET)
    public ModelAndView listAllReviews(@PathVariable final String storeId, final Model model) {
        final List<BookingServicesDetail> bookingServicesDetails = this.bookingDetailService
                .findBookingsByStoreId(storeId);
        model.addAttribute("reviewModels",
                this.reviewDetailMapper.getReviewModelsFromBookingDetailEntities(bookingServicesDetails));
        model.addAttribute("bookingDetail",
                this.bookingDetailService.getLatestBookingByStoreIdAndCustomerId(storeId, getCustomerId()));
        model.addAttribute("storeDetail",
                this.storeDetailMapper.getModelFromEntity(this.clientDetailServiceMongo.findByStoreId(storeId)));
        return getModelAndView("client/store-review-page", model);
    }

    @RequestMapping(value = "store/{storeId}/reviews", method = RequestMethod.POST)
    public ModelAndView saveReview(@PathVariable final String storeId,
            @ModelAttribute final ReviewInputDataModel reviewDataModel, final Model model) {
        System.out.println(reviewDataModel);

        this.bookingDetailService.addReview(reviewDataModel, getCustomerId());

        final List<BookingServicesDetail> bookingServicesDetails = this.bookingDetailService
                .findBookingsByStoreId(storeId);
        model.addAttribute("reviewModels",
                this.reviewDetailMapper.getReviewModelsFromBookingDetailEntities(bookingServicesDetails));
        model.addAttribute("bookingDetail",
                this.bookingDetailService.getLatestBookingByStoreIdAndCustomerId(storeId, getCustomerId()));
        model.addAttribute("storeDetail",
                this.storeDetailMapper.getModelFromEntity(this.clientDetailServiceMongo.findByStoreId(storeId)));
        return getModelAndView("redirect:/search/store/" + storeId + "/reviews", model);
    }
}
