package br.com.jnsdev.financeiro.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.jnsdev.financeiro.domain.FormaPagamento;
import br.com.jnsdev.financeiro.service.FormaPagamentoService;

@Component
public class StringToFormaPagamentoConverter implements Converter<String, FormaPagamento> {

    @Autowired
    private FormaPagamentoService service;

    @Override
    public FormaPagamento convert(String text) {
        if (text.isEmpty()) {
            return null;
        }
        Long id = Long.valueOf(text);
        return service.buscaPorId(id);
    }
}
