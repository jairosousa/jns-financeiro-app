package br.com.jnsdev.financeiro.service.projection;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

public class DclienteVal {

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal despesas;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal receitas;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal saldo;

	public DclienteVal() {
		super();
	}

	public DclienteVal(BigDecimal despesas, BigDecimal receitas) {
		this.despesas = despesas;
		this.receitas = receitas;
	}

	public BigDecimal getDespesas() {
		return despesas;
	}

	public void setDespesas(BigDecimal despesas) {
		this.despesas = despesas;
	}

	public BigDecimal getReceitas() {
		return receitas;
	}

	public void setReceitas(BigDecimal receitas) {
		this.receitas = receitas;
	}

	public BigDecimal getSaldo() {
		return receitas.subtract(despesas);
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
