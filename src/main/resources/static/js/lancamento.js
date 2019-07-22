$(document).ready(function() {
    moment.locale('pt-BR');
    var tabler = $('#table-lancamento-receita')
        .DataTable({
            // dom: 'Bfrtip',
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
                url: '/lancamentos/receita/datatables/server',
                data: 'data'
            },
            columns: [{
                    data: 'id'
                },
                {
                    data: 'nome'
                },
                {
                    data: 'descricao'
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
                    data: 'fornecedor.nome'
                },
                {
                    data: 'dtRecebimento',
                    render: function(dtRecebimento) {
                        return moment(dtRecebimento).format('L');
                    },
                    className: "text-center"
                },
                {
                    orderable: false,
                    data: 'id', // adicionar
                    // botão
                    // editar
                    "render": function(id) {
                        return '<a class="btn btn-success btn-sm btn-block" href="/lancamentos/receita/editar/' +
                            id +
                            '" role="button"><i class="fas fa-edit"></i></a>';
                    }
                },
                {
                    orderable: false,
                    data: 'id', // adicionar // botão // excluir
                    "render": function(id) {
                        return '<a class="btn btn-danger btn-sm btn-block" href="/lancamentos/receita/excluir/' +
                            id +
                            '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                    }
                }
            ]
        });

    var tabled = $('#table-lancamento-despesa')
        .DataTable({
            // dom: 'Bfrtip',
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
                url: '/lancamentos/despesa/datatables/server',
                data: 'data'
            },
            columns: [{
                data: 'id'
            },
                {
                    data: 'nome'
                },
                {
                    data: 'descricao'
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
                    data: 'fornecedor.nome'
                },{
                    data: 'categoria.nome'
                },
                {
                    data: 'dtPagamento',
                    render: function(dtPagamento) {
                        return (dtPagamento != null) ? moment(dtPagamento).format('L') : '';
                    },
                    className: "text-center"
                },{
                    data: 'dtVencimento',
                    render: function(dtVencimento) {
                        return moment(dtVencimento).format('L');
                    },
                    className: "text-center"
                },
                {
                    data: 'gastoFixo',
                    render: function(gastoFixo) {
                        return gastoFixo ? 'Sim' : 'Não';
                    },
                    className: "text-center"
                }, {
                    data: 'pagamento',
                    render: function(pagamento) {
                        return (pagamento == 'APRAZO') ? 'À prazo' : 'A vista';
                    }
                }, {
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
                    data: 'formaPagamento.nome'
                },
                {
                    orderable: false,
                    data: 'id', // adicionar
                    // botão
                    // editar
                    "render": function(id) {
                        return '<a class="btn btn-success btn-sm btn-block" href="/lancamentos/editar/' +
                            id +
                            '" role="button"><i class="fas fa-edit"></i></a>';
                    }
                },
                {
                    orderable: false,
                    data: 'id', // adicionar // botão // excluir
                    "render": function(id) {
                        return '<a class="btn btn-danger btn-sm btn-block" href="/lancamentos/excluir/' +
                            id +
                            '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                    }
                }
            ]
        });
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

$("#1").change(function() {
    if ($(this).prop("checked") == true) {
        $('#qtdsParcelas').show(1000);
    }
});

$("#1").trigger("change");

$("#0").change(function() {
    if ($(this).prop("checked") == true) {
        $('#qtdsParcelas').hide(1000);
    }
});

$("#0").trigger("change");