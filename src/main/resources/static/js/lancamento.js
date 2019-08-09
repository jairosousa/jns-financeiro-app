$(document).ready(function() {
    moment.locale('pt-BR');
    var mes = $('#mes').val();
    var ano = $('#ano').val();
    $('#mesAtual').text(mes);
    $('#anoAtual').text(ano);

    var tabler = criarTabelaReceita(mes, ano);

    var tabled = criarTabelaDespesas(mes, ano);

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
            $('#mesAtual').text(mes);
        } else {
            $('#mesAtual').text(mes);
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
        $('#mesAtual').text(mes);

        tabler.destroy();
        tabled.destroy();
        tabler = criarTabelaReceita(mes, ano);
        tabled = criarTabelaDespesas(mes, ano);
    });

    function criarTabelaDespesas(mes) {
        return $('#table-lancamento-despesa')
            .DataTable({
                // dom: 'Bfrtip',
                processing: true,
                serverSide: true,
                "language": {
                    "url": "/datatables/translationBR"
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
                // dom: 'Bfrtip',
                "createdRow": function(row, data, index) {
                    $('td', row).eq(3).addClass('text-success');
                },
                processing: true,
                serverSide: true,
                "language": {
                    "url": "/datatables/translationBR"
                },
                searching: true,
                order: [
                    [3, "desc"]
                ],
                lengthMenu: [5, 10, 15],
                responsive: true,
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