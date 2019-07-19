package br.com.jnsdev.financeiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jnsdev.financeiro.domain.LancamentoReceita;
import br.com.jnsdev.financeiro.repository.projection.LancamentoReceitaDTO;

@Repository
public interface LancamentoReceitaRepository extends JpaRepository<LancamentoReceita, Long> {

    @Query("select l.id as id, l.nome as nome, l.descricao as descricao,"
    		+ " l.valor as valor, l.dtLancamento as dtLacamento,"
    		+ " l.fornecedor as fornecedor, lr.dtRecebimento as dtRecebimento"
    		+ " from LancamentoReceita lr "
    		+ "	join lr.lancamento l"
    		+ " where l.cliente.id = :id")
    Page<LancamentoReceitaDTO> findAllByIdCliente(Long id, Pageable pageable);

    @Query("select l.id as id, l.nome as nome, l.descricao as descricao," 
    		+ " l.valor as valor, l.dtLancamento as dtLacamento," 
    		+ " l.fornecedor as fornecedor, lr.dtRecebimento as dtRecebimento" 
    		+ " from LancamentoReceita lr "
    		+ " join lr.lancamento l"
    		+ " where l.cliente.id = :id " +
            "AND l.nome  like '%' || :search || '%' " +
            "OR l.fornecedor.nome  like '%' || :search || '%'"  )
    Page<LancamentoReceitaDTO> findAllBySearchByIdCliente(Long id, String search, Pageable pageable);
}
