<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<!DOCTYPE html>
<html>
	<head>
		<title>Organize Me</title>

		<script
			src="<c:url value="/resources/libs/jsrender/1.0.0-beta/jsrender.min.js" />"></script>
		<script
			src="<c:url value="/resources/libs/momentjs/2.7.0/moment.min.js" />"></script>
		
		<script type="text/javascript">
			function search() {
				var categoryId = $('#categoryId').val();
				
				$.ajax({
					type: "GET",
					url: "<c:url value="/contents" />",
					data: {
						categoryId: categoryId
					},
					success: function (data) {
						console.log(data);
						
						$("#contents").html(
								$("#contentTemplate").render(data)
						);
					},
					error: function (error) {
						console.log(error);
					},
					dataType: "json"
				});
			}
		</script>
	</head>
	
	<body>
		<script id="contentTemplate" type="text/x-jsrender">
			<tr>
				<td>{{>category.name}}</td>
				<td>{{>type}}</td>
				<td>{{>language}}</td>
				<td>{{>title}}</td>
				<td>{{>locationType}}</td>
				<td><a href="{{>location}}" target="_blank">{{>location}}</a></td>
				<td>{{>author.username}}</td>
				<td>{{>~format(createdTime, "timestamp")}}</td>
			</tr>
		</script>
		
		<script type="text/javascript">
			$.views.helpers({
				format: function (val, format) {
					switch (format) {
					case "timestamp":
						return moment(val).format("YYYY-MM-DD hh:mm:ss");
					}
				}
			});
		</script>
		
		<form class="form-horizontal">
			<fieldset>
				<legend>Search</legend>
				<div class="form-group">
					<label for="categoryId" class="col-lg-2 control-label">Category</label>
					<div class="col-lg-10">
						<select id="categoryId" class="form-control">
							<option value="0">All</option>
							<c:forEach var="category" items="${categories}">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<input type="button" value="Search" onclick="search()" class="btn btn-default">
					</div>
				</div>
			</fieldset>
		</form>
		
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
