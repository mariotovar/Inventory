<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<section id="tabla">
	<div class="float-right">
         <a href="${pageContext.request.contextPath}/order/purchase/start" class="btn btn-outline-prin m-2">
         	<span><i class="fas fa-plus"></i></span> 
         		<spring:message code="label.purchaseord.newOrder"/>
         </a>
         <a href="${pageContext.request.contextPath}/order/purchase/history" class="btn btn-outline-prin m-2">
         	<span><i class="fas fa-archive"></i></span> 
         		<spring:message code="label.purchaseord.records"/>
         </a> 
     </div>
     <div style="zoom: 0.8" class="table-responsive">
     	
     	<c:choose>
	 		<c:when test="${listRows.size() > 0}">
		         <table class="table table-sm text-center table-striped table-hover">
		             <tr class="primary">
		                 <th>
		                 	<spring:message code="label.purchaseord.orderNumber"/>
		                 </th>
		                 <th>
		                 	<spring:message code="label.purchaseord.date"/>
		                 </th>
		                 <th class="text-left">
		                 	<spring:message code="label.provider.title"/>
		                 </th>		                 
		                 <th class="text-right">
		                 	<spring:message code="label.purchaseord.totalUSD"/>
		                 </th>
		                 <th class="text-right">
		                 	<spring:message code="label.purchaseord.totalMXN"/>
		                 </th>
				         <c:choose>
						 	<c:when test="${action eq 'HISTORY'}">
				                 <th class="text-right">
				                 	<spring:message code="label.purchaseord.paymentUSD"/>
				                 </th>
				                 <th class="text-right">
				                 	<spring:message code="label.purchaseord.expensesMXN"/>
				                 </th>						 	
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
					<c:forEach items="${listRows}" var="purchaseOrder">
			             <tr>
			               	 <td class="text-left">
		        				<c:set var="label_detail">
									<spring:message code="label.purchaseord.btn.detail"/>
			                 	</c:set>			 
			               	 	<a href="${pageContext.request.contextPath}/order/purchase/detail/${purchaseOrder.year}/${purchaseOrder.pk}"
			               	 		data-toggle="tooltip" data-placement="top" title="${label_detail}">
			                		<span><i class="fas fa-file-alt"></i></span> 			                		
			                	</a>
		                		<label>
									&nbsp;${purchaseOrder.orderNumber}
								</label>								
			               	 </td>
			                 <td>
			                 	<label>
			  	               		<fmt:formatDate pattern="dd-MM-yyyy"  value = "${purchaseOrder.purchaseDate}" />
			                 	</label>
			                 </td>
			                 <td class="text-left">
			                 	<label>
			  	               		${purchaseOrder.supplier}
			                 	</label>
			                 </td>			                 
			                 <td class="text-right">
			                 	<label>
			                 		<span class="currency">${purchaseOrder.totalOrderUSD}</span>
			                 	</label>
			                 </td>
			                 <td class="text-right">
			                 	<label>
			                 		<span class="currency">${purchaseOrder.totalOrderMXN}</span>
			                 	</label>
			                 </td>
					         <c:choose>
							 	<c:when test="${action eq 'HISTORY'}">
									 <td class="text-right">
									 	<label>
					                 		<span class="currency">${purchaseOrder.totalPaymentUSD}</span>
					                 	</label>
									 </td>                 
					                 <td class="text-right">
					                 	<label>
					                 		<span class="currency">${purchaseOrder.adminExpensesMXN}</span>
					                 	</label>
					                 </td> 							 	
					         		 <td>
					         			<label>
					         				<b><spring:message code="label.status.${purchaseOrder.statusOrder}"/></b>					         				
					         			</label>
					         		 </td>             
					         	</c:when>		             
								<c:otherwise>
					                 <td class="text-right">
				        				<c:set var="label_cancel">
											<spring:message code="label.purchaseord.btn.cancel"/>
					                 	</c:set>			   					                
					                 	<c:set var="label_update">
											<spring:message code="label.purchaseord.btn.update"/>
					                 	</c:set>						                 						                 
										<c:if test="${purchaseOrder.numberOfInputs == 0}">
											<a href="${pageContext.request.contextPath}/order/purchase/update/${purchaseOrder.year}/${purchaseOrder.pk}"
												data-toggle="tooltip" data-placement="top" title="${label_update}" class="btn btn-primary btn-sm">
												&nbsp;
												<span><i class="fas fa-redo-alt"></i></span> 
												&nbsp;
											</a> 	
											<a href="${pageContext.request.contextPath}/order/purchase/cancel/${purchaseOrder.year}/${purchaseOrder.pk}"
												data-toggle="tooltip" data-placement="top" title="${label_cancel}" class="btn btn-danger btn-sm">
												&nbsp;
												<span><i class="fas fa-trash-alt"></i></span> 
												&nbsp;
											</a> 																                 	
					                 	</c:if>					                 
					                 	<a href="${pageContext.request.contextPath}/order/purchase/received/${purchaseOrder.year}/${purchaseOrder.pk}" class="btn btn-primary btn-sm">
											<span><i class="fas fa-money-check-alt"></i></span> 
											<spring:message code="label.purchaseord.btn.received"/>
										</a> 
					                 	<a href="${pageContext.request.contextPath}/order/purchase/upload/${purchaseOrder.year}/${purchaseOrder.pk}" class="btn btn-primary btn-sm">
											<span><i class="fa fa-upload" aria-hidden="true"></i></span> 
											<spring:message code="label.purchaseord.attach"/>
										</a> 										
										<a href="${pageContext.request.contextPath}/order/purchase/payment/${purchaseOrder.year}/${purchaseOrder.pk}" class="btn btn-info btn-sm">
											<span><i class="fas fa-money-check-alt"></i></span> 
											<spring:message code="label.purchaseord.btn.payment"/>
										</a> 
										<a href="${pageContext.request.contextPath}/order/purchase/expense/${purchaseOrder.year}/${purchaseOrder.pk}" class="btn btn-info btn-sm">
											<span><i class="fas fa-truck"></i></span> 
											<spring:message code="label.purchaseord.btn.expenses"/>
										</a> 				
										<a href="${pageContext.request.contextPath}/order/purchase/close/${purchaseOrder.year}/${purchaseOrder.pk}" class="btn btn-success btn-sm">
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
		               		<a href="${pageContext.request.contextPath}/order/purchase/history/${rowYear}" class="btn ${classYear}">
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