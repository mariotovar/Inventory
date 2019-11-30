<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<div class="secciones clear">
	    <form:input path="pk" type="text" />
	    <form:input path="user" type="text" />
		<form:input path="status" type="text" />
	</div>
	<div class="label-success blink">
		* <spring:message code="label.action.success"/>
	</div>	
	<c:if test="${action eq 'READ' or action eq 'DELETE' }">
		<div class="label-read">		
		</div>
	</c:if>	
	<c:if test="${action eq 'DELETE'}">
		<div class="label-warning">		
			<spring:message code="label.action.delete"/>
		</div>
	</c:if>