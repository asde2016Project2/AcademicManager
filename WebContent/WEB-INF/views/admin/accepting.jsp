<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3>Accepting ${username}</h3>

<div id="row">

	<div class="panel panel-primary" style="display: inline-block;">
		<div class="panel-heading">Webcam</div>
		<div class="panel-body">
			<div id="my_camera"></div>
		</div>
		<div class="panel-footer">
			<form>
				<input type="button" class="btn btn-primary" id="capture"
					value="capture" onClick="take_snapshot()">
			</form>
		</div>
	</div>
	
	<div id="photo-captured" class="panel panel-success" style="display: none;">
		<div class="panel-heading">Photo captured</div>
		<div class="panel-body">
			<div id="results"></div>
		</div>
		<div class="panel-footer">
			<form:form method="post" modelAttribute="acceptingStudentForm"
				action="accepting">
				<form:input id="photo" path="photo" type="hidden" name="photo" value="" />
				<form:input id="username" path="username" type="hidden" name="username"
					value="${username}" />
				<form:button id="loading-accept" class="btn btn-success" type="submit">Accept</form:button>
			</form:form>
		</div>
	</div>
	
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">Please wait...</div>
				<div class="modal-body">
					<div class="progress">
						<div
							class="progress-bar progress-bar-primary progress-bar-striped active"
							role="progressbar" aria-valuenow="100" aria-valuemin="100"
							aria-valuemax="100" style="width: 100%"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script type="text/javaScript">
	Webcam.set({
		width : 320,
		height : 240,
		image_format : 'png',
		png_quality : 100
	});
	Webcam.attach('#my_camera');
</script>

<script type="text/javaScript">
	function take_snapshot() {
		Webcam.snap(function(data_uri) {
					document.getElementById('results').innerHTML = '<img src="'+data_uri+'"/>';
					var raw_image_data = data_uri.replace(
							/^data\:image\/\w+\;base64\,/, '');
					document.getElementById('photo').value = raw_image_data;
				});
		$("#photo-captured").css('display', 'inline-block');
		
	}
</script>