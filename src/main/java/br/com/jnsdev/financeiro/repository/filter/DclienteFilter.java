package br.com.jnsdev.financeiro.repository.filter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DclienteFilter {

	private String mesAno;

	public DclienteFilter() {
		this.mesAno = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM-yyyy"));
	}

	public LocalDate getDateParse() {
		return LocalDate.parse(mesAno + "-01");
	}

	public int getMes() {
		return getDateParse().getMonthValue();
	}

	public int getAno() {
		return getDateParse().getYear();
	}

	@Override
	public String toString() {
		return "DclienteFilter [mesAno=" + mesAno + ", getDateParse()=" + getDateParse() + "]";
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

}
