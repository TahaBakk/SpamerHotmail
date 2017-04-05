


import java.io.IOException;
import java.security.Security;
import java.util.Properties;
import java.util.Scanner;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Created by x3727349s on 03/04/17.
 */
public class Hotmail {

    public void generateAndSendHotmail(String correo, String usuario, String pass, String textoEnviar, String titulo) {
        Properties mailProperties;
        Session mailSession;
        MimeMessage genMailMessage;
        try {
        mailProperties = System.getProperties();
        // port de hotmail
        mailProperties.put("mail.smtp.port", "587");

        // para hotmail hay que especificar el host en las propiedades ( con gmail no hace falta esta linea )
        mailProperties.setProperty("mail.host", "smtp.live.com");

        // autenticacio
        mailProperties.put("mail.smtp.auth", "true");
        // seguretat
        mailProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

        mailSession = Session.getDefaultInstance( mailProperties, null);
        mailProperties.setProperty("mail.pop3s.port", "995");

        genMailMessage = new MimeMessage( mailSession );

        // añadir email del destinatario en InternetAddress
        genMailMessage.addRecipient( Message.RecipientType.TO, new InternetAddress(correo) );
        //genMailMessage.setSubject(titulo);

            genMailMessage.setContent("asdasdasdasdasd", "text/html");

        System.out.println("Mail Session has been created successfully..");

        /*Transport transport = mailSession.getTransport("smtps");
        // añadir el correo hotmail y el pass de la cuenta de la que se enviara el correo
        transport.connect("smtp.live.com",25,usuario,pass);
        //transport.sendMessage( genMailMessage, genMailMessage.getAllRecipients() );
        transport.sendMessage(genMailMessage, genMailMessage.getAllRecipients());
        transport.close();*/

        Transport transport = mailSession.getTransport("smtp");
        transport.connect("smtp.live.com", usuario, pass );
        transport.sendMessage( genMailMessage, genMailMessage.getAllRecipients());
        transport.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
