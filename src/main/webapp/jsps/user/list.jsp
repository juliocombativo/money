<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<h2>Users</h2>

<table>
  <tr>
  	<th>Full Name</th>
  	<th>e-mail</th>
  	<th>Login</th>
  </tr>
<c:forEach items="${users}" var="user">
  <tr>
  	<td>${user.fullName}</td>
  	<td>${user.email}</td>
  	<td>${user.login}</td>
  	<td><a href="<c:url value="/user/edit/${user.id}.html" />">Edit</a></td>
  </tr>
</c:forEach>
  <tr>
  	<td colspan="3" align="center">
  		<a href="<c:url value="/user/add.html" />">Create</a>
  	</td>
  </tr>
</table>
