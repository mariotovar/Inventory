<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
	<head>
	    <!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	<!-- Bootstrap CSS -->
        <link href="<c:url value='/static/css/login.css' />"  rel="stylesheet"></link>
        <link href="<c:url value='/static/css/estilo.css' />"  rel="stylesheet"></link>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css">
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script defer src="https://use.fontawesome.com/releases/v5.7.2/js/all.js"></script>
       	<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
      	<!-- Optional JavaScript -->
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
      	<script src="https://unpkg.com/gijgo@1.9.11/js/gijgo.min.js" type="text/javascript"></script>
    	<link href="https://unpkg.com/gijgo@1.9.11/css/gijgo.min.css" rel="stylesheet" type="text/css" />		
	</head>
 	<body>
 		<div class="container-fluid fondo">
			<header id="header" class="row">
				<tiles:insertAttribute name="header" />
			</header>
			<section class="row cont">
				<tiles:insertAttribute name="body" />						
				<footer id="footer" class="container-fluid">
					<tiles:insertAttribute name="footer" />
				</footer>
			</section>
		</div>
	</body>
</html>