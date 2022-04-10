<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>

<jsp:include page="../common/header.jsp"></jsp:include>

<section class="site-conatiner">
	<div class="container-fluid">
		<div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-3 ">
			<h3>Sign-in</h3>
			<hr />
			<c:if test="${not empty errorMessage}">
				<div class="has-error col-sm-12">
					<span class="help-block">${errorMessage}</span>
				</div>
			</c:if>
			<form:form commandName="signinDataModel" action="${contextPath}/signin/customer" method="POST">

				<div class="form-group col-sm-12">
					<label class="control-label" for="firstName">Email</label>
					<form:input path="emailId" class="first-name form-control" required="required" placeholder="Enter your emailId" />
					<div class="has-error">
						<form:errors path="emailId" class="help-block" />
					</div>
				</div>
				<div class="form-group col-sm-12">
					<label class="control-label" for="password">Password</label>
					<form:password path="password" class="password form-control" required="required" placeholder="Enter password" />
					<div class="has-error">
						<form:errors path="password" class="help-block" />
					</div>
				</div>
				<form:hidden path="returnUrl" />
				<form:hidden path="searchUrl" />
				<c:if test="${not empty signinDataModel.returnUrl}">
					<div class="form-group col-sm-6 col-xs-12 pull-left">
						<a href="${contextPath}${signinDataModel.returnUrl}&searchUrl=${signinDataModel.searchUrl}">Continue without sign in <i
							class="fa fa-arrow-right"></i>
						</a>
					</div>
				</c:if>

				<div class="form-group col-sm-6 col-xs-12 pull-right">
					<button class="form-control btn btn-search" type="submit">Sign in</button>
				</div>
				<div class="form-group col-sm-6 col-xs-12 pull-right">
					<a href="${contextPath}/register/customer">Register </a>
				</div>
			</form:form>

		</div>
	</div>
</section>


<jsp:include page="../common/footer.jsp"></jsp:include>