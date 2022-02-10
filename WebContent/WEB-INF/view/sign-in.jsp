<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html>
<head>
<title>SignInPage</title>

<link type = "text/css"
	  rel = "stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id = "container">
		<h3>Sign In</h3>
		<form:form action = "saveAccount" modelAttribute = "account" method = "POST">
			<table>
				<tbody>
					<tr>
						<td><label>Username:</label></td>
						<td><form:input class="inputFields" path = "username" /></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><form:input class="inputFields" path = "password" /></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><form:input class="inputFields" path = "email" /></td>
					</tr>
					<tr>
						<td><input type = "submit" value = "Sign In" class = "button"/></td>
						<td><input type = "button" value = "Log In"	class = "button" onclick = 
							"window.location.href='logInPage'; return false;"/></td>
					</tr>
				</tbody>
			</table>
		</form:form>
</div>
</body>
</html>