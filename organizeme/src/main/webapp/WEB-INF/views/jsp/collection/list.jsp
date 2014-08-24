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
					<th>Contents</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="collection" items="${collections}">
					<tr>
						<td><a href="/organizeme/collections/${collection.id}">${collection.name}</a></td>
						<td>${collection.description}</td>
						<td>${collection.contents}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>