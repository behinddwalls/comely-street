<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="showGlobalSearchForm" value="hidden" />
<c:set var="showGlobalSearchFormView" value="" />

<c:if
	test="${empty searchRequestDataModel.city && empty searchRequestDataModel.area}">
	<c:set var="showGlobalSearchForm" value="" />
	<c:set var="showGlobalSearchFormView" value="hidden" />
</c:if>
<style>
.global-search-form {
	margin-top: 1rem;
}

.global-search-form .search-form-item {
	background-color: rgba(255, 255, 255, 0.82);
}
</style>

<form:form commandName="searchRequestDataModel"
	class="global-search-form ${showGlobalSearchForm}" method="get"
	action="${contextPath}/search/stores">
	<div class="form-group col-sm-5  col-xs-12">
		<form:select path="city" id="search-city"
			class="search-form-item search-city form-control" required="required">
			<form:option value=""> Select City </form:option>
			<c:forEach items="${siteAvailabilityAreas}"
				var="siteAvailabilityArea">
				<form:option value="${siteAvailabilityArea.city}">${siteAvailabilityArea.city}</form:option>
			</c:forEach>
		</form:select>
	</div>
	<div class="form-group col-sm-5   col-xs-12">
		<form:select path="area" id="search-area"
			class="search-form-item search-area form-control">
			<form:option value=""> Select Area </form:option>
		</form:select>
	</div>
	<input type="hidden" name="pagination.offSet" value="0" />
	<input type="hidden" name="pagination.pageSize" value="10" />
	<div class="form-group col-sm-2 col-xs-12 pull-right">
		<button type="submit"
			class="form-control btn btn-search text-uppercase" id="searchButton">
			<i class="fa fa-search"></i> Search
		</button>
	</div>

</form:form>

<div
	class="col-sm-12 margin-top-10 margin-bottom-10 global-search-form-view ${showGlobalSearchFormView}">
	<i class="fa fa-map-marker"></i>
	<c:if test="${not empty searchRequestDataModel.area}">
		${searchRequestDataModel.area},	
	</c:if>
	${searchRequestDataModel.city} - <a href="#">change location</a>
</div>

<script type="text/javascript">
    window.GlobalSearchForm = function() {

        $('.global-search-form-view a').on('click', function() {
            $('.global-search-form').removeClass('hidden');
            $('.global-search-form-view').addClass('hidden');
        });

        var cookieNameForCity = "cs_location_city";

        function populateAreasForCity(selectedCity, selectedAreaIfAny) {
            //populate the areas
            <c:forEach items="${siteAvailabilityAreas}"
                				var="siteAvailabilityArea">
            if ("${siteAvailabilityArea.city}" === selectedCity) {
                <c:forEach items="${siteAvailabilityArea.areas}"
                    				var="area">
                if ("${area}" === selectedAreaIfAny) {
                    $('.global-search-form select.search-area').append('<option selected value="${area}">${area}</option>');
                } else {
                    $('.global-search-form select.search-area').append('<option value="${area}">${area}</option>');
                }
                </c:forEach>
            }
            </c:forEach>
        }

        $('.global-search-form').on("change", "select.search-city", function(e) {
            var city = $(this).val();
            if (city) {
                console.log(city);
                document.cookie = cookieNameForCity + "=" + city + ";path=/";
                populateAreasForCity(city, "")
            }
        });

        var cookieCitySelectedValue = siteJS.getCookie(cookieNameForCity);
        if (cookieCitySelectedValue) {
            $('.global-search-form select.search-city option[value="' + cookieCitySelectedValue + '"]').attr('selected', 'selected');

            if (!$('.global-search-form select.search-area').val()) {
                populateAreasForCity(cookieCitySelectedValue, "${searchRequestDataModel.area}");
            }
        }

    }
    window.addEventListener("load", GlobalSearchForm, false);
</script>