$(document).ready(function () {
	$("#nome").focus();
	moment.locale('pt-BR');
    var table = $('#table-fornecedor').DataTable({
    	"language": {
    		"url": "/datatables/translationBR"
        },
    	searching: true,
    	order: [[ 0, "asc" ]],
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

function createPhone() {
    var phones = document.getElementsByClassName('phones');

    var lastPhone = phones[phones.length - 1];

    var new_phone = lastPhone.cloneNode(true);

    var input = new_phone.getElementsByTagName('input')[0];
    input.value = '';
    input.setAttribute('id', 'telefones' + phones.length + '.numero');
    input.setAttribute('name', 'telefones[' + phones.length + '].numero');

    var errorText = new_phone.getElementsByClassName('invalid-feedback')[0];

    if (errorText) {
        errorText.innerHTML = '';
    }

    document.getElementById('phones-wrapper').appendChild(new_phone);
    
    $(".tel")
	.mask("(99) 9999-9999?9")
	.focusout(function (event) {
		var target, phone, element;
		target = (event.currentTarget) ? event.currentTarget : event.srcElement;
		phone = target.value.replace(/\D/g, '');
		element = $(target);
		element.unmask();
		if (phone.length > 10) {
			element.mask("(99) 99999-999?9");
		} else {
			element.mask("(99) 9999-9999?9");
		}
	});
}   