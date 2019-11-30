<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<form:form class="order" method="POST" modelAttribute="purchaseOrder" enctype="multipart/form-data">

	<div id="payments" class="section">

		<div class="title-catalog">
			<b>
				<spring:message code="label.payment.title"/> 
				&nbsp;<spring:message code="label.purchaseord.attach"/> 				
				&nbsp;-&nbsp; 
				<spring:message code="label.purchaseord.title"/> 
				&nbsp;${purchaseOrder.orderNumber}
			</b>
			<hr>			
		</div>	
	          
	     <div class="detail">
	         
	         <div class="row">
	          <div class="col-12 col-md-4 text-right">
	            <h6 class="titulos mt-1">
	         		<b>
	         			<spring:message code="label.payment.charge"/> 
	         		</b>
	         	</h6>
	          </div>	          
	          <div class="col-12 col-md-4 text-right">
	            <h6 class="titulos mt-1">
	         		<b>
	         			<spring:message code="label.payment.toCharge"/> 
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
					<h4><span class="currency">${purchaseOrder.amountPaidUSD}</span></h4>					
		        </div>
		        <div class="col-12 col-md-4 text-right">
		          <h4><span class="currency">${purchaseOrder.amountToPay}</span></h4>	
		        </div>
		        <div class="col-12 col-md-4 text-right">
		        	<h4><span class="currency">${purchaseOrder.totalUSD}</span></h4>	
		        </div>              	              
	         </div>     
	         	         		    	         
		    <div class="table-responsive table-reduce">
		         <table class="table table-sm text-center table-striped table-hover edit">
		             <tr class="primary">
		                 <th class="text-left">
		                 	<spring:message code="label.payment.date"/> 
		                 </th>
		                 <th class="text-right">
		                 	<spring:message code="label.payment.amountUSD"/> 
		                 </th>
		                 <th width="50%" class="text-center">
		                 	<spring:message code="label.purchaseord.upload.document"/> 
		                 </th>
		                  <th class="text-center">
			                 	<spring:message code="label.purchaseord.document"/>
			                 </th>
		             </tr>    	
		             <c:forEach items="${purchaseOrder.payments}" var="payment" varStatus="pays">
		             	<tr>
		                 <td class="text-left">
		                 	<label>
		                 		<fmt:formatDate value="${payment.paymentDate}" pattern="dd-MM-yyyy HH:mm" />  
							</label>
		                 </td>	    
						 <td class="text-right">
		                 	<label>
		                 		<span class="currency">${payment.amountUSD}</span>
							</label>
		                 </td>	
		                 <td class="text-left">
			                <div class="input-group mb-3">
					            <div class="custom-file">
					                <input type="file" name="files"  class="custom-file-input inputOrderPayment" id="inputOrderPayment${pays.index}"/>
					                <label class="custom-file-label lblOrderPayment" for="inputGroupFile02"><spring:message code="label.purchaseord.upload.document"/> </label>
					                <form:input type="hidden" class="attach" id="attach${pays.index}" path="payments[${pays.index}].attach"/>
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
		    
		     <div class="buttons text-right">
				<a href="${pageContext.request.contextPath}/order/purchase/payment/${purchaseOrder.year}/${purchaseOrder.pkPurchase}" class="btn btn-info btn-sm">
		      		<span><i class="fas fa-times"></i></span> 
	     				<spring:message code="label.back"/>	
		      	</a>			     
		        <button type="submit" class="btn btn-primary btn-sm">
		        	&nbsp;<spring:message code="label.purchaseord.save.document"/>
		        	<span><i class="fas fa-chevron-circle-right"></i></span>            	
		        </button>
		     </div> 		            	         
	              
	     </div>

	</div>
	<script>
	$(".inputOrderPayment").each(function(index){
		var id = $(this).attr("id");
		$('#'+id).on('change',function(){
            var fileName = $(this).val();
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length);
            $(this).next('.lblOrderPayment').html(fileName);
            $(this).next().next().val(fileName);
        })
          
	});
	</script>
     
 </form:form> 