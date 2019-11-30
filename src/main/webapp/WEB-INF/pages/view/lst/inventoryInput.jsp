<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<section class="simple_table">
	<div class="float-right">
         <a href="${pageContext.request.contextPath}/inventory/input/new" class="btn btn-outline-prin m-2">
         	<span><i class="fas fa-plus"></i></span> 
         		<spring:message code="label.input.newInput"/> 
         </a>         
     </div>     
     <div class="table-responsive">
      	<c:choose>
	 		<c:when test="${listRows.size() > 0}">         
		         <table class="table table-sm text-center table-striped table-hover">
		             <tr class="primary">
		                 <th>
		                 	#&nbsp;<spring:message code="label.input.inputNumber"/> 
		                 </th>
		                 <th>
		                 	<spring:message code="label.input.date"/>
		                 </th>
		                 <th class="text-right">
		                 	<spring:message code="label.input.totalUSD"/>
		                 </th>
		                 <th class="text-right">
		                 	<spring:message code="label.input.totalMXN"/>
		                 </th>
		             </tr>
					<c:forEach items="${listRows}" var="inventoryInput">
			             <tr>
			               	 <td class="text-left">
		        				<c:set var="label_detail">
									<spring:message code="label.purchaseord.btn.detail"/>
			                 	</c:set>			 
			               	 	<a href="${pageContext.request.contextPath}/inventory/input/detail/${inventoryInput.year}/${inventoryInput.pk}"
			               	 		data-toggle="tooltip" data-placement="top" title="${label_detail}">
			                		<span><i class="fas fa-file-alt"></i></span> 			                		
			                	</a>
		                		<label>
									&nbsp;${inventoryInput.inputNumber}
								</label>								
			               	 </td>			               	 
			                 <td>
			                 	<label>
			                 		<fmt:formatDate pattern="dd-MM-yyyy HH:mm"  value = "${inventoryInput.inputDate}" />
			                 	</label>
			                 </td>
			                 <td class="text-right">
			                 	<label>
			                 		<span class="currency">${inventoryInput.totalOrderUSD}</span>
			                 	</label>
			                 </td>
			                 <td class="text-right">
			                 	<label>
			                 		<span class="currency">${inventoryInput.totalOrderMXN}</span>
			                 	</label>
			                 </td>
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
	               		<a href="${pageContext.request.contextPath}/view/history/inventoryInput/${rowYear}" class="btn ${classYear}">
	               			${currentYear - status.index}
	               		</a>							
	                 	<span class="sr-only">(current)</span>
	               </span>
	             </li>        		        		
        	</c:forEach>
        	</ul>
        </div>  
     
     </div>
        
 </section> 