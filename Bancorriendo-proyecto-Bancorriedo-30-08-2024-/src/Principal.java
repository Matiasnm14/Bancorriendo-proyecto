import Gestiones.Gestion;
import Gestiones.Transferiencia;
import Servicios.*;
import java.util.Scanner;

public class Principal {
    Scanner taclado = new Scanner(System.in);
    public static void main(String[] args) {
        Gestion g = new Gestion();
        Principal p = new Principal();
        Transferiencia t = new Transferiencia();
        t.contruccionArreglosServicios();
        String opcion;
         do{
             System.out.println("1) Iniciar Sesion");
             System.out.println("2) Registrarse");
             System.out.println("0) Cerrar");
             opcion = p.taclado.nextLine();
             switch (opcion){
                 case "1":
                     g.iniciarSesion();
                     break;
                 case "2":
                     g.registrarNuevoCliente();
                     break;
                 case "0":
                     System.out.println("Cerrando programa...");
                     break;
                 default:
                     System.out.println("Opcion no valida");
                     break;
             }
         }while (!opcion.equalsIgnoreCase("0"));
    }


}
