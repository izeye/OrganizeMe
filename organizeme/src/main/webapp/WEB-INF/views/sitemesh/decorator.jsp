<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="description" content="Organize Me">
		<meta name="author" content="CTB">
		
		<title>Organize Me: <sitemesh:write property="title" /></title>

		<link rel="stylesheet" href="<c:url value="/resources/libs/bootstrap/3.1.1/css/bootstrap.min.css" />">
		
		<script src="<c:url value="/resources/libs/jquery/2.1.1/jquery-2.1.1.min.js" />"></script>
		
		<!--[if lt IE 9]>
			<script src="<c:url value="/resources/libs/html5shiv/3.7.0/html5shiv.js" />"></script>
			<script src="<c:url value="/resources/libs/respondjs/1.4.2/respond.min.js" />"></script>
		<![endif]-->
		
		<sitemesh:write property="head" />
	</head>
	<body>
		<div class="container">
			<sitemesh:write property="body" />
		</div>
		
		<p><a href="j_spring_security_logout">Logout</a></p>
		
		<script src="<c:url value="/resources/libs/bootstrap/3.1.1/js/bootstrap.min.js" />"></script>
	</body>
</html>
