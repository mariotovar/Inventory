<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> --%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
	<head>
		<title><spring:message code="app.title"/></title>
	    <!-- Required meta tags -->
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	<!-- Bootstrap CSS -->
        <link href="<c:url value='/static/css/estilo.css' />"  rel="stylesheet"></link>
        <link href="<c:url value='/static/css/order.css' />"  rel="stylesheet"></link>
        <link href="<c:url value='/static/css/home.css' />"  rel="stylesheet"></link>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css">
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script defer src="https://use.fontawesome.com/releases/v5.7.2/js/all.js" integrity="sha384-0pzryjIRos8mFBWMzSSZApWtPl/5++eIfzYmTgBBmXYdhvxPc+XcFEk+zJwDgWbP" crossorigin="anonymous"></script>
       	<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
      	<!-- Optional JavaScript -->
    	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!--      	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>      -->
		<script src="https://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>     		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script>
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
      	<script src="https://unpkg.com/gijgo@1.9.11/js/gijgo.min.js" type="text/javascript"></script>
    	<link href="https://unpkg.com/gijgo@1.9.11/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    	<!-- local scripts-->
    	<script type="text/javascript" src="<c:url value='/static/js/moment.js' />"></script>
    	<script type="text/javascript" src="<c:url value='/static/js/utils.js' />"></script>
    	<script type="text/javascript" src="<c:url value='/static/js/catalog.js' />"></script>
<%--     	<script type="text/javascript" src="<c:url value='/static/js/order.js' />"></script> --%>
		<link rel="stylesheet"
			href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
			integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
			crossorigin="anonymous">


</head>
 	<body>
 		<div class="container-fluid fondo">
			<header id="header" class="row">
				<tiles:insertAttribute name="header" />
			</header>
			<section class="row cont">
				<tiles:insertAttribute name="menu" />
	             <article class="col-9 col-lg-10 der d-flex nowrap">
	                <aside class="row cambiante">
	                    <div class="col-12 contenido-cambiante" id="contenido-cambio">
	                    	<tiles:insertAttribute name="body" />
	                    </div>
	                </aside>                
            	 </article>								
				<footer id="footer" class="container-fluid">
					<tiles:insertAttribute name="footer" />
				</footer>
			</section>
		</div>
	</body>
	<script>
		function context() {
		   return "${pageContext.request.contextPath}";
		}
	</script>
</html>