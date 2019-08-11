package br.com.jnsdev.financeiro.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jnsdev.financeiro.domain.LancamentoDespesa;
import br.com.jnsdev.financeiro.repository.lancamento.LancamentoDespesaRepositoryQuery;
import br.com.jnsdev.financeiro.repository.projection.LancamentoDespesaDTO;

@Repository
public interface LancamentoDespesaRepository extends JpaRepository<LancamentoDespesa, Long>{

        @Query("select ld.id as id, ld.nome as nome, ld.descricao as descricao,"
                        + " ld.valor as valor, ld.dtLancamento as dtLacamento,"
                        + " ld.fornecedor as fornecedor, ld.categoria as categoria,"
                        + " ld.dtPagamento as dtPagamento, ld.dtVencimento as dtVencimento,"
                        + " ld.gastoFixo as gastoFixo, ld.pagamento as pagamento, ld.qtdParcelas as qtdParcelas,"
                        + " ld.numParcela as numParcela, ld.valorParcela as valorParcela, ld.formaPagamento as formaPagamento"
                        + " from LancamentoDespesa ld"
                        + " where ld.cliente.id = :id"
                        + " and extract(month from ld.dtVencimento) = :mes"
                        + " and extract(year from ld.dtVencimento) = :ano")
        Page<LancamentoDespesaDTO> findAllByIdClienteByMonth(Long id, int mes, int ano, Pageable pageable);
        
        @Query("select ld.id as id, ld.nome as nome, ld.descricao as descricao,"
                + " ld.valor as valor, ld.dtLancamento as dtLacamento,"
                + " ld.fornecedor as fornecedor, ld.categoria as categoria,"
                + " ld.dtPagamento as dtPagamento, ld.dtVencimento as dtVencimento,"
                + " ld.gastoFixo as gastoFixo, ld.pagamento as pagamento, ld.qtdParcelas as qtdParcelas,"
                + " ld.numParcela as numParcela, ld.valorParcela as valorParcela, ld.formaPagamento as formaPagamento"
                + " from LancamentoDespesa ld" 
                + " where "
                + "(ld.nome  like '%' || :search || '%'"
                + " AND extract(month from ld.dtVencimento) = :mes"
                + " AND extract(year from ld.dtVencimento) = :ano" 
                + " AND ld.cliente.id = :id)"
                + " OR "
                + "(ld.fornecedor.nome  like '%' || :search || '%'"
                + " AND extract(month from ld.dtVencimento) = :mes"
                + " AND extract(year from ld.dtVencimento) = :ano" 
                + " AND ld.cliente.id = :id)"
                + " OR "
                + "(ld.descricao  like '%' || :search || '%'"
                + " AND extract(month from ld.dtVencimento) = :mes"
                + " AND extract(year from ld.dtVencimento) = :ano" 
                + " AND ld.cliente.id = :id)")
        Page<LancamentoDespesaDTO> findAllBySearchByIdClienteByMonth(Long id, int mes, int ano, String search,
                Pageable pageable);

        @Query("select ld " + "from LancamentoDespesa ld " + "where ld.formaPagamento.id = :idFormaPagamento")
        List<LancamentoDespesa> hasFormaPagamentoDepesasCadastrada(Long idFormaPagamento);

        @Query("select ld " + "from LancamentoDespesa ld " + "where ld.categoria.id = :idCategoria")
        List<LancamentoDespesa> hasCategoriaDepesasCadastrada(Long idCategoria);

        @Query("select ld.dtVencimento from LancamentoDespesa ld where ld.id = :id")
        LocalDate findDataVencimento(Long id);

        @Query("select ld from LancamentoDespesa ld" + " where ld.cliente.id = :id"
                        + " AND month(ld.dtVencimento) = :mes" + " AND year(ld.dtVencimento) = :ano"
                        + " AND ld.dtPagamento is null")
        Optional<List<LancamentoDespesa>> findByNotDataPagamento(Long id, int mes, int ano);
        
//        ///**DASHBOARD**///
        @Query("select sum(ld.valorParcela)"
        		+ " from LancamentoDespesa ld" 
        		+ " where ld.cliente.id = :id" 
        		+ " AND month(ld.dtVencimento) = :mes" 
        		+ " AND year(ld.dtVencimento) = :ano")
        Optional<BigDecimal> findSumMes(Long id, int mes, int ano);

}
