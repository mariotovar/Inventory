<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" modelAttribute="purchaseOrder">

	<div id="received" class="wide-section">

		<div class="title-catalog">
			<b>
				<spring:message code="label.purchaseord.receive"/> 
				&nbsp;-&nbsp; 
				<spring:message code="label.purchaseord.title"/> 
				&nbsp;${purchaseOrder.orderNumber}
			</b>
			<hr>			
		</div>			
	         <div class="row">
	          <div class="col-12 col-md-4 text-right">
	          	&nbsp;
	          </div>	          
	          <div class="col-12 col-md-4 text-right">
	            <h6 class="titulos mt-1">
	         		<b>
	         			<spring:message code="label.payment.received"/>
	         		</b>
	         	</h6>
	          </div>
	          <div class="col-12 col-md-4 text-right">
	            <h6 class="titulos mt-1">
	         		<b>
	         			<spring:message code="label.payment.total"/>
	         		</b>
	         	</h6>
	          </div>	              	              
	         </div>         
	         <div class="row">
		        <div class="col-12 col-md-4 text-right">
					&nbsp;
		        </div>
		        <div class="col-12 col-md-4 text-right">
		          <h4><span id="amountReceived"></span> USD</h4>
		        </div>
		        <div class="col-12 col-md-4 text-right">
		        	<h4><span id="totalAmount"></span> USD</h4>
		        	<input id="total" type="hidden" value="${purchaseOrder.totalUSD}"/>
		        </div>              	              
	         </div>  		
		
		<br />		    			          
	     <div class="detail">  		    	         
		    <div class="table-responsive table-reduce">
		         <table class="table table-sm text-center table-striped table-hover edit">
		             <tr class="primary">
		             	 <th class="text-left">
		             	 	#&nbsp;<spring:message code="label.cart.lot"/>
		             	 </th>
		                 <th class="text-left">
		                 	<spring:message code="label.product.pnumber"/>
		                 </th>
		                 <th class="text-left">
		                 	<spring:message code="label.product.pdescription"/>
		                 </th>		                
		                 <th class="text-center">
		                 	<spring:message code="label.cart.qty"/>
		                 </th>
		                 <th  width="10%" class="text-center">
		                 	<spring:message code="label.purchaseord.entry"/>
		                 </th>
		                   <th class="text-left">
		                 	<spring:message code="label.product.serial"/>
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
		             <c:set var="isComplete" value="true" /> 
					 <c:forEach items="${purchaseOrder.items}" var="item" varStatus="statusItem">		             
			        	<c:forEach items="${item.inputs}" var="input" varStatus="statusInput">			        		
	        				<c:set var="readonly">
				             	<c:if test="${not empty input.lot}">
									<c:out value="true" />
				             	</c:if>
		                 	</c:set>			        	
				             <tr>
				             	 <td class="text-left">
				             	 	<label>
	 					        		 <c:choose>
							             	<c:when test="${empty input.lot}">
							             		TBD
							             		<c:set var="isComplete" value="false" />
							             	</c:when>
							             	<c:otherwise>
							             		${input.lotNumber}						             		
							             	</c:otherwise>						             		 		
							             </c:choose>
 				             	 		 <form:input path="items[${statusItem.index}].inputs[${statusInput.index}].lot" type="hidden"/>
				             	 	</label>
				             	 </td>
				                 <td class="text-left">
				                 	<label>${item.value}</label>
				                 </td>
				                 <td class="text-left">
				                 	<label>${item.description}</label>
				                 </td>	
				                         
				                 <td class="text-center">
				                 <c:choose>				              
    								<c:when test="${item.product.apply()}">
				                 		<label>1</label>
				                 	</c:when>
				                 	<c:otherwise>
    									<label>${item.qty}</label>	
    								</c:otherwise>
				                 </c:choose>
				                 </td>
				                 <td class="text-right">
				                 	<c:choose>
	    								<c:when test="${!item.product.apply()}">
							             		<form:input path="items[${statusItem.index}].inputs[${statusInput.index}].receivedQty" 
							             		class="form-control qty input" type="text" size="4" maxlength="10" readonly="${readonly}"
							             		data-key="${statusInput.index}" data-price="${item.priceUSD}" />
					             		</c:when>
					             		<c:otherwise>
	    									<form:input path="items[${statusItem.index}].inputs[${statusInput.index}].receivedQty"   									
	    									class="form-control qty input" type="text" readonly="${readonly}" data-price="${item.priceUSD}"/>
	    								</c:otherwise>
				             		</c:choose>				             							             						                 
				                 </td>
				                  <td class="text-left">
               						<c:choose>
										<c:when test="${item.product.apply()}">    												
	             	 							<form:input path="items[${statusItem.index}].inputs[${statusInput.index}].serial" type="text"
	             	 				 			class="form-control input" readonly="${readonly}" maxlength="20"  />
										</c:when>
										<c:otherwise>
												<label>
													${input.serial}
												</label>	
											</c:otherwise>
									</c:choose>
				                 </td>	 
				                 <td class="text-right">
				                 	<label>
				                 		<span class="currency">${item.priceUSD}</span>
									</label>
				                 </td>
				                 <td class="text-right">
				                 	<label>
				                 		<span id="subtotal${statusInput.index}" class="currency" data-key="${statusInput.index}" data-price="${item.priceUSD}">
				                 			${item.priceUSD * input.receivedQty}
				                 		</span>
									</label>
				                 </td>				                 
				                 <td class="text-left">
									<form:input path="items[${statusItem.index}].inputs[${statusInput.index}].notes" 
									class="form-control" type="text" size="40" maxlength="100" readonly="${readonly}" />				                 				                 	
				                 </td>			                 
				             </tr>
			             </c:forEach>   
		             </c:forEach>		              	             		               
		        </table>     
		    </div>	 
		    
		     <div class="buttons text-right">
				<a href="${pageContext.request.contextPath}/view/lst/purchaseOrder" class="btn btn-info btn-sm">
		      		<span><i class="fas fa-times"></i></span> 
	     				<spring:message code="label.back"/>	
		      	</a>	
		      	<c:if test="${not isComplete}">
			        <button type="submit" class="btn btn-primary btn-sm">
			        	&nbsp;<spring:message code="label.continue"/>
			        	<span><i class="fas fa-chevron-circle-right"></i></span>            	
			        </button>		      	
		      	</c:if>     		     
		     </div> 		            	         
	              
	     </div>

	</div>

 </form:form> 
 
 
<script>

	$(document).ready(function(){			

		calculateAmounts();
		$(".input").blur(function() {
			calculateSubTotal($(this));
			calculateAmounts();
		});	
		function calculateAmounts(){
			var amountReceived = 0;
			$( ".input.qty" ).each(function() {
				var qty = $(this).val();
				var price = $(this).attr("data-price");
				amountReceived += qty*price;
			});				
			var totalAmount = $('#total').val();
			$( "#amountReceived" ).text('$' + numeral(amountReceived).format('0,0.00'));	
			$( "#totalAmount" ).text('$' + numeral(totalAmount).format('0,0.00'));	
		}		
		
	});	


</script>