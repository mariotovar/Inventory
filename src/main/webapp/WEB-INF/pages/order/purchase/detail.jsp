<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  	
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
  	
  	<!-- -------------------------------------------------------------- --> 
 	<!-- Detail section -->
 	<!-- -------------------------------------------------------------- -->

<form:form method="POST" modelAttribute="purchaseOrder">

    <div id="detail" class="section">

	 	<div class="title-catalog">
			<b>
				<spring:message code="label.purchaseord.title"/> 
				&nbsp;${purchaseOrder.orderNumber}
			</b>
			<hr>			
		</div>	

		<c:choose>
			<c:when test="${action eq 'READ'}">
				<div class="order-success blink">
					* <spring:message code="label.action.success"/> 
				</div>
			</c:when>	
			<c:when test="${action eq 'DELETE'}">
				<div class="label-warning">		
					<spring:message code="label.action.delete"/> 
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
	        			<spring:message code="label.purchaseord.status"/>
	        		</b>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">				
				<spring:message code="label.status.${purchaseOrder.status}"/> 
	       </div>
	    </div>	
	    <hr />
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        		<b>
	        			<spring:message code="label.purchaseord.date"/>
	        		</b>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">						
				<fmt:formatDate value="${purchaseOrder.purchaseDate}" pattern="dd-MM-yyyy HH:mm" />  
	       </div>
	    </div>	
	    <hr />		
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<h6>
	        		<b>
	        			<spring:message code="label.provider.title"/>
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">
				<h6>
					${purchaseOrder.provider.value}
				</h6>
	       </div>
	    </div>
	    <hr />		
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<h6>
	        		<b>
	        			<spring:message code="label.provider.email"/>
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">
				<h6>
					${purchaseOrder.provider.email}
				</h6>
	       </div>
	       <c:if test="${not empty purchaseOrder.provider.emailCC1}">
			<div class="col-12 col-md-3 mt-2">
				<h6>
					<b> <spring:message code="label.provider.emailCC" /> (1)
					</b>
				</h6>
			</div>
			<div class="col-12 col-md-9 mt-2">
				<h6>${purchaseOrder.provider.emailCC1}</h6>
			</div>
		</c:if>
		<c:if test="${not empty purchaseOrder.provider.emailCC2}">
			<div class="col-12 col-md-3 mt-2">
				<h6>
					<b> <spring:message code="label.provider.emailCC" /> (2)
					</b>
				</h6>
			</div>
			<div class="col-12 col-md-9 mt-2">
				<h6>${purchaseOrder.provider.emailCC2}</h6>
			</div>			
		</c:if>
	    </div>
	    <hr />		
		<div class="row">
			<div class="col-12 col-md-3 mt-2">
				<h6>
					<b>
						<spring:message code="label.shipto.title" />
					</b>
				</h6>
			</div>
			<div class="col-12 col-md-9 mt-2">
				<h6>
					${purchaseOrder.shipto.value} | 
					${purchaseOrder.shipto.address}, ${purchaseOrder.shipto.zip}, ${purchaseOrder.shipto.state}, ${purchaseOrder.shipto.country}
				</h6>
			</div>
		</div>
		<hr />	    
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<h6>
	        		<b class="info-total">
	        			<spring:message code="label.total.order"/>
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">
				<h6 class="info-total">
					<b>
						<span class="currency">${purchaseOrder.totalUSD}</span> USD
						|
						<span class="currency">${purchaseOrder.totalMXN}</span> MXN
					</b>
				</h6>
	       </div>
	    </div>		

	    <div class="table-responsive table-items">
	         <table class="table table-sm text-center table-striped table-hover edit">
	             <tr class="primary">
	             	 <th class="text-left">
	             	 	<spring:message code="label.cart.item"/>
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
	                 	<spring:message code="label.cart.unit.usd"/>
	                 </th>
	               
	                 <th class="text-right">
	                 	<spring:message code="label.cart.total.usd"/>
	                 </th>
	                 <th class="text-right">
	                 	<spring:message code="label.cart.unit.mxn"/>
	                 </th>
	                 <th class="text-right">
	                 	<spring:message code="label.cart.total.mxn"/>
	                 </th>                
	             </tr>     
 				 <c:forEach items="${purchaseOrder.items}" var="item" varStatus="status">
		             <tr>
		               	 <td class="text-left">
		               	 	${status.index + 1}
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
		                 <td>
		                 	${item.qty}
		                 </td>
		                 <td class="text-right">
		                 	<span class="currency">${item.priceUSD}</span>		                 	
		                 </td>     
		                 <td class="text-right">
		                 	<span class="currency">${item.subtotalUSD}</span>		                 		                 	
		                 </td>  
		                 <td class="text-right">
		                 	<span class="currency">${item.priceMXN}</span>		                 
		                 </td>     
		                 <td class="text-right">
		                 	<span class="currency">${item.subtotalMXN}</span>		                 		                 	
		                 </td>     			         		                    			                                
		             </tr>
	        	 </c:forEach>		              
	        </table>     
	    </div>  
	    <c:if test="${not empty purchaseOrder.notes}">
	    	<span class="notes">
	    		*** ${purchaseOrder.notes}
	    	</span>
	    </c:if>	    
	    <hr />		
	    
	    <div class="row">
	        <div class="col-12 col-md-2 mt-2">
	        	<h6>
	        		<b class="info-total">
	        			<spring:message code="label.purchaseord.total.received"/>
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-8 mt-2">
				<h6 class="info-total">
					<b>
						<span class="currency">${purchaseOrder.totalUSD}</span>&nbsp;USD&nbsp;
						(<spring:message code="label.payment.total"/>) 
						|
						<span class="currency">${purchaseOrder.amountReceived}</span>&nbsp;USD&nbsp;
						(<spring:message code="label.purchaseord.received"/>)
					</b>
				</h6>
	       </div>
	       <div class="col-12 col-md-2 mt-2">
				<h6 class="alert-red">
					<c:if test="${purchaseOrder.amountReceived >= purchaseOrder.totalUSD}">
						<b>
							!!!&nbsp;<spring:message code="label.complete"/>
						</b>						
					</c:if>
				</h6>
	       </div>		       
	    </div>		    

		<c:choose>
			<c:when test="${purchaseOrder.amountReceived > 0}">
			    <div class="table-responsive table-items">
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
			                 <th class="text-center">
			                 	<spring:message code="label.purchaseord.entry"/>
			                 </th>
			                 <th class="text-right">
			                 	<spring:message code="label.cart.unit.usd"/>
			                 </th>
			                  <th class="text-right">
	                   				<spring:message code="label.product.serial"/>
	               			  </th>
			                 <th class="text-right">
			                 	<spring:message code="label.cart.total.usd"/>
			                 </th>
			                 <th width="30%" class="text-center">
			                 	<spring:message code="label.cart.notes"/>
			                 </th>
			                 <th class="text-center">
			                 	<spring:message code="label.purchaseord.document"/>
			                 </th>
			             </tr>   
						<c:forEach items="${purchaseOrder.items}" var="item" varStatus="statusItem">		             
				        	<c:forEach items="${item.inputs}" var="input" varStatus="statusInput">
					             <tr>
					             	 <td class="text-left">			             	 	
					             	 	${input.lotNumber}			             	 	
					             	 </td>
					                 <td class="text-left">
					                 	${item.value}
					                 </td>
					                 <td class="text-left">
					                 	${item.description}
<%-- 					                 	${item.product.apply()} --%>
					                 </td>		             
					                 <td class="text-center">
<%-- 					                 	${item.qty} --%>

			 								<c:choose>
			    								<c:when test="${item.product.apply()}">
<!-- 							                 			<label>	 -->
							                 			1
<!-- 							                 			</label> -->
							                 	</c:when>
							                 	<c:otherwise>
<!-- 			    														<label> -->
			    														${item.qty}
<!-- 			    														</label>	 -->
			    								</c:otherwise>
							                 </c:choose>
					                 </td>
					                 <td class="text-center">
<!-- 					                 <label>	 -->
					                   	${input.receivedQty}
<!-- 					                   	</label> -->
					                 </td>
					                 <td class="text-right">	
					                 	<span class="currency">${item.priceUSD}</span>		                 	
					                 </td>
					                  <td class="text-left">
					                 	${input.serial}
					                 </td>	                 
					                 <td class="text-right">	
					                 	<span class="currency">${item.priceUSD * input.receivedQty}</span>		                 	
					                 </td>	                 
					                 <td class="text-left">
					                 	${input.notes}
					                 </td>
					                 <td class="text-center">
					                  <c:if test = "${fn:contains(existFile, input.lotNumber)}">
					                	<a href="${pageContext.request.contextPath}/order/purchase/lot/download/${purchaseOrder.year}/${input.lotNumber}">
					                	
											<i class="fas fa-download"></i>
										</a>
										</c:if>
					                 </td>					                 
					             </tr>
				             </c:forEach>   
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
						<span class="currency">${purchaseOrder.amountPaidUSD}</span>&nbsp;USD&nbsp;
						(<spring:message code="label.payment.paid"/>) 
						|
						<span class="currency">${purchaseOrder.amountToPay}</span>&nbsp;USD&nbsp;
						(<spring:message code="label.payment.toPay"/>)
					</b>
				</h6>
	       </div>
	        <div class="col-12 col-md-2 mt-2">
				<h6 class="alert-red">
					<c:if test="${purchaseOrder.amountPaidUSD >= purchaseOrder.totalUSD}">
						<b>
							!!!&nbsp;<spring:message code="label.complete"/>
						</b>						
					</c:if>
				</h6>
	       </div>		       
	    </div>			    
	    <c:choose>
			<c:when test="${purchaseOrder.amountPaidUSD > 0}">
			    <div class="table-responsive table-items">
			         <table class="table table-sm text-center table-striped table-hover edit">
			             <tr class="primary">
			             	 <th class="text-center">
			             	 	<spring:message code="label.payment.date"/>
			             	 </th>
			                 <th class="text-right">
			                 	<spring:message code="label.payment.amountUSD"/>
			                 </th>
			                 <th width="60%"  class="text-center">
			                 	<spring:message code="label.payment.notes"/>
			                 </th>      
			                  <th class="text-center">
		                 	<spring:message code="label.purchaseord.document"/>
		                 </th>           
			             </tr>     
		 				 <c:forEach items="${purchaseOrder.payments}" var="payment">
				             <tr>
				             
<!-- 				               	 	<span class="currency"></span> -->
				               	 <td class="text-center">
				               	 
				               	 	<fmt:formatDate value="${payment.paymentDate}" pattern="dd-MM-yyyy HH:mm" />  
				               	 </td>
				               	 <td class="text-right">
				               	 	<span class="currency">${payment.amountUSD}</span>
				               	 </td>
				                 <td class="text-left">
				                 	${payment.notes}
				                 </td>  
				                  <td class="text-center">
	                 				 <c:if test = "${fn:contains(PurchaseexistFilePayment, payment.payNumber)}">
				                	<a href="${pageContext.request.contextPath}/order/purchase/payment/download/${purchaseOrder.year}/${payment.payNumber}">
				                	
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
	    
        <hr />		
		    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<h6>
	        		<b class="info-total">
	        			<spring:message code="label.expense.title"/> 	        			
	        		</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">
				<h6 class="info-total">
					<b>
						<span class="currency">${purchaseOrder.amountExpensesMXN}</span>&nbsp;MXN
					</b>
				</h6>
	       </div>
	    </div>		    
	    <c:choose>
			<c:when test="${purchaseOrder.amountExpensesMXN > 0}">
			    <div class="table-responsive table-items">
			        <table class="table table-sm text-center table-striped table-hover edit">
			             <tr class="primary">
			             	 <th class="text-center">
			             	 	<spring:message code="label.expense.date"/> 
			             	 </th>			                 
			                 <th class="text-right">
			                 	<spring:message code="label.expense.amount"/>&nbsp;MXN
			                 </th>
							 <th class="text-center">
							 	<spring:message code="label.expense.type"/> 
							 </th>
			                 <th width="50%" class="text-center">
			                 	<spring:message code="label.expense.notes"/> 
			                 </th>                 
			             </tr>     
						 <c:forEach items="${purchaseOrder.expenses}" var="expense">
				             <tr>
				               	 <td class="text-center">
				               	 	<fmt:formatDate value="${expense.expenseDate}" pattern="dd-MM-yyyy HH:mm" />  
				               	 </td>
				               	 <td class="text-right">
				               	 	<span class="currency">${expense.amountMXN}</span>
				               	 </td>					               	 
				               	 <td class="text-center">
							    	<c:forEach items="${expenseTypes}" var="expenseType">
				                 		<c:if test="${expense.type==expenseType.pk}">
				                 			<b><spring:message code="label.expense.type.${expenseType}"/></b>
				                 		</c:if>
							    	</c:forEach>				               	 	
				                 </td>	                 
				                 <td class="text-left">
				                 	${expense.notes}
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
		    	<!-- 
				<c:if test="${action eq 'DELETE'}">
		    		<div class="text-right check-mail">
			      		<form:checkbox path="emailing" checked="checked" /> 
			      		<spring:message code="label.purchaseord.send.mail"/>			      			
			       	</div>   
			    	<br />
				</c:if>
				 -->		
				<c:choose>
					<c:when test="${action eq 'CLOSE' or action eq 'DELETE'}">
						<a href="${pageContext.request.contextPath}/order/purchase/history" class="btn btn-secondary btn-sm">
				      		<span><i class="fas fa-times"></i></span> 
			     				<spring:message code="label.back"/>	
				      	</a>
					</c:when>	
					<c:otherwise>
						<button class="btn btn-primary btn-sm mail-confirm">
				      		<span><i class="fas fa-print"></i></span> 
			     				<spring:message code="label.purchaseord.send.mail"/>	
				      	</button>						
						<a href="${pageContext.request.contextPath}/view/lst/purchaseOrder" class="btn btn-secondary btn-sm">
				      		<span><i class="fas fa-times"></i></span> 
			     				<spring:message code="label.back"/>	
				      	</a>				      	
					</c:otherwise>			
				</c:choose>				    				    	
				<c:choose>
					<c:when test="${action eq 'CLOSE'}">
				      	<button type="button" class="order-submit btn btn-success btn-sm">
				      		<span><i class="far fa-trash-alt"></i></span> 
		      				<spring:message code="label.action.close.order"/>		      				
				      	</button>
					</c:when>	
					<c:when test="${action eq 'DELETE'}">
				      	<button type="button" class="order-submit btn btn-danger btn-sm">
				      		<span><i class="far fa-trash-alt"></i></span> 
		      				<spring:message code="label.action.cancel.order"/>		      				
				      	</button>
					</c:when>			
				</c:choose>				    	
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
        	${purchaseOrder.provider.email}?
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

		$(".order-submit").click(function(e) {		
			e.preventDefault();
			var form = $(this).closest('form');			
			$('#loader').modal('show');
			form.submit();
			return true;
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
				url : context()+"/order/purchase/mailing",
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