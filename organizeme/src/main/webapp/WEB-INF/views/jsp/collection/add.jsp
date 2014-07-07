<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
	<head>
		<title>Organize Me</title>
		
		<script type="text/javascript">
			function addCollection() {
				var name = $('#name').val();
				var description = $('#description').val();
				
				$('#name').val('');
				$('#description').val('');
				
				$.ajax({
					type: "POST",
					url: "<c:url value="/collection/add" />",
					data: {
						name: name,
						description: description
					},
					success: function (data) {
						console.log(data);

						alert('Success!');
						
						window.location = "<c:url value="/collection/mine" />";
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
					<label for="name" class="col-lg-2 control-label">Name</label>
					<div class="col-lg-10">
						<input id="name" type="text" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="description" class="col-lg-2 control-label">Description</label>
					<div class="col-lg-10">
						<input id="description" type="text" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<input type="button" value="Add" onclick="addCollection()" class="btn btn-default">
					</div>
				</div>
			</fieldset>
		</form>
	</body>
</html>
