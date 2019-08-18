package br.com.jnsdev.financeiro.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "ATIVIDADES")
public class Atividade extends AbstractEntity {

	@Column(name = "momento", nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Timestamp momento;

	@Column(name = "ACAO", nullable = false)
	private String acao;

	@Column(name = "TITULO", nullable = false)
	private String titulo;

	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Atividade() {
		super();
	}

	public Atividade(Long id) {
		super.setId(id);
	}

	public Atividade(String acao, String titulo, Usuario usuario) {
		this.acao = acao;
		this.titulo = titulo;
		this.usuario = usuario;
	}

	public Timestamp getMomento() {
		return momento;
	}

	public void setMomento(Timestamp momento) {
		this.momento = momento;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Atividade [momento=" + momento + ", acao=" + acao + ", titulo=" + titulo + ", usuario=" + usuario
				+ ", getId()=" + getId() + "]";
	}

}
