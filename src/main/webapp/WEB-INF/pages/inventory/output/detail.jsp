<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  	
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
  	
  	<!-- -------------------------------------------------------------- --> 
 	<!-- Detail section -->
 	<!-- -------------------------------------------------------------- -->

<form:form method="POST" modelAttribute="inventoryOutput">

    <div id="detail" class="section">

	 	<div class="title-catalog">
			<b>
				<spring:message code="label.output.title"/> ${inventoryOutput.orderNumber}
			</b>			
			<hr>			
		</div>	


	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
        		<b>
        			<spring:message code="label.output.date"/>
        		</b>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">						
				<fmt:formatDate value="${inventoryOutput.inventoryDate}" pattern="dd-MM-yyyy HH:mm" />  
	       </div>
	    </div>	
	    <hr />		
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<h6>
	        		<b class="info-total">
	        			<spring:message code="label.output.total"/>
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">
				<h6 class="info-total">
					<b>
						<span class="currency">${inventoryOutput.totalMXN}</span>&nbsp;MXN
						|
						<span class="currency">${inventoryOutput.totalUSD}</span>&nbsp;USD
					</b>
				</h6>
	       </div>
	    </div>		
	    <div class="table-responsive table-items">
	         <table class="table table-sm text-center table-striped table-hover edit">
	             <tr class="primary">
	                 <th class="text-center">
	                 	# <spring:message code="label.cart.item"/>
	                 </th>

	                 <th class="text-left">
	                 	<spring:message code="label.product.pnumber"/>
	                 </th>
	                 <th class="text-left">
	                 	<spring:message code="label.product.pdescription"/>
	                 </th>
	                 <th class="text-left">
	                 	<spring:message code="label.product.condition"/>
	                 </th>			 	                 
	                 <th class="text-center">
	                 	<spring:message code="label.cart.qty"/>
	                 </th>
	                 <th class="text-right">
	                 	<spring:message code="label.cart.unit.mxn"/>
	                 </th>
	                 <th class="text-right">
	                 	<spring:message code="label.cart.total.mxn"/>
	                 </th>
	                 <th width="30%" class="text-center">
	                 	<spring:message code="label.cart.notes"/>
	                 </th>
	             </tr>    
	             <c:set var="index" value="0" />
	             <c:forEach items="${inventoryOutput.items}" var="item">
	             	<c:forEach items="${item.inputs}" var="input">
	             			 <c:set var="index" value="${index+1}" />
				             <tr>
				          		 <td class="text-center">
				               	 	${index}
				               	 </td>
				               	 <td class="text-left">
				               	 	${item.value}
				               	 </td>
				                 <td class="text-left">
				                 	${item.description}
				                 </td>			
				                 <td class="text-left">
				                 	${item.keyCondition}
				                 </td>				                 	               	 				                 
				                 <td class="text-right">
				                 	${input.outputs[0].qty}
				                 </td>
				                 <td class="text-right">
				                 	<span class="currency">${input.outputs[0].costMXN}</span>
				                 </td>  
				                 <td class="text-right">
				                 	<span class="currency">${input.outputs[0].subtotalMXN}</span>
				                 </td>  
				                 <td class="text-left">
				                 	${input.notes}
				                 </td>  					                 					                 
				             </tr>	
			             </c:forEach>						             	
	        	 </c:forEach>	
	        </table>     
	    </div>   
	    <hr />	
	    <div class="actions">           
			<div class="text-right buttons">				 
		    	<br />
				<a href="${pageContext.request.contextPath}/view/history/inventoryOutput" class="btn btn-secondary btn-sm">
		      		<span><i class="fas fa-times"></i></span> 
	     				<spring:message code="label.back"/>		
		      	</a>	
			</div>    
		 </div>	
	</div>
	
</form:form>