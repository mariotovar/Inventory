<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  	
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
  	
  	<!-- -------------------------------------------------------------- --> 
 	<!-- Detail section -->
 	<!-- -------------------------------------------------------------- -->

<form:form method="POST" modelAttribute="inventoryInput">

    <div id="detail" class="section">

	 	<div class="title-catalog">
			<b>
				<spring:message code="label.input.title"/> ${inventoryInput.orderNumber}
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
						|
						<span class="currency">${inventoryInput.totalMXN}</span>&nbsp;MXN
					</b>
				</h6>
	       </div>
	    </div>		

	    <div class="table-responsive table-items">
	         <table class="table table-sm text-center table-striped table-hover edit">
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
			             	 	<span class="print-ticket" data-pk="${row.pk}">
               	 					<i class="fas fa-print"></i>
               	 				</span>						               	 
			               	 	${row.lotNumber}
			               	 </td>			             
			               	 <td class="text-left">
			               	 	${row.value}
			               	 </td>
			                 <td class="text-left">
			                 	${row.description}
			                 </td>
							<td class="text-left">
								${row.condition.key}
							</td>				                 
			                 <td class="text-right">
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
		    	<br />
				<a href="${pageContext.request.contextPath}/view/history/inventoryInput" class="btn btn-secondary btn-sm">
		      		<span><i class="fas fa-times"></i></span> 
	     				<spring:message code="label.back"/>
		      	</a>	
			</div>    
		 </div>	
	</div>
	
</form:form>

	<!-- -------------------------------------------------------------- -->	
	<script>

		$(".print-ticket").click(function(e) {
			e.preventDefault();
			var ticket = $(this).attr("data-pk");
			$.ajax({
	    		async: true,
				type : "GET",
				url : context()+"/inventory/input/printing/"+ticket,
			    beforeSend: function(){
			    	$('#loader').modal('show');
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