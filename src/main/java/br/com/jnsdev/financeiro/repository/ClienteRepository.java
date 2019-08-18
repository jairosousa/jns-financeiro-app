package br.com.jnsdev.financeiro.repository;

import br.com.jnsdev.financeiro.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Query("select c from Cliente c where c.usuario.email like :email")
	Optional<Cliente> findByClienteEmail(String email);

}
