<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:errors />

<form:form>
<form:hidden path="id"/>
<table>
	<tr>
		<th colspan="2" scope="col">Account Type</th>
	</tr>
	<tr>
		<th scope="row">Name</th>
		<td><form:input path="name" /></td>
	</tr>
	<tr>
		<th scope="row">Is Asset?</th>
		<td><form:checkbox path="asset"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="Create" /></td>
	</tr>
</table>
</form:form>