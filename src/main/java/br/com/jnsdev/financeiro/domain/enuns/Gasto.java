package br.com.jnsdev.financeiro.domain.enuns;

public enum Gasto {

    FIXO(1, "Fixo"), PARCELADO(2, "Parcelado");

    private long cod;
    private String desc;

    Gasto(long cod, String desc) {
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
