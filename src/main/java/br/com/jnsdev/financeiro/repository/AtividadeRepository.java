package br.com.jnsdev.financeiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jnsdev.financeiro.domain.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long>{

	@Query("select a from Atividade a "
			+ "where a.acao like '%' || :search || '%' "
			+ "OR a.titulo like '%' || :search || '%' "
			+ " order by a.momento asc")
	Page<Atividade> findAllByNome(String search, Pageable pageable);
	
	
	Page<Atividade> findTop5ByOrderByMomentoDesc(Pageable pageable);
}
