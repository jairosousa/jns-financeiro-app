package br.com.jnsdev.financeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jnsdev.financeiro.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Query("select c from Cliente c where c.usuario.email like :email")
	Optional<Cliente> findByUsuarioEmail(String email);

}
