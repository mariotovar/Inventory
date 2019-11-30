<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<nav class="col navbar navbar-light">
	<div class="col-7 col-sm-4 col-md-5 col-lg-3">
		<a class="navbar-brand" href="#"> 
			<img src="<c:url value='/static/img/logo.svg' />" height="40" alt="logo">
		</a>
	</div>
	<div class="col-5 col-sm-4 col-md-3 col-lg-2 offset-sm-4 offset-md-4 offset-lg-7 cerrar header-login">		
		<a href="?lang=en"><spring:message code="label.language.english"/></a>
		|
		<a href="?lang=es"><spring:message code="label.language.spanish"/></a>
	</div>
</nav>
