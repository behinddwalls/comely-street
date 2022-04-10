<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>


<jsp:include page="../common/header.jsp"></jsp:include>

<section class="site-conatiner">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-3 ">
				<!-- -- -->
				<br />
				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger">
						<strong>Error!</strong> ${errorMessage}
					</div>
				</c:if>
				<c:if test="${not empty successMessage}">
					<div class="alert alert-success">
						<strong>Success!</strong> ${successMessage}
					</div>
				</c:if>
				<div class="panel-group" id="accordion">
					<!-- Basic Detail -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#collapse1" class="button width-100"> Basic Detail</a>
							</h4>
						</div>
						<div id="collapse1" class="panel-collapse collapse in">
							<div class="panel-body">
								<form:form commandName="customerProfileDataModel" action="${contextPath}/customer/profile" method="POST">

									<div class="form-group col-sm-12">
										<label class="control-label" for="first-name">Full Name</label>
										<form:input path="customerDataModel.fullName" class="first-name form-control" required="required" placeholder="Enter your full name" />
										<div class="has-error">
											<form:errors path="customerDataModel.fullName" class="help-block" />
										</div>
									</div>
									<div class="form-group col-sm-12">
										<label class="control-label" for="mobileNumber">Mobile number (+91)</label>
										<form:input path="customerDataModel.mobileNumber" class="mobileNumber form-control" required="required"
											placeholder="Enter your 10-digit mobile number" />
										<div class="has-error">
											<form:errors path="customerDataModel.mobileNumber" class="help-block" />
										</div>
									</div>
									<input type="hidden" name="updateType" value="basicDetail" />
									<div class="form-group col-sm-6 col-xs-12 pull-right">
										<button class="form-control btn btn-search" type="submit">Save Profile</button>
									</div>
								</form:form>

							</div>
						</div>
					</div>



					<!-- Email Update -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#collapse2" class="button width-100"> Update Email</a>
							</h4>
						</div>
						<div id="collapse2" class="panel-collapse collapse ">
							<div class="panel-body">

								<form:form commandName="customerProfileDataModel" action="${contextPath}/customer/profile" method="POST">

									<div class="form-group col-sm-12">
										<label class="control-label" for="email">Email </label>
										<form:input path="customerDataModel.emailId" class="email form-control" required="required" placeholder="Enter your emailId" />
										<div class="has-error">
											<form:errors path="customerDataModel.emailId" class="help-block" />
										</div>
									</div>
									<input type="hidden" name="updateType" value="email" />
									<div class="form-group col-sm-6 col-xs-12 pull-right">
										<button class="form-control btn btn-search" type="submit">Update Email</button>
									</div>
								</form:form>

							</div>
						</div>
					</div>

					<!-- Password Update -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#collapse3" class="button width-100"> Update Password</a>
							</h4>
						</div>
						<div id="collapse3" class="panel-collapse collapse ">
							<div class="panel-body">

								<form:form commandName="customerProfileDataModel" action="${contextPath}/customer/profile" method="POST">

									<div class="form-group col-sm-12">
										<label class="control-label" for="password">Password </label>
										<form:password path="customerDataModel.password" class="password form-control" required="required" placeholder="Enter new password" />
										<div class="has-error">
											<form:errors path="customerDataModel.password" class="help-block" />
										</div>
									</div>
									<input type="hidden" name="updateType" value="password" />
									<div class="form-group col-sm-6 col-xs-12 pull-right">
										<button class="form-control btn btn-search" type="submit">Update Password</button>
									</div>
								</form:form>

							</div>
						</div>
					</div>

				</div>


			</div>
		</div>
	</div>

</section>


<jsp:include page="../common/footer.jsp"></jsp:include>