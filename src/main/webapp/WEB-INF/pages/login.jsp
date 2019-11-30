<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>
	<spring:message code="label.access" />
</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- vinculo a bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<!-- Temas-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
</head>
<body>
	<div id="Contenedor">
		<br />
		<div class="Icon">
			<span class="glyphicon glyphicon-user"></span>
		</div>
		<div class="ContentForm">
			<form name="login" action="<c:url value='/login' />" method='POST'>
				<c:if test="${not empty error}">
					<div class="error">
						<spring:message code="message.baduserpass" />
					</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">
						<spring:message code="message.logout" />
					</div>
				</c:if>
				<div class="input-group input-group-lg">
					<span class="input-group-addon" id="sizing-addon1">
						<i class="glyphicon glyphicon-user"></i>
					</span> 
					<input type="text" class="form-control" name="username" id="username" aria-describedby="sizing-addon1">
				</div>
				<br>
				<div class="input-group input-group-lg">
					<span class="input-group-addon" id="sizing-addon1">
						<i class="glyphicon glyphicon-lock"></i>
					</span>
					<input type="password" name="password" class="form-control"	aria-describedby="sizing-addon1">
				</div>
				<br>
				<button class="btn btn-lg btn-primary btn-block btn-signin"
					id="submit" name="submit" type="submit">
					<spring:message code="button.login" />
				</button>
				<!-- 
				<div class="opcioncontra">
					<a href="">
					<spring:message code="message.forgotpass" /></a>
				</div>
				 -->
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
	</div>
</body>
<!-- vinculando a libreria Jquery-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Libreria java scritp de bootstrap -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</html>