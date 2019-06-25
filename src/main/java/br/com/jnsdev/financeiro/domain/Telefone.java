package br.com.jnsdev.financeiro.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "TELEFONES")
public class Telefone extends AbstractEntity {

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoTelefone tipo;

	@Column(name = "numero", nullable = false)
	private String numero;
	
	@ManyToOne
	@JoinColumn(name="id_fornecedor")
	private Fornecedor fornecedor;

	public Telefone() {
		super();
	}

	public Telefone(Long id) {
		super.setId(id);
	}

	public TipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Telefone [tipo=" + tipo + ", numero=" + numero + ", getId()=" + getId() + "]";
	}

}
