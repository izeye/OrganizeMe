<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="description" content="Organize Me">
<meta name="author" content="CTB">

<title>Organize Me: <sitemesh:write property="title" /></title>

<!--
<link rel="stylesheet"
	href="<c:url value="/resources/libs/bootstrap/3.1.1/css/bootstrap.amelia.min.css" />">
-->
<link rel="stylesheet"
	href="<c:url value="/resources/libs/bootstrap/3.1.1/css/bootstrap.cerulean.min.css" />">

<script
	src="<c:url value="/resources/libs/jquery/2.1.1/jquery-2.1.1.min.js" />"></script>

<!--[if lt IE 9]>
			<script src="<c:url value="/resources/libs/html5shiv/3.7.0/html5shiv.js" />"></script>
			<script src="<c:url value="/resources/libs/respondjs/1.4.2/respond.min.js" />"></script>
		<![endif]-->

<sitemesh:write property="head" />

<!--
		<style type="text/css">
			.darkBG {
				background: #999;
			}
			
			.darkerBG {
				background: #666;
			}
			
			.darkestBG {
				background: #333;
			}
		</style>
		  -->
</head>
<body>
	<header class="navbar-inverse">
		<div class="container">
			<nav role="navigation">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">OrganizeMe</a>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active"><a href="<c:url value="/content/all" />">All</a></li>
							<li><a href="<c:url value="/content/friends" />">Friends</a></li>
							<li><a href="<c:url value="/content/mine" />">Mine</a></li>
							<li><a href="<c:url value="/content/add" />"><span class="glyphicon glyphicon-plus"></span></a></li>
							<li><a href="<c:url value="/collection/all" />">Collection All</a></li>
							<li><a href="<c:url value="/collection/friends" />">Collection Friends</a></li>
							<li><a href="<c:url value="/collection/mine" />">Collection Mine</a></li>
							<li><a href="<c:url value="/collection/add" />"><span class="glyphicon glyphicon-plus"></span></a></li>
							<sec:authorize access="hasRole('ROLE_SUPERVISOR')">
								<li><a href="<c:url value="/categories/add" />">Admin</a></li>
							</sec:authorize>
						</ul>
						<sec:authorize access="isAuthenticated()">
							<ul class="nav navbar-nav navbar-right">
								<li><a href="#">Hi, <sec:authentication property="principal.username" />!</a></li>
								<li><a href="<c:url value="/j_spring_security_logout" />">Logout</a></li>
							</ul>
						</sec:authorize>
						<sec:authorize access="!isAuthenticated()">
							<ul class="nav navbar-nav navbar-right">
								<li><a href="<c:url value="/login.html" />">Login</a></li>
							</ul>
						</sec:authorize>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>
	</header>

	<div class="container">
		<!--
			<div class="row">
				<div class="darkBG col-sm-6 col-xs-12">test</div>
				<div class="darkerBG col-sm-3 col-xs-6">test2</div>
				<div class="darkestBG col-sm-3 col-xs-6">test3</div>
			</div>
			  -->

		<sitemesh:write property="body" />
	</div>

	<script
		src="<c:url value="/resources/libs/bootstrap/3.1.1/js/bootstrap.min.js" />"></script>

	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	
	  ga('create', 'UA-51671871-1', 'cafe24.com');
	  ga('send', 'pageview');
	</script>
</body>
</html>
