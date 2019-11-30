<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<form:form class="catalog" method="POST" modelAttribute="parameterValues">


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
	        <div class="col-12 col-md-6 mt-2">
		        <h6 class="titulos mt-1 text-right">
		        	<label>
		        		<spring:message code="label.config.${paramValue.key}"/>:
		        	</label>
		        </h6>		
	        </div>        
	        <div class="col-12 col-md-6 mt-2">
				<form:input path="values['${paramValue.key}']" value="${paramValue.value}" class="form-control qty" maxlength="10"/>
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