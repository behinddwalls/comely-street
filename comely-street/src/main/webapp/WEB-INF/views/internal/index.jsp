<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>

<jsp:include page="../common/header.jsp"></jsp:include>

<section class="site-conatiner">
	<div class="container-fluid">
		<div class="col-sm-12">
			<h1>Internal Landing page</h1>
			<hr />
			<ul>
				<li><a href="${contextPath}/internal/store/manager/">Store
						Manager</a></li>
				<li><a href="${contextPath}/internal/city/manager">City
						Manager</a></li>
				<li><a href="${contextPath}/internal/area/manager">Area
						Manager</a></li>
				<li><a href="${contextPath}/internal/serviceCategory/manager">Service
						Category Manager</a></li>
			</ul>


		</div>
	</div>
</section>


<jsp:include page="../common/footer.jsp"></jsp:include>