$(document).ready(function () {
	moment.locale('pt-BR');
    var table = $('#table-fornecedor').DataTable({
    	"language": {
    		"url": "/datatables/translationBR"
        },
    	searching: true,
    	order: [[ 1, "asc" ]],
    	lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/fornecedores/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'nome'},
            {
                data: 'dtCadastro', render:
                    function (dtCadastro) {
                        return moment(dtCadastro).format('L');
                    }
            },
            {data: 'atividade'},
            {data: 'endereco.logradouro'},
            {data: 'endereco.numero'},
            {data: 'endereco.complemento'},
            {data: 'endereco.cep'},
            {data: 'endereco.bairro'},
            {data: 'endereco.cidade'},
            {data: 'endereco.estado'},
            {orderable: false, 
             data: 'id', // adicionar botão editar
                "render": function(id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/fornecedores/editar/'+ 
                    	id +'" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
             data: 'id', // adicionar botão excluir
                "render": function(id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/fornecedores/excluir/'+ 
                    	id +'" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }               
            }
        ]
    });
});    