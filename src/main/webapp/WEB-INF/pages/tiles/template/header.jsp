<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<nav class="col navbar navbar-light">
	<div class="col-7 col-sm-4 col-md-5 col-lg-3">
		<a class="navbar-brand" href="#"> <img
			src="<c:url value='/static/img/logo.svg' />" height="40" alt="logo">
		</a>
	</div>
	<div class="col-5 col-sm-4 col-md-3 col-lg-2 offset-sm-4 offset-md-4 offset-lg-7 cerrar">
		<a href="?lang=en"><spring:message code="label.language.english"/></a>
		|
		<a href="?lang=es"><spring:message code="label.language.spanish"/></a>	
		<form action="<c:url value='/logout' />" method="get">
			<button type="button" class="btn btnsesion  border-left" onclick="parentNode.submit();">
				<span> 
					<i class="fas fa-power-off"></i>
				</span> 
				<spring:message code="button.closesession"/>
			</button>
		</form>
	</div>
</nav>

<div id="loader" class="modal fade loader-modal-lg" data-backdrop="static" data-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-sm">
        <div class="modal-content" style="width: 48px; color: #004ba0">
            <span class="fa fa-spinner fa-spin fa-8x"></span>
        </div>
    </div>
</div>	

