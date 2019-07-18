package br.com.jnsdev.financeiro.datatables;

public class DatatablesColunas {

    public static final String[] USUARIOS = {"id", "email", "ativo", "perfis"};
    public static final String[] CATEGORIAS = {"id", "nome"};
    public static final String[] FP = {"id", "nome"};
    public static final String[] FORNECEDORES = {"id", "nome", "dtCadastro", "atividade", "logradouro", "N°", "complemento", "cep", "bairro", "cidade", "uf"};
    public static final String[] PAGAMENTOS = {"id"};
    public static final String[] LANCAMENTOS_RECEITA = {"id", "nome","descricao", "valor", "dtLancamento", "fornecedor.nome", "dtRecebimento"};
    public static final String[] LANCAMENTOS = {"id", "tipo", "nome", "dtLancamento", "valor", "categoria.nome", "fornecedor.nome", "descricao"};
}
