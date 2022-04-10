<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<style type="text/css">
</style>


<jsp:include page="../../common/header.jsp"></jsp:include>

<section class="site-conatiner">
	<div class="container-fluid">
		<div class="col-sm-12">
			<h1>Manage Stores</h1>
			<hr />
			<form:form class="search-form" commandName="searchRequestDataModel" action="${contextPath}/internal/store/manager">
				<div class="form-group col-sm-6">
					<form:select path="city" id="search-city" class="search-form-item search-city form-control" required="required">
						<form:option value="">Select City</form:option>
						<c:forEach items="${siteAvailabilityAreas}" var="siteAvailabilityArea">
							<form:option value="${siteAvailabilityArea.city}">${siteAvailabilityArea.city}</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group col-sm-6">
					<form:select path="area" id="search-area" class="search-form-item search-area form-control">
						<form:option value="">Select Area</form:option>
					</form:select>
				</div>
				<div class="form-group col-sm-6">
					<form:input path="storeName" id="search-store-name" class="search-form-item search-store-name form-control" placeholder="Store Name" />
				</div>
				<div class="form-group col-sm-6">
					<form:select path="storeVerified" id="search-store-verified" class="search-form-item search-store-verified form-control">
						<form:option value="false">Not Verified</form:option>
						<form:option value="true">Verified</form:option>
					</form:select>
				</div>
				<div class="form-group col-sm-6">
					<form:checkbox path="exclude" id="search-store-verified" class="search-form-item search-store-verified " />
					Disable Verified/Not verified?
				</div>
				<div class="form-group col-sm-6 ">
					<input type="submit" class="form-control btn btn-primary" value="Find Store" />
				</div>
			</form:form>
		</div>
		<div class="col-sm-12">
			<c:if test="${not empty storeDetails}">
				<h3>Select store to manage</h3>
				<div>
					<span>Total stores found: ${fn:length(storeDetails)}</span>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered">
						<tr>
							<th>Store Name</th>
							<th>City</th>
							<th>Area</th>
							<th>Actions</th>
						</tr>
						<c:forEach items="${storeDetails}" var="storeDetail">
							<tr>
								<td>${storeDetail.name}</td>
								<td>${storeDetail.city}</td>
								<td>${storeDetail.area}</td>
								<td><c:if test="${storeDetail.verified}">
										<i class="fa fa-check"></i>
									</c:if> <c:if test="${not storeDetail.verified}">
										<i class="fa fa-close"></i>
									</c:if> <a href="${contextPath}/internal/store/manager/${storeDetail.id}">Manage</a> <a
									href="${contextPath}/internal/store/manager/${storeDetail.id}/password">Manage Password</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
			<c:if test="${ empty storeDetails}">
				<h1>No Stores Found</h1>
			</c:if>
		</div>
	</div>
</section>

<jsp:include page="../../common/footer.jsp"></jsp:include>

<script type="text/javascript">
    $('.search-form').on("change", "select.search-city", function(e) {
        var city = $(this).val();
        if (city) {
            console.log(city);
            populateAreasForCity(city, "")
        }
    });

    $(window).ready(function() {
        var city = $('select.search-city').val();
        if (city) {
            console.log(city);
            populateAreasForCity(city, "${searchRequestDataModel.area}")
        }
    });

    function populateAreasForCity(selectedCity, selectedAreaIfAny) {
        //populate the areas
        <c:forEach items="${siteAvailabilityAreas}"
            				var="siteAvailabilityArea">
        if ("${siteAvailabilityArea.city}" === selectedCity) {
            <c:forEach items="${siteAvailabilityArea.areas}"
                				var="area">
            if ("${area}" === selectedAreaIfAny) {
                $('.search-form select.search-area').append('<option selected value="${area}">${area}</option>');
            } else {
                $('.search-form select.search-area').append('<option value="${area}">${area}</option>');
            }
            </c:forEach>
        }
        </c:forEach>
    }
</script>
