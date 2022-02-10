<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<title>SignInPage</title>
<link type = "text/css"
	  rel = "stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/temp.css">
	  <link type = "text/css"
	  rel = "stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/style.css">
<style>
.table{font-family:arial, sans-serif;font-size:30px;}
td{padding:10px;}
#container{ position:absolute; left:100px; top:100px;width:100%;}
</style>
</head>
<body>

<div class ="menu">
	<div class="dropdown" style = "float:left;margin-left: 50px; ">
		<p class="dropbtn"><a style = "color:black;text-decoration:none;" href="home"> Home </a></p>
	</div>
	<div class="dropdown" style = "float:left;margin-left: 50px; ">
		<p class="dropbtn">Components</p>
		<div class="dropdown-content">
			<a href="cpus"> Processors </a>
			<a href="gpus"> Graphics Cards </a>
			<a href="hdds"> Hard Drives </a>
			<a href="psus"> Power Supply </a>
		</div>
	</div>
	<div class="dropdown" style = "float:right;margin-right: 50px; ">
		<p class="dropbtn">Account</p>
		<div class="dropdown-content">
			<a href="cart"> Cart </a>
			<a href="order"> Order </a>
			<a href="details"> Details </a>
			<a href="update"> Update </a>
			<a href="signIn"> Sign Out </a>
		</div>
	</div>

</div>

<div id = "container">
	<table style = "width:1000px;">
		<tbody>
			<tr>
				<form:form action = "updateUser" >
				<td><label>New Username:</label></td>
				<td><input type="text" name = "newUser" class="inputFields"  /></td>
				<td><input type = "submit" value = "Update"	class = "button"/></td>
				<td style = "width:450px"><label style = "color:red">${userError}</label></td>
				</form:form>
			</tr>
			<tr>
			<form:form action = "updatePass" >
				<td><label>New Password:</label></td>
				<td><input type="text" name = "newPass" class="inputFields"  /></td>
				<td><input type = "submit" value = "Update"	class = "button"/></td>
					<td style = "width:450px"><label style = "color:red">${passError}</label></td>
				</form:form>
			</tr>
			<tr>
			<form:form action = "updateEmail" >
				<td><label>New Email:</label></td>
				<td><input type="text" name = "newEmail" class="inputFields"  /></td>
				<td><input type = "submit" value = "Update"	class = "button"/></td>
					<td style = "width:450px"><label style = "color:red">${emailError}</label></td>
				</form:form>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>