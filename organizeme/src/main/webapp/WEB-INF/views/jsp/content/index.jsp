<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<!DOCTYPE html>
<html>
	<head>
		<title>Organize Me</title>
	</head>
	
	<body>
		<table class="table table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th>Category</th>
					<th>Type</th>
					<th>Language</th>
					<th>Title</th>
					<th>Location Type</th>
					<th>Location</th>
					<th>Author</th>
					<th>Created Time</th>
				</tr>
			</thead>
			
			<tbody id="contents">
				<c:forEach var="content" items="${contents}">
					<tr>
						<td>${content.category.name}</td>
						<td>${content.type}</td>
						<td>${content.language}</td>
						<td>${content.title}</td>
						<td>${content.locationType}</td>
						<td><a href="${content.location}" target="_blank">${content.location}</a></td>
						<td>${content.author.username}</td>
						<td>${content.createdTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
