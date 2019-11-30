<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="table-responsive">
    <table class="table table-sm text-center table-striped table-hover">
        <tr class="primary">
            <th class="text-left">Part Number</th>
            <th class="text-left">Part Description</th>
            <th class="text-right">Stock Max.</th>
            <th class="text-right">Stock Min.</th>
            <th class="text-right">Inputs</th>
            <th class="text-right">Outputs</th>
            <th class="text-right">Stock</th>
            <th class="text-right">Price USD</th>
            <th class="text-right">Cost USD</th>
        </tr>
 		<c:forEach items="${ctrlPage.listRows}" var="inventoryRow">
	         <tr>
	           	 <td class="text-left">
	           	 	${inventoryRow.value}
	           	 </td>
	             <td class="text-left">
	             	${inventoryRow.description}
	             </td>
	             <td class="text-right">
	             	${inventoryRow.stockMax}
	             </td>
	          	 <td class="text-right">
	          	 	${inventoryRow.stockMin}
	          	 </td>     
	           	 <td class="text-right">
	           	 	${inventoryRow.inputs}
	           	 </td>
	           	 <td class="text-right">
	           	 	${inventoryRow.outputs}
	           	 </td>	           	 	          	           		 
	           	 <td class="text-right">
	           	 	${inventoryRow.stock}
	           	 </td>
	             <td class="text-right">
	             	<span class="currency">${inventoryRow.priceUSD}</span>
	             </td>
	             <td class="text-right">
	             	<span class="currency">${inventoryRow.costUSD}</span>
	             </td>		                		                                 
	         </tr>
     	</c:forEach>
   </table>
</div>