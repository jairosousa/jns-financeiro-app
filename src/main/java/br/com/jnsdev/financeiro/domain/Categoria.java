package br.com.jnsdev.financeiro.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "CATEGORIAS")
public class Categoria extends AbstractEntity {

	@Column(name = "NOME", nullable = false)
	private String nome;

	public Categoria() {
		super();
	}

	public Categoria(Long id) {
		super.setId(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Categoria [nome=" + nome + ", getId()=" + getId() + "]";
	}

}
