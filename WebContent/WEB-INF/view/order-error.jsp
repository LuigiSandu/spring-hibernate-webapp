<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<title>Order</title>
<link type = "text/css"
	  rel = "stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/temp.css">
	  <link type = "text/css"
	  rel = "stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/style.css">
<style>
#container{ position:absolute; left:100px; top:100px; width:100%;}
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
	<form:form action = "saveOrder" modelAttribute = "credential">
			<table>
				<tbody>
					<tr>
						<td><label>Card Number:</label></td>
						<td><form:input class="inputFields" path = "cardNumber" /></td>
						<td><label style = "color:red">${cardNumberError}${emptyCartError}</label></td>
					</tr>
					<tr>
						<td><label>CVC:</label></td>
						<td><form:input class="inputFields" path = "CVC" /></td>
						<td><label style = "color:red">${cvcError}</label></td>
					</tr>
					<tr>
						<td><input type = "submit" value = "Order" class = "button"/></td>
					</tr>
				</tbody>
			</table>
	</form:form>
</div>
</body>
</html>