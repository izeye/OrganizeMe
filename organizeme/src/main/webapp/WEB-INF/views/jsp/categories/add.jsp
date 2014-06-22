<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<!DOCTYPE html>
<html>
	<head>
		<title>Organize Me</title>
		
		<script type="text/javascript">
			function addCategory() {
				var parentCategoryId = $('#parentCategoryId').val();
				var newCategoryName = $('#newCategoryName').val();
				
				console.log(newCategoryName);
				
				$.ajax({
					type: "POST",
					url: "<c:url value="/categories/add" />",
					data: {
						newCategoryName: newCategoryName,
						parentCategoryId: parentCategoryId,
					},
					success: function (data) {
						console.log(data);

						alert('Success!');
					},
					error: function (error) {
						console.log(error);
						
						alert("Error!");
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
					<label for="parentCategoryId" class="col-lg-2 control-label">Parent category (not supported yet)</label>
					<div class="col-lg-10">
						<select id="parentCategoryId" class="form-control">
							<c:forEach var="category" items="${categories}">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="newCategoryName" class="col-lg-2 control-label">Category name</label>
					<div class="col-lg-10">
						<input id="newCategoryName" type="text" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<input type="button" value="Add" onclick="addCategory()" class="btn btn-default">
					</div>
				</div>
			</fieldset>
		</form>
	</body>
</html>
