package br.com.jnsdev.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Value("${url.default}")
	private String url;
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SpringTemplateEngine template;

	public void enviarPedidoDeConfirmacaoDeCadastro(String destino, String codigo) throws MessagingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = 
				new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
		
		Context context = new Context();
		context.setVariable("titulo", "Bem vindo ao Financys");
		context.setVariable("texto", "Precisamos que confirme seu cadastro, clicando no link abaixo");
		context.setVariable("linkConfirmacao", url + "/u/confirmacao/cadastro?codigo=" + codigo);
		
		String html = template.process("email/confirmacao", context);
		helper.setTo(destino);
		helper.setText(html, true);
		helper.setSubject("Confirmacao de Cadastro");
		helper.setFrom("nao-responder@gmail.com");
		
		helper.addInline("logo", new ClassPathResource("/static/image/logo2.png"));
		
		mailSender.send(message);
		
	}
	
	public void enviarPedidoRedefinicaoSenha(String destino, String verificador) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = 
        		new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
        
        Context context = new Context();
        context.setVariable("titulo", "Redefinição de Senha");
        context.setVariable("texto", "Para redefinir sua senha use o código de verficação " +
                "quando exigido no formulário." );
        context.setVariable("verificador", verificador);
        
        String html = template.process("email/confirmacao", context);        
        helper.setTo(destino);
        helper.setText(html, true);
        helper.setSubject("Redefinição de Senha");
        helper.setFrom("no-replay@gmail.com");

        helper.addInline("logo", new ClassPathResource("/static/image/logo2.png"));  
       
        mailSender.send(message);		
	}

}
