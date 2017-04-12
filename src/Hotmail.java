


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

    public void generateAndSendHotmail(String correo, String usuario, String pass, String textoEnviar, String titulo,String rutaFitxero) {
        Properties mailProperties;
        Session mailSession;
        MimeMessage genMailMessage;
        try {
        mailProperties = System.getProperties();
        mailProperties.put("mail.smtp.port", "587");//le ponemos el puerto del hotmail
        mailProperties.put("mail.smtp.ssl.trust", "smtp.live.com");//se añade esta linia sino da errores con el protocolo ssl
        mailProperties.setProperty("mail.host", "smtp.live.com");// configuracion para que funcione en hotmail
        mailProperties.put("mail.smtp.auth", "true");// autentificacio
        mailProperties.put("mail.smtp.starttls.enable", "true");// seguretat

        System.out.println("Mail Server Properties have been setup successfully..");

        mailSession = Session.getDefaultInstance( mailProperties, null);
        mailProperties.setProperty("mail.pop3s.port", "995");

        genMailMessage = new MimeMessage( mailSession );

        // añadir email del destinatario en InternetAddress
        genMailMessage.addRecipient( Message.RecipientType.TO, new InternetAddress(correo) );
        genMailMessage.setSubject(titulo);
        genMailMessage.setFrom( new InternetAddress( correo ));
//--------------------------------------------------------------------------------------------
            // Creamos un cuerpo del correo con ayuda de la clase BodyPart
            BodyPart bp = new MimeBodyPart();
            // Asignamos el texto del correo
            bp.setText(textoEnviar);
            // Creamos un multipart al correo
            Multipart mp = new MimeMultipart();
            // Agregamos el texto al cuerpo del correo mp
            mp.addBodyPart(bp);
            // Ahora el proceso para adjuntar el archivo
            bp = new MimeBodyPart();
            String fitxero = rutaFitxero;
            DataSource fuente = new FileDataSource(fitxero);
            bp.setDataHandler(new DataHandler(fuente));
            bp.setFileName(fitxero);
            mp.addBodyPart(bp);

            // Asignamos al mensaje todas las partes que creamos anteriormente
            //generateMailMessage.setContent(mp);
            genMailMessage.setContent(mp, "text/html");

//--------------------------------------------------------------------------------------------

            //genMailMessage.setContent(textoEnviar, "text/html");

        System.out.println("Mail Session has been created successfully..");

        Transport transport = mailSession.getTransport("smtp");
        transport.connect("smtp.live.com", usuario, pass );
        transport.sendMessage( genMailMessage, genMailMessage.getAllRecipients());
        transport.close();



        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public void mostrarMensajes(String usuario, String pass) {
        try {
            MimeMessage genMailMessage;
            Properties mailProperties;
            Session mailSession;

            mailProperties = System.getProperties();
            mailProperties.put("mail.smtp.port", "587");//le ponemos el puerto del hotmail
            mailProperties.put("mail.smtp.ssl.trust", "smtp.live.com");//se añade esta linia sino da errores con el protocolo ssl
            mailProperties.setProperty("mail.host", "smtp.live.com");// configuracion para que funcione en hotmail
            mailProperties.put("mail.smtp.auth", "true");// autentificacio
            mailProperties.put("mail.smtp.starttls.enable", "true");// seguretat

            System.out.println("Mail Server Properties have been setup successfully..");

            mailSession = Session.getDefaultInstance(mailProperties, null);
            mailProperties.setProperty("mail.pop3s.port", "995");

            Store store = mailSession.getStore("pop3s");

            store.connect("pop3.live.com", 995, usuario, pass);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);

            // los 5 ultimos mensajes
            /*for (int i = messages.length - 5; i < messages.length; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);

            }*/

            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
                System.out.println("FLAG -> "+message.getFlags().toString());

            }

            emailFolder.close(false);
            store.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
