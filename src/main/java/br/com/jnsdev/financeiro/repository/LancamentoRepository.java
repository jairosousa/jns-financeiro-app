package br.com.jnsdev.financeiro.repository;

import br.com.jnsdev.financeiro.domain.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    @Query("select l from Lancamento l where l.cliente.id = :id")
    Page<Lancamento> findAllByIdCliente(Long id, Pageable pageable);

    @Query("select l from Lancamento l where l.cliente.id = :id " +
            "AND l.tipo like '%' || :search || '%' " +
            "OR l.nome  like '%' || :search || '%' " +
            "OR l.categoria.nome  like '%' || :search || '%' " +
            "OR l.fornecedor.nome  like '%' || :search || '%'"  )
    Page<Lancamento> findAllBySearchByIdCliente(Long id, String search, Pageable pageable);
}
