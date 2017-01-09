$(document).ready(function(){
	//OPEN MENU ADMIN
		$("#button-menu").click(function(){
			if($("#menu").is(':visible')){
				$("#menu").hide("slide", { direction: "left" }, 500);
			}
			else {
				$("#menu").show("slide", { direction: "left" }, 500);
			}
		});
	//END OPEN MENU ADMIN
});