<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<form:form method="POST" modelAttribute="saleOrder" enctype="multipart/form-data">

	<div id="payments" class="section">

		<div class="title-catalog">
			<b>
				<spring:message code="label.payment.title"/> 
				&nbsp;<spring:message code="label.purchaseord.attach"/> 				
				&nbsp;-&nbsp; 
				<spring:message code="label.saleord.title"/> 
				&nbsp;${saleOrder.orderNumber}
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
					<h4><span class="currency">${saleOrder.amountCharged}</span>&nbsp;MXN&nbsp;</h4>					
		        </div>
		        <div class="col-12 col-md-4 text-right">
		          <h4><span class="currency">${saleOrder.amountToCharge}</span>&nbsp;MXN&nbsp;</h4>	
		        </div>
		        <div class="col-12 col-md-4 text-right">
		        	<h4><span class="currency">${saleOrder.totalMXN}</span>&nbsp;MXN&nbsp;</h4>	
		        </div>              	              
	         </div>     
	         	         		    	         
		    <div class="table-responsive table-reduce">
		    	<div class="text-right">
				     <button type="button" class="btn btn-outline-prin m-1 btn-add">
				        <i class="fas fa-plus"></i>
				     </button>	 		    		    	
		    	</div>
		         <table class="table table-sm text-center table-striped table-hover edit">
		             <tr class="primary">
		                 <th class="text-left">
		                 	<spring:message code="label.payment.date"/> 
		                 </th>
		                 <th class="text-right">
		                 	<spring:message code="label.payment.amountMXN"/> 
		                 </th>
		                 <th width="50%" class="text-center">
		                 	<spring:message code="label.purchaseord.upload.document"/> 
		                 </th>
		                  <th width="2%" class="text-center">
		                  <spring:message code="label.purchaseord.document"/>
		                 </th>
		             </tr>    	
		             <c:forEach items="${saleOrder.payments}" var="payment" varStatus="pays">
		             	<tr>
		                 <td class="text-left">
		                 	<label>
		                 		<fmt:formatDate value="${payment.paymentDate}" pattern="dd-MM-yyyy HH:mm" />  
							</label>
		                 </td>	    
						 <td class="text-right">
		                 	<label>
		                 		<span class="currency">${payment.amountMXN}</span>
							</label>
		                 </td>	
		                 <td class="text-left">
			                <div class="input-group mb-3">
					            <div class="custom-file">
					                <input type="file" name="files"  class="custom-file-input inputFilePayment" id="inputFilePayment${pays.index}"/>
					                <label class="custom-file-label lblPayment" for="inputGroupFile02"><spring:message code="label.purchaseord.upload.document"/> </label>
					                <form:input type="hidden" class="attach" id="attach${pays.index}" path="payments[${pays.index}].attach" value=""/>
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
		                 		 <c:if test = "${fn:contains(existFilePayment, payment.payNumber)}">
					                	<a href="${pageContext.request.contextPath}/order/sale/payment/download/${saleOrder.year}/${payment.payNumber}">
					                	
											<i class="fas fa-download"></i>
										</a>
									</c:if>
		                 </td>
		                </tr>		             
		             </c:forEach>             		               
		        </table>     
		    </div>	 
		    
		     <div class="buttons text-right">
				<a href="${pageContext.request.contextPath}/order/sale/payment/${saleOrder.year}/${saleOrder.pkSale}" class="btn btn-info btn-sm">
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
	$(".inputFilePayment").each(function(index){
		var id = $(this).attr("id");
		$('#'+id).on('change',function(){
            var fileName = $(this).val();
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length);
            $(this).next('.lblPayment').html(fileName);
            $(this).next().next().val(fileName);
        })
          
});
	</script>
     
 </form:form> 