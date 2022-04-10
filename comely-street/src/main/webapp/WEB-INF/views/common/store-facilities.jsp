<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>

<c:set var="cssClassPrefix" value="" />
<c:if test="${not empty param.cssClassPrefix}">
	<c:set var="cssClassPrefix" value="${param.cssClassPrefix}" />
</c:if>

<c:if test="${param.acAvailable eq true}">
	<i class="fa black ${cssClassPrefix}acAvailable" title="AC Available">AC</i>
</c:if>
<c:if test="${param.acAvailable eq false}">
	<i class="fa gray ${cssClassPrefix}acAvailable" title="No AC">AC</i>
</c:if>

<c:if test="${param.parkingAvailable eq true}">
	<i class="fa fa-car black ${cssClassPrefix}parkingAvailable" title="Parking"></i>
</c:if>
<c:if test="${param.parkingAvailable eq false}">
	<i class="fa fa-car gray ${cssClassPrefix}parkingAvailable" title="No Parking"></i>
</c:if>

<c:if test="${param.acceptsCreditCards eq true}">
	<i class="fa fa-credit-card black ${cssClassPrefix}acceptsCreditCards" title="Accepts Credit Card"></i>
</c:if>
<c:if test="${param.acceptsCreditCards eq false}">
	<i class="fa fa-credit-card gray ${cssClassPrefix}acceptsCreditCards" title="Doesn't accepts Credit Card"></i>
</c:if>

<c:if test="${param.wifiAvailable eq true}">
	<i class="fa fa-wifi black ${cssClassPrefix}wifiAvailable" title="Wifi Available"></i>
</c:if>
<c:if test="${param.wifiAvailable eq false}">
	<i class="fa fa-wifi gray ${cssClassPrefix}wifiAvailable" title="No Wifi"></i>
</c:if>

<c:if test="${param.tvAvailable eq true}">
	<i class="fa black ${cssClassPrefix}tvAvailable" title="TV Available">TV</i>
</c:if>
<c:if test="${param.tvAvailable eq false}">
	<i class="fa gray ${cssClassPrefix}tvAvailable" title="No TV">TV</i>
</c:if>

