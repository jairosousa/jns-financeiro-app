package br.com.jnsdev.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jnsdev.financeiro.domain.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long>{

}
