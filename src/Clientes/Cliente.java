package Clientes;

import Cuentas.Cuenta;
import Cuentas.Transaccion;

public class Cliente {
    String nombre;
    String ci;
    String password;
    Cuenta[] cuentas = new Cuenta[3];
    Beneficiario[] beneficiarios = new Beneficiario[5];

    public Cliente(String nombre, String ci, String password) {
        this.nombre = nombre;
        this.ci = ci;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Cliente)){
            return false;
        }
        Cliente cliente = (Cliente) obj;
        return (this.ci == cliente.ci);
    }

    public String getNombre() {
        return nombre;
    }

    public String getCi() {
        return ci;
    }

    public Cuenta[] getCuentas() {
        return cuentas;
    }

    public Beneficiario[] getBeneficiarios() {
        return beneficiarios;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", ci=" + ci +
                ", password='" + password + '\'' +
                '}';
    }
}
