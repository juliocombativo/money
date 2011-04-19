<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
$(function() {
	$('#date').datepicker({ dateFormat: 'dd/mm/yy' });
	$('#addElement').button().click(function(){
		var clon = $('.detail_form :first').clone(true);
		$(clon).appendTo('#detail_input');

		var index = $('.detail_form').length;
		$(clon).find('select,input').each(function() {
			$(this).attr('name', $(this).attr('name').replace('[0]', '[' + index + ']'));
			alert($(this).attr('name'));
		});
	});
	$('input[type=submit]').button();
});
</script>

<h1>Register a Movement</h1>

<form:form>
<table>
  <tr>
	<th>Date:</th>
	<td><form:input path="date" id="date" /></td>
  </tr>
  <tr>
  	<th>Type:</th>
  	<td>
  		<form:radiobutton path="expense" value="true"/> Expense
  		<form:radiobutton path="expense" value="false"/> Income
  	</td>
  </tr>
</table>

<h4>Movements</h4>
<div id="detail_input">
<c:forEach items="${command.details}" varStatus="i">
<table class="detail_form">
  <tr class="detail_account">
    <th>Account:</th>
    <td><form:select path="details[${i.index}].account" items="${accounts}" itemLabel="name" itemValue="id" /></td>
  </tr>
  <tr class="detail_amount">
  	<th>Amount:</th>
  	<td><form:input path="details[${i.index}].amount"/></td>
  </tr>
</table>
</c:forEach>
</div>

<a href="#" id="addElement" class="ui-icon-add">Split</a>
<input type="submit" value="Send" />
</form:form>

<div id="registers">
<c:forEach items="${lastMovements}" var="movement">
  <div class="single_register" class="<c:if test="${movement[0].expense}">expense</c:if><c:if test="${not movement[0].expense}">income</c:if>">
    <div class="movement_date">
    	<fmt:formatDate value="${movement[0].date}" pattern="dd/MM/yyyy"/>
    </div>
    <c:forEach items="${movement[0].details}" var="detail">
    <div class="movement_detail">
    	<h4>${detail.account.name}</h4>
    	<span class="movement_amount"><fmt:formatNumber type="currency" value="${detail.amount}" pattern="$ 000.00" /></span>
    </div>
    </c:forEach>
  </div>
</c:forEach>
</div>