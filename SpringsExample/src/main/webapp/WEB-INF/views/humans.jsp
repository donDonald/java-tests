<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Humans</title>
</head>
<body>

<div><a href="humans">Humans</a></div>
<div><a href="cities">Cities</a></div>

<h3>Add a new human:</h3>

<form:form method="post" action="addHuman" commandName="human">
	<table>
		<tr>
			<td><label>First name</label></td>
			<td><form:input path="firstName" /></td>
		</tr>
		<tr>
			<td><label>Second name</label></td>
			<td><form:input path="secondName" /></td>
		</tr>
		<tr>
			<td><label>City</label></td> 
			<td>
				<form:select path="city">
					<form:options items="${cityList}" itemLabel="name" />
				</form:select>
<%--
				<form:select path="city.id">
					<form:options items="${cityList}" itemLabel="name" itemValue="id" />
				</form:select>
--%>
<%--
				<form:select path="city">
					<c:forEach items="${cityList}" var="city">
						<form:option items="${cityList}" itemLabel="name" itemValue="id" value="${city} }" />
					</c:forEach>
				</form:select>
--%>
			</td>
		</tr>
 		<tr>
			<td colspan="2"><input type="submit" value="Submit" /></td>
		</tr>
	</table>
</form:form>
  
<h3>Humans list:</h3>
<c:if test="${!empty humanList}">
	<table class="data">
		<tr>
			<th>Id</th>
			<th>1st name</th>
			<th>2nd name</th>
			<th>Location</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${humanList}" var="human">
			<tr>
				<td>${human.id}</td>
				<td>${human.firstName}</td>
				<td>${human.secondName}</td>
				<td>
					<form:form method="post" action="addHuman" commandName="human">
						<form:input path="id" type="hidden" value="${human.id}" />
						<form:input path="firstName" type="hidden" value="${human.firstName}" />
						<form:input path="secondName" type="hidden" value="${human.secondName}" />
						<form:select path="city.id">
							<c:forEach items="${cityList}" var="city">
								<c:choose>
									<c:when test="${ city.id==human.city.id }">
										<option value="${city.id}" selected> ${city.name} </option>
									</c:when>
									<c:otherwise>
										<option value="${city.id}"> ${city.name} </option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
						<input type="submit" value="Submit" />
					</form:form>
				</td>
				<td><a href="deleteHuman/${human.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>


</body>
</html>
