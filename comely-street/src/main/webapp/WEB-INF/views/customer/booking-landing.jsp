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
			<div class="col-sm-6 col-sm-offset-3 col-xs-12">
				<h3>My Bookings</h3>
				<hr />
				<div class="">
					<a class="btn btn-search width-100" href="${contextPath}/customer/bookings/todays">Today's Bookings</a> <br /> <br />
				</div>
				<div class="">
					<a class="btn btn-search width-100" href="${contextPath}/customer/bookings/upcoming">Upcoming Bookings</a> <br /> <br />
				</div>
				<div class="">
					<a class="btn btn-search width-100" href="${contextPath}/customer/bookings/past">Past Bookings</a> <br /> <br />
				</div>
			</div>


		</div>
	</div>
</section>

<jsp:include page="../common/footer.jsp"></jsp:include>