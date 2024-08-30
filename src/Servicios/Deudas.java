package Servicios;

import java.util.Date;

public class Deudas {
    String codigo;
    double monto;
    Date fecha;
    public Deudas(String codigo, double monto, Date fecha) {
        this.codigo = codigo;
        this.monto = monto;
        this.fecha = fecha;
    }
}
