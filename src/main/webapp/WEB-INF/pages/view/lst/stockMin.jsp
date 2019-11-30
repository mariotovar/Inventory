<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<div class="section">

	<div class="alert-red title-catalog ">				
		<b>
			STOCK MINIMO
		</b>				
		<hr>			
	</div>	
	
	<div class="table-responsive">
	    	<c:choose>
	 		<c:when test="${listRows.size() > 0}">
			    <table class="table table-sm text-center table-striped table-hover">
			        <tr class="primary">
		                 <th class="text-center">
		                 	<spring:message code="label.product.pnumber"/>
		                 </th>
		                 <th class="text-center">
		                 	<spring:message code="label.product.pdescription"/>
		                 </th>
			            <th class="text-right">
			            	<spring:message code="label.cart.stockMin"/>.
			            </th>
			            <th class="text-right">
			            	<spring:message code="label.cart.stockMax"/>
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
			        </tr>
			 		<c:forEach items="${listRows}" var="inventoryRow">
				         <tr>
				           	 <td class="text-left">
				           	 	${inventoryRow.value}
				           	 </td>
				             <td class="text-left">
				             	${inventoryRow.description}
				             </td>
				             <td class="text-right">
				             	${inventoryRow.stockMin}
				             </td>
				          	 <td class="text-right">
				          	 	${inventoryRow.stockMax}
				          	 </td>     
				           	 <td class="text-right">
				           	 	${inventoryRow.inputs}
				           	 </td>
				           	 <td class="text-right">
				           	 	${inventoryRow.outputs}
				           	 </td>	           	 	          	           		 
				           	 <td class="text-right" class="alert-red">
				           	 	<b class="alert-red">${inventoryRow.stock}</b>
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
