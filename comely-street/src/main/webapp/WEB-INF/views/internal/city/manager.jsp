<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>

<jsp:include page="../../common/header.jsp"></jsp:include>

<section class="site-conatiner">
	<div class="container-fluid">
		<div class="col-sm-12">
			<h1>City Manager</h1>
			<hr />
			<h2>Select City</h2>

			<div class="form-group col-sm-12">
				<select name="siteAvailabilityAreaId" id="select-city"
					class="select-city form-control" required="required">
					<option value="">Select City</option>
					<c:forEach items="${siteAvailabilityAreas}"
						var="siteAvailabilityArea">
						<option
							value='{ "id": "${siteAvailabilityArea.id}" , "city" : "${siteAvailabilityArea.city}"}'>${siteAvailabilityArea.city}</option>
					</c:forEach>
				</select>
			</div>


			<h4>Rename city</h4>
			<form class="manage-city-form">
				<div class="form-group col-sm-6">
					<input name="siteAvailabilityAreaId" type="hidden" value="" /> <input
						name="city" id="rename-city" class="form-control rename-city"
						placeholder="Rename City Name" /> <input type="hidden"
						name="action" value="rename" />
				</div>
				<div class="form-group col-sm-3 rename-button">
					<input type="button" class="form-control btn btn-warning"
						value="Rename City" />
				</div>
				<div class="form-group col-sm-3 deactivate-button">
					<input type="button" class="form-control btn btn-danger"
						value="Deactivate City" />
				</div>
			</form>

			<div class="clearfix"></div>
			<h2>Add New City</h2>

			<form:form class="search-form"
				action="${contextPath}/internal/city/manager">
				<div class="form-group col-sm-6">
					<input name="city" id="city-name" class="form-control"
						placeholder="Enter New City Name" /> <input type="hidden"
						name="action" value="add" />
				</div>
				<div class="form-group col-sm-6 ">
					<input type="submit" class="form-control btn btn-primary"
						value="Add New City" />
				</div>
			</form:form>

		</div>
	</div>
</section>


<jsp:include page="../../common/footer.jsp"></jsp:include>

<script>
    $('.select-city').on('change', function(e) {
        var siteAvailability = JSON.parse($(this).val());
        $('.manage-city-form input[name="siteAvailabilityAreaId"]').val(siteAvailability.id)
        $('.manage-city-form input[name="city"]').val(siteAvailability.city)
    });
</script>