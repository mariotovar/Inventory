<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<div class="section">

	<div class="alert-red title-catalog ">				
		<b>
			<spring:message code="label.home.expired"/>
		</b>				
		<hr>			
	</div>	
	
	<div class="table-responsive">
	    	<c:choose>
	 		<c:when test="${listRows.size() > 0}">
			    <table class="table table-sm text-center table-striped table-hover">
			        <tr class="primary">
		                 <th class="text-left">
		                 	# <spring:message code="label.cart.lot"/>
		                 </th>
		                 <th class="text-left">
		                 	<spring:message code="label.product.pnumber"/>
		                 </th>
		                 <th class="text-left">
		                 	<spring:message code="label.product.pdescription"/>
		                 </th>
			            <th class="text-right">
			            	<spring:message code="label.cart.inputs"/>
			            </th>
			            <th class="text-right">
			            	<spring:message code="label.cart.outputs"/>
			            </th>
			            <th class="text-right">
			            	<spring:message code="label.cart.stock"/>
			            </th>
			            <th class="text-right">
			            	<spring:message code="label.cart.dateExpired"/>
			            </th>
			        </tr>
			 		<c:forEach items="${listRows}" var="inventoryRow">
				         <tr>
				             <td class="text-left">
				             	${inventoryRow.lotNumber}
				             </td>
				           	 <td class="text-left">
				           	 	${inventoryRow.value}
				           	 </td>
				             <td class="text-left">
				             	${inventoryRow.description}
				             </td>	             
				          	 <td class="text-right">
				          	 	${inventoryRow.inputs}
				          	 </td>   
				          	 <td class="text-right">
				          	 	${inventoryRow.outputs}
				          	 </td>     	          	             		 
				           	 <td class="text-right">
				           	 	${inventoryRow.stock}
				           	 </td>      
				           	 <td class="text-right">
				           	 	<b class="alert-red">
				           	 		<fmt:formatDate value="${inventoryRow.expiredDate}" pattern="dd-MM-yyyy" />
				           	 	</b>				           	 	  				           	 	
				           	 </td>   				           	 
				         </tr>
			     	</c:forEach>
			   </table>
		 	</c:when>
		 	<c:otherwise>
		 		<br />
				<div class="alert alert-info no-results">
				  <strong> 
				  	<spring:message code="label.notresult"/> 
				  </strong>.
				</div>		
		 	</c:otherwise>
		 </c:choose>	
	</div>

</div>
