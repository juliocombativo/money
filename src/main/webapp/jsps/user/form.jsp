<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:errors path="*" />

<form:form>
<form:hidden path="id"/>
<table>
	<tr>
		<th colspan="3" scope="col">User Information</th>
	</tr>
	<tr>
		<th scope="row">Full Name:</th>
		<td><form:input path="fullName" /></td>
		<td><form:errors path="fullName" /></td>
	</tr>
	<tr>
		<th scope="row">Email:</th>
		<td><form:input path="email"/></td>
		<td><form:errors path="email" /></td>
	</tr>
	<tr>
		<th scope="row">Login</th>
		<td><form:input path="login"/></td>
		<td><form:errors path="login" /></td>
	</tr>
	<tr>
		<th scope="row">Password:</th>
		<td><form:password path="password"/></td>
		<td><form:errors path="password" /></td>
	</tr>
	<tr>
		<th scope="row">Repeat password:</th>
		<td><form:password path="repeatPassword"/></td>
		<td><form:errors path="repeatPassword" /></td>
	</tr>
	<tr>
		<td><input type="submit" value="Send" /></td>
	</tr>
</table>
</form:form>