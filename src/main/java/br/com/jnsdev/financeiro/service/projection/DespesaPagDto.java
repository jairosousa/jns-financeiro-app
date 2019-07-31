package br.com.jnsdev.financeiro.service.projection;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class DespesaPagDto {

	@NotNull
	private Long id;

	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dtPagamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(LocalDate dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	@Override
	public String toString() {
		return "DespesaPagDto [id=" + id + ", dtPagamento=" + dtPagamento + "]";
	}

}
