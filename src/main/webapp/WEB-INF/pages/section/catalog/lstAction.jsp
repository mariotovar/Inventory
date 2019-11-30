<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

	<c:set var="pk" value="${param.pk}"/>
	<a href="${pageContext.request.contextPath}/catalog/read/${beanName}/${pk}" class="btn btn-info btn-sm">
		<span><i class="fas fa-file-alt"></i></span> 
		<spring:message code="button.read"/>
	</a>                    
	<a href="${pageContext.request.contextPath}/catalog/form/${beanName}/${pk}" class="btn btn-primary btn-sm">
		<span><i class="fas fa-redo-alt"></i></span> 
		<spring:message code="button.update"/>
	</a>                 
	<a href="${pageContext.request.contextPath}/catalog/form/${beanName}/-${pk}" class="btn btn-danger btn-sm">
		<span><i class="far fa-trash-alt"></i></span>
		<spring:message code="button.delete"/>
	</a>