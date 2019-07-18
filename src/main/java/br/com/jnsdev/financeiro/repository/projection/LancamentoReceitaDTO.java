package br.com.jnsdev.financeiro.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.jnsdev.financeiro.domain.Fornecedor;

public interface LancamentoReceitaDTO {

	Long getId();

	String getNome();

	String getDescricao();

	BigDecimal getValor();

	LocalDate getDtLacamento();

	Fornecedor getFornecedor();

	LocalDate getDtRecebimento();
}
