package br.com.jnsdev.financeiro.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.jnsdev.financeiro.domain.LancamentoDespesa;
import br.com.jnsdev.financeiro.repository.projection.LancamentoDespesaDTO;

public class LancamentoDespesaRepositoryImpl implements LancamentoDespesaRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<LancamentoDespesaDTO> findAllBySearchByIdClienteByMonth(Long id, int mes, int ano, String search,
			Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<LancamentoDespesaDTO> criteria = builder.createQuery(LancamentoDespesaDTO.class);
		Root<LancamentoDespesa> root = criteria.from(LancamentoDespesa.class);
		
		criteria.select(builder.construct(LancamentoDespesaDTO.class,
					root.get("nome"),
					root.get("descricao"),
					root.get("valor"),
					root.get("dtLancamento"),
					root.get("fornecedor").get("nome"),
					root.get("categoria").get("nome"),
					root.get("dtPagamento"),
					root.get("dtVencimento"),
					root.get("gastoFixo"),
					root.get("pagamento"),
					root.get("qtdParcelas"),
					root.get("numParcela"),
					root.get("valorParcela"),
					root.get("formaPagamento").get("nome")
					
				));
		
		Predicate[] predicates = criarRestricoes(id, mes, ano, search, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<LancamentoDespesaDTO> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		List<LancamentoDespesaDTO> list = query.getResultList();
		
		list.forEach(l -> System.out.println(l.getNome()));
	
		return new PageImpl<>(list, pageable, total(id, mes, ano, search));
						
	}

	private Long total(Long id, int mes, int ano, String search) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<LancamentoDespesa> root = criteria.from(LancamentoDespesa.class);

		Predicate[] predicates = criarRestricoes(id, mes, ano, search, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<LancamentoDespesaDTO> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalregistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalregistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalregistrosPorPagina);
		
	}

	private Predicate[] criarRestricoes(Long id, int mes, int ano, String search, CriteriaBuilder builder,
			Root<LancamentoDespesa> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.like(builder.lower(root.get("nome")), "%" + search.toLowerCase() + "%" ));
		predicates.add(builder.equal(root.get("cliente").get("id"), id));
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
