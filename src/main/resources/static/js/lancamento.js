$(document).ready(function() {
    moment.locale('pt-BR');
    var mes = $('#mes').val();
    var ano = $('#ano').val();
    $('#mesAtual').text(meses(mes));
    $('#anoAtual').text(ano);

    var tabler = criarTabelaReceita(mes, ano);

    var tabled = criarTabelaDespesas(mes, ano);

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

        tabler.destroy();
        tabled.destroy();
        tabler = criarTabelaReceita(mes, ano);
        tabled = criarTabelaDespesas(mes, ano);
    });

    $("#anterior").on("click", function() {
        mes = parseInt(mes) - 1;
        if (mes < 1) {
            ano = parseInt(ano) - 1;
            $('#anoAtual').text(ano);
            mes = 12;
            $('#mesAtual').text(meses(mes));
        } else {
            $('#mesAtual').text(meses(mes));
        }

        tabler.destroy();
        tabled.destroy();
        tabler = criarTabelaReceita(mes, ano);
        tabled = criarTabelaDespesas(mes, ano);
    });

    $("#voltarMesAtual").on("click", function() {
        mes = new Date().getMonth() + 1;
        ano = new Date().getFullYear();

        $('#anoAtual').text(ano);
        $('#mesAtual').text(meses(mes));

        tabler.destroy();
        tabled.destroy();
        tabler = criarTabelaReceita(mes, ano);
        tabled = criarTabelaDespesas(mes, ano);
    });

    function criarTabelaDespesas(mes) {
        return $('#table-lancamento-despesa')
            .DataTable({
            	dom: 'Bfrtip',
                buttons: [
                	{
                        extend:    'copyHtml5',
                        text:      '<i class="far fa-copy"></i>',
                        titleAttr: 'Copy',
                        className: 'btn btn-outline-dark'
                    },
                	{
                        extend: 'excel',
                        text:      '<i class="far fa-file-excel"></i>',
                        titleAttr: 'Excel',
                        orientation: 'landscape',
                        pageSize: 'LEGAL',
                        title: 'Tabela de Lançamentos Despesas',
                        className: 'btn btn-outline-success',
                        exportOptions: {
                            columns: [ 0, 1,  2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 ]
                        }
                    },
                    {
                        extend: 'pdfHtml5',
                        text:      '<i class="far fa-file-pdf"></i>',
                        titleAttr: 'PDF',
                        orientation: 'landscape',
                        pageSize: 'LEGAL',
                        download: 'open',
                        className: 'btn btn-outline-danger',
                        title: 'Tabela de Lançamentos Despesas',
                        exportOptions: {
                            columns: [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
                        }
                    }
                ],
                processing: true,
                serverSide: true,
                "language": {
                    "url": "/datatables/translationBR",
                    	buttons: {
                            copyTitle: 'Copiado para área de transferencia',
                            copyKeys: 'Impresso <i>ctrl</i> ou <i>\u2318</i> + <i>C</i> para copiar os dados da tabela para sua área de transferência. <br><br>Para cancelar, clique nesta mensagem ou pressione Esc.',
                            copySuccess: {
                                _: '%d Linhas copiadas',
                                1: '1 linha copiada'
                            }
                        }	
                },
                searching: true,
                order: [
                    [0, "asc"]
                ],
                lengthMenu: [5, 10, 15],
                responsive: true,
                //            scrollY: "40vh",
                ajax: {
                    url: '/lancamentos/despesa/datatables/server/' + mes + "/" + ano,
                    data: 'data'
                },
                columns: [{
                        data: 'id'
                    },
                    {
                        data: 'nome'
                    },
                    {
                        data: 'valor',
                        render: $.fn.dataTable.render.number('.', ',', 2, 'R$ '),
                        className: "text-right"
                    },
                    {
                        data: 'dtLancamento',
                        render: function(dtLancamento) {
                            return moment(dtLancamento).format('L');
                        },
                        className: "text-center"
                    },
                    {
                        data: 'dtPagamento',
                        render: function(dtPagamento) {
                            return (dtPagamento != null) ? moment(dtPagamento).format('L') : '';
                        },
                        className: "text-center"
                    }, {
                        data: 'dtVencimento',
                        render: function(dtVencimento) {
                            return moment(dtVencimento).format('L');
                        },
                        className: "text-center"
                    },
                    {
                        data: 'pagamento',
                        render: function(pagamento) {
                            return (pagamento == 'APRAZO') ? 'À prazo' : 'A vista';
                        }
                    },
                    {
                        data: 'qtdParcelas'
                    },
                    {
                        data: 'numParcela'
                    },
                    {
                        data: 'valorParcela',
                        render: $.fn.dataTable.render.number('.', ',', 2, 'R$ '),
                        className: "text-right"
                    },
                    {
                        data: 'gastoFixo',
                        render: function(gastoFixo) {
                            return gastoFixo ? 'Sim' : 'Não';
                        },
                        className: "text-center"
                    },
                    {
                        data: 'formaPagamento.nome'
                    },
                    {
                        data: 'fornecedor.nome'
                    },
                    {
                        data: 'categoria.nome'
                    },
                    {
                        data: 'descricao'
                    },
                    {
                        orderable: false,
                        data: 'id', // adicionar
                        // botão
                        // editar
                        "render": function(id) {
                            return '<a class="btn btn-success btn-sm btn-block" href="/lancamentos/despesa/editar/' +
                                id +
                                '" role="button"><i class="fas fa-edit"></i></a>';
                        }
                    },
                    {
                        orderable: false,
                        data: 'id', // adicionar // botão // excluir
                        "render": function(id) {
                            return '<a class="btn btn-danger btn-sm btn-block" href="/lancamentos/despesa/excluir/' +
                                id +
                                '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                        }
                    }
                ]
            });
    }

    function criarTabelaReceita(mes) {
        return $('#table-lancamento-receita')
            .DataTable({
            	processing: true,
                serverSide: true,searching: true,
                order: [
                    [3, "desc"]
                ],
                lengthMenu: [5, 10, 15],
                responsive: true,
            	dom: 'Bfrtip',
            	buttons: [
            		{
                        extend:    'copyHtml5',
                        text:      '<i class="far fa-copy"></i>',
                        titleAttr: 'Copy',
                        className: 'btn btn-outline-dark'
                    },
                	{
                        extend: 'excel',
                        title: 'Tabela de Lançamentos Receitas',
                        text:      '<i class="far fa-file-excel"></i>',
                        titleAttr: 'Excel',
                        className: 'btn btn-outline-success',
                        exportOptions: {
                            columns: [ 0, 1,  2, 3, 4, 5 ]
                        }
                    },
                    {
                        extend: 'pdfHtml5',
                        text:      '<i class="far fa-file-pdf"></i>',
                        titleAttr: 'PDF',
                        download: 'open',
                        className: 'btn btn-outline-danger',
                        title: 'Tabela de Lançamentos Receitas',
                        exportOptions: {
                            columns: [ 0, 1, 2, 3, 4, 5]
                        }
                    }
                ],
                "createdRow": function(row, data, index) {
                    $('td', row).eq(3).addClass('text-success');
                },
                "language": {
                    "url": "/datatables/translationBR"
                },
                //            scrollY: "40vh",
                ajax: {
                    url: '/lancamentos/receita/datatables/server/' + mes + "/" + ano,
                    data: 'data'
                },
                columns: [{
                        data: 'id'
                    },
                    {
                        data: 'nome'
                    },
                    {
                        data: 'valor',
                        render: $.fn.dataTable.render.number('.', ',', 2, 'R$ '),
                        className: "text-right"
                    },
                    {
                        data: 'dtRecebimento',
                        render: function(dtRecebimento) {
                            return moment(dtRecebimento).format('L');
                        },
                        className: "text-center"
                    },
                    {
                        data: 'fornecedor.nome'
                    },
                    {
                        data: 'dtLancamento',
                        render: function(dtLancamento) {
                            return moment(dtLancamento).format('L');
                        },
                        className: "text-center"
                    },
                    {
                        data: 'descricao'
                    },
                    {
                        orderable: false,
                        data: 'id', // adicionar
                        // botão
                        // editar
                        "render": function(id) {
                            return '<a class="btn btn-success btn-sm btn-block d-inline mr-2" href="/lancamentos/receita/editar/' +
                                id +
                                '" role="button" title="Editar"><i class="fas fa-edit"></i></a>' +
                                '<a class="btn btn-danger btn-sm btn-block d-inline" href="/lancamentos/receita/excluir/' +
                                id +
                                '" role="button" data-toggle="modal" data-target="#confirm-modal" title="Excluir"><i class="fas fa-times-circle"></i></a>';
                        }
                    }
                ]
            });
    }
    
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
})

$('#qtdsParcelas').hide();

$("#gastoFixo").change(function() {
    if ($(this).prop("checked") == true) {
        $('#paglabel').text('Sim')
    } else {
        $('#paglabel').text('Não')
    }
});

$("#gastoFixo").trigger("change");

$("#pag-1").change(function() {
    if ($(this).prop("checked") == true) {
        $('#qtdsParcelas').show(500);
    }
});

$("#pag-1").trigger("change");

$("#pag-0").change(function() {
    if ($(this).prop("checked") == true) {
        $('#qtdsParcelas').hide(500);
    }
});

$("#pag-0").trigger("change");