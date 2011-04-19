<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form>
<form:hidden path="email" />
<form:hidden path="login" />
<table>
	<tr>
		<th colspan="2" scope="col">User Registration</th>
	</tr>
	<tr>
		<th scope="row">User Name</th>
		<td><form:input path="login" /></td>
	</tr>
	<tr>
		<th scope="row">Email</th>
		<td><form:input path="email"/></td>
	</tr>
	<tr>
		<th scope="row">Full Name</th>
		<td><form:input path="fullName" /></td>
	</tr>
	<tr>
		<td><input type="submit" value="Crear Usuario" /></td>
	</tr>
</table>
</form:form>