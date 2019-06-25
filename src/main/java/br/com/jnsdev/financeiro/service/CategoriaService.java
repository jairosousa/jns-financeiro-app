package br.com.jnsdev.financeiro.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jnsdev.financeiro.domain.Categoria;
import br.com.jnsdev.financeiro.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = false)
	public void salvar(Categoria categoria) {
		repository.save(categoria);

	}

}
