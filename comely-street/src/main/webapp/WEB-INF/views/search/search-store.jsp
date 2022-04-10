<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>
<!DOCTYPE html>
<title>ComelyStreet - Search Stores</title>
<jsp:include page="../common/header.jsp"></jsp:include>

<style>
.search-form-row {
	background-color: #fafafa;
}

.search-result {
	border-top: 1px solid #e6e6e6;
}

.search-result .search-filters {
	/* 	border-right: 1px solid #e6e6e6; */
	
}

.search-result .search-result-items p {
	margin: 5px 0;
}

.search-result .search-filters-close {
	z-index: 5;
	margin-bottom: -20px;
}

.search-result .search-filter-icon {
	border-bottom: 1px solid #e6e6e6;
	padding-bottom: 10px !important;
}

.search-result .search-item {
	border-bottom: 1px solid #e6e6e6;
	overflow: auto;
}

.search-result .search-item a {
	text-decoration: none;
}

.search-result .search-item .search-item-head a {
	color: #000 !important;
}

.search-item-book-button {
	padding: 30px 0px;
	/* 	padding-right: 50px; */
}

.search-item-book-button a {
	min-width: 150px;
}

.search-item-book-button-xs {
	padding-bottom: 30px;
	padding-top: 10px;
	width: 100%;
}

.search-result-item-head {
	margin-top: 10px;
	padding-bottom: 10px;
	padding-left: 0px;
	border-bottom: 1px solid #e6e6e6;
}
</style>
<section class="site-conatiner">
	<div class="container-fluid search-container">
		<div class="row search-form-row">
			<jsp:include page="global-search-form.jsp"></jsp:include>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row search-result">
			<div class="col-sm-12 hidden-sm hidden-md hidden-lg search-filter-icon">
				<br /> <i class="fa fa-filter fa-x"></i>
			</div>
			<div class="pull-right col-sm-12 search-filters-close hidden">
				<br /> <i class="fa fa-close fa-x"></i>
			</div>
			<div class="col-sm-4 col-md-3 col-xs-12 search-filters hidden-xs">
				<jsp:include page="filter-and-search-result.jsp"></jsp:include>
			</div>
			<div class="col-sm-8 col-md-9 col-xs-12 search-result-items">

				<!-- New Results -->
				<c:choose>
					<c:when test="${not empty clientDataModels}">
						<div class="search-result-item-head col-sm-12">Found ${searchRequestDataModel.pagination.totalCount} store(s)</div>
						<div class="clearfix"></div>
						<c:forEach items="${clientDataModels}" var="clientDataModel">
							<div class="search-item">
								<div class="col-sm-8 col-xs-12">
									<h4 class="search-item-head">
										<a href="${contextPath}/search/store?id=${clientDataModel.id}">${clientDataModel.name}</a>
									</h4>
									<p>
										<jsp:include page="../common/star-rating-view.jsp">
											<jsp:param value="${clientDataModel.rating}" name="rating" />
										</jsp:include>
									</p>
									<p>
										<i class="fa fa-map-marker"></i> <span>${clientDataModel.address},</span><span> ${clientDataModel.area},</span><span>
											${clientDataModel.city} -</span><span> ${clientDataModel.zipCode}</span>
									</p>
									<p>
										<i class="fa fa-phone"></i> <a href="tel:+91${clientDataModel.mobileNumber}"> +91 ${clientDataModel.mobileNumber}</a>
									</p>
									<p class="col-sm-5  padding-left-0">
										<jsp:include page="../common/store-type-view.jsp">
											<jsp:param value="${clientDataModel.storeType.type}" name="storeTypeValue" />
											<jsp:param value="${clientDataModel.storeType}" name="storeType" />
										</jsp:include>
									</p>
									<p class="col-sm-7 padding-left-0">
										<jsp:include page="../common/store-facilities.jsp">
											<jsp:param value="${clientDataModel.storeFacilitiesDetail.tvAvailable}" name="tvAvailable" />
											<jsp:param value="${clientDataModel.storeFacilitiesDetail.acAvailable}" name="acAvailable" />
											<jsp:param value="${clientDataModel.storeFacilitiesDetail.parkingAvailable}" name="parkingAvailable" />
											<jsp:param value="${clientDataModel.storeFacilitiesDetail.wifiAvailable}" name="wifiAvailable" />
											<jsp:param value="${clientDataModel.storeFacilitiesDetail.acceptsCreditCards}" name="acceptsCreditCards" />
										</jsp:include>
									</p>


								</div>
								<div class="col-sm-4 col-xs-12 search-item-book-button hidden-xs">
									<a href="${contextPath}/search/store/signin_intertitial?nextUrl=${storeUrlPrefix}${clientDataModel.id}${searchUrl}"
										class="btn btn-search pull-right">Book</a>
								</div>
								<div class="col-xs-12 search-item-book-button-xs hidden-md hidden-lg hidden-sm">
									<a href="${contextPath}/search/store/signin_intertitial?nextUrl=${storeUrlPrefix}${clientDataModel.id}${searchUrl}"
										class="col-xs-12 btn btn-search pull-right">Book</a>
								</div>
							</div>
						</c:forEach>
						<br />
						<jsp:include page="../common/pagination.jsp">
							<jsp:param name="showNext" value="${searchRequestDataModel.pagination.showNextButton}" />
							<jsp:param name="showPrev" value="${searchRequestDataModel.pagination.showPrevButton}" />
							<jsp:param name="paginationSelectorPrefix" value=".filter-pagination" />
							<jsp:param name="actionHandler" value="new searchStorePageJS().searchFormSubmit" />
							<jsp:param name="actionHandlerParam" value="" />
						</jsp:include>
					</c:when>
					<c:otherwise>
						<h4>No Result found. Please update your search criteria.</h4>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</section>

<jsp:include page="../common/footer.jsp"></jsp:include>

<script type="text/javascript">
    $(document).ready(function() {

        $(".search-filter-icon").on("click", function(e) {
            $(".search-filters").removeClass("hidden-xs");
            $(".search-filter-icon").addClass("hidden-xs");
            $(".search-filters-close").removeClass("hidden");
        });

        $(".search-filters-close i").on("click", function(e) {
            $(".search-filters").addClass("hidden-xs");
            $(".search-filter-icon").removeClass("hidden-xs");
            $(".search-filters-close").addClass("hidden");
        });

    });
    var searchStorePageJS = function() {
        var searchFormSubmit = function() {
            $('form.global-search-form.filter-search-form').submit();
        }
        return {
            searchFormSubmit : searchFormSubmit
        }
    };
</script>