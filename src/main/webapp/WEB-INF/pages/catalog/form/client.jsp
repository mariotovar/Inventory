<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<form:form class="catalog" method="POST" modelAttribute="client">

 	<div class="title-catalog">
		<b><spring:message code="label.client.title"/></b>
		<hr>			
	</div>	
   	<jsp:include page="/WEB-INF/pages/section/catalog/formHeader.jsp"/>
    <div class="secciones">        
    	<h6 class="titulos mt-1">
    		<form:label path="value"><spring:message code="label.client.name"/></form:label>
    	</h6>
        <form:input path="value" type="text" class="form-control" maxlength="40" />
    </div>
    <div class="secciones row">
        <div class="col-12 col-md-9 mt-2">
	        <h6 class="titulos mt-1">
	        	<form:label path="address"><spring:message code="label.client.address"/></form:label>
	        </h6>
	        <form:input path="address" type="text" class="form-control" maxlength="200" />
        </div>
        <div class="col-12 col-md-3 mt-2">
	        <h6 class="titulos mt-1">
	        	<form:label path="zip"><spring:message code="label.client.zip"/></form:label>
	        </h6>
	        <form:input path="zip" type="text" class="form-control" maxlength="10" />
        </div>        
    </div>    
    <div class="secciones row">
        <div class="col-12 col-md-6 mt-2">
        	<h6 class="titulos mt-1">
        		<form:label path="state"><spring:message code="label.client.state"/></form:label>	
        	</h6>
			<form:input path="state" type="text" class="form-control" maxlength="20" />
        </div>        
        <div class="col-12 col-md-6 mt-2">
			<h6 class="titulos mt-1">
				<form:label path="country"><spring:message code="label.client.country"/></form:label>	
			</h6>
			<form:input path="country" type="text" class="form-control" maxlength="20" />
       </div>
    </div>
    <div class="secciones row">
    	<div class="col-12 col-md-12 mt-2">
	        <h6 class="titulos mt-1">
	        	<form:label path="email"><spring:message code="label.client.email"/></form:label>
	        </h6>
	        <form:input path="email" type="text" class="form-control" maxlength="40" />
        </div>
    </div>  
    <div class="secciones row">
        <div class="col-12 col-md-12 mt-2">
	        <h6 class="titulos mt-1">
	        	<form:label path="rfc"><spring:message code="label.client.taxid"/></form:label>
	        </h6>        
        	<form:input path="rfc" type="text" class="form-control" maxlength="40" />
		</div>        	
    </div> 
	<jsp:include page="/WEB-INF/pages/section/catalog/formAction.jsp"/>

</form:form> 