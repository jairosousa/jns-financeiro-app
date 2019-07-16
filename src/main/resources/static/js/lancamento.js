$(document).ready(function () {
    moment.locale('pt-BR');
    var table = $('#table-lancamento').DataTable({
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
        scrollY:  "40vh",
        ajax: {
            url: '/lancamentos/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'tipo', className: 'text-center'},
            {data: 'nome'},
            {
                data: 'dtLancamento', render:
                    function (dtLancamento) {
                        return moment(dtLancamento).format('L');
                    },
                className: "text-center"
            },
            {data: 'valor', render: $.fn.dataTable.render.number('.', ',', 2, 'R$ '), className: "text-right"},
            {data: 'categoria.nome'},
            {data: 'fornecedor.nome'},
            {data: 'descricao'},
            {
                orderable: false,
                data: 'id', // adicionar botão editar
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/lancamentos/editar/' +
                        id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {
                orderable: false,
                data: 'id', // adicionar botão excluir
                "render": function (id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/lancamentos/excluir/' +
                        id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });

})

$("#parcelado").change(function() {
  if ($(this).prop("checked") == true) {
    $('#gasto').append(
    		
    	`
    	<div class="form-check">
	          <div class="input-group">
	             <input type="checkbox" value="1" class="form-check-input" id="fixo" th:field="*{gasto}">
	    		<label class="form-check-label text-dark" for="fixo">Fixo</label>  
	          </div>
        </div>
        <div class="form-check">
	        <div class="input-group">
	           <input type="checkbox" value="2" class="form-check-input" id="parcelado" th:field="*{gasto}">
	           <label class="form-check-label text-dark" for="parcelado">Parcelada</label>  
	        </div>
        </div>
        `
    )
  }
});

$("#parcelado").trigger("change");