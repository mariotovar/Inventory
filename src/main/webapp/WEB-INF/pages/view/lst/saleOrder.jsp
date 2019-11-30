<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<section id="tabla">
	<div class="float-right">
         <a href="${pageContext.request.contextPath}/order/sale/start" class="btn btn-outline-prin m-2">
         	<span><i class="fas fa-plus"></i></span> 
         	<spring:message code="label.saleord.newOrder"/>
         </a>
         <a href="${pageContext.request.contextPath}/order/sale/history" class="btn btn-outline-prin m-2">
         	<span><i class="fas fa-archive"></i></span> 
         		<spring:message code="label.saleord.records"/>
         </a> 
     </div>
     <div class="table-responsive">
     
     	<c:choose>
	 		<c:when test="${listRows.size() > 0}">
          
	         <table class="table table-sm text-center table-striped table-hover">
	             <tr class="primary">
	                 <th class="text-left">
	                 	<spring:message code="label.saleord.orderNumber"/>
	                 </th>
	                 <th>
	                 	<spring:message code="label.saleord.date"/>
	                 </th>
	                 <th class="text-right">
	                 	<spring:message code="label.total.order"/>&nbsp;MXN
	                 </th>
	                 <th class="text-right">
	                 	<spring:message code="label.saleord.shipping"/>&nbsp;MXN
	                 </th>
	                 <th class="text-right">
	                 	<spring:message code="label.purchaseord.paymentMXN"/>&nbsp;
	                 </th>
			         <c:choose>
					 	<c:when test="${action eq 'HISTORY'}">
			         		<th>
			         			<spring:message code="label.purchaseord.status"/>
			         		</th>             
			         	</c:when>		             
						<c:otherwise>
							<th>
								<spring:message code="label.purchaseord.actions"/>
							</th>             
						</c:otherwise>
			         </c:choose>
	             </tr>
				 <c:forEach items="${listRows}" var="saleOrder">
	             	<tr>
		               	 <td class="text-left">
	        				<c:set var="label_detail">
								<spring:message code="label.purchaseord.btn.detail"/>
		                 	</c:set>			 
		               	 	<a href="${pageContext.request.contextPath}/order/sale/detail/${saleOrder.year}/${saleOrder.pk}"
		               	 		data-toggle="tooltip" data-placement="top" title="${label_detail}">
		                		<span><i class="fas fa-file-alt"></i></span> 			                		
		                	</a>
	                		<label>
								&nbsp;${saleOrder.orderNumber}
							</label>								
		               	 </td>	               	 		
		                 <td>
		                 	<label>
		  	               		<fmt:formatDate pattern="dd-MM-yyyy HH:mm"  value = "${saleOrder.saleDate}" />
		                 	</label>
		                 </td>
		                 <td class="text-right">
		                 	<label>
		                 		<span class="currency">${saleOrder.totalOrderMXN}</span>
		                 	</label>
		                 </td>	  
		                 <td class="text-right">
		                 	<label>
		                 		<c:choose>
		                 			<c:when test="${saleOrder.shippingCostMXN >= 0}">
		                 				<span class="currency">${saleOrder.shippingCostMXN}</span>
		                 			</c:when>
		                 			<c:otherwise>
		                 				<span class="currency alert-red">${saleOrder.shippingCostMXN * -1}</span>
		                 			</c:otherwise>
		                 		</c:choose>	                 		
		                 	</label>
		                 </td>		                      
						 <td class="text-right">
						 	<label>
		                 		<span class="currency">${saleOrder.totalPaymentMXN}</span>
		                 	</label>
						 </td> 	                         	                        
				         <c:choose>
						 	<c:when test="${action eq 'HISTORY'}">
				         		<td>
				         			<label>
			         					<b><spring:message code="label.status.${saleOrder.statusOrder}"/></b>	
				         			</label>
				         		</td>             
				         	</c:when>		             
							<c:otherwise>
				                 <td>
									<a href="${pageContext.request.contextPath}/order/sale/payment/${saleOrder.year}/${saleOrder.pk}" class="btn btn-info btn-sm">
										<span><i class="fas fa-money-check-alt"></i></span> 
										<spring:message code="label.purchaseord.btn.payment"/>
									</a> 
									<a href="${pageContext.request.contextPath}/order/sale/close/${saleOrder.year}/${saleOrder.pk}" class="btn btn-success btn-sm">
										<span><i class="fas fa-lock"></i></span> 
										&nbsp;<spring:message code="label.purchaseord.btn.close"/>&nbsp;
									</a> 							
					             </td>
							</c:otherwise>
				         </c:choose>                                 
	             	</tr>
	        	</c:forEach>
	        </table>
	        
	 	</c:when>
	 	<c:otherwise>
	 		<br />
			<div class="alert alert-info no-results">
			  <strong> 
			  	<spring:message code="label.notresult"/> 
			  </strong>.
			</div>		
	 	</c:otherwise>
	 </c:choose>	        
        
       <c:if test="${action == 'HISTORY'}">
        <div class="float-right">
        	<ul class="pagination m-3">
        	<c:forEach begin="0" end="4" varStatus="status">
        		<c:set var="rowYear" value="${currentYear - status.index}" />
        		<c:set var="classYear">
       				<c:choose>
						<c:when test="${year == rowYear}">
		               		<c:out value="btn-outline-select" />
						</c:when>
						<c:otherwise>
		               		<c:out value="btn-outline-prin" />
		               	</c:otherwise>
					</c:choose>		
        		</c:set>
	            <li class="page-item active" aria-current="page">
	               <span class="page-link">
	               		<a href="${pageContext.request.contextPath}/order/sale/history/${rowYear}" class="btn ${classYear}">
	               			${currentYear - status.index}
	               		</a>							
	                 	<span class="sr-only">(current)</span>
	               </span>
	             </li>        		        		
        	</c:forEach>
        	</ul>
          </div>
       </c:if>        
    
    </div>
        
 </section> 