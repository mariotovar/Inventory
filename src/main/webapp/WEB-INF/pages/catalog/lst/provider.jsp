<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<section id="tabla">

	 <jsp:include page="/WEB-INF/pages/section/catalog/lstHeader.jsp"/>

     <c:choose>
	 	<c:when test="${ctrlPage.listRows.size() > 0}">
		     <div class="table-responsive">
		         <table class="table table-sm text-center table-striped table-hover">
		             <tr class="primary">
		                 <th><spring:message code="label.provider.name"/></th>
		                 <th><spring:message code="label.provider.email"/></th>
		                 <th><spring:message code="label.provider.mobilephone"/></th>
		                 <th><spring:message code="label.provider.taxid"/></th>
		                 <th><spring:message code="label.provider.contact"/></th>
		                 <th><spring:message code="label.actions"/></th>
		             </tr>
	 				 <c:forEach items="${ctrlPage.listRows}" var="provider">
			             <tr>
			               	 <td class="text-left">
			               	 	<label>
			               	 		${provider.value}
			               	 	</label>
			               	 </td>
			                 <td class="text-left">
			               	 	<label>
			                 		${provider.email}
			                 	</label>
			                 </td>
			                 <td class="text-center">
			               	 	<label>
			                 		${provider.mobilePhone}
			                 	</label>
			                 </td>
			                 <td class="text-left">
			               	 	<label>
			                 		${provider.taxID}
			                 	</label>
			                 </td>
			                 <td class="text-left">
			               	 	<label>
			                 		${provider.contact}
			                 	</label>
			                 </td>
			                 <td>
							     <jsp:include page="/WEB-INF/pages/section/catalog/lstAction.jsp">
							        <jsp:param name="pk" value="${provider.pk}"/>
							      </jsp:include>												
				             </td>                     
			             </tr>
		        	 </c:forEach>
		        </table>     
		     </div>
		    <jsp:include page="/WEB-INF/pages/section/catalog/lstPagination.jsp"/>
	 	</c:when>
	 	<c:otherwise>
			<div class="alert alert-info no-results">
			  <strong><spring:message code="label.notresult"/></strong>.
			</div>		
	 	</c:otherwise>
	 </c:choose>
     

 </section> 