<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>

<jsp:include page="common/header.jsp"></jsp:include>

<style>
.search-container {
	background: url('${contextPath}/resources/images/search-bimg.png')
		!important;
	background-size: cover !important;
	background-position: center center !important;
	background-attachment: fixed !important;
}

.search-form-row {
	padding: 0rem 1rem;
	padding-bottom: 8rem;
}

.search-container  h3 {
	color: #fff;
}

/*Form Wizard*/
.bs-wizard {
	border-bottom: solid 1px #e0e0e0;
	padding: 0 0 10px 0;
}

.bs-wizard>.bs-wizard-step {
	padding: 0;
	position: relative;
}

.bs-wizard>.bs-wizard-step+.bs-wizard-step {
	
}

.bs-wizard>.bs-wizard-step .bs-wizard-stepnum {
	color: #595959;
	font-size: 16px;
	margin-bottom: 5px;
}

.bs-wizard>.bs-wizard-step .bs-wizard-info {
	color: #999;
	font-size: 14px;
}

.bs-wizard>.bs-wizard-step>.bs-wizard-dot {
	position: absolute;
	width: 30px;
	height: 30px;
	display: block;
	background: #42305F;
	top: 45px;
	left: 50%;
	margin-top: -15px;
	margin-left: -15px;
	border-radius: 50%;
}

.bs-wizard>.bs-wizard-step>.bs-wizard-dot:after {
	content: ' ';
	width: 14px;
	height: 14px;
	background: #fbbd19;
	border-radius: 50px;
	position: absolute;
	top: 8px;
	left: 8px;
}

.bs-wizard>.bs-wizard-step>.progress {
	position: relative;
	border-radius: 0px;
	height: 8px;
	box-shadow: none;
	margin: 20px 0;
}

.bs-wizard>.bs-wizard-step>.progress>.progress-bar {
	width: 0px;
	box-shadow: none;
	background: #42305F;
}

.bs-wizard>.bs-wizard-step.complete>.progress>.progress-bar {
	width: 100%;
}

.bs-wizard>.bs-wizard-step.active>.progress>.progress-bar {
	width: 50%;
}

.bs-wizard>.bs-wizard-step:first-child.active>.progress>.progress-bar {
	width: 0%;
}

.bs-wizard>.bs-wizard-step:last-child.active>.progress>.progress-bar {
	width: 100%;
}

.bs-wizard>.bs-wizard-step.disabled>.bs-wizard-dot {
	background-color: #f5f5f5;
}

.bs-wizard>.bs-wizard-step.disabled>.bs-wizard-dot:after {
	opacity: 0;
}

.bs-wizard>.bs-wizard-step:first-child>.progress {
	left: 50%;
	width: 50%;
}

.bs-wizard>.bs-wizard-step:last-child>.progress {
	width: 50%;
}

.bs-wizard>.bs-wizard-step.disabled a.bs-wizard-dot {
	pointer-events: none;
}
/*END Form Wizard*/
</style>
<section class="site-conatiner">
	<div class="container-fluid search-container">
		<br />
		<h3 class="text-center">Reserve your seat at your fingertips</h3>
		<div class="col-xs-12 col-md-10 col-md-offset-1 search-form-row ">
			<jsp:include page="search/global-search-form.jsp"></jsp:include>
		</div>
	</div>
</section>
<section>
	<div class="container-fluid">
		<div class="col-xs-12 bs-wizard" style="border-bottom: 0;">
			<div class="col-xs-4 bs-wizard-step complete">
				<div class="text-center bs-wizard-stepnum">Search</div>
				<div class="progress">
					<div class="progress-bar"></div>
				</div>
				<a href="#" class="bs-wizard-dot"></a>
				<div class="bs-wizard-info text-center hidden-xs">Search the service you want at the best salons</div>
			</div>

			<div class="col-xs-4 bs-wizard-step complete">
				<!-- complete -->
				<div class="text-center bs-wizard-stepnum">Reserve</div>
				<div class="progress">
					<div class="progress-bar"></div>
				</div>
				<a href="#" class="bs-wizard-dot"></a>
				<div class="bs-wizard-info text-center hidden-xs">Book your appointment online</div>
			</div>

			<div class="col-xs-4 bs-wizard-step active">
				<!-- complete -->
				<div class="text-center bs-wizard-stepnum">Confirmation</div>
				<div class="progress">
					<div class="progress-bar"></div>
				</div>
				<a href="#" class="bs-wizard-dot"></a>
				<div class="bs-wizard-info text-center hidden-xs">Get confirmation and enjoy</div>
			</div>

		</div>
	</div>
</section>
<section></section>
<jsp:include page="common/footer.jsp"></jsp:include>

<script type="text/javascript">
    //     $(document).ready(function(e) {
    //         updateHeight();
    //         $(window).on("resize", updateHeight);
    //     });

    //     function updateHeight() {
    //         $(".search-container").css("height", (screen.height - 50) + "px");
    //         $(".search-container h1").height();
    //         $(".search-form-row").height();
    //         var windowHeight = $(window).height();
    //         $(".search-container h1").css("margin-top",
    //                 ($(window).height() - 50 - $(".search-container h1").height() - $(".search-form-row").height()) / 3 + "px");
    //     }
</script>