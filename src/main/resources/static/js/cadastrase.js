$('#btn-submit').on('click', function(evt) {
//	evt.preventDefault();
	$("#form input").each(function(){
		if($(this).val() == '') {
			$("#loader-form").addClass("loader").hide();
			
		} else {
			$("#loader-form").addClass("loader").show();
		}
	})
	
})