$(document).ready(function() {
    moment.locale('pt-BR');
    var mes = $('#mes').val();
    var ano = $('#ano').val();
    $('#mesAtual').text(mes);
    $('#anoAtual').text(ano);


    $("#proximo").on("click", function() {
        mes = parseInt(mes) + 1;
        if (mes > 12) {
            ano = parseInt(ano) + 1;
            $('#anoAtual').text(ano);
            mes = 1;
            $('#mesAtual').text(mes);
        } else {
            $('#mesAtual').text(mes)
        }

        atualizarDespesas(mes, ano);
    });

    $("#anterior").on("click", function() {
        $(document).ready(function() {
            mes = parseInt(mes) - 1;
            if (mes < 1) {
                ano = parseInt(ano) - 1;
                $('#anoAtual').text(ano);
                mes = 12;
                $('#mesAtual').text(mes);
            } else {
                $('#mesAtual').text(mes);
            }

            atualizarDespesas(mes, ano);

        });
    });
    $("#voltarMesAtual").on("click", function() {
        $(document).ready(function() {
            mes = new Date().getMonth() + 1;
            ano = new Date().getFullYear();

            $('#anoAtual').text(ano);
            $('#mesAtual').text(mes);

            atualizarDespesas(mes, ano);
        })
    })

    function atualizarDespesas(mes, ano) {

        $.ajax({
            method: "GET",
            url: "/pagamentos/listar/ajax/",
            data: {
                mes: mes,
                ano: ano
            },
            beforeSend: function() {
                $(".despesa").fadeOut(0, function() {
                    $(this).empty();
                });
            },
            success: function(response) {

                if (response.length > 142) {
                    $(".despesa").fadeIn(0, function() {
                        $(this).append(response);
                    })
                } else {
                    $(".despesa").fadeIn(0, function() {
                        $(this).append(`<div class="row h4 m-0 text-center text-success">
                            <div class="col-md-12">
                    <ul><li>Não há despesa vencida</li></ul>

                        </div>
                    </div>`)
                    });
                }

            },
        });
    }
    $('.btn-editar').on('click', function(event) {
        event.preventDefault();

        var botaoEditar = $(event.currentTarget);
        var id = botaoEditar.data('codigo');

        $.ajax({
            method: "GET",
            url: "/pagamentos/despesa/edit/" + id,
            beforeSend: function() {
                // Abrir Modal
                $("#editar-despesas-modal").modal('show');

            },
            success: function(data) {
                $('#form-edit-promo #edt_id').val(data.id);
                $('#form-edit-promo #nome').val(data.nome);
                $('#form-edit-promo #descricao').val(data.descricao);
                $('#form-edit-promo #valorParcela').val(data.valorParcela);
            },
            error: function() {
                alert("Ops... ocorreu um erro, tente mais tarde.");
            }
        });
    });

}); // Fim reader

$(function() {
    $(document).ready(function() {


    })
});