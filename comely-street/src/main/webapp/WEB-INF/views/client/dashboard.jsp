<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<title>ComelyStreet - Store View</title>
<jsp:include page="../common/header.jsp"></jsp:include>


<section class="site-conatiner">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<h3>Store Dashboard</h3>
				<hr />
			</div>
			<div>
				<form:form action="${contextPath}/store/manage/dashboard" class="store-booking-list-form">
					<div class="form-group col-sm-6">
						<label for="bookingDate">Select Booking date</label> <input type="text" class="form-control" id="bookingDate" name="startDate"
							value="${startDate}" style="background-color: #fff !important;" readonly="readonly" placeholder="01 Oct, 20XX" /> <input name="storeId"
							value="${storeId}" type="hidden" /> <input type="hidden" name="pagination.offSet"
							value="${storeBookingListPageRequestDataModel.pagination.offSet}" /> <input type="hidden" name="pagination.pageSize"
							value="${storeBookingListPageRequestDataModel.pagination.pageSize}" /> <input type="hidden" name="pagination.pageAction" value="NEXT" />
					</div>
					<div class="form-group col-sm-6">
						<label for=""><br /></label>
						<button type="submit" class="form-control btn btn-search">Search Bookings</button>
					</div>
				</form:form>
			</div>
			<div class="clearfix"></div>
			<div class="panel-group col-sm-12" id="accordion" role="tablist" aria-multiselectable="true">
				<c:if test="${not empty storeBookingList}">
					<c:forEach items="${storeBookingList}" var="bookingDetail" varStatus="bookingStatus">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="heading${bookingStatus.index}">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${bookingStatus.index}" aria-expanded="false"
										aria-controls="collapse${bookingStatus.index}" class="collapsed btn accordion-plus-button"> ${bookingDetail.bookingTimeSlot} -
										${bookingDetail.customerName} - ${bookingDetail.customerMobileNumber} </a>
								</h4>
							</div>
							<div id="collapse${bookingStatus.index}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${bookingStatus.index}">
								<div class="panel-body">
									<label>Email: ${bookingDetail.customerEmail} </label> <br /> <label> Service(s) Booked: </label> <br />
									<c:forEach items="${bookingDetail.selectedServices}" var="serviceItem" varStatus="serviceItemStatus">
										<div class="col-xs-12 booked-service-item">
											<span class="pull-left">${serviceItem.name} </span> <span class="pull-right">Rs. ${serviceItem.price}, ${serviceItem.time}
												${fn:toLowerCase(serviceItem.timeUnit)}</span>
										</div>
<!-- 								        &nbsp;&nbsp; -->
<%-- 								        <span> ${serviceItem.serviceCategory.serviceCategoryName}:${serviceItem.name} - Time: ${serviceItem.time}${serviceItem.timeUnit} - --%>
<%-- 											Price: INR ${serviceItem.price}<br /> --%>
<!-- 										</span> -->
									</c:forEach>
									<label>Total Price: INR ${bookingDetail.totalPrice}</label>
								</div>
							</div>
						</div>
					</c:forEach>
					<jsp:include page="../common/pagination.jsp">
						<jsp:param name="showNext" value="${storeBookingListPageRequestDataModel.pagination.showNextButton}" />
						<jsp:param name="showPrev" value="${storeBookingListPageRequestDataModel.pagination.showPrevButton}" />
						<jsp:param name="paginationSelectorPrefix" value=".store-booking-list-form" />
						<jsp:param name="actionHandler" value="new storeBookingPageJS().queryBookingList" />
						<jsp:param name="actionHandlerParam" value="" />
					</jsp:include>
				</c:if>
				<c:if test="${empty storeBookingList}">
					<h3>No Bookings found.</h3>
				</c:if>
			</div>
		</div>
	</div>
</section>

<jsp:include page="../common/footer.jsp"></jsp:include>

<script type="text/javascript">
    var startDate = new Date();
    startDate.setDate(startDate.getDate());

    var endDate = new Date();
    endDate.setDate(endDate.getDate() + 7);

    $('#bookingDate').datepicker({
        format : "dd M, yyyy",
        orientation : "top right",
        autoclose : true,
        todayHighlight : true
    });

    $('#bookingDate').on('change', function(e) {
        $(".store-booking-list-form input[name='pagination.offSet']").val(0);
        $(".store-booking-list-form input[name='pagination.pageSize']").val(0);
    });

    var storeBookingPageJS = function() {
        var queryBookingList = function() {
            $('form.store-booking-list-form').submit();
        }
        return {
            queryBookingList : queryBookingList
        }
    };
</script>
