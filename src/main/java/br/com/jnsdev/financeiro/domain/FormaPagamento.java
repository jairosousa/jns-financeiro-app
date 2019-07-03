package br.com.jnsdev.financeiro.domain;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "FORMAS_PAGAMENTO")
public class FormaPagamento extends AbstractEntity {

	@Column(name = "NOME", nullable = false)
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	public FormaPagamento() {
		super();
	}

	public FormaPagamento(Long id) {
		super.setId(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "FormaPagamento [nome=" + nome + ", cliente=" + cliente + ", getId()=" + getId() + "]";
	}

}
