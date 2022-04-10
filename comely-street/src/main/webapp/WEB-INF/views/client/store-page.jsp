<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<title>ComelyStreet - Book Apointment</title>
<jsp:include page="../common/header.jsp"></jsp:include>

<style type="text/css">
.modify-search-link {
	margin-top: 20px;
}

.panel-body {
	text-transform: capitalize;
}

#accordion {
	text-transform: capitalize;
}

.accordion-plus-button {
	width: 100%;
	text-align: left;
	padding: 0;
	font-weight: bold;
}

.service-item.checkbox-inline {
	margin: 0 3px;
	font-size: 100px;
	transform: scale(1.3);
}

.search-form-row {
	background-color: #fafafa;
}
</style>

<section class="site-conatiner">
	<div class="container-fluid bookingForm">
		<div style="overflow: auto;" class="row">
			<div class="col-sm-12">
				<div class="pull-right modify-search-link hidden-xs ">
					<c:if test="${not empty searchUrl}">
						<a href="${searchUrl}" class="black"><i class="fa fa-arrow-left"></i> Modify Your Search</a>
					</c:if>
				</div>
				<div>
					<h3>${clientDataModel.name}</h3>
					<p>
						<b><i class="fa fa-map-marker "></i> </b> ${clientDataModel.address},${clientDataModel.area}, ${clientDataModel.city},
						${clientDataModel.zipCode}
					</p>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="col-sm-3">
				<p>
					<a href="${contextPath}/search/store/${clientDataModel.id}/reviews" style="text-decoration: none;"> <jsp:include
							page="../common/star-rating-view.jsp">
							<jsp:param value="${clientDataModel.rating}" name="rating" />
						</jsp:include> <i class="fa fa-pencil-square-o black"></i>
					</a>
				</p>
			</div>
			<div class="col-sm-3">
				<p>
					<jsp:include page="../common/store-facilities.jsp">
						<jsp:param value="${clientDataModel.storeFacilitiesDetail.tvAvailable}" name="tvAvailable" />
						<jsp:param value="${clientDataModel.storeFacilitiesDetail.acAvailable}" name="acAvailable" />
						<jsp:param value="${clientDataModel.storeFacilitiesDetail.parkingAvailable}" name="parkingAvailable" />
						<jsp:param value="${clientDataModel.storeFacilitiesDetail.wifiAvailable}" name="wifiAvailable" />
						<jsp:param value="${clientDataModel.storeFacilitiesDetail.acceptsCreditCards}" name="acceptsCreditCards" />
					</jsp:include>
				</p>
			</div>
			<div class="col-sm-3">
				<p>
					<jsp:include page="../common/store-type-view.jsp">
						<jsp:param value="${clientDataModel.storeType.type}" name="storeTypeValue" />
						<jsp:param value="${clientDataModel.storeType}" name="storeType" />
					</jsp:include>
				</p>
			</div>
			<div class="clearfix"></div>
			<form action="${contextPath}/appointment/bookAppointment" method="post">

				<div class="form-group col-sm-7  services">
					<label for="services">Services and Fees</label>
					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						<c:set var="index" value="0" scope="page" />
						<c:forEach items="${clientServicesMap}" var="clientServicesEntry" varStatus="categoryStatus">
							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="heading${categoryStatus.index}">
									<h4 class="panel-title">
										<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${categoryStatus.index}" aria-expanded="false"
											aria-controls="collapse${categoryStatus.index}" class="collapsed button accordion-plus-button">${clientServicesEntry.key} </a>
									</h4>
								</div>
								<div id="collapse${categoryStatus.index}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${categoryStatus.index}">
									<div class="panel-body padding-0">

										<c:forEach items="${clientServicesEntry.value}" var="serviceItem" varStatus="serviceItemStatus">
											<div class="col-xs-12 booked-service-item">
												<span class="col-xs-10 padding-0">${serviceItem.name} <br /> <span><b>Price</b>: Rs ${serviceItem.price} - <b>Time:</b>
														${serviceItem.time}&nbsp;${fn:toLowerCase(serviceItem.timeUnit)}</span></span> <span class="pull-right col-xs-1 margin-top-10"><input
													type="checkbox" value="${serviceItem.id}" name="selectedServices[${index}]" class="service-item checkbox-inline" /></span>
											</div>

											<c:set var="index" value="${index + 1}" scope="page" />
										</c:forEach>

									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="form-group col-sm-5 selected-services ">
					<label for="selectedService">Selected Services</label> <a href='#showServices' class='show-selected-services hidden'>(Show all Service)</a><a
						href='#showServices' class='hide-selected-services hidden'>(Show less Service)</a><br />
					<p>No Service Selected Yet.</p>
				</div>
				<div class="form-group col-sm-6 booking ">
					<label for="bookingDate">Select Booking date</label> <input type="text" class="form-control" id="bookingDate" name="bookingDate"
						style="background-color: #fff !important;" readonly="readonly" placeholder="01 Oct, 20XX" />
				</div>
				<div class="form-group col-sm-6 timeslot ">
					<label for="bookingTimeSlot">Select Time Slot</label> <select name="bookingTimeSlot" class="form-control" id="bookingTimeSlot">
						<option value="--Select Time Slot--">--Select Time Slot--</option>
					</select>
				</div>
				<c:if test="${not isCustomerAuthenticated}">
					<div class="customer-info">
						<div class="form-group col-sm-12">
							<h4>Your Information</h4>
						</div>
						<div class="form-group col-sm-4">
							<label for="customerName">Enter Your Name</label> <input type="text" class="form-control" id="name" name="name" placeholder="e.g. Amar Singh" />
						</div>
						<div class="form-group col-sm-4">
							<label for="mobileNumber">Mobile Number</label> <input type="text" class="form-control" id="mobileNumber" name="mobileNumber"
								placeholder="e.g. 8090809080" />
						</div>
						<div class="form-group col-sm-4">
							<label for="emailId">Email</label> <input type="text" class="form-control" id="email" name="email" placeholder="comely@gmail.com (Optional)" />
						</div>
					</div>
				</c:if>
				<input type="hidden" name="storeId" value="${clientDataModel.id}" />
				<div class="form-group col-sm-4 col-xs-12 pull-right">
					<br />
					<button type="submit" class="form-control btn btn-search" id="bookSlot">Book Slot</button>
				</div>
			</form>
		</div>
	</div>

	<div class="container-fluid">
		<br />
		<div class="col-sm-12">
			<hr>
		</div>

	</div>
</section>


<jsp:include page="../common/footer.jsp"></jsp:include>

<script type="text/javascript">
    var seperator = "COMELYSTREET";
    var startDate = new Date();
    startDate.setDate(startDate.getDate());

    var endDate = new Date();
    endDate.setDate(endDate.getDate() + 7);

    $('#bookingDate').datepicker({
        format : "dd M, yyyy",
        //orientation : "top right",
        autoclose : true,
        todayHighlight : true,
        startDate : startDate,
        endDate : endDate
    });

    $(document).ready(
            function() {

                var clientDataModelJSON = {};
                //on load initialize client Data Model
                getClientDataModel().done(function(result) {
                    if (typeof (result) === "object") {
                        clientDataModelJSON = result;
                    }
                }).fail(function(result) {
                    console.error("failed to get client data model")
                }).always(function(result) {
                    console.log("Client Data Model Call in Init")
                });

                function getClientDataModel() {
                    // fetch and set cities
                    return $.ajax({
                        url : "${contextPath}/search/store/${clientDataModel.id}/json",
                        method : "POST",
                        data : {},
                        dataType : "JSON"
                    });
                }

                var serviceItemIdToServiceMap = {};
                var serviceItemsCheckBoxList = [];
                $(".services").on(
                        "click",
                        '.service-item',
                        function(e) {
                            console.log("called")

                            if ($.isEmptyObject(clientDataModelJSON)) {
                                console.log("empty")
                                getClientDataModel().done(function(result) {
                                    if (typeof (result) === "object") {
                                        clientDataModelJSON = result;
                                    }
                                }).fail(function(result) {
                                    console.error("failed to get client data model")
                                }).always(function(result) {
                                    console.log("Client Data Model Call in Init")
                                });
                            } else {

                                if ($.isEmptyObject(serviceItemIdToServiceMap)) {
                                    $.each(clientDataModelJSON.serviceItemDetails, function(index, serviceItemDataModel) {
                                        serviceItemIdToServiceMap[serviceItemDataModel.id] = serviceItemDataModel;
                                    });
                                }
                                var serviceItemsCheckBox = $("input[type='checkbox']:checked.service-item");
                                if (!serviceItemsCheckBox || serviceItemsCheckBox.length == 0) {
                                    $(".selected-services p").html("No Item Selected Yet.");
                                    return;
                                }
                                var selectedItemsHtml = "";
                                var totalCost = 0;
                                serviceItemsCheckBoxList = [];
                                var itemIndex = 0;
                                $.each(serviceItemsCheckBox, function(item) {
                                    var serviceItemsCheckBoxValue = $(serviceItemsCheckBox[item]).val();
                                    serviceItemsCheckBoxList.push(serviceItemsCheckBoxValue);
                                    var serviceItem = serviceItemIdToServiceMap[serviceItemsCheckBoxValue];
                                    if (itemIndex > 1) {
                                        selectedItemsHtml = selectedItemsHtml.concat("<li class='hidden'>" + serviceItem.name + " - INR "
                                                + serviceItem.price + " - " + serviceItem.time + " "
                                                + siteJS.capitalize(serviceItem.timeUnit.toLowerCase()) + "</li>");
                                    } else {
                                        selectedItemsHtml = selectedItemsHtml.concat("<li>" + serviceItem.name + " - INR " + serviceItem.price
                                                + " - " + serviceItem.time + " " + siteJS.capitalize(serviceItem.timeUnit.toLowerCase()) + "</li>");
                                    }
                                    totalCost += serviceItem.price;
                                    itemIndex++;
                                });
                                $(".selected-services p").html(selectedItemsHtml);
                                $(".selected-services p").append("<br/><b style='color:blue'> Total Cost: INR " + totalCost + "</b>");
                                $("input#bookingDate").val("");
                                oldSelectedDate = "";
                                $("select#bookingTimeSlot").html("<option value='--Select TimeSlot--'>--Select TimeSlot--</option>");
                                if (itemIndex > 2) {
                                    $('a.show-selected-services').removeClass('hidden');
                                    $('a.hide-selected-services').addClass('hidden');
                                } else {
                                    $('a.show-selected-services').addClass('hidden');
                                    $('a.hide-selected-services').addClass('hidden');
                                }
                            }

                        });

                var oldSelectedDate = "";
                //fetch and set timeslots on selecting date
                $('input#bookingDate').on('change', function(e) {
                    var selectedDate = $(this).val();
                    var date = new Date(selectedDate);
                    //stop if same date
                    if (oldSelectedDate && oldSelectedDate == selectedDate) {
                        return;
                    }
                    oldSelectedDate = selectedDate;
                    console.log("selected Date " + selectedDate);

                    $.ajax({
                        url : "${contextPath}/appointment/getTimeSlotsForStore",
                        method : "POST",
                        data : JSON.stringify(getBookingServicesDataModel(selectedDate, "${clientDataModel.id}")),
                        dataType : "JSON",
                        contentType : "application/json"
                    }).done(function(result) {
                        console.log("success");
                        console.log(result);
                        if (result) {
                            //do something
                            $("select#bookingTimeSlot").html("<option value='--Select Time--'>--Select Time--</option>");
                            $.each(result, function(key, value) {
                                $("select#bookingTimeSlot").append("<option value='"+key+"'>" + value + "</option>");
                            });
                        } else {
                            //do something
                            console.log("do else timslots")
                        }

                    }).fail(function(error) {
                        console.log("error");
                        console.log(error);
                    }).always(function() {
                        console.log("complete");
                    });
                });

                function getBookingServicesDataModel(selectedDate, storeId) {

                    bookingServicesDataModel = {
                        selectedServices : serviceItemsCheckBoxList,
                        bookingDate : selectedDate,
                        storeId : storeId
                    };
                    return bookingServicesDataModel;
                }

                $('.selected-services').on('click', 'a.show-selected-services', function(e) {
                    $('.selected-services p li.hidden').addClass('mark-for-hidden');
                    $('.selected-services p li.hidden').removeClass('hidden');
                    $('a.show-selected-services').addClass('hidden');
                    $('a.hide-selected-services').removeClass('hidden');
                });

                $('.selected-services').on('click', 'a.hide-selected-services', function(e) {
                    $('.selected-services p li.mark-for-hidden').addClass('hidden');
                    $('.selected-services p li.mark-for-hidden').removeClass('mark-for-hidden');
                    $('a.show-selected-services').removeClass('hidden');
                    $('a.hide-selected-services').addClass('hidden');
                });

            });
</script>