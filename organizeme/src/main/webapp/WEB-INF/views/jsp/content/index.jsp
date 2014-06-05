<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<!DOCTYPE html>
<html>
	<head>
		<title>Organize Me</title>
		
		<script type="text/javascript">
			var username = "<sec:authentication property="principal.username" />";
			
			function prepend(category, type, language, title, locationType, location) {
				$('#contents').prepend(
						"<tr><td>" + category
						+ "</td><td>" + type
						+ "</td><td>" + language
						+ "</td><td>" + title
						+ "</td><td>" + locationType
						+ "</td><td><a href=\"" + location + "\" target=\"_blank\">" + location
						+ "</td><td>" + username
						+ "</td><td>Just now</td></tr>");
			}
			
			function addContent() {
				var categoryId = $('#categoryId').val();
				var categoryName = $('#categoryId option:selected').text();
				var type = $('#type').val();
				var language = $('#language').val();
				var title = $('#title').val();
				var locationType = $('#locationType').val();
				var location = $('#location').val();
				
				$('#title').val('');
				$('#location').val('');
				
				$.ajax({
					type: "POST",
					url: "content/add",
					data: {
						categoryId: categoryId,
						type: type,
						language: language,
						title: title,
						locationType: locationType,
						location: location
					},
					success: function (data) {
						console.log(data);
						
						prepend(categoryName, type, language, title, locationType, location);
					},
					error: function (error) {
						console.log(error);
						
						alert("Duplicate content!");
					},
					dataType: "json"
				});
			}
		</script>
	</head>
	
	<body>
		<h1>Organize Me</h1>
		<label for="categoryId">Category</label>
		<select id="categoryId">
			<c:forEach var="category" items="${categories}">
				<option value="${category.id}">${category.name}</option>
			</c:forEach>
		</select>
		<label for="type">Type</label>
		<select id="type">
			<c:forEach var="contentType" items="${contentTypes}">
				<option value="${contentType}">${contentType}</option>
			</c:forEach>
		</select>
		<label for="language">Language</label>
		<select id="language">
			<c:forEach var="language" items="${languages}">
				<option value="${language}">${language}</option>
			</c:forEach>
		</select>
		<label for="title">Title</label>
		<input id="title" type="text"><br/>
		
		<label for="locationType">Location Type</label>
		<select id="locationType">
			<c:forEach var="locationType" items="${locationTypes}">
				<option value="${locationType}">${locationType}</option>
			</c:forEach>
		</select>
		<label for="location">Location</label>
		<input id="location" type="text"><br/>
		
		<input type="button" value="Add" onclick="addContent()"><br/>
		
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
