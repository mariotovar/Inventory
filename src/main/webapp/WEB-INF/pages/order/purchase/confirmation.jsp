<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" modelAttribute="purchaseOrder">

	<div id="expenses" class="wide-section">

		<div class="title-catalog">
			<b>
				<spring:message code="label.purchaseord.confirmation"/> 
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
	          <h4><span class="currency">${purchaseOrder.amountReceived}</span> USD</h4>
	        </div>
	        <div class="col-12 col-md-4 text-right">
	        	<h4><span class="currency">${purchaseOrder.totalUSD}</span> USD</h4>
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
		                 <th class="text-center">
		                 	<spring:message code="label.purchaseord.entry"/>
		                 </th>
 						<th class="text-center">
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
					<c:forEach items="${purchaseOrder.items}" var="item" varStatus="statusItem">		             
			        	<c:forEach items="${item.inputs}" var="input" varStatus="statusInput">
				             <tr>
				             	 <td class="text-left">
				             	 	<label>
	 					        		 <c:choose>
							             	<c:when test="${empty input.lot}">
							             		TBD
							             	</c:when>
							             	<c:otherwise>
							             		${input.lotNumber}					             		
							             	</c:otherwise>						             		 		
							             </c:choose>
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
				                 <td class="text-center">
				                   	<label>${input.receivedQty}</label>
				                   	 
				                 </td>
				                  <td class="text-left">
				                 	<label>${input.serial}</label>
				                 </td>				                 
				                 <td class="text-right">
				                 	<label>
				                 		<span class="currency">${item.priceUSD}</span>
									</label>
				                 </td>	    	 
								 <td class="text-right">
				                 	<label>
				                 		<span class="currency">${item.priceUSD * input.receivedQty}</span>
									</label>
				                 </td>	
				                 <td class="text-left">
				                 	<label>${input.notes}</label>
				                 </td>			                 
				             </tr>
			             </c:forEach>   
		             </c:forEach>		              	             		               
		        </table>     
		    </div>	 
		    
		     <div class="buttons text-right">
				<a href="${pageContext.request.contextPath}/order/purchase/lotReceived/${purchaseOrder.year}/${purchaseOrder.pkPurchase}" class="btn btn-info btn-sm">
		      		<span><i class="fas fa-times"></i></span> 
	     				<spring:message code="label.back"/>		
		      	</a>			     
		        <button type="submit" class="btn btn-success btn-sm">
		        	&nbsp;<spring:message code="label.purchaseord.save.received"/>	
		        	<span><i class="fas fa-chevron-circle-right"></i></span>            	
		        </button>
		     </div> 		            	         
	              
	     </div>

	</div>

 </form:form> 
 