package Gestiones;
import Servicios.*;
public class Transferiencia {
    Servicio [] luz = new Servicio[3];
    Servicio[] agua = new Servicio[3];
    Servicio[] tele = new Servicio[3];
    Colegiatura[] colegiaturas = new Colegiatura[3];
    public void contruccionArreglosServicios(){
        luz[0] = new Servicio("1");
        luz[0].getDeudas()[0] = new Deudas(123,"Junio");
        luz[1] = new Servicio("2");
        luz[1].getDeudas()[0] = new Deudas(103, "Julio");
        luz[2] = new Servicio("1");
        luz[2].getDeudas()[0] = new Deudas(123,"Agosto");

        agua[0] = new Servicio("1");
        agua[0].getDeudas()[0] = new Deudas(210,"Junio");
        agua[1] = new Servicio("2");
        agua[1].getDeudas()[0] = new Deudas(123,"Julio");

        tele[0] = new Servicio("73456234");
        tele[0].getDeudas()[0] = new Deudas(90,"Junio");
        tele[1] = new Servicio("78234542");
        tele[1].getDeudas()[0] = new Deudas(100, "Julio");

        colegiaturas[0] = new Colegiatura("86308", 1500);
        colegiaturas[1] = new Colegiatura("86003", 1700);
        colegiaturas[2] = new Colegiatura("86430", 1890);
    }
}
