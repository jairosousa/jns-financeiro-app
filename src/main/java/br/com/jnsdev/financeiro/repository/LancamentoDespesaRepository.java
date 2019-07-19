package br.com.jnsdev.financeiro.repository;

import br.com.jnsdev.financeiro.repository.projection.LancamentoDespesaDTO;
import br.com.jnsdev.financeiro.repository.projection.LancamentoReceitaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jnsdev.financeiro.domain.LancamentoDespesa;

@Repository
public interface LancamentoDespesaRepository extends JpaRepository<LancamentoDespesa, Long> {

    @Query("select l.id as id, l.nome as nome, l.descricao as descricao,"
            + " l.valor as valor, l.dtLancamento as dtLacamento,"
            + " l.fornecedor as fornecedor, ld.categoria as categoria," +
            " ld.dtPagamento as dtPagamento, ld.dtVencimento as dtVencimento," +
            " ld.gastoFixo as gastoFixo, ld.pagamento as pagamento, ld.qtdParcelas as qtdParcelas," +
            " ld.numParcela as numParcela, ld.valorParcela as valorParcela, ld.formaPagamento as formaPagamento"
            + " from LancamentoDespesa ld"
            + "	join ld.lancamento l"
            + " where l.cliente.id = :id")
    Page<LancamentoDespesaDTO> findAllByIdCliente(Long id, Pageable pageable);

    @Query("select l.id as id, l.nome as nome, l.descricao as descricao,"
            + " l.valor as valor, l.dtLancamento as dtLacamento,"
            + " l.fornecedor as fornecedor, ld.categoria as categoria," +
            " ld.dtPagamento as dtPagamento, ld.dtVencimento as dtVencimento," +
            " ld.gastoFixo as gastoFixo, ld.pagamento as pagamento, ld.qtdParcelas as qtdParcelas," +
            " ld.numParcela as numParcela, ld.valorParcela as valorParcela, ld.formaPagamento as formaPagamento"
            + " from LancamentoDespesa ld "
            + " join ld.lancamento l"
            + " where l.cliente.id = :id " +
            "AND l.nome  like '%' || :search || '%' " +
            "OR l.fornecedor.nome  like '%' || :search || '%'"  )
    Page<LancamentoDespesaDTO> findAllBySearchByIdCliente(Long id, String search, Pageable pageable);
}
