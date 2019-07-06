package br.com.jnsdev.financeiro.domain;

import br.com.jnsdev.financeiro.domain.enuns.TipoLancamento;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("serial")
@Entity
@Table(name = "lancamentos")
public class Lancamento extends AbstractEntity {

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull(message = "{NotNull.lancamento.tipo}")
    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @NotNull
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    @Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
    private BigDecimal valor;

    @Column(name = "data_lancamento", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dtLancamento;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor = new Fornecedor();

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Lancamento() {
    }

    public Lancamento(Long id) {
        super.setId(id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Lancamento{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tipo=" + tipo +
                ", valor=" + valor +
                ", dtLancamento=" + dtLancamento +
                ", categoria=" + categoria +
                ", fornecedor=" + fornecedor +
                ", cliente=" + cliente +
                '}';
    }
}
