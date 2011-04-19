<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table>
  <tr>
    <th colspan="4">Assets</th>
  </tr>
<c:set var="assetTotal" value="0" />
<c:forEach items="${assets}" var="account">
  <tr>
    <td><c:out value="${account.id}" /> </td>
    <td><c:out value="${account.accountType.name}" /> </td>
    <td><c:out value="${account.name}" /> </td>
    <td align="right"><fmt:formatNumber value="${account.currentAmount}" currencySymbol="$" type="currency" minFractionDigits="2" /></td>
  </tr>
  <c:set var="assetTotal" value="${assetTotal + account.currentAmount}" ></c:set>
</c:forEach>
  <tr>
  	<td colspan="3">Asset Total:</td>
  	<td align="right"><fmt:formatNumber value="${assetTotal}" currencySymbol="$" type="currency" minFractionDigits="2" /></td>
  </tr>
  
  <tr>
    <th colspan="4">Debts</th>
  </tr>
<c:set var="debtsTotal" value="0" />
<c:forEach items="${debts}" var="account">
  <tr>
    <td><c:out value="${account.id}" /> </td>
    <td><c:out value="${account.accountType.name}" /> </td>
    <td><c:out value="${account.name}" /> </td>
    <td align="right"><fmt:formatNumber value="${account.currentAmount}" currencySymbol="$" type="currency" minFractionDigits="2" /></td>
  </tr>
  <c:set var="debtsTotal" value="${debtsTotal + account.currentAmount}" ></c:set>
</c:forEach>
  <tr>
  	<td colspan="3">Debts Total:</td>
  	<td align="right"><fmt:formatNumber value="${debtsTotal}" currencySymbol="$" type="currency" minFractionDigits="2" /></td>
  </tr>
  <tr>
  	<th colspan="3">Your balance:</th>
  	<td><fmt:formatNumber value="${assetTotal - debtsTotal}" currencySymbol="$" type="currency" minFractionDigits="2" /></td>
  </tr>
</table>