package br.com.conam.desafiojsf.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailApp {
	
	public static void enviarEmail(String destinatario, String assunto, String mensagem) {
		
        Properties props = new Properties();
        props.put("mail.smtp.host", Mensagem.getMessage("email.host"));
        props.put("mail.smtp.socketFactory.port", Mensagem.getMessage("email.socketFactory.port"));
        if(Mensagem.getMessage("email.usaSSL").toUpperCase().equals("SIM")){
//        	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        	props.put("mail.smtp.starttls.enable", "true");
        }
        props.put("mail.smtp.auth", Mensagem.getMessage("email.auth"));
        props.put("mail.smtp.port", Mensagem.getMessage("email.port"));

        Session session = null;
        
        if(Mensagem.getMessage("email.usaAutenticacao").toUpperCase().equals("SIM")){
	        session = Session.getInstance(props,
	                    new javax.mail.Authenticator() {
	                         protected PasswordAuthentication getPasswordAuthentication() 
	                         {
	                               return new PasswordAuthentication(Mensagem.getMessage("email.usuario"), Mensagem.getMessage("email.senha"));
	                         }
	                    });
        }
        else {
        	session = Session.getInstance(props, null);
        }
        
        /** Ativa Debug para sessão */
        session.setDebug(true);

        try {

              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress(Mensagem.getMessage("email.remetente"))); //Remetente

              Address[] toUser = InternetAddress //Destinatário(s)
                         .parse(destinatario);  

              message.setRecipients(Message.RecipientType.TO, toUser);
              message.setSubject(assunto);//Assunto
              message.setText(mensagem);
              
              /** Método para enviar a mensagem criada */
              Transport.send(message);

              System.out.println("E-mail enviado");

         } catch (MessagingException e) {
              throw new RuntimeException(e);
        }
  }
}
