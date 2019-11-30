<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

    <div class="actions">           
		<div class="text-right buttons">
	      	  <c:choose>
	      		 <c:when test="${action eq 'READ'}">
			      	<button type="button" class="btn btn-secondary btn-sm back">
			      		<span><i class="fas fa-times"></i></span> 
	      				<spring:message code="label.return"/>	
			      	</button>	      
	      		</c:when>	      		
	      		<c:otherwise>
			      <button type="button" class="btn btn-secondary btn-sm back">
			      	<span><i class="fas fa-times"></i></span> 
			      	<spring:message code="label.cancel"/>
			      </button>    
		      	  <c:choose>
		      		 <c:when test="${action eq 'DELETE'}">
				      	<button type="button" class="btn btn-danger btn-sm submit">
				      		<span><i class="far fa-trash-alt"></i></span> 
		      				<spring:message code="label.delete"/>	
				      	</button>	      
		      		</c:when>	      		
		      		<c:otherwise>
		      		 	<button type="button" class="btn btn-success btn-sm submit">
		      				<span><i class="fas fa-save"></i></span> 
		      				<spring:message code="label.save"/>	
		      			</button>	      
		      		</c:otherwise>
		    	</c:choose>			        
	      		</c:otherwise>
	    	</c:choose>		
		</div>    
	 </div>
     <div class="results">           
		<div class="text-right buttons">
	      <a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/catalog/page/${beanName}/1">
	      	<span><i class="fas fa-times"></i></span> 
	      	<spring:message code="label.return"/>
	      </a>
		</div>          
     </div>  