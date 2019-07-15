package br.com.jnsdev.financeiro.repository;

import br.com.jnsdev.financeiro.domain.LancamentoReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoReceitaRepository extends JpaRepository<LancamentoReceita, Long> {
}
