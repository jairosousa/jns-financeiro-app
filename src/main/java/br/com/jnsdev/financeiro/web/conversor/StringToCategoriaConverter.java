package br.com.jnsdev.financeiro.web.conversor;

import br.com.jnsdev.financeiro.domain.Categoria;
import br.com.jnsdev.financeiro.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategoriaConverter implements Converter<String, Categoria> {

    @Autowired
    private CategoriaService service;

    @Override
    public Categoria convert(String text) {
        if (text.isEmpty()) {
            return null;
        }
        Long id = Long.valueOf(text);
        return service.buscarPorId(id);
    }
}
