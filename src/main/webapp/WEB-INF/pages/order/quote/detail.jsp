<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  	
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
  	
  	<!-- -------------------------------------------------------------- --> 
 	<!-- Detail section -->
 	<!-- -------------------------------------------------------------- -->

<form:form method="POST" modelAttribute="quoteOrder">

    <div id="detail" class="section">

	 	<div class="title-catalog">
			<b>
				<spring:message code="label.quote.title"/> 
				&nbsp;${quoteOrder.orderNumber}
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
		</c:choose>

	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        		<b>
	        			<spring:message code="label.quote.status"/>
	        		</b>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">				
				<spring:message code="label.status.${quoteOrder.status}"/> 
	       </div>
	    </div>	
	    <hr />
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        		<b>
	        			<spring:message code="label.quote.date"/>
	        		</b>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">						
				<fmt:formatDate value="${quoteOrder.quoteDate}" pattern="dd-MM-yyyy HH:mm" />  
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
					${quoteOrder.client.value}
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
					${quoteOrder.client.email}
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
						<span class="currency">${quoteOrder.totalMXN}</span> MXN
						|
						<span class="currency">${quoteOrder.totalUSD}</span> USD
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
	                 	<spring:message code="label.cart.unit.mxn"/>
	                 </th>	               
	                 <th class="text-right">
	                 	<spring:message code="label.cart.total.mxn"/>
	                 </th>
	                 <th class="text-right">
	                 	<spring:message code="label.cart.unit.usd"/>
	                 </th>
	                 <th class="text-right">
	                 	<spring:message code="label.cart.total.usd"/>
	                 </th>                
	             </tr>     
 				 <c:forEach items="${quoteOrder.items}" var="item" varStatus="status">
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
		                 	<span class="currency">${item.costMXN}</span>		                 	
		                 </td>     
		                 <td class="text-right">
		                 	<span class="currency">${item.subCostMXN}</span>		                 		                 	
		                 </td>  
		                 <td class="text-right">
		                 	<span class="currency">${item.costUSD}</span>		                 
		                 </td>     
		                 <td class="text-right">
		                 	<span class="currency">${item.subCostUSD}</span>		                 		                 	
		                 </td>     			         		                    			                                
		             </tr>
	        	 </c:forEach>		              
	        </table>     
	    </div>  
 
	    <div class="actions">           
			<div class="text-right buttons">				 
		    	<br />		
				<c:choose>
					<c:when test="${action eq 'DELETE'}">
						<a href="${pageContext.request.contextPath}/order/quote/history" class="btn btn-secondary btn-sm">
				      		<span><i class="fas fa-times"></i></span> 
			     				<spring:message code="label.back"/>	
				      	</a>
				      	<button type="button" class="order-submit btn btn-danger btn-sm">
				      		<span><i class="far fa-trash-alt"></i></span> 
		      				<spring:message code="label.action.cancel.order"/>		      				
				      	</button>				      	
					</c:when>	
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/view/lst/quoteOrder" class="btn btn-secondary btn-sm">
				      		<span><i class="fas fa-times"></i></span> 
			     				<spring:message code="label.back"/>	
				      	</a>	
				      	<c:if test="${quoteOrder.status ne 'CANCEL'}">
							<button class="btn btn-primary btn-sm mail-confirm">
					      		<span><i class="fas fa-print"></i></span> 
				     				<spring:message code="label.quote.send.mail"/>	
					      	</button>				      	
				      	</c:if>
					</c:otherwise>			
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
        	${quoteOrder.client.email}?
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
				url : context()+"/order/quote/mailing",
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