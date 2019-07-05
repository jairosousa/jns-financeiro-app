package br.com.jnsdev.financeiro.service;

import br.com.jnsdev.financeiro.domain.FormaPagamento;
import br.com.jnsdev.financeiro.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository repository;

	public void salvar(FormaPagamento fp) {
		repository.save(fp);
	}

}
