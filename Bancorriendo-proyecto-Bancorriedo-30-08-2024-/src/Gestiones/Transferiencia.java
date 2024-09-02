package Gestiones;
import Servicios.*;
public class Transferiencia {
    Servicio [] luz = new Servicio[3];
    Servicio[] agua = new Servicio[3];
    Servicio[] tele = new Servicio[3];
    Colegiatura[] colegiaturas = new Colegiatura[3];
    public Servicio[] getLuz() {
        return luz;
    }

    public Servicio[] getAgua() {
        return agua;
    }

    public Servicio[] getTele() {
        return tele;
    }

    public Colegiatura[] getColegiaturas() {
        return colegiaturas;
    }
}
