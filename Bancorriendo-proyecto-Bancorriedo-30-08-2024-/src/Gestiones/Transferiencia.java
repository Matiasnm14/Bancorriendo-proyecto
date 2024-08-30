package Gestiones;
import Servicios.*;

import java.util.Date;
import java.util.Scanner;

public class Transferiencia {
    static Scanner teclado = new Scanner(System.in);
    Gestion g = new Gestion();
    Luz[] luz = new Luz[3];
    Agua[] agua = new Agua[3];
    Telecomunicaciones[] tele = new Telecomunicaciones[3];
        Date date = new Date();
    public void contruccionArreglosServicios(){
        luz[0] = new Luz("1");
        luz[0].getDeudas()[0] = new Deudas(123,"Junio");
        luz[1] = new Luz("2");
        luz[1].getDeudas()[0] = new Deudas(103, "Julio");
        luz[2] = new Luz("1");
        luz[2].getDeudas()[0] = new Deudas(123,"Agosto");

        agua[0] = new Agua("1");
        agua[0].getDeudas()[0] = new Deudas(210,"Junio");
        agua[1] = new Agua("2");
        agua[1].getDeudas()[0] = new Deudas(123,"Julio");

        tele[0] = new Telecomunicaciones("73456234");
        tele[0].getDeudas()[0] = new Deudas(90,"Junio");
        tele[1] = new Telecomunicaciones("78234542");
        tele[1].getDeudas()[0] = new Deudas(100, "Julio");
    }
    public void pagarDeudas(Servicio[] servicios, String mensaje){
        int contador=0;
        int posicion;
        System.out.print("Ingrese "+mensaje+":");
        boolean pase;
        String codigo="na";
        do {
            try {
                pase = false;
                codigo = teclado.nextLine();
                int codigoP = Integer.parseInt(codigo);
            } catch (Exception e) {
                pase = true;
                System.out.println("Ingrese un numero valido!");
            }
        }while (pase);
        boolean pase1 = true;
        for (int i = 0; i < servicios.length; i++) {
            if (servicios[i].getCodigo().equals(codigo)){
                posicion = i;
                System.out.println(servicios[i].getDeudas()[0]);
                pase1 = true;
                String opcion;
                do {
                    System.out.println("Desea pagar la deuda? ");
                    System.out.println("1) Si");
                    System.out.println("2) No");
                    opcion = teclado.nextLine();
                    switch (opcion){
                        case "1":
                            pagarSi(servicios, i);
                            break;
                        case "2":
                            System.out.println("OK");
                            break;
                        default:
                            System.out.println("Seleccione una opcion valida!");
                            break;
                    }
                }while (!opcion.equalsIgnoreCase("2")&&!opcion.equalsIgnoreCase("1"));
                break;
            } else {
                pase1 = false;
            }
        }
        if (!pase1){
            System.out.println("No se encontró lo que buscabas");
        }
    }
    public void pagarSi(Servicio[] servicios, int posicion){
        g.listarCuentas();
       boolean pase;
       String numeroDeCuenta = "na";
       int numeroCuentaP;
        do{
            try{
                pase = false;
                System.out.println("Ingrese numero de cuenta: ");
                numeroDeCuenta = teclado.nextLine();
                numeroCuentaP = Integer.parseInt(numeroDeCuenta);
            }catch (Exception e){
                pase = true;
                try{
                    throw new MiException("Ingrese un numero de cuenta valido!");
                }catch (Exception E){
                    E.printStackTrace();
                    String k = teclado.nextLine();
                }
            }
        }while (pase);
        for (int i = 0; i < g.clientes[g.numeroCliente].getCuentas().length; i++) {
            if (g.clientes[g.numeroCliente].getCuentas()[i] != null){
                if (g.clientes[g.numeroCliente].getCuentas()[i].getNumeroDeCuenta().equalsIgnoreCase(numeroDeCuenta)){
                    if(g.clientes[g.numeroCliente].getCuentas()[i].isTipoCuenta()){
                        g.clientes[g.numeroCliente].getCuentas()[i].debitar(servicios[posicion].getDeudas()[0].getMonto());
                    }else{
                        if(g.clientes[g.numeroCliente].getCuentas()[i].getSaldo() < servicios[posicion].getDeudas()[0].getMonto()){
                            System.out.println("Saldo insuficiente!");
                        }else{
                            g.clientes[g.numeroCliente].getCuentas()[i].debitar(servicios[posicion].getDeudas()[0].getMonto());
                        }
                    }
                }
            }
        }
    }

}
