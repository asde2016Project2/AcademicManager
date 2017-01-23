<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Accepting ${username}</h1>

	
	<div id="my_camera"></div><br>
	
	<form>
		<input type="button" id="capture" value="capture" onClick="take_snapshot()">
	</form>
	
	<div id="results"></div><br>
	
	<form:form method="post" modelAttribute="acceptingStudentForm" action="accepting">
		<form:input id="photo" path="photo" type="hidden" name="photo" value=""/>
		<form:input id="username" path="username" type="hidden" name="username" value="${username}"/>
		<form:select id="accepted" path="accepted" type="hidden" name="accepted">
			<form:option value="ACCEPTED" label="ACCEPT" />
			<form:option value="NOT_ACCEPTED" label="NOT ACCEPT"></form:option>
		</form:select>
		<form:button type="submit">Accept</form:button>
	</form:form>
	
	<script type="text/javaScript">
		Webcam.set({
			width: 320,
			height: 240,
			image_format: 'png',
			png_quality: 100
		});
		Webcam.attach( '#my_camera' );
	</script>
	
	<script type="text/javaScript">
		function take_snapshot() {
			// take snapshot and get image data
			Webcam.snap( function(data_uri) {
				// display results in page
				document.getElementById('results').innerHTML = '<img src="'+data_uri+'"/>';
				var raw_image_data = data_uri.replace(/^data\:image\/\w+\;base64\,/, '');
		        document.getElementById('photo').value = raw_image_data;
			});
		}
	</script>