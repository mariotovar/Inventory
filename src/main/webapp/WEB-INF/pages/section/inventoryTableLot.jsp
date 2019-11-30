<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="table-responsive">
    <table class="table table-sm text-center table-striped table-hover">
        <tr class="primary">
            <th class="text-left"># Lot</th>
            <th class="text-left">Part Number</th>
            <th class="text-left">Part Description</th>
            <th class="text-right">Inputs</th>
            <th class="text-right">Outputs</th>
            <th class="text-right">Stock</th>
        </tr>
		<c:forEach items="${ctrlPage.listRows}" var="inventoryRow">
	         <tr>
	             <td class="text-left">
	             	${inventoryRow.lot}
	             </td>
	           	 <td class="text-left">
	           	 	${inventoryRow.value}
	           	 </td>
	             <td class="text-left">
	             	${inventoryRow.description}
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
	         </tr>
	     </c:forEach>
   </table>     
</div>
        