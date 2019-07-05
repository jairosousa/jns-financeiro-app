$('#cep').blur(function() {
    $('.fa-exclamation-triangle').css("color", "red")
    var cep = replaceMasck($(this).val());
    console.log(cep)
    if (cep) {
        if (cep.length == 8) {
            $.ajax({
                method: "GET",
                url: "/enderecos/cep/" + cep,
                success: function(data) {

                    $("#logradouro").val(data.logradouro);
                    $("#bairro").val(data.bairro);
                    $("#complemento").val(data.complemento);
                    $("#cidade").val(data.localidade);
                    $("#estado").val(data.uf);
                    $('#numero').focus();

                    $('.fa-exclamation-triangle').css("color", "green")
                }
            })
        }
    }
})

//retirar a maccara do input
function replaceMasck(cep) {
    // return cep.replace('_','').replace('.', '').replace('-', '');
    return cep.replace(/[^0-9]/g, '')
};

if ($('#cep').val() == null) {
    $('.fa-exclamation-triangle').css("color", "red")
} else {
    $('.fa-exclamation-triangle').css("color", "green")
}