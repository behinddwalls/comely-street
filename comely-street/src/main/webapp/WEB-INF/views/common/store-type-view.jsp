<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>


<c:if test="${param.storeType eq 'FEMALE' or param.storeType eq 'UNISEX'}">
	<i class="fa fa-female"></i>
</c:if>
<c:if test="${param.storeType eq 'MALE' or param.storeType eq 'UNISEX'}">
	<i class="fa fa-male"></i>
</c:if>
<c:if test="${not empty param.storeTypeValue}">
	${param.storeTypeValue}
</c:if>

