package br.com.jnsdev.financeiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jnsdev.financeiro.domain.FormaPagamento;
import br.com.jnsdev.financeiro.repository.FormaPagamentoRepository;

@Service
public class FormasPagamentoService {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Transactional(readOnly = true)
	public List<FormaPagamento> buscarTodosPorUsuario(Long id) {
		return formaPagamentoRepository.findAllById(id);
	}

	@Transactional(readOnly = true)
	public boolean temFormaDePagamentos(Long id) {
		return buscarTodosPorUsuario(id).isEmpty();
	}

}
