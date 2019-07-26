package br.com.jnsdev.financeiro.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jnsdev.financeiro.domain.LancamentoReceita;
import br.com.jnsdev.financeiro.repository.projection.LancamentoReceitaDTO;

@Repository
public interface LancamentoReceitaRepository extends JpaRepository<LancamentoReceita, Long> {

    @Query("select lr.id as id, lr.nome as nome, lr.descricao as descricao,"
    		+ " lr.valor as valor, lr.dtLancamento as dtLacamento,"
    		+ " lr.fornecedor as fornecedor, lr.dtRecebimento as dtRecebimento"
    		+ " from LancamentoReceita lr"
    		+ " where lr.cliente.id = :id"
    		+ " and extract(month from lr.dtRecebimento) = :mes "
    		+ " and extract(year from lr.dtRecebimento) = :ano ")
    Page<LancamentoReceitaDTO> findAllByIdClienteByMonth(Long id, int mes, int ano, Pageable pageable);

    @Query("select lr.id as id, lr.nome as nome, lr.descricao as descricao," 
    		+ " lr.valor as valor, lr.dtLancamento as dtLacamento," 
    		+ " lr.fornecedor as fornecedor, lr.dtRecebimento as dtRecebimento" 
    		+ " from LancamentoReceita lr"
    		+ " where lr.cliente.id = :id"
    		+ " AND extract(month from lr.dtRecebimento) = :mes"
    		+ " AND extract(year from lr.dtRecebimento) = :ano "
            + " AND lr.nome  like '%' || :search || '%'"
            + " OR lr.fornecedor.nome  like '%' || :search || '%'")
    Page<LancamentoReceitaDTO> findAllBySearchByIdClienteByMonth(Long id, int mes, int ano, String search, Pageable pageable);

	@Query("select lr.dtRecebimento from LancamentoReceita lr where lr.id = :id")
	LocalDate findDataVencimento(Long id);
    
}
