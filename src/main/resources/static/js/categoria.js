$(document).ready(function() {
    moment.locale('pt-BR');
    var table = $('#table-categoria').DataTable({
        dom: 'Bfrtip',
        buttons: [
        	'copyHtml5',
        	{
                extend: 'excel',
                title: 'Tabela de Categorias',
                exportOptions: {
                    columns: [ 0, 1 ]
                }
            },
            {
                extend: 'pdfHtml5',
                download: 'open',
                title: 'Tabela de Categorias',
                exportOptions: {
                    columns: [ 0, 1 ]
                }
            }
        ],

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
            [1, "asc"]
        ],
        lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/categorias/datatables/server',
            data: 'data'
        },
        columns: [
            { data: 'id' },
            { data: 'nome' },
            {
                orderable: false,
                data: 'id', // adicionar botão editar
                "render": function(id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/categorias/editar/' +
                        id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {
                orderable: false,
                data: 'id', // adicionar botão excluir
                "render": function(id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/categorias/excluir/' +
                        id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });


});