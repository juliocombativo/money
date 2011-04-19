<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<h2>Account Types</h2>

<table>
  <tr>
  	<th>Account Type</th>
  	<th>Asset?</th>
  </tr>
<c:forEach items="${types}" var="typeVar">
  <tr>
  	<td>${typeVar.name}</td>
  	<td>
  		<c:if test="${typeVar.asset}">Yes</c:if>
  		<c:if test="${not typeVar.asset}">No</c:if>
  	</td>
  	<td><a href="<c:url value="/accounttype/edit/${typeVar.id}.html" />">Edit</a></td>
  </tr>
</c:forEach>
  <tr>
  	<td colspan="3" align="center">
  		<a href="<c:url value="/accounttype/add.html" />">Create</a>
  	</td>
  </tr>
</table>
