<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  	
  	
  	<!-- -------------------------------------------------------------- --> 
 	<!-- Checkout section -->
 	<!-- -------------------------------------------------------------- -->

    <div id="checkout" class="section-wide">

		<form:form class="catalog order" method="POST" modelAttribute="inventoryOutput">

		 	<div class="title-catalog">
				<b>
					<spring:message code="label.output.title"/>
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
		                 <th class="text-center">
		                 	# <spring:message code="label.cart.lot"/>
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
		                 <th class="text-right">
		                 	<spring:message code="label.cart.qty"/>
		                 </th>
		                 <th class="text-right">
		                 	<spring:message code="label.cart.unit.mxn"/>
		                 </th>
		                 <th class="text-right">
		                 	<spring:message code="label.cart.total.mxn"/>
		                 </th>
		             </tr>    
		             <c:forEach items="${inventoryOutput.items}" var="item">
			         	<c:forEach items="${item.inputs}" var="input">
			         		<c:set var="index" value="${index + 1}" />	 		         		             
			             <tr>
			          		 <td class="text-center">
			               	 	${index}
			               	 </td>
			               	 <td class="text-center">
			               	 	${input.lotNumber}
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
			             </tr>		
			             </c:forEach>					             	
		        	 </c:forEach>			        	 		        	 
		        </table>     
		    </div>  
		    <hr />		
		    
		    <div class="actions">  
				<div class="text-right buttons"> 
					<button type="button" class="btn btn-info btn-sm back">
			      		<span><i class="fas fa-times"></i></span> 
		     				<spring:message code="label.back"/>	
			      	</button>	
		   		 	<button type="submit" class="btn btn-success btn-sm">
			        	&nbsp;<spring:message code="label.input.confirm"/>
			        	<span><i class="fas fa-chevron-circle-right"></i></span>            	
		   			</button>	
				</div>  	     		
			 </div>	
			 
		</form:form>	
	
	</div>
	