package br.com.jnsdev.financeiro.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.jnsdev.financeiro.domain.enuns.Gasto;

@Entity
@Table(name = "lancamentos_despesas")
public class LancamentoDespesa extends Lancamento {

	@Column(name = "data_pagamento", nullable = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dtPagamento;

	@Column(name = "data_vencimento", nullable = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dtVencimento;

	@Column(name = "parcelado")
	private boolean parcelado;

	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	private Gasto gasto;

	@Column(name = "qtd_parcelas", nullable = true)
	private Integer qtdParcelas;

	@Column(name = "numero_parcela", nullable = true)
	private Integer numParcela;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "id_forma_pagamento")
	private FormaPagamento formaPagamento;

	@OneToOne
	@JoinColumn(name = "id")
	@MapsId
	private Lancamento lancamento;

	public LancamentoDespesa() {
	}

	public LancamentoDespesa(Long id) {
		super(id);
	}

	public LocalDate getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(LocalDate dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	public LocalDate getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(LocalDate dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public boolean isParcelado() {
		return parcelado;
	}

	public void setParcelado(boolean parcelado) {
		this.parcelado = parcelado;
	}

	public Gasto getGasto() {
		return gasto;
	}

	public void setGasto(Gasto gasto) {
		this.gasto = gasto;
	}

	public Integer getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public Integer getNumParcela() {
		return numParcela;
	}

	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	@Override
	public String toString() {
		return "LancamentoDespesa{" + "id= " + super.getId() + ", dtPagamento=" + dtPagamento + ", dtVencimento="
				+ dtVencimento + ", parcelada=" + parcelado + ", gasto=" + gasto + ", qtdParcelas=" + qtdParcelas
				+ ", numParcela=" + numParcela + ", categoria=" + categoria + ", formaPagamento=" + formaPagamento
				+ '}';
	}
}
