<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="../common/head.jsp"></jsp:include>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>

<section class="site-conatiner">
	<div class="container-fluid">
		<div
			class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-3 ">
			<c:if test="${bookingDetail ne null}">
				<c:if test="${bookingDetail.bookingStatus ne 'BOOKED'}">
					<h4>Booking failed. Please contact us at comely@gmail.com.</h4>
				</c:if>
				<c:if test="${bookingDetail.bookingStatus eq 'BOOKED'}">
					<br/>
					<label>Your booking is confirmed.</label> <br/>
					<label>Date:</label> ${bookingDetail.bookingDate} <br/>
					<label>Time:</label> ${bookingDetail.bookingTimeSlot} Hrs <br/>
					<label>Location:</label> ${bookingDetail.storeName}, ${bookingDetail.storeAddress}, ${bookingDetail.storeArea} <br/>
					<label>Contact number:</label> ${bookingDetail.storeMobileNumber} ${bookingDetail.storeOtherContactNumbers} <br/>
					<label>Booked Services: </label> <br />
					<c:forEach items="${bookingDetail.selectedServices}"
						var="serviceItem" varStatus="serviceItemStatus">
						<div class="col-xs-12 booked-service-item">
							<span class="pull-left">${serviceItem.name} </span> <span
								class="pull-right">Rs. ${serviceItem.price},
								${serviceItem.time} ${fn:toLowerCase(serviceItem.timeUnit)}</span>
						</div>
					</c:forEach>
					<label>Total Price: INR ${bookingDetail.totalPrice}</label>
				</c:if>
			</c:if>
			<c:if test="${bookingDetail eq null}">
				<h4>Booking failed. Please contact us at comely@gmail.com</h4>
			</c:if>
		</div>
	</div>
</section>
