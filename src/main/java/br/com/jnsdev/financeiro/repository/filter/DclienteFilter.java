package br.com.jnsdev.financeiro.repository.filter;

import java.time.LocalDate;

public class DclienteFilter {

	private int mes;

	private int ano;

	public DclienteFilter() {
		this.mes = LocalDate.now().getMonthValue();
		this.ano = LocalDate.now().getYear();
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

}
