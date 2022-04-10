<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>

<jsp:include page="../common/header.jsp"></jsp:include>

<section class="site-conatiner">
	<div class="container-fluid">
		<div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-3 ">
			<h3>Register</h3>
			<hr />
			<form:form commandName="customerRegisterDataModel" action="${contextPath}/register/customer" method="POST">

				<div class="form-group col-sm-6 ">
					<label class="control-label" for="first-name">First Name</label>
					<form:input path="firstName" class="first-name form-control" required="required" placeholder="Enter your first name" />
					<div class="has-error">
						<form:errors path="firstName" class="help-block" />
					</div>
				</div>
				<div class="form-group col-sm-6">
					<label class="control-label" for="last-name">Last Name</label>
					<form:input path="lastName" class="last-name form-control" placeholder="Enter your last name" />
					<div class="has-error">
						<form:errors path="lastName" class="help-block" />
					</div>
				</div>
				<div class="form-group col-sm-12">
					<label class="control-label" for="mobileNumber">Mobile number</label>
					<form:input path="mobileNumber" class="mobileNumber form-control" required="required" placeholder="Enter your mobile number" />
					<div class="has-error">
						<form:errors path="mobileNumber" class="help-block" />
					</div>
				</div>
				<div class="form-group col-sm-12">
					<label class="control-label" for="email">Email</label>
					<form:input path="emailId" class="email form-control" required="required" placeholder="Enter your emailId" />
					<div class="has-error">
						<form:errors path="emailId" class="help-block" />
					</div>
				</div>
				<div class="form-group col-sm-6">
					<label class="control-label" for="password">Password</label>
					<form:password path="password" class="password form-control" required="required" placeholder="Enter password" />
					<div class="has-error">
						<form:errors path="password" class="help-block" />
					</div>
				</div>
				<div class="form-group col-sm-6">
					<label class="control-label" for="confirm-password">Confirm Password</label>
					<form:password path="verifyPassword" class="confirm-password form-control" required="required" placeholder="confirm your password" />
					<div class="has-error">
						<form:errors path="verifyPassword" class="help-block" />
					</div>
				</div>
				<div class="form-group col-sm-6 col-xs-12 pull-right">
					<button class="form-control btn btn-search" type="submit">Register Your Account</button>
				</div>
			</form:form>

		</div>
	</div>
</section>


<jsp:include page="../common/footer.jsp"></jsp:include>