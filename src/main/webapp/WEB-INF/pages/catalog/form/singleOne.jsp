<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<title>SingleOneForm</title>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="<c:url value='/static/catalog/catalog.js' />"></script>
</head>
<body>
	<h3>Welcome, Enter The SingleOneForm Details</h3>
	<form:form class="catalog" method="POST" modelAttribute="singleOne">
		<table>
			<tr>
				<td>
					<form:label path="attrIntValue">attrIntValue</form:label>
				</td>
				<td>
					<form:input path="attrIntValue" value="10" />
				</td>
			</tr>
			<tr>
				<td>
					<form:label path="attrDoubleValue">attrDoubleValue</form:label>
				</td>
				<td>
					<form:input path="attrDoubleValue" value="10" />
				</td>
			</tr>
			<tr>
				<td>
					<form:label path="attrStringValue">attrStringValue</form:label>
				</td>
				<td>
					<form:input path="attrStringValue" value="hola" />
				</td>
			</tr>
			<tr>
				<td>
					<form:label path="attrBooleanValue">attrBooleanValue</form:label>
				</td>
				<td>
					<form:checkbox path="attrBooleanValue" />
				</td>
			</tr>	
			<tr>
				<td>
					<form:label path="attrDateValue">attrDateValue</form:label>
				</td>
				<td>
					<form:input path="attrDateValue" value="01.01.2018" />
				</td>
			</tr>						
			<tr>
				<td>
					<input type="submit" value="Enviar" />
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>