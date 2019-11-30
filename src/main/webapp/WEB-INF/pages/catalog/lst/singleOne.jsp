<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<title>SingleOneForm</title>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="<c:url value='/static/catalog/catalog.js' />"></script>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UIF-8">
<title>Lista Catalogos</title>
</head>
<body>

		<table border="1">
			<tr>
				<th>
					attrIntValue
				</th>
				<th>
					attrDoubleValue
				</th>
				<th>
					attrStringValue
				</th>	
				<th>
					attrBooleanValue
				</th>
				<th>
					attrDateValue
				</th>
				<th>
					Editar
				</th>
				<th>
					Eliminar
				</th>								
			</tr>
			<c:forEach var="singleOne" items="${lst}" >																				
				<tr>
					<td>
						<c:out value="${singleOne.attrIntValue}" />
					</td>
					<td>
						<c:out value="${singleOne.attrDoubleValue}" />
					</td>
					<td>
						<c:out value="${singleOne.attrStringValue}" />
					</td>
					<td>
						<c:out value="${singleOne.attrBooleanValue}" />
					</td>
					<td>
						<c:out value="${singleOne.attrDateValue}" />
					</td>		
		
					<td>
						<a href="TBD">Eliminar</a>
					</td>							
				</tr>
			</c:forEach>
		</table>
		<a href="<c:url value='/catalog/form/singleOne' />">Agregar</a>
	</body>
</html>