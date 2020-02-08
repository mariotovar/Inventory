<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  	
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
  	
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
  	<!-- -------------------------------------------------------------- --> 
 	<!-- Detail section -->
 	<!-- -------------------------------------------------------------- -->

<form:form method="POST" modelAttribute="saleOrder">

    <div id="detail" class="section">

	 	<div class="title-catalog">
				<b>
					<spring:message code="label.saleord.title"/>
					&nbsp;${saleOrder.orderNumber}
				</b>			
			<hr>			
		</div>	

		<c:choose>
			<c:when test="${action eq 'READ'}">
				<div class="order-success blink">
					* <spring:message code="label.action.success"/> 
				</div>
			</c:when>	
			<c:when test="${action eq 'CLOSE'}">
				<div class="order-success">		
					<spring:message code="label.action.close"/> 
				</div>
			</c:when>			
		</c:choose>

	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        		<b>
	        			<spring:message code="label.saleord.status"/>
	        		</b>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">				
				<spring:message code="label.status.${saleOrder.status}"/> 
	       </div>
	    </div>
	    <hr />
	    
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<b>
	        		<spring:message code="label.saleord.date"/>
	        	</b>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">						
				<fmt:formatDate value="${saleOrder.saleDate}" pattern="dd-MM-yyyy HH:mm" />  
	       </div>
	    </div>	
	    <hr />		
	    		    
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<h6>
	        		<b>
	        			<spring:message code="label.client.title"/>
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">
				<h6>
					${saleOrder.client.value}
				</h6>
	       </div>
	    </div>
	    <hr />		
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<h6>
	        		<b>
	        			<spring:message code="label.client.email"/>
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">
				<h6>
					${saleOrder.client.email}
				</h6>
	       </div>
	    </div>
	    <hr />	
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<h6>
	        		<b>
	        			<spring:message code="label.saleord.shipping"/>
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">
				<h6>						
					<span class="currency">
						${saleOrder.shippingCostMXN < 0 ? -saleOrder.shippingCostMXN:saleOrder.shippingCostMXN}
					</span>
					MXN
					<c:choose>
						<c:when test="${saleOrder.shippingCostMXN >= 0}">
							<b>
								(+ <spring:message code="label.saleord.charge.customer"/>)
							</b>
						</c:when>
						<c:otherwise>
							<b>
								( <spring:message code="label.saleord.expense"/>)
							</b>
						</c:otherwise>
					</c:choose>
				</h6>
	       </div>
	    </div>
	    <hr />			    		    
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<h6>
	        		<b class="info-total">
	        			<spring:message code="label.total.subtotal"/>
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-2 mt-2">
				<h6 class="info-total text-right">
					<b>
						<span class="currency">${saleOrder.totalMXN}</span>&nbsp;MXN
					</b>
				</h6>
	       </div>
	       <div class="col-12 col-md-1 mt-2">
				<h6 class="info-total text-center">
					<b>
						|						
					</b>
				</h6>
	       </div>	     	       
	       <div class="col-12 col-md-2 mt-2">
				<h6 class="info-total text-right">
					<b>
						<span class="currency">${saleOrder.totalUSD}</span>&nbsp;USD						
					</b>
				</h6>
	       </div>	       
	    </div>		
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<h6>
	        		<b class="info-total">
	        			<spring:message code="label.total.iva"/>
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-2 mt-2">
				<h6 class="info-total text-right">
					<b>
						<span class="currency">${saleOrder.totalMXN * saleOrder.taxIVA}</span> MXN
					</b>
				</h6>
	       </div>
	       <div class="col-12 col-md-1 mt-2">
				<h6 class="info-total text-center">
					<b>
						|						
					</b>
				</h6>
	       </div>	     	       
	        <div class="col-12 col-md-2 mt-2">
				<h6 class="info-total text-right">
					<b>
						<span class="currency">${saleOrder.totalUSD * saleOrder.taxIVA}</span> USD
					</b>
				</h6>
	       </div>	       
	    </div>		
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<h6>
	        		<b class="info-total">
	        			<spring:message code="label.total.order"/>
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-2 mt-2">
				<h6 class="info-total text-right">
					<b>
						<span class="currency">${(saleOrder.totalMXN * saleOrder.taxIVA) + saleOrder.totalMXN}</span> MXN
					</b>
				</h6>
	       </div>
	       <div class="col-12 col-md-1 mt-2">
				<h6 class="info-total text-center">
					<b>
						|						
					</b>
				</h6>
	       </div>	     	       	       
	        <div class="col-12 col-md-2 mt-2">
				<h6 class="info-total text-right">
					<b>
						<span class="currency">${(saleOrder.totalUSD * saleOrder.taxIVA) + saleOrder.totalUSD}</span> USD
					</b>
				</h6>
	       </div>
	    </div>			    

		<c:set var="index" value="0" />
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
	             <c:forEach items="${saleOrder.items}" var="item">
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
								${item.condition.key}
							 </td>			                 			               	 				                 
			                 <td class="text-right">
			                 	${input.outputs[0].qty}
			                 </td>
			                 <td class="text-right">
			                 	<span class="currency">
			                 		${input.outputs[0].costMXN}
			                 	</span>
			                 </td>				                 
			                 <td class="text-right">
			                 	<span class="currency">
			                 		${input.outputs[0].subtotalMXN}
			                 	</span>
			                 </td>  
			             </tr>			             			          
		             </c:forEach>							             	
	        	 </c:forEach>	
	        </table>     
	    </div>  
	    
	    <c:if test="${not empty saleOrder.notes}">
	    	<span class="notes">
	    		*** ${saleOrder.notes}
	    	</span>
	    </c:if>	    
	    <hr />	
	    
	    <div class="row">
	        <div class="col-12 col-md-2 mt-2">
	        	<h6>
	        		<b class="info-total">
	        			<spring:message code="label.payment.title"/>
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-8 mt-2">
				<h6 class="info-total">
					<b>
						<span class="currency">${saleOrder.amountCharged}</span>&nbsp;MXN&nbsp;
						(<spring:message code="label.payment.charge"/>) 
						|
						<span class="currency">${saleOrder.amountToCharge}</span>&nbsp;MXN&nbsp;
						(<spring:message code="label.payment.toCharge"/>)
					</b>
				</h6>
	       </div>
		        <div class="col-12 col-md-2 mt-2">
					<h6 class="alert-red">
						<c:if test="${saleOrder.amountCharged >= saleOrder.totalMXN}">
							<b>
								!!!&nbsp;<spring:message code="label.complete"/>
							</b>						
						</c:if>
					</h6>
		       </div>	       
	    </div>	
	    
	    <c:choose>
			<c:when test="${saleOrder.amountCharged > 0}">
			    <div class="table-responsive table-items">
			         <table class="table table-sm text-center table-striped table-hover edit">
			             <tr class="primary">
			             	 <th class="text-center">
			             	 	<spring:message code="label.payment.date"/>
			             	 </th>
			                 <th class="text-right">
			                 	<spring:message code="label.payment.amountMXN"/>
			                 </th>
			                 <th width="65%"  class="text-center">
			                 	<spring:message code="label.payment.notes"/>
			                 </th>  
			                  <th width="2%"  class="text-center">
		                 		 <spring:message code="label.purchaseord.document"/>
		            
			                 </th>                     
			             </tr>     
		 				 <c:forEach items="${saleOrder.payments}" var="payment">
				             <tr>
				               	 <td class="text-center">
				               	 	<fmt:formatDate value="${payment.paymentDate}" pattern="dd-MM-yyyy HH:mm" />  
				               	 </td>
				               	 <td class="text-right">
				               	 	<span class="currency">${payment.amountMXN}</span>
				               	 </td>
				                 <td class="text-left">
				                 	${payment.notes}
				                 </td>  
				                   	<td>
		                 		 		<c:if test = "${fn:contains(existFilePayment, payment.payNumber)}">
					                	<a href="${pageContext.request.contextPath}/order/sale/payment/download/${saleOrder.year}/${payment.payNumber}">
					                	
											<i class="fas fa-download"></i>
										</a>
									</c:if>
		                 			</td> 			         		                    			                                
				             </tr>
			        	 </c:forEach>		              
			        </table>  
			     </div>
	     	</c:when>
			<c:otherwise>
				<div class="alert alert-info no-results">
				  <strong> 
				  	<spring:message code="label.notresult"/>
				  </strong>.
				</div>				
			</c:otherwise>
		</c:choose>
	    		    	    
	    <div class="actions">           
			<div class="text-right buttons">				 
		    	<br />
				<c:choose>
					<c:when test="${saleOrder.status eq 'CLOSE'}">
						<a href="${pageContext.request.contextPath}/order/sale/history" class="btn btn-secondary btn-sm">
				      		<span><i class="fas fa-times"></i></span> 
			     				<spring:message code="label.back"/>	
				      	</a>				      	
					</c:when>	
					<c:otherwise>
						<button id="print-note" class="btn btn-success btn-sm">
				      		<span><i class="fas fa-print"></i></span> 
			     				<spring:message code="label.saleord.print.note"/>	
				      	</button>
						<button class="btn btn-primary btn-sm mail-confirm">
				      		<span><i class="fas fa-at"></i></span> 
			     				<spring:message code="label.purchaseord.send.mail"/>	
				      	</button>					      	 	
						<a href="${pageContext.request.contextPath}/view/lst/saleOrder" class="btn btn-secondary btn-sm">
				      		<span><i class="fas fa-times"></i></span> 
			     				<spring:message code="label.back"/>	
				      	</a>			      	
					</c:otherwise>			
				</c:choose>	
				<c:if test="${action eq 'CLOSE'}">
			      	<button type="submit" class="btn btn-success btn-sm">
			      		<span><i class="far fa-trash-alt"></i></span> 
	      				<spring:message code="label.action.close.order"/>	
			      	</button>
				</c:if>			
			</div>    
		 </div>	
	</div>
	
</form:form>

<div id="modal-mailing" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title title-catalog">
        	<spring:message code="label.mail.confirm.title"/>	
        </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>
        	<spring:message code="label.mail.confirm.description"/>
        	${saleOrder.client.email}?
        </p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">
        	<spring:message code="label.cancel"/>
        </button>
        <button type="button" class="btn btn-primary mail-submit">
        	<spring:message code="label.accept"/>
        </button>
      </div>
    </div>
  </div>
</div>

	<!-- -------------------------------------------------------------- -->	
	<script>			
			
		$("#print-note").click(function(e) {
			e.preventDefault();
			
			window.open(context()+"/order/sale/printing",'_blank');

			return false;
		});
		
		$(".mail-confirm").click(function(e) {
			e.preventDefault();			
			$('#modal-mailing').modal('show');
			return false;
		});
		
		$(".mail-submit").click(function(e) {		
			e.preventDefault();
			$.ajax({
	    		async: true,
				type : "GET",
				url : context()+"/order/sale/mailing",
				dataType : 'json',
			    beforeSend: function(){
			    	$('#loader').modal('show');
			    	$('#modal-mailing').modal('hide');
			    },					
				success : function(response) {
					
				},
			    complete: function(){
					setTimeout(function() {
					      $("#loader").modal("hide");
					    }, 500);
				},						
				error : function(xhr, ajaxOptions, thrownError) {
					alert('error');
				}				
	    	});	
			return true;
		});
				
				
	</script>	
	<!-- -------------------------------------------------------------- -->	