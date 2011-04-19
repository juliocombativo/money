<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:errors path="*" />

<form:form>
<table>
	<tr>
		<th colspan="2" scope="col">Add Account</th>
	</tr>
	<tr>
		<th scope="row">Account Name</th>
		<td><form:input path="name" /></td>
	</tr>
	<tr>
		<th scope="row">Account Type</th>
		<td>
			<form:select path="accountType" items="${types}" itemLabel="name" itemValue="id" />
		</td>
	</tr>
	<tr>
		<th scope="row">Start Amount</th>
		<td><form:input path="startAmount" /></td>
	</tr>
	<tr>
		<td><input type="submit" value="Send" /></td>
	</tr>
</table>
</form:form>