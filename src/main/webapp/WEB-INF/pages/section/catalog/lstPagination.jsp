<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<div class="row paginacion align-items-center">
    <div class="col-12 col-sm-6 col-lg-3 mr-auto">
        <div class="dataTables_info" id="table_info" role="status" aria-live="polite">
        	<spring:message code="label.page"/> ${ctrlPage.numberOfPage} <spring:message code="label.of"/> ${ctrlPage.numberOfPages}
        </div>
    </div>
    <nav aria-label="col-12 col-sm-6 col-lg-5 Page navigation">
        
        <ul class="pagination m-3">
          <c:if test="${ctrlPage.numberOfPage != 1}">
           <li class="page-item">	               	 
             <a class="page-link" href="${pageContext.request.contextPath}/${path}/page/${beanName}/${ctrlPage.numberOfPage - 1}">
             	<spring:message code="label.before"/>
             </a>
           </li>
          </c:if>
	      <c:forEach begin="1" end="${ctrlPage.numberOfPages}" varStatus="i">
	      	<c:choose>
	      		<c:when test="${ctrlPage.numberOfPage == i.index}">		              
	            <li class="page-item active" aria-current="page">
	               <span class="page-link">
	                 ${i.index}
	                 <span class="sr-only">(current)</span>
	               </span>
	             </li>
	      		</c:when>
	      		<c:otherwise>
	            <li class="page-item">
	            		<a class="page-link" href="${pageContext.request.contextPath}/${path}/page/${beanName}/${i.index}">
	            			${i.index}
	            		</a>
	            </li>         		
	      		</c:otherwise>
	      	</c:choose>
	      </c:forEach>      
   	      <c:if test="${ctrlPage.numberOfPage != ctrlPage.numberOfPages}">
           <li class="page-item">
             <a class="page-link" href="${pageContext.request.contextPath}/${path}/page/${beanName}/${ctrlPage.numberOfPage + 1}">
             	<spring:message code="label.next"/>
             </a>
           </li>
          </c:if>
        </ul>
      </nav>
</div>