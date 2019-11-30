<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  	
  	
  	<!-- -------------------------------------------------------------- --> 
 	<!-- Checkout section -->
 	<!-- -------------------------------------------------------------- -->

    <div id="checkout" class="wide-section">

		<form:form class="catalog order" method="POST" modelAttribute="inventoryInput">

		 	<div class="title-catalog">
				<b>
					<spring:message code="label.input.title"/>
				</b>

				<hr>			
			</div>		
	
		    <div class="row">
		        <div class="col-12 col-md-3 mt-2">
	        		<b>
	        			<spring:message code="label.input.date"/>
	        		</b>
		        </div>        
		        <div class="col-12 col-md-9 mt-2">						
					<fmt:formatDate value="${inventoryInput.inventoryDate}" pattern="dd-MM-yyyy HH:mm" />  
		       </div>
		    </div>	
		    <hr />				    
		    <div class="row">
		        <div class="col-12 col-md-3 mt-2">
		        	<h6>
		        		<b class="info-total">
		        			<spring:message code="label.input.total"/>
		        		</b>
		        	</h6>
		        </div>        
		        <div class="col-12 col-md-9 mt-2">
					<h6 class="info-total">
						<b>
							<span class="currency">${inventoryInput.totalUSD}</span>&nbsp;USD
						</b>
					</h6>
		       </div>
		    </div>		

		    <div class="table-responsive table-items">
		         <table class="table table-sm text-center table-striped table-hover edit">
		             <tr class="primary">
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
		                 	<spring:message code="label.cart.unit.usd"/>
		                 </th>
		                 <th class="text-right">
		                 	<spring:message code="label.cart.total.usd"/>
		                 </th>
		                 <th width="30%" class="text-center">
		                 	<spring:message code="label.cart.notes"/>
		                 </th>
		             </tr>    
	 				 <c:forEach items="${inventoryInput.rows}" var="row" varStatus="status">
			             <tr>
			               	 <td class="text-left">
			               	 	${row.value}
			               	 </td>
			                 <td class="text-left">
			                 	${row.description}
			                 </td>
							<td class="text-left">
								${row.condition.key}
							</td>			                 
			                 <td>
			                 	${row.qty}
			                 </td>
			                 <td class="text-right">
			                 	<span class="currency">${row.priceUSD}</span>
			                 </td>
			                 <td class="text-right">
			                 	<span class="currency">${row.subtotalUSD}</span>
			                 </td>
			                 <td class="text-left">
			                 	${row.notes}
			                 </td> 
			             </tr>
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
	