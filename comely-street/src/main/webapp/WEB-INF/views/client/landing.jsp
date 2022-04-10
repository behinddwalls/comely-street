<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<title>ComelyStreet - Business</title>
<jsp:include page="../common/header.jsp"></jsp:include>

<section class="site-conatiner">
	<div class="container-fluid">
		<br />
		<br />
		<div class="form-group">
			<div class="col-sm-6 col-md-4 col-sm-offset-4">
				<a href="${contextPath}/store/onboard">
					<button type="button" class="form-control btn btn-primary"
						id="register">Register New Business</button>
				</a>
			</div>
		</div>
		<div class="clearfix"></div>
		<br />
		<div class="form-group text-center">or</div>
		<div class="form-group">
			<div class="col-sm-6 col-md-4 col-sm-offset-4">
				<a href="${contextPath}/signin/store">
					<button type="button" class="form-control btn btn-primary"
						id="manage-business">Manage Existing Business</button>
				</a>
			</div>
		</div>
	</div>
</section>


<jsp:include page="../common/footer.jsp"></jsp:include>