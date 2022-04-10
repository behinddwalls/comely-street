<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<title>ComelyStreet - Store Review Page</title>
<jsp:include page="../common/header.jsp"></jsp:include>

<style>
.back-link {
	margin-top: 20px;
}
</style>

<section class="site-conatiner">
	<div class="container-fluid">
		<div class="col-sm-12 row">
			<div class="pull-right  back-link hidden-xs">
				<a href="${contextPath}/search/store?id=${storeDetail.id}"><i class="fa fa-arrow-left"></i>Go back to store</a>
			</div>
			<div class="pull-left">
				<h3>${storeDetail.name}</h3>
			</div>
			<div class="clearfix"></div>


			<c:set var="bookingId" value="" />
			<c:if test="${null ne bookingDetail}">
				<c:set var="bookingId" value="${bookingDetail.id}" />
			</c:if>
		</div>
		<div class="row">
			<jsp:include page="write-review-form.jsp">
				<jsp:param name="storeId" value="${storeDetail.id}" />
				<jsp:param name="bookingId" value="${bookingId}" />
				<jsp:param name="isCustomerAuthenticated" value="${isCustomerAuthenticated}" />
			</jsp:include>
		</div>
		<div class="clearfix"></div>


		<div class="col-sm-12 row">
			<hr />
			<c:if test="${not empty reviewModels}">
				<h4>All Reviews</h4>
				<c:forEach items="${reviewModels}" var="item">

					<div>
						<b>${item.customerName}</b>
					</div>
					<span><jsp:include page="../common/star-rating-view.jsp">
							<jsp:param value="${item.rating}" name="rating" />
						</jsp:include> </span>
					<div>
						<span>${item.reviewComment}</span>
					</div>
					<hr />
				</c:forEach>
			</c:if>
			<c:if test="${ empty reviewModels}">
				<h5>No Reviews found</h5>
			</c:if>
		</div>

	</div>

</section>
<jsp:include page="../common/footer.jsp"></jsp:include>