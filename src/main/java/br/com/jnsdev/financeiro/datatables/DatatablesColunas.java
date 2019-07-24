package br.com.jnsdev.financeiro.datatables;

public class DatatablesColunas {

    public static final String[] USUARIOS = {"id", "email", "ativo", "perfis"};
    public static final String[] CATEGORIAS = {"id", "nome"};
    public static final String[] FP = {"id", "nome"};
    public static final String[] FORNECEDORES = {"id", "nome", "dtCadastro", "atividade", "logradouro", "N°", "complemento", "cep", "bairro", "cidade", "uf"};
    public static final String[] FORMAS_PAGAMENTOS = {"id",  "nome"};
    public static final String[] LANCAMENTOS_RECEITA = {"id", "nome", "valor", "dtRecebimento", "fornecedor.nome", "dtLancamento", "descricao"};
    public static final String[] LANCAMENTOS_DESPESA = {"id", "nome", "valor", "dtLancamento", "dtPagamento", "dtVencimento", "pagamento", "qtdParcelas", "numParcela", "valorParcela", "gastoFixo", "formaPagamento.nome", "fornecedor.nome", "categoria.nome", "descricao"};
}
