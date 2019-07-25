package br.com.jnsdev.financeiro.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jnsdev.financeiro.domain.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{

	@Query("select fp from FormaPagamento fp where fp.cliente.id = :id AND fp.nome like '%' || :search || '%'")
	Page<FormaPagamento> findAllByNomeById(Long id, String search, Pageable pageable);

	@Query("select fp from FormaPagamento fp where fp.cliente.id = :id")
	Page<FormaPagamento> findAllById(Long id, Pageable pageable);
	
	@Query("select fp from FormaPagamento fp where fp.cliente.id = :idCliente")
	List<FormaPagamento> findAllByIdCliente(Long idCliente);


}
