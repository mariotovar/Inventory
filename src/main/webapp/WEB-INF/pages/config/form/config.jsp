<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<form:form class="section" method="POST" modelAttribute="parameterValues">

 	<div class="title-catalog">
		<b>
			<spring:message code="label.config.title"/>
		</b>
		<hr>			
	</div>	
	<div class="label-success blink">
		* <spring:message code="label.action.success"/>
	</div>	
    <div class="secciones row">
		<c:forEach items="${parameterValues.values}" var="paramValue" varStatus="status">
	        <div class="col-12 col-md-4 mt-2">
		        <h6 class="titulos mt-1 text-right">
		        	<label>
		        		<spring:message code="label.config.${paramValue.key}"/>:
		        	</label>
		        </h6>		
	        </div>        
	        <div class="col-12 col-md-8 mt-2">
	        	<c:choose>
	        		<c:when test="${paramValue.key eq 'TERMS_CONDITIONS'}">
	        			<form:textarea path="values['${paramValue.key}']" value="${paramValue.value}" rows="5" cols="30" class="md-textarea form-control" maxlength="999"  />
	        		</c:when>
	        		<c:otherwise>
	        			<form:input path="values['${paramValue.key}']" value="${paramValue.value}" class="form-control" />
	        		</c:otherwise>
	        	</c:choose>	        
	       </div>
		</c:forEach>
	</div>

    <div class="actions">           
		<div class="text-right buttons">
      	<button type="button" class="btn btn-secondary btn-sm back">
      		<span><i class="fas fa-times"></i></span> 
    		<spring:message code="label.return"/>	
      	</button>	    
	 	<button type="button" class="btn btn-success btn-sm map-submit">
			<span><i class="fas fa-save"></i></span> 
			<spring:message code="label.save"/>	
		</button>	  
	   </div>
	</div>

</form:form> 

<script>

	$(document).ready(function(){			
		$("textarea[maxlength]").on("propertychange input", function() {
		    if (this.value.length > this.maxlength) {
		        this.value = this.value.substring(0, this.maxlength);
		    }  
		});
	});	

</script>		


