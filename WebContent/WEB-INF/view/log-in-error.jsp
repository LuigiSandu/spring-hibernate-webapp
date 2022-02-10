<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html>
<head>
<title>Log In</title>
 
<link type = "text/css"
	  rel = "stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/style.css">
	 
</head>
<body>
<div class = "main">
	<div id = "container">
		<h3>Log In</h3>
		<form:form action = "logIn" modelAttribute = "account" method = "POST">
			<!--  need to associate this data with customer id -->
			<form:hidden path = "id"/>
			
			<table>
				<tbody>
					<tr>
						<td><label>Username:</label></td>
						<td><form:input class="inputFields" path = "username" /></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><form:input class="inputFields"  path = "password" /></td>
					</tr>
					<tr><td style = "color:red">Wrong User or Pass</td></tr>
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