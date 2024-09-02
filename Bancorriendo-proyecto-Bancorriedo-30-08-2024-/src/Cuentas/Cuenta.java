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
        }
    }
    public boolean debitar(double monto) {
        if(tipoCuenta){
            if (monto > 0) {
                this.saldo -= monto;
            }else{
                System.out.println("Escoge un monto valido!");
            }
        }else{
            if(saldo < monto){
                System.out.println("Saldo insuficiente!");
                return false;
            }else{
                if (monto > 0) {
                    this.saldo -= monto;
                }else{
                    System.out.println("Escoge un monto valido!");
                }
            }
        }
        return true;
    }
    public boolean pagarDeuda(double deuda){
        if(deuda > this.saldo){
            System.out.println("Saldo insuficiente!");
            return false;
        }else{
            this.saldo += deuda;
        }
        return true;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isMoneda() {
        return moneda;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}