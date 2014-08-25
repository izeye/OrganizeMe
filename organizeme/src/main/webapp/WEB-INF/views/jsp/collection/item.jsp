<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Organize Me</title>
	</head>
	
	<body>
		<table class="table table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th>Name</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${collection.name}</td>
					<td>${collection.description}</td>
				</tr>
			</tbody>
		</table>
		<div>
			Contents
		</div>
		<table class="table table-striped table-hover table-condensed">
			<tbody>
				<c:forEach var="content" items="${collection.contents}">
					<tr>
						<td>${content.name}</td>
						<td>${content.type}</td>
						<td>${content.language}</td>
						<td>${content.author.username}</td>
						<td>${content.progress}</td>
						<td>${content.createdTime}</td>
					</tr>
				</c:forEach>
			</tbody>
<!-- TODO: add more content -->
<!--  -->
		</table>
	</body>
</html>