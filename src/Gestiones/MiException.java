package Gestiones;

public class MiException extends Exception{
    public MiException(String mensaje){
        super(mensaje+" PRESIONE ENTER PARA REINTENTAR!");
    }

}
