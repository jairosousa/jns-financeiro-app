package br.com.jnsdev.financeiro.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name = "FORNECEDORES")
public class Fornecedor extends AbstractEntity {

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "DATA_CADASTRO", nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dtCadastro = LocalDate.now();

	@Column(name = "ATIVIDADE")
	private String atividade;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	public Fornecedor() {
	}

	public Fornecedor(Long id) {
		super.setId(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDate dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Fornecedor [nome=" + nome + ", dtCadastro=" + dtCadastro + ", atividade=" + atividade + ", endereco="
				+ endereco + ", getId()=" + getId() + "]";
	}

}
