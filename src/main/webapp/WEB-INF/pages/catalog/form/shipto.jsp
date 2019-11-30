<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form class="catalog" method="POST" modelAttribute="shipto">

 	<div class="title-catalog">
		<b><spring:message code="label.shipto.title"/></b>
		<hr>			
	</div>	
   	<jsp:include page="/WEB-INF/pages/section/catalog/formHeader.jsp"/>
    <div class="secciones row">    
    	<div class="col-12 col-md-12 mt-2">    
	    	<h6 class="titulos mt-1">
	    		<form:label path="value"><spring:message code="label.shipto.value"/></form:label>
	    	</h6>
	        <form:input path="value" type="text" class="form-control" maxlength="40" />
        </div>
    </div>
    <div class="secciones row">
        <div class="col-12 col-md-9 mt-2">
	        <h6 class="titulos mt-1">
	        	<form:label path="address"><spring:message code="label.shipto.address"/></form:label>
	        </h6>
	        <form:input path="address" type="text" class="form-control" maxlength="40" />
        </div>
        <div class="col-12 col-md-3 mt-2">
	        <h6 class="titulos mt-1">
	        	<form:label path="zip"><spring:message code="label.shipto.zip"/></form:label>
	        </h6>
	        <form:input path="zip" type="text" class="form-control" maxlength="10" />
        </div>        
    </div> 
     <div class="secciones row">
        <div class="col-12 col-md-6 mt-2">
        	<h6 class="titulos mt-1">
        		<form:label path="state"><spring:message code="label.shipto.state"/></form:label>	
        	</h6>
			<form:input path="state" type="text" class="form-control" maxlength="20" />
        </div>        
        <div class="col-12 col-md-6 mt-2">
			<h6 class="titulos mt-1">
				<form:label path="country"><spring:message code="label.shipto.country"/></form:label>	
			</h6>
			<form:input path="country" type="text" class="form-control" maxlength="20" />
       </div>
    </div>
        <div class="secciones row">    
    	<div class="col-12 col-md-12 mt-2">    
	    	<h6 class="titulos mt-1">
	    		<form:label path="phone"><spring:message code="label.shipto.phone"/></form:label>
	    	</h6>
	        <form:input path="phone" type="text" class="form-control" maxlength="40" />
        </div>
    </div>
    

    

    
    
    
   

   
    
      
      
	<jsp:include page="/WEB-INF/pages/section/catalog/formAction.jsp"/>

</form:form>


<script>
	
	$(document).ready(function(){			
		
		//Selected timeExpired
		var pkTimeExpired = $("#timeExpired").attr('data-pk');
		$('#timeExpired option[value='+pkTimeExpired+']').attr('selected', 'selected');

		//Selected rotable | serializable
	    
	        
	});	
	
</script>	
<!-- -------------------------------------------------------------- -->
<!-- -------------------------------------------------------------- -->
