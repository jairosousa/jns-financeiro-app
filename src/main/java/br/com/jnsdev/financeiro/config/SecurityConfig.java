package br.com.jnsdev.financeiro.config;

import br.com.jnsdev.financeiro.domain.enuns.PerfilTipo;
import br.com.jnsdev.financeiro.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ADMIN = PerfilTipo.ADMIN.getDesc();
    private static final String USUARIO = PerfilTipo.USUARIO.getDesc();

    @Autowired
    private UsuarioService service;

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/u/cadastro/usuario/novo", "/u/cadastro/usuario/save", "/u/cadastro/realizado").permitAll()
                .antMatchers("/u/confirmacao/cadastro").permitAll()
    			.antMatchers("/u/p/**").permitAll()
                // acesso privados admin
                .antMatchers("/u/editar/senha", "/u/confirmar/senha").hasAnyAuthority(USUARIO, ADMIN)
                .antMatchers("/enderecos/cep/").hasAnyAuthority(USUARIO, ADMIN)
                .antMatchers("/u/**").hasAuthority(ADMIN)
                .antMatchers("/fornecedores/*").hasAnyAuthority(ADMIN, USUARIO)
                .antMatchers("/fornecedores/editar/*").hasAuthority(ADMIN)
                .antMatchers("/fornecedores/excluir/*").hasAuthority(ADMIN)
                .antMatchers("/atividades/*").hasAuthority(ADMIN)
                // acesso privados USUARIO
                .antMatchers("/fp/**").hasAuthority(USUARIO)
                .antMatchers("/lancamentos/**").hasAuthority(USUARIO)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/principal", true)
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/acesso-negado");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }
}
