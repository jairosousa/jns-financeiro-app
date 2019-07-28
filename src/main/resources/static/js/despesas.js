$(document).ready(function () {
    moment.locale('pt-BR');
    var mes = $('#mes').val();
    var ano = $('#ano').val();
    $('#mesAtual').text(mes);
    $('#anoAtual').text(ano);


    $("#proximo").on("click", function () {
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

    $("#anterior").on("click", function () {
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

    $("#voltarMesAtual").on("click", function () {
        mes = new Date().getMonth() + 1;
        ano = new Date().getFullYear();

        $('#anoAtual').text(ano);
        $('#mesAtual').text(mes);

        atualizarDespesas(mes, ano);
    });

    function atualizarDespesas(mes, ano) {
        console.log("mes", mes);
        console.log("ano", ano);

        $.ajax({
            method: "GET",
            url: "/pagamentos/listar/ajax",
            data: {
                mes: mes,
                ano: ano
            },
            beforeSend: function () {
                $(".despesa").fadeOut(0, function () {
                    $(this).empty();
                });
            },
            success: function (response) {

                if (response.length > 150) {
                    $(".despesa").fadeIn(0, function () {
                        $(this).append(response);
                    })
                } else {
                    $(".despesa").fadeIn(0, function () {
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
});// Fim reader