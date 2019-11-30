<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<section id="tabla">
	<div class="float-right">
         <a href="${pageContext.request.contextPath}/order/sales/start" class="btn btn-outline-prin m-2">
         	<span><i class="fas fa-plus"></i></span> 
         	<spring:message code="label.quote.newOrder"/>
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
	                
	             </tr>
				 <c:forEach items="${listRows}" var="sale">
	             	<tr>
		               	 <td class="text-left">
		               	 
		                	<label>
		               	 		&nbsp;${sale.orderNumber}
		               	 	</label>
		               	 </td>			
		                 <td>
		                 	<label>
		  	               		<fmt:formatDate pattern="dd-MM-yyyy HH:mm"  value = "${sale.quoteDate}" />
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
            
     
     </div>
        
 </section> 