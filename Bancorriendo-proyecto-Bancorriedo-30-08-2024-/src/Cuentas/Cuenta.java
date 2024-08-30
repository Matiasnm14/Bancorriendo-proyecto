package Cuentas;

public class Cuenta {
    String numeroDeCuenta;
    boolean moneda;
    double saldo;
    boolean tipoCuenta;
    Transaccion[] transacciones = new Transaccion[100];
    public Cuenta(boolean moneda, String numeroDeCuenta,double saldo, boolean tipoCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
        this.moneda = moneda;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
    }
    public Cuenta(){

    }

    @Override
    public String toString() {
        String monedaString;
        String tipoDeCuentaString;
        if(moneda){
            monedaString = "Dolares";
        }else{monedaString = "Bolivianos";}
        if(tipoCuenta){
            tipoDeCuentaString = "Credito";
        }else{tipoDeCuentaString = "Debito";}
        if(tipoCuenta) return "Numero de Cuenta: "+numeroDeCuenta+" Tipo de moneda: "+ monedaString+ " Tipo de cuenta: "+tipoDeCuentaString+ " Deuda: "+ saldo;
        if(!tipoCuenta) return "Numero de Cuenta: "+numeroDeCuenta+" Tipo de moneda: "+ monedaString+ " Tipo de cuenta: "+tipoDeCuentaString+ " Saldo: "+ saldo;

        return "na";
    }

    public Transaccion[] getTransacciones() {
        return transacciones;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public boolean isTipoCuenta() {
        return tipoCuenta;
    }

    public void abonar(double saldo) {
        if (saldo > 0) {
            this.saldo += saldo;
        }else{
            System.out.println("Escoge un monto valido!");
        }
    }
    public void debitar(double monto) {
        if (monto > 0) {
            this.saldo -= monto;
        }else{
            System.out.println("Escoge un monto valido!");
        }
    }

    public double getSaldo() {
        return saldo;
    }
}