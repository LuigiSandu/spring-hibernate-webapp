<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<title>Cart</title>
<link type = "text/css"
	  rel = "stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/temp.css">
<style>
#cartItems{top:100px;left:100px;position:absolute; height:100%}
#item{height:220px;background-color:white;margin-top:15px;}
.text{display:inline-block;margin-top:102px;margin-right:30px;position:absolute;width:600px;}
.pic{width:235px;height:220px;display:inline-block;margin-left:30px;}
.delete{position:absolute;margin-top:102px;left:1000px;}
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
	
<div id = "cartItems">
		<c:forEach var = "item" items = "${items}" varStatus="loop">
		<c:url var = "deleteLink" value = "/menu/deleteItem">
				<c:param name = "itemId" value = "${loop.index+1}"/>
		</c:url>
		
		<c:set var="string" value="${fn:substring(item,0,3)}"/> 
		<c:set var ="id" value = "${loop.index+1}" />
		<c:set var = "finalVal" value = "${fn:toLowerCase(string)}"/>
		
		<div id = "item">
				<span class = "text">${loop.index+1}. </span>
				
				<img class = "pic" src = "${pageContext.request.contextPath}/resources/pics/${finalVal}s/${finalVal}item${ids[id]}.jpg">
				
				<span class = "text">${item}</span>
				<button class = "delete"  onclick = "window.location.href='${deleteLink}'; return false;">Delete</button> 
		</div>
		</c:forEach>
</div>

</body>

</html>