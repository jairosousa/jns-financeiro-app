package br.com.jnsdev.financeiro.domain;

import br.com.jnsdev.financeiro.domain.enuns.Pagamento;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "lancamentos_despesas")
public class LancamentoDespesa extends Lancamento {

	@Column(name = "data_pagamento", nullable = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dtPagamento;

	@Column(name = "data_vencimento", nullable = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dtVencimento;

	@Column(name = "gasto_fixo")
	private boolean gastoFixo;

	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	private Pagamento pagamento;

	@Column(name = "qtd_parcelas", nullable = true)
	private Integer qtdParcelas;

	@Column(name = "numero_parcela", nullable = true)
	private Integer numParcela;
	
	@NotNull
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    @Column(name="valor_parcela",columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
    private BigDecimal valorParcela;

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

	public boolean isGastoFixo() {
		return gastoFixo;
	}

	public void setGastoFixo(boolean gastoFixo) {
		this.gastoFixo = gastoFixo;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	@Override
	public String toString() {
		return "LancamentoDespesa{" +
				"dtPagamento=" + dtPagamento +
				", dtVencimento= " + dtVencimento +
				", gasto fixo= " + gastoFixo +
				", pagamento= " + pagamento +
				", qtdParcelas= " + qtdParcelas +
				", numParcela= " + numParcela +
				", categoria= " + categoria +
				", formaPagamento= " + formaPagamento.getNome() +
				'}';
	}
}
