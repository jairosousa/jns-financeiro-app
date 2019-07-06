package br.com.jnsdev.financeiro.service;

import br.com.jnsdev.financeiro.domain.Lancamento;
import br.com.jnsdev.financeiro.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    public void salvar(Lancamento lancamento) {
        repository.save(lancamento);
    }
}
