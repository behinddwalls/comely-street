<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<style>
.site-conatiner {
	/* 	margin-top: 0px; */
	/* 	position: relative; */
	/* 	padding: 0; */
	
}

.navbar {
	margin-bottom: 0px;
}

.app-navbar-text {
	padding-top: 14px;
	padding-bottom: 12px;
}

.app-navbar-text:hover {
	background: #42305F;
	color: #fff !important;
}

.fa-1-5x {
	font-size: 1.5em;
}
</style>

<nav id="comely-street-nav" class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar ">
			<div class="row ">
				<a href="${contextPath}/"> <span class="col-xs-4 app-navbar-text text-center"> <i class="fa fa-search fa-1-5x" title="Search"></i>
				</span>
				</a> <a href="${contextPath}/customer/profile"> <span class="col-xs-4 app-navbar-text text-center"> <i class="fa fa-user fa-1-5x"
						title="Profile"></i>
				</span>
				</a> <a href="${contextPath}/customer/bookings"> <span class="col-xs-4 app-navbar-text text-center"> <i class="fa fa-calendar fa-1-5x"
						title="Appointments"></i>
				</span>
				</a>
			</div>
		</div>

	</div>
	<!-- /.container-fluid -->
</nav>