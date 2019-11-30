<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<section id="tabla">

	 <jsp:include page="/WEB-INF/pages/section/catalog/lstHeader.jsp"/>

     <c:choose>
	 	<c:when test="${ctrlPage.listRows.size() > 0}">
		     <div class="table-responsive">
		         <table class="table table-sm text-center table-striped table-hover">
		             <tr class="primary">
		                 <th class="text-left">
		                 	<spring:message code="label.product.pnumber"/>
		                 </th>
		                 <th class="text-left">
		                 	<spring:message code="label.product.pdescription"/>
		                 </th>
		                 <th>
		                 	<spring:message code="label.product.stockmin"/>
		                 </th>
		                 <th>
		                 	<spring:message code="label.product.stockmax"/>
		                 </th>
		                 <th class="text-right">
		                 	<spring:message code="label.product.priceusd"/>
		                 </th>
		                 <th class="text-right">
		                 	<spring:message code="label.product.pricemxn"/>
		                 </th>                 
		                 <th>
		                 	<spring:message code="label.actions"/>
						</th>
		             </tr>
	 				 <c:forEach items="${ctrlPage.listRows}" var="product">
			             <tr>
			               	 <td class="text-left">
			               	 	<label>
			               	 		${product.value}
			               	 	</label>
			               	 </td>
			                 <td class="text-left">
			               	 	<label>
				                 	${product.description}
			               	 	</label>
			                 </td>
			                 <td>
			               	 	<label>
				                 	${product.stockMin}
			               	 	</label>
			                 </td>
			                 <td>
			               	 	<label>
				                 	${product.stockMax}
			               	 	</label>
			                 </td>                    
			                 <td class="text-right">
			               	 	<label>
			                 		<span class="currency">${product.priceUSD}</span>
			               	 	</label>
			                 </td>
			                 <td class="text-right">
			               	 	<label>
			                 		<span class="currency">${product.priceMXN}</span>
			               	 	</label>
			                 </td>        
			                 <td>				
							     <jsp:include page="/WEB-INF/pages/section/catalog/lstAction.jsp">
							        <jsp:param name="pk" value="${product.pk}"/>
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
			  <strong> 
			  	<spring:message code="label.notresult"/> 
			  </strong>.
			</div>		
	 	</c:otherwise>
	 </c:choose>

 </section> 
 
