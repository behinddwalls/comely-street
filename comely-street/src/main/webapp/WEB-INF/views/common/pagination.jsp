<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<style>
.pagination .next {
	margin-left: 5px;
}
</style>

<c:set var="next" value="" />
<c:set var="prev" value="" />

<c:if test="${not param.showNext}">
	<c:set var="next" value="hidden" />
</c:if>
<c:if test="${not param.showPrev}">
	<c:set var="prev" value="hidden" />
</c:if>
<div class="text-center">
	<ul class="pagination">
		<li class="next pull-right ${next}">
			<button type="submit" class="btn">Next »</button>
		</li>
		<li class="previous pull-right ${prev}">
			<button type="submit" class="btn">« Prev</button>
		</li>

	</ul>
</div>
<div class="clearfix"></div>

<script type="text/javascript">
    window.GlobalPagination = function() {
        var funcCall = "${param.actionHandler}('${param.actionHandlerParam}');";

        $('.previous button').click(function(e) {
            var pageSize = parseInt($('${param.paginationSelectorPrefix} input[name="pagination.pageSize"]').val());
            var offSet = parseInt($('${param.paginationSelectorPrefix} input[name="pagination.offSet"]').val());
            $('${param.paginationSelectorPrefix} input[name="pagination.pageAction"]').val('PREV');
            $('${param.paginationSelectorPrefix} input[name="pagination.offSet"]').val(offSet - pageSize)
            eval(funcCall);
        });

        $('.next button').click(function(e) {
            var pageSize = parseInt($('${param.paginationSelectorPrefix} input[name="pagination.pageSize"]').val());
            var offSet = parseInt($('${param.paginationSelectorPrefix} input[name="pagination.offSet"]').val());
            $('${param.paginationSelectorPrefix} input[name="pagination.pageAction"]').val('NEXT');
            $('${param.paginationSelectorPrefix} input[name="pagination.offSet"]').val(offSet + pageSize)
            eval(funcCall);
        });
    }

    window.addEventListener("load", GlobalPagination, false);
</script>
