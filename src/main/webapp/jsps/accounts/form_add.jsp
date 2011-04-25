<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
<!--
$(function() {
	$('[name=startAmount]').attr('min', '0');
	$('form').validate();
});
//-->
</script>

<form:form>
<table>
	<tr>
		<th colspan="2" scope="col">Add Account</th>
	</tr>
	<tr>
		<th scope="row">Account Name</th>
		<td><form:input path="name" cssClass="required" /></td>
	</tr>
	<tr>
		<th scope="row">Account Type</th>
		<td>
			<form:select path="accountType" items="${types}" itemLabel="name" itemValue="id" />
		</td>
	</tr>
	<tr>
		<th scope="row">Start Amount</th>
		<td><form:input path="startAmount" cssClass="required" /></td>
	</tr>
	<tr>
		<td><input type="submit" value="Send" /></td>
	</tr>
</table>
</form:form>