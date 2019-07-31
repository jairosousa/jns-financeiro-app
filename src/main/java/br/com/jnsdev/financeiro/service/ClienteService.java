package br.com.jnsdev.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jnsdev.financeiro.domain.Cliente;
import br.com.jnsdev.financeiro.domain.enuns.PerfilTipo;
import br.com.jnsdev.financeiro.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente getUsuarioLogado() {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		return buscarPorUsuarioEmail(username);
	}

	@Transactional(readOnly = true)
	public Cliente buscarPorUsuarioEmail(String email) {
		return repository.findByUsuarioEmail(email).orElse(new Cliente());
	}

	@Transactional(readOnly = false)
	public void salvar(Cliente cliente) {
		repository.save(cliente);
	}

	@Transactional(readOnly = false)
	public void editar(Cliente cliente) {
		Cliente c2 = repository.findById(cliente.getId()).get();
		c2.setNome(cliente.getNome());
		c2.setDtNascimento(cliente.getDtNascimento());
		c2.getEndereco().setLogradouro(cliente.getEndereco().getLogradouro());
		c2.getEndereco().setNumero(cliente.getEndereco().getNumero());
		c2.getEndereco().setCep(cliente.getEndereco().getCep());
		c2.getEndereco().setBairro(cliente.getEndereco().getBairro());
		c2.getEndereco().setComplemento(cliente.getEndereco().getComplemento());
		c2.getEndereco().setCidade(cliente.getEndereco().getCidade());
		c2.getEndereco().setEstado(cliente.getEndereco().getEstado());
	}

	@SuppressWarnings("serial")
	public boolean userHasAdmin(User user) {
		return user.getAuthorities().contains(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return PerfilTipo.ADMIN.getDesc();
			}
		});
	}

}
