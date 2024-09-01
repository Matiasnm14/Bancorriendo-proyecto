package Servicios;

public class Deudas {
    String mes;
    double monto;

    public Deudas(double monto, String mes) {
        this.mes = mes;
        this.monto = monto;
    }

    public String getMes() {
        return mes;
    }
    public double getMonto() {
        return monto;
    }
    @Override
    public String toString() {
        return "Deudas{" +
                "mes='" + mes + '\'' +
                ", monto=" + monto +
                '}';
    }
}