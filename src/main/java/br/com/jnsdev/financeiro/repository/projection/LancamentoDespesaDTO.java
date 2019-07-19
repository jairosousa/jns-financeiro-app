package br.com.jnsdev.financeiro.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.jnsdev.financeiro.domain.Categoria;
import br.com.jnsdev.financeiro.domain.FormaPagamento;
import br.com.jnsdev.financeiro.domain.Fornecedor;
import br.com.jnsdev.financeiro.domain.enuns.Pagamento;

public interface LancamentoDespesaDTO {

	Long getId();

	String getNome();

	String getDescricao();

	BigDecimal getValor();

	LocalDate getDtLacamento();

	Fornecedor getFornecedor();

	Categoria getCategoria();
	
	LocalDate getDtPagamento();
	
	LocalDate getDtVencimento();
	
	boolean getGastoFixo();
	
	String getPagamento();
	
	Integer getQtdParcelas();
	
	Integer getNumParcela();
	
	BigDecimal getValorParcela();
	
	FormaPagamento getFormaPagamento();
	
}
