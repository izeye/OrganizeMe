<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Organize Me</title>
		
		<script type="text/javascript">
			function prepend(type, language, title, locationType, location) {
				$('#contents').prepend(
						"<tr><td>" + type
						+ "</td><td>" + language
						+ "</td><td>" + title
						+ "</td><td>" + locationType
						+ "</td><td><a href=\"" + location + "\" target=\"_blank\">" + location + "</td></tr>");
			}
			
			function addContent() {
				var type = $('#type').val();
				var language = $('#language').val();
				var title = $('#title').val();
				var locationType = $('#locationType').val();
				var location = $('#location').val();
				
				$.ajax({
					type: "POST",
					url: "content/add",
					data: {
						type: type,
						language: language,
						title: title,
						locationType: locationType,
						location: location
					},
					success: function (data) {
						console.log(data);
						
						prepend(type, language, title, locationType, location);
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
					<th>Type</th>
					<th>Language</th>
					<th>Title</th>
					<th>Location Type</th>
					<th>Location</th>
				</tr>
			</thead>
			
			<tbody id="contents">
				<c:forEach var="content" items="${contents}">
					<tr>
						<td>${content.type}</td>
						<td>${content.language}</td>
						<td>${content.title}</td>
						<td>${content.locationType}</td>
						<td><a href="${content.location}" target="_blank">${content.location}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
