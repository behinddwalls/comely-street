<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>

<c:forEach begin="1" end="5" var="val">
	<c:if test="${val le param.rating}">
		<label class="star-rating__ico_filled fa fa-star-o "></label>
	</c:if>
	<c:if test="${val gt param.rating}">
		<label class="star-rating__ico_unfilled fa fa-star-o"></label>
	</c:if>
</c:forEach>