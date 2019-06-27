package br.com.jnsdev.financeiro.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "TELEFONES")
public class Telefone extends AbstractEntity {

	@Column(name = "numero", nullable = false)
	private String numero;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;

	public Telefone() {
		super();
	}

	public Telefone(Long id) {
		super.setId(id);
	}

	public Telefone(Fornecedor fornecedor) {
		this.setFornecedor(fornecedor);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public String toString() {
		return "Telefone ["+ "numero=" + numero + ", getId()=" + getId() + ", Fornecedor: "+ fornecedor.getNome() +" ]";
	}

}
