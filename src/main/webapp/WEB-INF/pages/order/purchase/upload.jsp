<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%-- <form:form method="POST" modelAttribute="purchaseOrder"> --%>
<form:form method="POST" modelAttribute="purchaseOrder" enctype="multipart/form-data"> 

	<div id="expenses" class="wide-section">

		<div class="title-catalog">
			<b>
				<spring:message code="label.purchaseord.attach"/> 
				&nbsp;-&nbsp; 
				<spring:message code="label.purchaseord.title"/> 
				&nbsp;${purchaseOrder.orderNumber}
			</b>
			<hr>			
		</div>			        			
		<br />		    			          
	    <div class="detail">  		     	     	    	 
	    
		<c:choose>
			<c:when test="${purchaseOrder.amountReceived > 0}">	    	            
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
			                 <th class="text-right">
			                 	<spring:message code="label.cart.unit.usd"/>
			                 </th>
			                 <th class="text-right">
			                 	<spring:message code="label.cart.total.usd"/>
			                 </th>
			                 <th width="40%" class="text-center">
			                 	<spring:message code="label.purchaseord.upload.document"/>
			                 </th>
			                 <th width="2%" class="text-center">
<%-- 			                 	<spring:message code="label.purchaseord.upload.document"/> --%>
			                 </th>
			             </tr>   
						<c:forEach items="${purchaseOrder.items}" var="item" varStatus="statusItem">		             
				        	<c:forEach items="${item.inputs}" var="input" varStatus="statusInput">
					             <tr>
					             	 <td class="text-left">
					             	 	<label>
					             	 		${input.lotNumber}
					             	 	</label>
					             	 </td>
					                 <td class="text-left">
					                 	<label>${item.value}</label>
					                 </td>
					                 <td class="text-left">
					                 	<label>${item.description}</label>
					                 </td>		             
					                 <td class="text-center">
					                 	<label>${item.qty}</label>
					                 </td>
					                 <td class="text-center">
					                   	<label>${input.receivedQty}</label>
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
<!-- 					                  <input type="file" name="files"  /> -->
					                <div class="input-group mb-3">
							            <div class="custom-file">
							                <input type="file" name="files"  class="custom-file-input inputGroupFile" id="inputGroupFile${statusItem.index}_${statusInput.index}"/>
							                <label class="custom-file-label lblPurchaseOrd" for="inputGroupFile02"><spring:message code="label.purchaseord.upload.document"/> </label>
										<form:input type="hidden" class="attach" id="attach${statusItem.index}_${statusInput.index}" path="items[${statusItem.index}].inputs[${statusInput.index}].attach" value=""/>
							            </div>
							            							           
							        </div>
               						<c:if test="${not empty error}">
												<div class="error">
													<spring:message code="message.error.emptyFile" />
												</div>
									</c:if>
									<c:if test="${not empty formatError}">
												<div class="error">
													<spring:message code="message.error.fileFormat" />
												</div>
									</c:if>
									   <td>
		                 		 		<c:if test = "${fn:contains(existFileLot, input.lotNumber)}">
					                	<a href="${pageContext.request.contextPath}/order/purchase/lot/download/${purchaseOrder.year}/${input.lotNumber}">
					                	
											<i class="fas fa-download"></i>
										</a>
									</c:if>
		                 			</td> 
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
			        <button type="submit" class="btn btn-primary btn-sm">
			        	&nbsp;<spring:message code="label.purchaseord.save.document"/>
			        	<span><i class="fas fa-chevron-circle-right"></i></span>            	
			        </button>
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
				    	    
	              
	     </div>

	</div>
	<script>

	$(".inputGroupFile").each(function(index){
		var id = $(this).attr("id");
		$('#'+id).on('change',function(){
            var fileName = $(this).val();
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length);
            $(this).next('.lblPurchaseOrd').html(fileName);
            $(this).next().next().val(fileName);
//	            $('.lblPurchaseOrd').next('.attach').val(fileName);
        })
          
	});
	</script>
 </form:form> 
 