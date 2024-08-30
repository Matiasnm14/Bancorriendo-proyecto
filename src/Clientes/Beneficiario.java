package Clientes;

public class Beneficiario {
    String nombreCompleto;
    String numeroDeCuenta;
    String nombreBanco;
    String ci;

    public Beneficiario(String nombreCompleto, String numeroDeCuenta, String nombreBanco, String ci) {
        this.nombreCompleto = nombreCompleto;
        this.numeroDeCuenta = numeroDeCuenta;
        this.nombreBanco = nombreBanco;
        this.ci = ci;
    }
}
