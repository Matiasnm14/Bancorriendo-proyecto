package Clientes;

public class Beneficiario {
    String nombreCompleto;
    String numeroDeCuenta;
    String nombreBanco;
    String ci;
    boolean cliente;

    public Beneficiario(String nombreCompleto, String numeroDeCuenta, String nombreBanco, String ci, boolean cliente) {
        this.nombreCompleto = nombreCompleto;
        this.numeroDeCuenta = numeroDeCuenta;
        this.nombreBanco = nombreBanco;
        this.ci = ci;
        this.cliente = cliente;
    }

    public boolean isCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Beneficiario{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", numeroDeCuenta='" + numeroDeCuenta + '\'' +
                ", nombreBanco='" + nombreBanco + '\'' +
                ", ci='" + ci + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}
