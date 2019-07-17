package br.com.jnsdev.financeiro.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("serial")
@Entity
@Table(name = "lancamentos_receitas")
public class LancamentoReceita extends Lancamento{

    @Column(name = "data_recebimento", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dtRecebimento;

    @OneToOne
    @JoinColumn(name="id")
    @MapsId
    private Lancamento lancamento;

    public LancamentoReceita() {
    	super();
    }
    

    public LancamentoReceita(Long id) {
        super.setId(id);;
    }

    public LocalDate getDtRecebimento() {
        return dtRecebimento;
    }

    public void setDtRecebimento(LocalDate dtRecebimento) {
        this.dtRecebimento = dtRecebimento;
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    @Override
    public String toString() {
        return "LancamentoReceita{" +
                "id= " + super.getId() +
                ", Nome= " + super.getNome() +
                ", cliente= " + super.getCliente().getNome() +
                ", dtRecebimento= " + dtRecebimento +
                '}';
    }
}
