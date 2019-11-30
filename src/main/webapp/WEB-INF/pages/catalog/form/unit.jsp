<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<form:form class="catalog" method="POST" modelAttribute="unit">

 	<div class="title-catalog">
		<b><spring:message code="label.unit.title"/></b>
		<hr>			
	</div>	
   	<jsp:include page="/WEB-INF/pages/section/catalog/formHeader.jsp"/>
    <div class="secciones">
        <h6 class="titulos mt-1">
        	<form:label path="value"><spring:message code="label.unit.description"/></form:label>
        </h6>
        <form:input path="value" type="text" class="form-control" maxlength="40" />
    </div>
    <jsp:include page="/WEB-INF/pages/section/catalog/formAction.jsp"/>

</form:form> 