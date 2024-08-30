package Servicios;

public class Servicio {
    String codigo;
    Deudas[] deudas = new Deudas[1];

    public Deudas[] getDeudas() {
        return deudas;
    }

    public Servicio(String codigo) {
        this.codigo = codigo;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
