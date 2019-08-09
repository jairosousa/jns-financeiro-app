$(document).ready(function() {
    moment.locale('pt-BR');
    var mes = $('#mes').val();
    var ano = $('#ano').val();
    $('#mesAtual').text(meses(mes));
    $('#anoAtual').text(ano);


    $("#proximo").on("click", function() {
        mes = parseInt(mes) + 1;
        if (mes > 12) {
            ano = parseInt(ano) + 1;
            $('#anoAtual').text(ano);
            mes = 1;
            $('#mesAtual').text(meses(mes));
        } else {
            $('#mesAtual').text(meses(mes))
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
                $('#mesAtual').text(meses(mes));
            } else {
                $('#mesAtual').text(meses(mes));
            }

            atualizarDespesas(mes, ano);

        });
    });
    
    $("#voltarMesAtual").on("click", function() {
        $(document).ready(function() {
            mes = new Date().getMonth() + 1;
            ano = new Date().getFullYear();

            $('#anoAtual').text(ano);
            $('#mesAtual').text(meses(mes));

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
                    <ul><li class="border border-left-success rounded shadow py-4 h4 "><i class="far fa-thumbs-up"></i> Não há despesa vencida no mês</li></ul>

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
    
    function meses(mes) {
    	var arrayMes = new Array(12);
    	arrayMes[0] = "Janeiro";
    	arrayMes[1] = "Fevereiro";
    	arrayMes[2] = "Março";
    	arrayMes[3] = "Abril";
    	arrayMes[4] = "Maio";
    	arrayMes[5] = "Junho";
    	arrayMes[6] = "Julho";
    	arrayMes[7] = "Agosto";
    	arrayMes[8] = "Setembro";
    	arrayMes[9] = "Outubro";
    	arrayMes[10] = "Novembro";
    	arrayMes[11] = "Dezembro";

    	return arrayMes[mes -1];
	}

}); // Fim reader
