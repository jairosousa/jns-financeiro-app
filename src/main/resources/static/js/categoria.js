$(document).ready(function() {
    moment.locale('pt-BR');
    var table = $('#table-categoria').DataTable({
        dom: 'Bfrtip',

        "language": {
            "url": "/datatables/translationBR"
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