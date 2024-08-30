package Gestiones;
import Servicios.*;

import java.util.Date;

public class Transferiencia {
    Gestion g = new Gestion();
    Luz[] luz = new Luz[3];
    Agua[] agua = new Agua[3];
    Telecomunicaciones[] tele = new Telecomunicaciones[3];
        Date date = new Date();
    public void contruccionArreglosServicios(String mensaje){
        luz[0] = new Luz("1", 150, date);
        luz[1] = new Luz("2", 160, date);
        luz[2] = new Luz("3", 155, date);
    }

}
