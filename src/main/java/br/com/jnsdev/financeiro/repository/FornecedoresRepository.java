package br.com.jnsdev.financeiro.repository;

import br.com.jnsdev.financeiro.domain.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedoresRepository extends JpaRepository<Fornecedor, Long>{

	@Query("select f from Fornecedor f "
			+ "where f.nome like :search% "
			+ "OR f.atividade like :search% "
			+ "OR f.endereco.logradouro like :search% "
			+ "OR f.endereco.bairro like :search% "
			+ "OR f.endereco.cidade like :search% "
			+ "OR f.endereco.estado like :search%")
	Page<Fornecedor> findAllBySearchNome(String search, Pageable pageable);

}
