<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html>
<head>
<title>Log in</title>
<link type = "text/css"
	  rel = "stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div class = "main">
	<div id = "container">
			<h3>Log in</h3>
			<form:form action = "logIn" modelAttribute = "account" method = "POST">
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
							<td><input type = "submit" value = "Log In" class = "button"/></td>
							<td><input type = "button" value = "Sign In" class = "button"	onclick = 
								"window.location.href='signIn'; return false;"/></td>
						</tr>
					</tbody>
				</table>
			</form:form>
		</div>
</div>
</body>

</html>