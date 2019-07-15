package br.com.jnsdev.financeiro.web.conversor;

import br.com.jnsdev.financeiro.domain.Fornecedor;
import br.com.jnsdev.financeiro.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToFornecedorConverter implements Converter<String, Fornecedor> {

    @Autowired
    private FornecedorService service;

    @Override
    public Fornecedor convert(String text) {
        if (text.isEmpty()) {
            return null;
        }
        Long id = Long.valueOf(text);
        return service.buscarPorId(id);
    }
}
