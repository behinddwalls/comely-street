<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="head.jsp"></jsp:include>


<c:if test="${deviceClassification.deviceRequestEnvironment eq 'APP'}">
	<jsp:include page="app-navbar.jsp"></jsp:include>
</c:if>
<c:if test="${isCustomerAuthenticated and deviceClassification.deviceRequestEnvironment ne 'APP'}">
	<jsp:include page="customer-navbar.jsp"></jsp:include>
</c:if>
<c:if test="${not isCustomerAuthenticated and deviceClassification.deviceRequestEnvironment ne 'APP'}">
	<jsp:include page="navbar.jsp"></jsp:include>
</c:if>