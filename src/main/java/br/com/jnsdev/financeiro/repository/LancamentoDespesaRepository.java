package br.com.jnsdev.financeiro.repository;

import br.com.jnsdev.financeiro.repository.projection.LancamentoDespesaDTO;
import br.com.jnsdev.financeiro.repository.projection.LancamentoReceitaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jnsdev.financeiro.domain.LancamentoDespesa;

import java.util.List;
import java.util.Optional;

@Repository
public interface LancamentoDespesaRepository extends JpaRepository<LancamentoDespesa, Long> {

    @Query("select ld.id as id, ld.nome as nome, ld.descricao as descricao,"
            + " ld.valor as valor, ld.dtLancamento as dtLacamento,"
            + " ld.fornecedor as fornecedor, ld.categoria as categoria," +
            " ld.dtPagamento as dtPagamento, ld.dtVencimento as dtVencimento," +
            " ld.gastoFixo as gastoFixo, ld.pagamento as pagamento, ld.qtdParcelas as qtdParcelas," +
            " ld.numParcela as numParcela, ld.valorParcela as valorParcela, ld.formaPagamento as formaPagamento"
            + " from LancamentoDespesa ld"
            + " where ld.cliente.id = :id")
    Page<LancamentoDespesaDTO> findAllByIdCliente(Long id, Pageable pageable);

    @Query("select ld.id as id, ld.nome as nome, ld.descricao as descricao,"
            + " ld.valor as valor, ld.dtLancamento as dtLacamento,"
            + " ld.fornecedor as fornecedor, ld.categoria as categoria," +
            " ld.dtPagamento as dtPagamento, ld.dtVencimento as dtVencimento," +
            " ld.gastoFixo as gastoFixo, ld.pagamento as pagamento, ld.qtdParcelas as qtdParcelas," +
            " ld.numParcela as numParcela, ld.valorParcela as valorParcela, ld.formaPagamento as formaPagamento"
            + " from LancamentoDespesa ld "
            + " where ld.cliente.id = :id " +
            "AND ld.nome  like '%' || :search || '%' " +
            "OR ld.fornecedor.nome  like '%' || :search || '%'"  )
    Page<LancamentoDespesaDTO> findAllBySearchByIdCliente(Long id, String search, Pageable pageable);

    @Query("select ld " +
            "from LancamentoDespesa ld " +
            "where ld.formaPagamento.id = :idFormaPagamento")
    List<LancamentoDespesa> hasFormaPagamentoDepesasCadastrada(Long idFormaPagamento);

    @Query("select ld " +
            "from LancamentoDespesa ld " +
            "where ld.categoria.id = :idCategoria")
    List<LancamentoDespesa> hasCategoriaDepesasCadastrada(Long idCategoria);

}
