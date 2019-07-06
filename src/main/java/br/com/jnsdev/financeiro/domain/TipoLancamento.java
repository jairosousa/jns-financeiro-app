package br.com.jnsdev.financeiro.domain;

public enum TipoLancamento {

    RECEITA(1, "Receita"), DESPESA(2, "Despesa");

    private long cod;
    private String desc;

    private TipoLancamento(long cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public long getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }
}
