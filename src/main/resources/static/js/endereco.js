$('#cep').blur(function() {
	var cep = $(this).val();
	
	$.ajax({
		method: "GET",
		url: "/enderecos/cep/" + cep,
		success: function(data){
			console.log(data)
			
			$("#logradouro").val(data.logradouro);
			$("#bairro").val(data.bairro);
			$("#complemento").val(data.complemento);
			$("#cidade").val(data.localidade);
			$("#estado").val(data.uf);
		}
	})
})