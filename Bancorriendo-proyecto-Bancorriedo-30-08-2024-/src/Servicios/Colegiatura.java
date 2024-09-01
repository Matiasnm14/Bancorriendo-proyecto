package Servicios;

public class Colegiatura {
    String codigo;
    double deuda;

    public Colegiatura(String codigo, double deuda) {
        this.codigo = codigo;
        this.deuda = deuda;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }
}
