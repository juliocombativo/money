<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>Accounts</h1>

<table>
  <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Type</th>
    <th>Current Amount</th>
  </tr>
<c:forEach items="${accounts}" var="account">
  <tr>
    <td><c:out value="${account.id}" /> </td>
    <td><c:out value="${account.name}" /> </td>
    <td><c:out value="${account.accountType.name}" /> </td>
    <td align="right"><fmt:formatNumber value="${account.currentAmount}" currencySymbol="$" type="currency" minFractionDigits="2" /></td>
  </tr>
</c:forEach>
  <tr>
  	<td colspan="3" align="center"><a href="<c:url value="/account/add.html" />" >Create New</a></td>
  </tr>
</table>