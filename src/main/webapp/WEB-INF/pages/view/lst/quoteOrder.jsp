<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<section id="tabla">
	<div class="float-right">
         <a href="${pageContext.request.contextPath}/order/quote/start" class="btn btn-outline-prin m-2">
         	<span><i class="fas fa-plus"></i></span> 
         	<spring:message code="label.quote.newOrder"/>
         </a>
         <a href="${pageContext.request.contextPath}/order/quote/history" class="btn btn-outline-prin m-2">
         	<span><i class="fas fa-archive"></i></span> 
         		<spring:message code="label.quote.records"/>
         </a> 
     </div>
     <div class="table-responsive">
     
     	<c:choose>
	 		<c:when test="${listRows.size() > 0}">
     	     
	         <table class="table table-sm text-center table-striped table-hover">
	             <tr class="primary">
	                 <th class="text-left">
	                 	<spring:message code="label.quote.orderNumber"/>
	                 </th>
	                 <th>
	                 	<spring:message code="label.quote.date"/>
	                 </th>
	                 <th class="text-right">
	                 	<spring:message code="label.client.title"/>
	                 </th>                 
	                 <th class="text-right">
	                 	<spring:message code="label.quote.totalMXN"/>
	                 </th>
	                 <th class="text-right">
	                 	<spring:message code="label.quote.totalUSD"/>
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
				 <c:forEach items="${listRows}" var="quoteOrder">
	             	<tr>
		               	 <td class="text-left">
		               	 	<a href="${pageContext.request.contextPath}/order/quote/detail/${quoteOrder.year}/${quoteOrder.pk}"
		               	 	   type="button" class="btn btn-light btn-sm" data-toggle="tooltip" data-placement="left" title="Go to Detail">
		                		<span><i class="fas fa-file-alt"></i></span> 
		                	</a>
		                	<label>
		               	 		&nbsp;${quoteOrder.orderNumber}
		               	 	</label>
		               	 </td>			
		                 <td>
		                 	<label>
		  	               		<fmt:formatDate pattern="dd-MM-yyyy HH:mm"  value = "${quoteOrder.quoteDate}" />
		                 	</label>
		                 </td>
		                 <td class="text-left">
		                 	<label>
		                 		${quoteOrder.client}
		                 	</label>
		                 </td>	  
		                 <td class="text-right">
		                 	<label>
		                 		<span class="currency">${quoteOrder.totalOrderMXN}</span>
		                 	</label>
		                 </td>	  	                 	                 
		                 <td class="text-right">
		                 	<label>
		                 		<span class="currency">${quoteOrder.totalOrderUSD}</span>
		                 	</label>
		                 </td>	       
				         <c:choose>
						 	<c:when test="${action eq 'HISTORY'}">
				         		<td>
				         			<label>
			         					<b><spring:message code="label.status.${quoteOrder.statusOrder}"/></b>	
				         			</label>
				         		</td>             
				         	</c:when>		             
							<c:otherwise>
							<c:set var="label_cancel">
											<spring:message code="label.purchaseord.btn.cancel"/>
					                 	</c:set>			   					                
					                 	<c:set var="label_update">
											<spring:message code="label.purchaseord.btn.update"/>
					                 	</c:set>
				                 <td>
									<a href="${pageContext.request.contextPath}/order/quote/update/${quoteOrder.year}/${quoteOrder.pk}"
										data-toggle="tooltip" data-placement="top" title="${label_update}" class="btn btn-primary btn-sm">
										&nbsp;
										<span><i class="fas fa-redo-alt"></i></span> 
										&nbsp;
									</a> 	
									<a href="${pageContext.request.contextPath}/order/quote/cancel/${quoteOrder.year}/${quoteOrder.pk}"
										data-toggle="tooltip" data-placement="top" title="${label_cancel}" class="btn btn-danger btn-sm">
										&nbsp;
										<span><i class="fas fa-trash-alt"></i></span> 
										&nbsp;
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
		               		<a href="${pageContext.request.contextPath}/order/quote/history/${rowYear}" class="btn ${classYear}">
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