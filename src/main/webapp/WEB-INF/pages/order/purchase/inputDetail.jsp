<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  	
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
  	
  	<!-- -------------------------------------------------------------- --> 
 	<!-- Detail section -->
 	<!-- -------------------------------------------------------------- -->

    <div id="inputDetail" class="section">

	 	<div class="title-catalog">
			<b>INVENTORY INPUT ORDER ${purchaseCart.orderNumber}</b>
			<hr>			
		</div>	

	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        		<b>Date</b>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">						
				<fmt:formatDate value="${purchaseCart.purchaseDate}" pattern="dd-MM-yyyy HH:mm" />  
	       </div>
	    </div>	
	    <hr />			
	    <div class="row">
	        <div class="col-12 col-md-3 mt-2">
	        	<h6>
	        		<b class="info-total">Total Order</b>
	        	</h6>
	        </div>        
	        <div class="col-12 col-md-9 mt-2">
				<h6 class="info-total">
					<b>
						<fmt:formatNumber value = "${purchaseCart.totalUSD}" type="currency" currencySymbol="$"/>&nbsp;USD
						|	
						<fmt:formatNumber value = "${purchaseCart.totalMXN}" type="currency" currencySymbol="$"/>&nbsp;MXN
					</b>
				</h6>
	       </div>
	    </div>		

	    <div class="table-responsive table-items">
	         <table class="table table-sm text-center table-striped table-hover edit">
	             <tr class="primary">
	                 <th class="text-left"># Lot</th>
	                 <th class="text-left">Part Number</th>
	                 <th class="text-left">Part Description</th>		             
	                 <th class="text-center">Input Qty.</th>
	                 <th class="text-right">Unit USD</th>
	                 <th class="text-center">Notes</th>
	             </tr>   
				<c:forEach items="${purchaseCart.items}" var="item" varStatus="statusItem">		             
		        	<c:forEach items="${item.inputs}" var="input" varStatus="statusInput">
			             <tr>
			             	 <td class="text-left">			             	 	
			             	 	${input.lot}			             	 	
			             	 </td>
			                 <td class="text-left">
			                 	${item.value}
			                 </td>
			                 <td class="text-left">
			                 	${item.description}
			                 </td>		             
			                 <td class="text-center">
			                   	${input.receivedQty}
			                 </td>
			                 <td class="text-right">			                 	
			                 	<fmt:formatNumber value = "${item.priceUSD}" type="currency"/>								
			                 </td>	                 
			                 <td class="text-center">
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
				<a href="${pageContext.request.contextPath}/view/lst/inventoryInput" class="btn btn-secondary btn-sm">
		      		<span><i class="fas fa-times"></i></span> 
	     				Regresar	
		      	</a>			    	
			</div>    
		 </div>	
	</div>
	