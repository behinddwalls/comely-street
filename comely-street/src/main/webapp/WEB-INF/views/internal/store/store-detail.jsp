<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="../../common/header.jsp"></jsp:include>

<style>
.service-item {
	border: 1px solid gray;
	overflow: auto;
	padding: 2px;
}
</style>

<section class="site-conatiner">
	<div class="container-fluid">
		<div class="col-sm-12">
			<h1>${storeDetail.name}</h1>
			<hr />
			<form:form commandName="storeDetail">

				<form:hidden path="id" />
				<div class="form-group col-sm-6">
					<label>Email Id</label>
					<form:input path="emailId" class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label>Mobile Number</label>
					<form:input path="mobileNumber" class="form-control" />
				</div>

				<div class="form-group col-sm-6">
					<label>Other Phone</label>
					<form:input path="otherPhoneNumbers" class="form-control" />
				</div>

				<div class="form-group col-sm-6">
					<label>Name</label>
					<form:input path="name" class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label>City</label>
					<form:select path="city" class="form-control">
						<form:option value=""></form:option>
						<c:forEach items="${siteAvailabilityAreas}" var="siteAvailabilityArea">
							<form:option value="${siteAvailabilityArea.city}">${siteAvailabilityArea.city}</form:option>
						</c:forEach>

					</form:select>
				</div>

				<div class="form-group col-sm-6">
					<label>Area</label>
					<form:select path="area" class="form-control">
						<form:option value=""></form:option>
						<c:forEach items="${siteAvailabilityAreas}" var="siteAvailabilityArea">
							<c:if test="${siteAvailabilityArea.city eq storeDetail.city}">
								<c:forEach items="${siteAvailabilityArea.areas}" var="area">
									<form:option value="${area}">${area}</form:option>
								</c:forEach>
							</c:if>
						</c:forEach>

					</form:select>
				</div>
				<div class="form-group col-sm-6">
					<label>Address</label>
					<form:input path="address" class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label>Zipcode</label>
					<form:input path="zipCode" class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label>StoreType</label>
					<form:select path="storeType" class="form-control">
						<form:option value=""></form:option>
						<form:option value="MALE">Male</form:option>
						<form:option value="FEMALE">Female</form:option>
						<form:option value="UNISEX">Unisex</form:option>
					</form:select>
				</div>

				<div class="form-group col-sm-6">
					<label>NumberOfEmployees</label>
					<form:input path="numberOfEmployees" class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label>NumberOfDaysAllowedForAdvancedBooking</label>
					<form:input path="numberOfDaysAllowedForAdvancedBooking" class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label>Is Verified?</label>
					<form:select path="verified" class="form-control">
						<form:option value="true">True</form:option>
						<form:option value="false">False</form:option>
					</form:select>
				</div>
				<div class="form-group col-sm-6">
					<label>Rating</label>
					<form:select path="rating" class="form-control">
						<form:option value="0">0</form:option>
						<form:option value="1">1</form:option>
						<form:option value="2">2</form:option>
						<form:option value="3">3</form:option>
						<form:option value="4">4</form:option>
						<form:option value="5">5</form:option>
					</form:select>
				</div>
				<div class="clearfix"></div>
				<h4>Facilities Details</h4>
				<div class="form-group col-sm-6">
					<label>Is AC?</label>
					<form:select path="storeFacilitiesDetail.acAvailable" class="form-control">
						<form:option value="true">True</form:option>
						<form:option value="false">False</form:option>
					</form:select>
				</div>
				<div class="form-group col-sm-6">
					<label>Is Parking?</label>
					<form:select path="storeFacilitiesDetail.parkingAvailable" class="form-control">
						<form:option value="true">True</form:option>
						<form:option value="false">False</form:option>
					</form:select>
				</div>
				<div class="form-group col-sm-6">
					<label>Does accepts Credit card ?</label>
					<form:select path="storeFacilitiesDetail.acceptsCreditCards" class="form-control">
						<form:option value="true">True</form:option>
						<form:option value="false">False</form:option>
					</form:select>
				</div>
				<div class="form-group col-sm-6">
					<label>Is TV Available ?</label>
					<form:select path="storeFacilitiesDetail.tvAvailable" class="form-control">
						<form:option value="true">True</form:option>
						<form:option value="false">False</form:option>
					</form:select>
				</div>
				<div class="form-group col-sm-6">
					<label>Is Wifi Available ?</label>
					<form:select path="storeFacilitiesDetail.wifiAvailable" class="form-control">
						<form:option value="true">True</form:option>
						<form:option value="false">False</form:option>
					</form:select>
				</div>

				<div class="clearfix"></div>
				<h4>Operation Details</h4>
				<div class="form-group col-sm-6">
					<label>Store Open Days</label>
					<form:input path="operationalTimeDetail.openDays" class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label>Store Open Time</label>
					<form:input path="operationalTimeDetail.openTime" class="form-control" />
				</div>

				<div class="form-group col-sm-6">
					<label>Store Close Time</label>
					<form:input path="operationalTimeDetail.closeTime" class="form-control" />
				</div>

				<div class="form-group col-sm-6">
					<label>Break Start Time</label>
					<form:input path="operationalTimeDetail.breakTimes[0].startTime" class="form-control" />
				</div>

				<div class="form-group col-sm-6">
					<label>Break End Time</label>
					<form:input path="operationalTimeDetail.breakTimes[0].endTime" class="form-control" />
				</div>
				<div class="clearfix"></div>
				<h4>Services Details</h4>
				<div class="service-items">
					<c:forEach items="${storeDetail.serviceItemDetails}" var="serviceItem" varStatus="item">
						<c:if test="${serviceItem.active eq true }">
							<div class="service-item">
								<div class="form-group col-sm-3">
									<label>Name ${item.index}</label>
									<form:hidden path="serviceItemDetails[${item.index}].id" />
									<form:input path="serviceItemDetails[${item.index}].name" class="form-control" />
								</div>
								<div class="form-group col-sm-2">
									<label>Category</label>
									<form:select path="serviceItemDetails[${item.index}].serviceCategory.id" class="form-control">
										<form:option value=""></form:option>
										<c:forEach items="${serviceCategories}" var="serviceCategory">
											<form:option value="${serviceCategory.id}">${serviceCategory.serviceCategoryName}</form:option>
										</c:forEach>
									</form:select>

								</div>
								<div class="form-group col-sm-2">
									<label>Price</label>
									<form:input path="serviceItemDetails[${item.index}].price" class="form-control" />
								</div>
								<div class="form-group col-sm-1">
									<label>Currency</label>
									<form:select path="serviceItemDetails[${item.index}].currency" class="form-control">
										<form:option value=""></form:option>
										<form:option value="INR">INR</form:option>
									</form:select>
								</div>
								<div class="form-group col-sm-1">
									<label>Time</label>
									<form:input path="serviceItemDetails[${item.index}].time" class="form-control" />
								</div>
								<div class="form-group col-sm-2">
									<label>Time Unit</label>
									<form:select path="serviceItemDetails[${item.index}].timeUnit" class="form-control">
										<form:option value=""></form:option>
										<form:option value="MINUTES">Minutes</form:option>
									</form:select>
								</div>
								<div class="form-group col-sm-1">
									<label>Delete</label>
									<button type="button" class="btn btn-danger form-control col-sm-6 delete-service-item">
										<i class="fa fa-trash fa-2x"></i>
									</button>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<div class="clearfix"></div>
				<br />
				<div class="form-group col-sm-1">
					<button type="button" class="btn btn-info form-control col-sm-6 add-service-item">
						<i class="fa fa-plus fa-2x"></i>
					</button>
				</div>
				<div class="clearfix"></div>
				<div class="form-group col-sm-6 pull-right">
					<button type="submit" class="btn btn-primary form-control ">
						<i class="fa fa-save fa-x"></i> Save Store Details
					</button>
				</div>


			</form:form>
		</div>
	</div>
</section>

<jsp:include page="../../common/footer.jsp"></jsp:include>


<script type="text/javascript">
    var serviceCategories = [];

    var serviceItemsCurrentLength = parseInt("${fn:length(storeDetail.serviceItemDetails)}");
    //     alert(serviceItemsCurrentLength)
    $(document).ready(function() {
        $.ajax({
            url : "${contextPath}/internal/servicecategories",
            method : "GET",
            data : {},
            dataType : "JSON",
            contentType : "application/json"
        }).fail(function(error) {
            alert("Failed to get Service categories")
        }).done(function(result) {
            console.log(result);
            //             $.each(result, function(index, item) {
            //                 serviceCategories[item.id] = item;
            //             });
            serviceCategories = result;
        }).always(function(result) {
            //always
        });

    });

    $('.add-service-item').on('click', function(e) {
        var serviceHtmlEntity = getInternalServiceItemHtmlEntity(serviceCategories, ++serviceItemsCurrentLength);
        $('.service-items').append(serviceHtmlEntity);
    });
    $('.service-items').on('click', '.remove-service-item', function(e) {
        $(this).closest('.service-item').remove();
    });
    $('.service-items').on('click', '.delete-service-item', function(e) {
        $(this).closest('.service-item').remove();
    });
</script>
