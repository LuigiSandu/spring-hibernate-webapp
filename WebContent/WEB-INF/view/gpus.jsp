<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %> 
<!DOCTYPE html>
<html>
<head>
<title>GPUs</title>
<link type = "text/css"
	  rel = "stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/temp.css">
<style>

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

<div id = "items">
			<c:forEach var = "gpu" items = "${gpus}">
				<c:url var = "getGPUId" value = "/menu/addGPUtoCart">
					<c:param name = "gpuId" value = "${gpu.id}"/>
				</c:url>
					<div class = "itemContainer">
						<img class = "pic" src = "${pageContext.request.contextPath}/resources/pics/gpus/gpuitem${gpu.id}.jpg">
						<p>${gpu.description}</p>
						<br><br><br><br><br>
						<button class = "addToCart"  onclick = "window.location.href='${getGPUId}'; return false;">Add to Cart</button>
					
					</div>
			</c:forEach>
</div>
	
</body>

</html>