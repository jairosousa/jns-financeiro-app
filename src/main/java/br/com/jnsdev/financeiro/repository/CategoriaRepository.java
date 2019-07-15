package br.com.jnsdev.financeiro.repository;

import br.com.jnsdev.financeiro.domain.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	@Query("select c from Categoria c where c.nome like '%' || :search || '%'")
	Page<Categoria> findAllByNome(String search, Pageable pageable);

	@Query("select c from Categoria c order by c.nome")
	List<Categoria> findAllOrdeByNome();
}
