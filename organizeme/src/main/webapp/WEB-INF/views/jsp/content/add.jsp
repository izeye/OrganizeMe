<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<!DOCTYPE html>
<html>
	<head>
		<title>Organize Me</title>
		
		<script type="text/javascript">
			function addContent() {
				var categoryId = $('#categoryId').val();
				var type = $('#type').val();
				var language = $('#language').val();
				var title = $('#title').val();
				var locationType = $('#locationType').val();
				var location = $('#location').val();
				var progress = $('#progress').val();
				var tags = $('#tags').val();
				
				$('#title').val('');
				$('#location').val('');
				
				$.ajax({
					type: "POST",
					url: "<c:url value="/content/add" />",
					data: {
						categoryId: categoryId,
						type: type,
						language: language,
						title: title,
						locationType: locationType,
						location: location,
						progress: progress,
						tags: tags
					},
					success: function (data) {
						console.log(data);

						alert('Success!');
						
						window.location = "<c:url value="/content/mine" />";
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
		<form class="form-horizontal">
			<fieldset>
				<legend>Add</legend>
				<div class="form-group">
					<label for="categoryId" class="col-lg-2 control-label">Category</label>
					<div class="col-lg-10">
						<select id="categoryId" class="form-control">
							<c:forEach var="category" items="${categories}">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="type" class="col-lg-2 control-label">Type</label>
					<div class="col-lg-10">
						<select id="type" class="form-control">
							<c:forEach var="contentType" items="${contentTypes}">
								<option value="${contentType}">${contentType}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="language" class="col-lg-2 control-label">Language</label>
					<div class="col-lg-10">
						<select id="language" class="form-control">
							<c:forEach var="language" items="${languages}">
								<option value="${language}">${language}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="title" class="col-lg-2 control-label">Title</label>
					<div class="col-lg-10">
						<input id="title" type="text" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="locationType" class="col-lg-2 control-label">Location Type</label>
					<div class="col-lg-10">
						<select id="locationType" class="form-control">
							<c:forEach var="locationType" items="${locationTypes}">
								<option value="${locationType}">${locationType}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="location" class="col-lg-2 control-label">Location</label>
					<div class="col-lg-10">
						<input id="location" type="text" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="progress" class="col-lg-2 control-label">Progress</label>
					<div class="col-lg-10">
						<select id="progress" class="form-control">
							<c:forEach var="progress" items="${progresses}">
								<option value="${progress}">${progress}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="tags" class="col-lg-2 control-label">Tags</label>
					<div class="col-lg-10">
						<input id="tags" type="text" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<input type="button" value="Add" onclick="addContent()" class="btn btn-default">
					</div>
				</div>
			</fieldset>
		</form>
	</body>
</html>
