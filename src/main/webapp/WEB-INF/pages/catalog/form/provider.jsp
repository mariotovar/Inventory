<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<form:form class="catalog" method="POST" modelAttribute="provider">

 	<div class="title-catalog">
		<b><spring:message code="label.provider.title"/></b>
		<hr>			
	</div>	
   	<jsp:include page="/WEB-INF/pages/section/catalog/formHeader.jsp"/>
    <div class="secciones">        
    	<h6 class="titulos mt-1">
    		<form:label path="value"><spring:message code="label.provider.name"/></form:label>
    	</h6>
        <form:input path="value" type="text" class="form-control" maxlength="40" />
    </div>
    <div class="secciones row">
        <div class="col-12 col-md-9 mt-2">
	        <h6 class="titulos mt-1">
	        	<form:label path="address"><spring:message code="label.provider.address"/></form:label>
	        </h6>
	        <form:input path="address" type="text" class="form-control" maxlength="200" />
        </div>
        <div class="col-12 col-md-3 mt-2">
	        <h6 class="titulos mt-1">
	        	<form:label path="zip"><spring:message code="label.provider.zip"/></form:label>
	        </h6>
	        <form:input path="zip" type="text" class="form-control" maxlength="10" />
        </div>        
    </div>
    <div class="secciones row">
        <div class="col-12 col-md-6 mt-2">
        	<h6 class="titulos mt-1">
        		<form:label path="state"><spring:message code="label.provider.state"/></form:label>	
        	</h6>
			<form:input path="state" type="text" class="form-control" maxlength="20" />
        </div>        
        <div class="col-12 col-md-6 mt-2">
			<h6 class="titulos mt-1">
				<form:label path="country"><spring:message code="label.provider.country"/></form:label>	
			</h6>
			<form:input path="country" type="text" class="form-control" maxlength="20" />
       </div>
    </div>
    <div class="secciones row">
        <div class="col-12 col-md-12 mt-2">
        <h6 class="titulos mt-1">
        	<form:label path="email"><spring:message code="label.provider.email"/></form:label>
        </h6>
        <form:input path="email" type="text" class="form-control" maxlength="40" />
        </div>
    </div>
    <div class="secciones row">
        <div class="col-12 col-md-12 mt-2">
	        <h6 class="titulos mt-1">
	        	<form:label path="emailCC1"><spring:message code="label.provider.emailCC"/> (1)</form:label>
	        </h6>
	        <form:input path="emailCC1" type="text" class="form-control" maxlength="40" />
        </div>
    </div>  
    <div class="secciones row">
        <div class="col-12 col-md-12 mt-2">
	        <h6 class="titulos mt-1">
	        	<form:label path="emailCC2"><spring:message code="label.provider.emailCC"/> (2)</form:label>
	        </h6>
	        <form:input path="emailCC2" type="text" class="form-control" maxlength="40" />
    	</div>
    </div>            
    <div class="secciones row">
        <div class="col-12 col-md-6 mt-2">
			<h6 class="titulos mt-1">
				<form:label path="localPhone"><spring:message code="label.provider.localphone"/></form:label>	
			</h6>
			<form:input path="localPhone" type="text" class="form-control"  maxlength="20"/>
        </div>        
        <div class="col-12 col-md-6 mt-2">
        	<h6 class="titulos mt-1">
        		<form:label path="mobilePhone"><spring:message code="label.provider.mobilephone"/></form:label>
        	</h6>
			<form:input path="mobilePhone" type="text" class="form-control" maxlength="20" />
       </div>
    </div>    
    <div class="secciones row">
        <div class="col-12 col-md-12 mt-2">
	        <h6 class="titulos mt-1">
	        	<form:label path="taxID"><spring:message code="label.provider.taxid"/></form:label>
	        </h6>
	        <form:input path="taxID" type="text" class="form-control" maxlength="40" />
		</div>
    </div> 
    <div class="secciones row">
        <div class="col-12 col-md-12 mt-2">
	        <h6 class="titulos mt-1">
	        	<form:label path="contact"><spring:message code="label.provider.contact"/></form:label>
	        </h6>
	        <form:input path="contact" type="text" class="form-control" maxlength="40" />
		</div>
    </div>     
	<jsp:include page="/WEB-INF/pages/section/catalog/formAction.jsp"/>

</form:form> 