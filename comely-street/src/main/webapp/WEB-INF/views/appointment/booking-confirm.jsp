<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>

<jsp:include page="../common/header.jsp"></jsp:include>

<jsp:include page="booking-receipt.jsp"></jsp:include>

<jsp:include page="../common/footer.jsp"></jsp:include>