
$('.btn-editar').on('click', function (event) {
    event.preventDefault();

    var botaoEditar = $(event.currentTarget);
    var id = botaoEditar.data('codigo');

    $.ajax({
        method: "GET",
        url: "/pagamentos/despesa/edit/" + id,
        beforeSend: function () {
            // Abrir Modal
            $("#editar-despesas-modal").modal('show');

        },
        success: function (data) {
            $('#form-edit-promo #edt_id').val(data.id);
            $('#form-edit-promo #nome').val(data.nome);
            $('#form-edit-promo #descricao').val(data.descricao);
            $('#form-edit-promo #valorParcela').val(numberParaReal(data.valorParcela));
        },
        error: function () {
            alert("Ops... ocorreu um erro, tente mais tarde.");
        }
    });
});

function numberParaReal(numero) {
    var formatado = numero.toFixed(2).replace(".", ",");
    return formatado;
}

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
});