import javax.mail.MessagingException;
import java.util.Scanner;

/**
 * Created by x3727349s on 03/04/17.
 */
public class Main {


    public static void main(String[] args)  {

        Scanner sc = new Scanner(System.in);
        Hotmail hotmail = new Hotmail();

        System.out.println("Introduce su correo:");
        String usuario = "tahabakk@hotmail.com";//sc.nextLine();
        System.out.println("-----------------------");
        System.out.println("Introduce la pass:");
        String pass = "";//sc.nextLine();
        System.out.println("-----------------------");
        System.out.println("Introduce el correo que recibira el mail:");
        String rutaenviar = "tahabakk@hotmail.com";//sc.nextLine();
        System.out.println("-----------------------");
        System.out.println("Introduce el titulo del mail:");
        String titulo = "tahaTest";//sc.nextLine();
        System.out.println("-----------------------");
        System.out.println("Introduce el texto del mail");
        String textoEnviar ="Hi taha" ;//sc.nextLine();
        System.out.println("-----------------------");

        hotmail.generateAndSendHotmail( rutaenviar, usuario, pass, textoEnviar, titulo);
        System.out.println("-----------------------");
        System.out.println("Programa finalizado");
        System.out.println("-----------------------");
    }

}
















