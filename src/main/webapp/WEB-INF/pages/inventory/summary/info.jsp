<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<section id="tabla">
          
 	<div class="title-catalog">
		<b>SUMMARY INFORMATION PRODUCT</b>
		<hr>			
	</div>	

	<div class="table-responsive">
	    <table class="table table-sm text-center table-striped table-hover">
	        <tr class="primary">
	            <th class="text-left">Part Number</th>
	            <th class="text-left">Part Description</th>
	            <th class="text-right">Stock Max.</th>
	            <th class="text-right">Stock Min.</th>
	            <th class="text-right">Stock</th>
	            <th class="text-right">Price USD</th>
	            <th class="text-right">Cost USD</th>
	            <th>Unit</th>
	        </tr>
	         <tr>
	           	 <td class="text-left">
	           	 	<b>${inventory.value}</b>
	           	 </td>
	             <td class="text-left">
	             	<b>${inventory.description}</b>
	             </td>
	             <td class="text-right">
	             	${inventory.stockMax}
	             </td>
	          	 <td class="text-right">
	          	 	${inventory.stockMin}
	          	 </td>               		 
	           	 <td class="text-right">
	           	 	${inventory.stock}
	           	 </td>
	             <td class="text-right">
	             	<span class="currency">${inventory.priceUSD}</span>
	             </td>
	             <td class="text-right">
	             	<span class="currency">${inventory.costUSD}</span>
	             </td>
	          	 <td>
	          	 	${inventory.unit}
	          	 </td>  		                		                                 
	         </tr>
	   </table>
	</div>
	    
	<br /><br />  
	  
 	<div class="title-catalog">
		<b>SUMMARY INFORMATION LOTS</b>
		<hr>			
	</div>		    
	    
	<div class="table-responsive">
	    <table class="table table-sm text-center table-striped table-hover">
	        <tr class="primary">
	            <th class="text-left">Purchase Order</th>
	            <th class="text-left"># Lot</th>
	            <th class="text-left">Provider</th>
	            <th class="text-right">Qty.</th>
	            <th class="text-right">Stock</th>
	            <th>Purchase Date</th>
	            <th>Expired Date</th>
	            <th class="text-right">PriceUSD</th>
	        </tr>
			<c:forEach items="${inventoryLots}" var="inventoryRow">
		         <tr>
		           	 <td class="text-left">
		           	 	${inventoryRow.purchaseOrder}
		           	 </td>
		             <td class="text-left">
		             	${inventoryRow.lot}
		             </td>
		             <td class="text-left">
		             	${inventoryRow.provider}
		             </td>
		          	 <td class="text-right">
		          	 	${inventoryRow.orderQty}
		          	 </td>               		 
		           	 <td class="text-right">
		           	 	${inventoryRow.stock}
		           	 </td>
		           	 <td>
		           	 	<fmt:formatDate value="${inventoryRow.purchaseDate}" pattern="dd-MM-yyyy HH:mm" />	           	 	
		           	 </td>
		           	 <td>
		           	 	<fmt:formatDate value="${inventoryRow.expiredDate}" pattern="dd-MM-yyyy HH:mm" />	           	 	
		           	 </td>
		             <td class="text-right">	             	
		             	<span class="currency">${inventoryRow.priceUSD}</span>
		             </td>       		                		                                 
		         </tr>
		     </c:forEach>
	   </table>     
	</div>
	        
     
 </section> 