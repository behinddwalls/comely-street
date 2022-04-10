<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>


<jsp:include page="../common/header.jsp"></jsp:include>


<section class="site-conatiner">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<div class="col-sm-8 col-sm-offset-2 padding-0">
					<h3 class="pull-left">${bookingPageHeading}&nbsp;Bookings</h3>
					<p class="pull-right margin-top-20 hidden-xs">
						<a href="${contextPath}/customer/bookings" class="black"><i class="fa fa-arrow-left"></i> Back to bookings </a>
					</p>
					<div class="clearfix"></div>
					<br />
				</div>


				<div class="panel-group col-sm-8 col-sm-offset-2 padding-0" id="accordion">

					<c:if test="${not empty customerBookingList}">
						<form:form class="hidden customer-booking-list-form" action="">
							<input type="hidden" name="pagination.offSet" value="${customerListBookingPageRequestDataModel.pagination.offSet}" />
							<input type="hidden" name="pagination.pageSize" value="${customerListBookingPageRequestDataModel.pagination.pageSize}" />
							<input type="hidden" name="pagination.pageAction" value="NEXT" />
						</form:form>
						<c:forEach items="${customerBookingList}" var="customerBooking" varStatus="customerBookingStatus">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion" href="#collapse${customerBookingStatus.index}" class="button width-100"><span>${customerBooking.time}
												Hrs, ${customerBooking.date} - ${customerBooking.storeDataModel.name}, ${customerBooking.storeDataModel.area},
												${customerBooking.storeDataModel.city}</span> </a>
									</h4>
								</div>
								<div id="collapse${customerBookingStatus.index}" class="panel-collapse collapse">
									<div class="panel-body ">
										<p class="">
											<b>Booked Services:</b>
										</p>

										<c:forEach items="${customerBooking.serviceItemDetails}" var="serviceItem">
											<div class="col-xs-12 booked-service-item">
												<span class="pull-left">${serviceItem.name} </span> <span class="pull-right">Rs. ${serviceItem.price}, ${serviceItem.time}
													${fn:toLowerCase(serviceItem.timeUnit)}</span>
											</div>
										</c:forEach>
										<div class="clearfix"></div>
										<br />
										<p>
											<span class=""><b>Total Price : </b> Rs. ${customerBooking.totalPrice}</span>
										</p>
									</div>
								</div>
							</div>
						</c:forEach>
						<jsp:include page="../common/pagination.jsp">
							<jsp:param name="showNext" value="${customerListBookingPageRequestDataModel.pagination.showNextButton}" />
							<jsp:param name="showPrev" value="${customerListBookingPageRequestDataModel.pagination.showPrevButton}" />
							<jsp:param name="paginationSelectorPrefix" value=".customer-booking-list-form" />
							<jsp:param name="actionHandler" value="new customerBookingPageJS().queryBookingList" />
							<jsp:param name="actionHandlerParam" value="" />
						</jsp:include>
					</c:if>
					<c:if test="${empty customerBookingList}">
						<h4>No Bookings found.</h4>
					</c:if>
				</div>
			</div>


		</div>
	</div>
</section>

<jsp:include page="../common/footer.jsp"></jsp:include>

<script type="text/javascript">
    var customerBookingPageJS = function() {
        var queryBookingList = function() {
            $('form.customer-booking-list-form').submit();
        }
        return {
            queryBookingList : queryBookingList
        }
    };
</script>