package Gestiones;
import Servicios.*;
import java.util.Scanner;

public class Transferiencia {
    Luz [] luz = new Luz[3];
    Agua[] agua = new Agua[3];
    Telecomunicaciones[] tele = new Telecomunicaciones[3];
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
}
