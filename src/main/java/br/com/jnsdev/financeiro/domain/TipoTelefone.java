package br.com.jnsdev.financeiro.domain;

public enum TipoTelefone {

	RES("Residencial"), COM("Comercial"), CEL("Celular");

	private String desc;

	private TipoTelefone(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

}
