<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>${username}</h1>
<form:form method="post" modelAttribute="acceptingStudentForm" action="accepting">
	<div id="results"></div>
	<form:input id="photo" path="photo" type="hidden" name="photo" value=""/>
	<form:input id="username" path="username" type="hidden" name="username" value="${username}"/>
	<form:button type="submit">OK</form:button>
</form:form>
	
	<h1>WebcamJS Test Page</h1>
	<h3>Demonstrates simple 320x240 capture &amp; display</h3>
	
	<div id="my_camera"></div>
	
	
	<!-- Configure a few settings and attach camera -->
	<script language="JavaScript">
		Webcam.set({
			width: 320,
			height: 240,
			image_format: 'png',
			png_quality: 100
		});
		Webcam.attach( '#my_camera' );
	</script>
	
	<!-- A button for taking snaps -->
	<form>
		<input type=button value="Take Snapshot" onClick="take_snapshot()">
	</form>
	
	<!-- Code to handle taking the snapshot and displaying it locally -->
	<script language="JavaScript">
		function take_snapshot() {
			// take snapshot and get image data
			Webcam.snap( function(data_uri) {
				// display results in page
				document.getElementById('results').innerHTML = '<img src="'+data_uri+'"/>';
				var raw_image_data = data_uri.replace(/^data\:image\/\w+\;base64\,/, '');
		        document.getElementById('photo').value = raw_image_data;
			} );
		}
	</script>