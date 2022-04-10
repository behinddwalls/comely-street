<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>
<!DOCTYPE html>

<jsp:include page="../common/header.jsp"></jsp:include>

<section>
	<div class="container-fluid msg-container">
		<div class="row">
			<br/>
			<h3 class="text-center"> Your registration ID is: ${clientDataModel.id}. </h3>
			<h4 class="text-center"> We will contact you shortly to get other information required for registration. </h4>
		</div>
	</div>
</section>

<jsp:include page="../common/site-js.jsp"></jsp:include>
