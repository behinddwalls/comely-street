<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<title>ComelyStreet - List Your Business</title>
<jsp:include page="../common/header.jsp"></jsp:include>

<section class="site-conatiner">
	<div class="container-fluid">
		<div class="col-sm-12">
			<form:form commandName="clientOnboardingInputDataModel"
				action="${contextPath}/store/register" method="POST"
				class="form-horizontal onboarding-form" role="form">
				<div class="form-group">
					<h3 class="text-center">Fill this information and we will
						contact you shortly</h3>
					<br />
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="clientName">Business
						Name</label>
					<div class="col-sm-8">
						<form:input type="text" class="form-control" id="clientName"
							path="storeName" placeholder="Enter business name" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="city">Select
						City</label>
					<div class="col-sm-8">
						<form:select class="form-control" id="city" path="city">
							<form:option value="">--Select City--</form:option>
							<c:forEach items="${siteAvailabilityAreas}"
								var="siteAvailabilityArea">
								<form:option value="${siteAvailabilityArea.city}"></form:option>
							</c:forEach>

						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="area">Area Name</label>
					<div class="col-sm-8">
						<form:select path="area" class="form-control" id="area">
							<form:option value="">--Select Area--</form:option>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="address">Address</label>
					<div class="col-sm-8">
						<form:input type="text" class="form-control" id="email"
							path="address" placeholder="Enter address" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="zipcode">Zip
						Code</label>
					<div class="col-sm-8">
						<form:input type="text" class="form-control" id="zipcode"
							path="zipCode" placeholder="Enter address" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="contactNumber">Contact
						Number(+91)</label>
					<div class="col-sm-8">
						<form:input type="text" class="form-control" id="contactNumber"
							path="contactNumber" placeholder="Enter contact number" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="email">Email</label>
					<div class="col-sm-8">
						<form:input type="text" class="form-control" id="email"
							path="email" placeholder="Enter email" />
					</div>
				</div>

				<div class="form-group">
					<br />
					<div class="col-sm-6 col-md-4 col-sm-offset-4">
						<button type="submit" class="form-control btn btn-search"
							id="register">Register</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</section>


<jsp:include page="../common/footer.jsp"></jsp:include>



<script type="text/javascript">
    window.onload = function() {

        function populateAreasForCity(selectedCity, selectedAreaIfAny) {
            //populate the areas
            <c:forEach items="${siteAvailabilityAreas}"
                				var="siteAvailabilityArea">
            if ("${siteAvailabilityArea.city}" === selectedCity) {
                <c:forEach items="${siteAvailabilityArea.areas}"
                    				var="area">
                if ("${area}" === selectedAreaIfAny) {
                    $('.onboarding-form select#area').append('<option selected value="${area}">${area}</option>');
                } else {
                    $('.onboarding-form select#area').append('<option value="${area}">${area}</option>');
                }
                </c:forEach>
            }
            </c:forEach>
        }

        $('.onboarding-form').on("change", "select#city", function(e) {
            var city = $(this).val();
            if (city) {
                console.log(city);
                populateAreasForCity(city, "")
            }
        });

    }
</script>
