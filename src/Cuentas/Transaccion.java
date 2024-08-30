package Cuentas;
import java.util.Date;
public class Transaccion {
    String abonado;
    String debitado;
    double monto;
    Date fecha;

    public Transaccion(String abonado,String debitado, double monto, Date fecha) {
        this.abonado = abonado;
        this.debitado = debitado;
        this.monto = monto;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "abonado='" + abonado + '\'' +
                ", debitado='" + debitado + '\'' +
                ", monto=" + monto +
                ", fecha=" + fecha +
                '}';
    }
}
