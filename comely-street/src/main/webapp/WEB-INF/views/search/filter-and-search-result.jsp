<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<style>
.filter-service-items .services-table-container {
	overflow: scroll;
	max-height: 100px;
}

.filter-service-items table td {
	padding: 2px 8px !important;
}
</style>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<form:form commandName="searchRequestDataModel" class="global-search-form filter-search-form" method="get" action="${contextPath}/search/stores">
	<div class="col-xs-12">
		<h5>Filters</h5>
	</div>
	<div class="form-group col-xs-12 hidden-sm hidden-lg hidden-md">
		<button type="submit" class="form-control btn btn-search text-uppercase">Apply</button>
	</div>
	<div class="form-group col-xs-12 hidden">
		<form:select path="city" id="search-city" class="search-form-item search-city form-control" required="required">
			<form:option value=""> Select City </form:option>
			<c:forEach items="${siteAvailabilityAreas}" var="siteAvailabilityArea">
				<form:option value="${siteAvailabilityArea.city}">${siteAvailabilityArea.city}</form:option>
			</c:forEach>
		</form:select>
	</div>
	<div class="form-group  col-xs-12 hidden">
		<form:select path="area" id="search-area" class="search-form-item search-area form-control">
			<form:option value=""> Select Area </form:option>
		</form:select>
	</div>
	<div class="filter-pagination">
		<input type="hidden" name="pagination.offSet" value="${searchRequestDataModel.pagination.offSet}" /> <input type="hidden"
			name="pagination.pageSize" value="${searchRequestDataModel.pagination.pageSize}" /> <input type="hidden" name="pagination.pageAction" value="NEXT" />
	</div>

	<div class="form-group  col-xs-12">
		<label for="service-category">Service Category</label>
		<form:select path="serviceCategory" id="filter-service-category" class="search-form-item form-control">
			<form:option value=""> Select Service category </form:option>
			<c:forEach items="${serviceCategories}" var="serviceCategory">
				<form:option value="${serviceCategory.serviceCategoryName}">${serviceCategory.serviceCategoryName}</form:option>
			</c:forEach>
		</form:select>
	</div>
	<div class="form-group  col-xs-12">
		<label for="service-category">Enter Service</label>
		<form:input id="filter-service-item-free-text" value="" placeholder="Type service name..." path="freeTextServiceItem"
			class="search-form-item form-control" />
	</div>
	<div class="form-group  col-xs-12">
		<label for="store-rating">Rating</label>
		<form:select path="rating" id="store-rating" class="search-form-item form-control">
			<form:option value="0"> Any </form:option>
			<form:option value="1"> Minimum 1 star</form:option>
			<form:option value="2"> Minimum 2 star </form:option>
			<form:option value="3"> Minimum 3 star </form:option>
			<form:option value="4"> Minimum 4 star </form:option>
			<form:option value="5"> 5 star </form:option>
		</form:select>
	</div>
	<div class="form-group  col-xs-12">
		<label for="store-type">Type</label>
		<form:select path="storeType" id="store-type" class="search-form-item form-control">
			<form:option value="NONE"> Any </form:option>
			<form:option value="UNISEX"> Unisex</form:option>
			<form:option value="FEMALE"> Ladies only </form:option>
			<form:option value="MALE"> Gents only </form:option>
		</form:select>
	</div>
	<div class="clearfix"></div>
	<div class="form-group  col-xs-12">
		<label for="facilities" class="pull-left">Filter by : </label>
		<div class="pull-left">
			<jsp:include page="../common/store-facilities.jsp">
				<jsp:param value="filter-facilities-" name="cssClassPrefix" />
				<jsp:param value="${storeFacilitiesDetail.tvAvailable}" name="tvAvailable" />
				<jsp:param value="${storeFacilitiesDetail.tvAvailable}" name="acAvailable" />
				<jsp:param value="${storeFacilitiesDetail.parkingAvailable}" name="parkingAvailable" />
				<jsp:param value="${storeFacilitiesDetail.wifiAvailable}" name="wifiAvailable" />
				<jsp:param value="${storeFacilitiesDetail.acceptsCreditCards}" name="acceptsCreditCards" />
			</jsp:include>
		</div>
	</div>
	<div class="form-group col-xs-12 filter-service-items"></div>
	<div class="form-group col-xs-12">
		<button type="submit" class="form-control btn btn-search text-uppercase">Apply</button>
	</div>
</form:form>


