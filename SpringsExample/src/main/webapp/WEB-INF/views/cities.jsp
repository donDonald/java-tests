<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Cities</title>
</head>
<body>

<div><a href="humans">Humans</a></div>
<div><a href="cities">Cities</a></div>

<h3>Add a new city:</h3>
<form:form method="post" action="addCity" commandName="city">
	<table>
		<tr>
			<td><label>Name</label></td>
			<td><form:input path="name" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Submit" /></td>
		</tr>
	</table>
</form:form>

<h3>Cities list:</h3>
<c:if test="${!empty cityList}">
	<table class="data">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${cityList}" var="city">
			<tr>
				<td>${city.id}</td>
				<td>${city.name}</td>
				<td><a href="deleteCity/${city.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>


</body>
</html>
