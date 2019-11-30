<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<section id="tabla">

	<jsp:include page="/WEB-INF/pages/section/catalog/lstHeader.jsp" />

	<c:choose>
		<c:when test="${ctrlPage.listRows.size() > 0}">
			<div class="table-responsive">
				<table class="table table-sm text-center table-striped table-hover">
					<tr class="primary">
						<th class="text-center">
							<spring:message	code="label.shipto.value" />
						</th>
						<th class="text-center">
							<spring:message	code="label.shipto.address" />
						</th>
						<th class="text-center">
							<spring:message code="label.shipto.zip" />
						</th>
						<th class="text-center">
							<spring:message code="label.shipto.country" />
						</th>
						<th class="text-center" >
							<spring:message	code="label.shipto.state" />
						</th>
						<th>
							<spring:message code="label.actions" />
						</th>
					</tr>
					<c:forEach items="${ctrlPage.listRows}" var="shipto">
						<tr>
							<td class="text-left"><label> ${shipto.value} </label></td>
							<td class="text-left"><label> ${shipto.address} </label></td>
							<td class="text-center"><label> ${shipto.zip} </label></td>
							<td class="text-center"><label> ${shipto.country} </label></td>						
							<td class="text-center"><label> ${shipto.state} </label></td>
							
							<td><jsp:include
									page="/WEB-INF/pages/section/catalog/lstAction.jsp">
									<jsp:param name="pk" value="${shipto.pk}" />
								</jsp:include></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<jsp:include page="/WEB-INF/pages/section/catalog/lstPagination.jsp" />
		</c:when>
		<c:otherwise>
			<div class="alert alert-info no-results">
				<strong> <spring:message code="label.notresult" />
				</strong>.
			</div>
		</c:otherwise>
	</c:choose>

</section>

