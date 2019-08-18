$(document).ready(function() {
    moment.locale('pt-BR');
    var table = $('#table-atividade').DataTable({
        dom: 'Bfrtip',
        buttons: [
        	'copyHtml5',
        	{
                extend: 'excel',
                title: 'Tabela de Atividades',
                exportOptions: {
                    columns: [ 0, 1 , 2, 3, 4]
                }
            },
            {
                extend: 'pdfHtml5',
                download: 'open',
                title: 'Tabela de Atividades',
                exportOptions: {
                    columns: [ 0, 1, 2, 3, 4 ]
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
            [1, "desc"]
        ],
        lengthMenu: [20, 30],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/atividades/datatables/server',
            data: 'data'
        },
        columns: [
            { data: 'id' },
            {
                data: 'momento',
                render: function(momento) {
                    return moment(momento).format('L LT');
                },
                className: "text-center"
            },
            { data: 'acao' },
            { data: 'titulo' },
            { data: 'usuario.email' },
        ]
    });


});