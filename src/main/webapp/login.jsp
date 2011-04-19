<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

	<h2>Login</h2>
	<form id="loginForm" name="loginForm"
		action="<c:url value="/login.do"/>" method="POST">
	<p><label>Username: <input type='text' name='j_username'
		value="marissa"></label></p>
	<p><label>Password: <input type='text' name='j_password'
		value="koala"></label></p>

	<p><input name="login" value="Login" type="submit"></p>
	</form>
	<div style="text-align: center">
	<form action="<c:url value="/logout.do"/>"><input type="submit"
		value="Logout"></form>
	</div>

	<h2>Your Photos</h2>

	<p><script type='text/javascript'
		src='json/photos?callback=pictureDisplay'></script></p>
