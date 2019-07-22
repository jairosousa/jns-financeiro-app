package br.com.jnsdev.financeiro.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("serial")
@Entity
@Table(name = "lancamentos_receitas")
public class LancamentoReceita extends Lancamento {

	@Column(name = "data_recebimento", nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dtRecebimento;

	@JoinColumn(name = "id")
	@MapsId
	private Long id;

	public LancamentoReceita() {
		super();
	}

	public LancamentoReceita(Long id) {
		super.setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDtRecebimento() {
		return dtRecebimento;
	}

	public void setDtRecebimento(LocalDate dtRecebimento) {
		this.dtRecebimento = dtRecebimento;
	}

	@Override
	public String toString() {
		return "LancamentoReceita{" + "id= " + getId() + ", Nome= " + super.getNome() + ", cliente= "
				+ super.getCliente().getNome() + ", cliente id= " + super.getCliente().getId() + ", dtRecebimento= "
				+ dtRecebimento + '}';
	}
}
