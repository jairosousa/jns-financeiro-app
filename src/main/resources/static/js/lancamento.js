$(document).ready(function() {
	moment.locale('pt-BR');
	var table = $('#table-lancamento')
		.DataTable({
			// dom: 'Bfrtip',
			processing : true,
			serverSide : true,
			"language" : {
				"url" : "/datatables/translationBR"
			},
			searching : true,
			order : [ [ 3, "desc" ] ],
			lengthMenu : [ 5, 10, 15 ],
			responsive : true,
			scrollY : "40vh",
			ajax : {
				url : '/lancamentos/datatables/server',
				data : 'data'
				},
				columns : [
					{
						data : 'id'
					},
					{
						data : 'tipo',
						className : 'text-center'
					},
					{
						data : 'nome'
					},
					{
						data : 'dtLancamento',
						render : function(dtLancamento) {
							return moment(dtLancamento).format('L');
						}, className : "text-center"
					},
					{
						data : 'valor',
						render : $.fn.dataTable.render.number('.', ',', 2, 'R$ '), className : "text-right"
					},
					{
						data : 'categoria.nome'
					},
					{
						data : 'fornecedor.nome'
					},
					{
						data : 'descricao'
					},
					{
						orderable : false,
						data : 'id', // adicionar
						// botão
						// editar
						"render" : function(id) {
							return '<a class="btn btn-success btn-sm btn-block" href="/lancamentos/editar/'
																+ id
																+ '" role="button"><i class="fas fa-edit"></i></a>';
													}
					},
					{
						orderable : false,
						data : 'id', // adicionar // botão // excluir
						"render" : function(id) {
							return '<a class="btn btn-danger btn-sm btn-block" href="/lancamentos/excluir/'
																+ id
																+ '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
							}
						} ]
					});

				})

$('#gasto').hide();
$('#qtdsParcelas').hide();

$("#aprazo").change(function() {
	if ($(this).prop("checked") == true) {
		$('#gasto').show(1000);
	}
});

$("#aprazo").trigger("change");

$("#parcelado").change(function() {
	if ($(this).prop("checked") == true) {
		$('#qtdsParcelas').show(1000);
	}
});

$("#parcelado").trigger("change");

$("#fixo").change(function() {
	if ($(this).prop("checked") == true) {
		$('#qtdsParcelas').hide(1000);
	}
});

$("#fixo").trigger("change");