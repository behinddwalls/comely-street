<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<jsp:include page="../../common/header.jsp"></jsp:include>

<section class="site-conatiner">
	<div class="container-fluid">
		<div class="col-sm-12">
			<h2>Manage Password for ${storeId}</h2>
			<hr />
			<c:if test="${not empty errorMessage}">
				${errorMessage}
			</c:if>
			<c:if test="${not empty successMessage}">
				${successMessage}
			</c:if>
			<br />
			<form:form action="${contextPath}/internal/store/manager/${storeId}/password" method="post">
				<div class="form-group col-sm-6">
					<input name="password" value="${password}" type="password" class="form-control" />
				</div>
				<input name="storeId" type="hidden" value="${storeId}" />
				<div class="form-group col-sm-6">
					<button type="submit" class="btn btn-search form-control">Save Password</button>
				</div>
			</form:form>
		</div>


	</div>
</section>

<jsp:include page="../../common/footer.jsp"></jsp:include>