package br.com.jnsdev.financeiro.domain.enuns;

public enum Pagamento {

    AVISTA(1, "À vista"), APRAZO(2, "A prazo");

    private long cod;
    private String desc;

    Pagamento(long cod, String desc) {
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
