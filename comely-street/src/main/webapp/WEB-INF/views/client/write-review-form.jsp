<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
.review-comment.form-control {
	min-height: 150px !important;
}
</style>

<div class="col-sm-12">
	<c:if test="${not empty param.bookingId && not empty param.storeId && param.isCustomerAuthenticated}">
		<h4>Write a review</h4>
		<form action="${contextPath}/search/store/${param.storeId}/reviews" method="post">
			<input type="hidden" name="storeId" value="${param.storeId}" /> <input type="hidden" name="bookingId" value="${param.bookingId}" />
			<div class="star-rating form-group">
				<div class="star-rating__wrap">
					<input class="star-rating__input" id="star-rating-5" type="radio" name="rating" value="5"> <label
						class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-5" title="5 out of 5 stars"></label> <input class="star-rating__input"
						id="star-rating-4" type="radio" name="rating" value="4"> <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-4"
						title="4 out of 5 stars"></label> <input class="star-rating__input" id="star-rating-3" type="radio" name="rating" value="3"> <label
						class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-3" title="3 out of 5 stars"></label> <input class="star-rating__input"
						id="star-rating-2" type="radio" name="rating" value="2"> <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-2"
						title="2 out of 5 stars"></label> <input class="star-rating__input" id="star-rating-1" type="radio" name="rating" value="1"> <label
						class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-1" title="1 out of 5 stars"></label>
				</div>
			</div>
			<div class="form-group">
				<textarea name="reviewComment" class="review-comment form-control" placeholder="Optional Feedback"></textarea>
			</div>
			<div class="form-group pull-right">
				<button type="submit" class="btn btn-primary">Submit Review</button>
			</div>

		</form>
	</c:if>
	<c:if test="${empty param.bookingId && not empty param.storeId &&  param.isCustomerAuthenticated}">
		<h5>Book appointment to write more review.</h5>
		<p>
			<a href="${contextPath}/search/store?id=${param.storeId}">Go Back to Store</a>
	</c:if>
</div>