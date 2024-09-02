package Gestiones;
import Clientes.Beneficiario;
import Clientes.Cliente;
import Cuentas.Cuenta;
import Cuentas.Transaccion;
import Servicios.*;

import java.util.Date;
import java.util.Scanner;

public class Gestion {
    Cliente[] clientes = new Cliente[100];
    Scanner teclado = new Scanner(System.in);
    Transferiencia t = new Transferiencia();
    int numeroCliente;
    int numeroTarjeta = 4000;
    Date date = new Date();
    public static boolean validarCarnet(String ci){return ci.matches("^[1-9][0-9]*$") && ci.length() ==7;}
    public static boolean validarPassword(String pin) {return pin.matches("^(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9_+&*-]+$") && pin.length() >= 8 && !pin.contains(" ");}
    public void crearListas(){
        t.getLuz()[0] = new Servicio("1");
        t.getLuz()[0].getDeudas()[0] = new Deudas(123,"Junio");
        t.getLuz()[1] = new Servicio("2");
        t.getLuz()[1].getDeudas()[0] = new Deudas(103, "Julio");
        t.getLuz()[2] = new Servicio("1");
        t.getLuz()[2].getDeudas()[0] = new Deudas(123,"Agosto");

        t.getAgua()[0] = new Servicio("1");
        t.getAgua()[0].getDeudas()[0] = new Deudas(210,"Junio");
        t.getAgua()[1] = new Servicio("2");
        t.getAgua()[1].getDeudas()[0] = new Deudas(123,"Julio");

        t.getTele()[0] = new Servicio("73456234");
        t.getTele()[0].getDeudas()[0] = new Deudas(90,"Junio");
        t.getTele()[1] = new Servicio("78234542");
        t.getTele()[1].getDeudas()[0] = new Deudas(100, "Julio");

        t.getColegiaturas()[0] = new Colegiatura("86308", 1500);
        t.getColegiaturas()[1] = new Colegiatura("86003", 1700);
        t.getColegiaturas()[2] = new Colegiatura("86430", 1890);
    }
    public void registrarNuevoCliente(){
        String nombre;
        String ci ;
        String ciP;
        String password;
        boolean prueba = false;
        for (int i = 0; i < clientes.length; i++) {
            if(clientes[i] == null){
                System.out.print("Ingrese nombre completo: ");
                nombre = teclado.nextLine();
                do{
                    System.out.print("Ingrese Carnet de identidad: ");
                    ciP = teclado.nextLine();
                    if(!validarCarnet(ciP))System.out.println("Carnet invalido!");
                }while(!validarCarnet(ciP));

                ci = ciP;
                for (int j = 0; j < clientes.length; j++) {
                    if(clientes[j]!=null){
                        if (clientes[j].getCi().equals(ciP)){
                            prueba = true;
                            System.out.println("Ya existe el usario con el CI!");
                        }
                    }
                }
               if (!prueba){
                   do{
                       System.out.print("Ingrese una contraseña: ");
                       password = teclado.nextLine();
                       if(!validarPassword(password)) System.out.println("La contrasena debe incluir Mayusculas, Numeros y sin espacios!");
                   }while (!validarPassword(password));

                   clientes[i] = new Cliente(nombre,ci,password);
               }
                break;
            }
        }
    }
    public void iniciarSesion(){
        String ciP= "na";
        int ci=0;
        boolean pase;
        boolean pase1 = true;
        String passsword;
        int intentos=3;
        String opcion;

        do{
            try{
                pase = false;
                System.out.print("Ingrese Carnet de identidad: ");
                ciP = teclado.nextLine();
                ci = Integer.parseInt(ciP);
            }catch (Exception e){
                pase = true;

                try {
                    throw new MiException("Carnet Invalido");
                }catch (Exception E){
                    E.printStackTrace();
                    String k=teclado.nextLine();
                }
            }

        }while(pase);
        int contador=0;
        for (int i = 0; i < clientes.length; i++) {
            if(clientes[i]!=null){
                if(clientes[i].getCi().equals(ciP)){
                    contador++;
                    numeroCliente = i;
                    break;
                }
            }
        }
        if(contador == 0){
            System.out.println("No es usario del banco");
            do{
                System.out.println("Quiere registrase?");
                System.out.println("1) Si");
                System.out.println("2) No");
                opcion = teclado.nextLine();
                switch (opcion){
                    case "1":
                        registrarNuevoCliente();
                        break;
                    case "2":
                        System.out.println("Volviendo al menu...");
                        break;
                    default:
                        System.out.println("Opcion no valida!");
                        break;

                }
            }while (!opcion.equalsIgnoreCase("1")&& !opcion.equalsIgnoreCase("2"));

            pase1 = false;
        }
        if(pase1){
            do{
                System.out.print("Ingrese contrasena: ");
                passsword = teclado.nextLine();
                if(passsword.equals(clientes[numeroCliente].getPassword())){
                    menu();
                    intentos = 0;
                }else{
                    System.out.println("Contrasena incorrecta. Tiene "+ (intentos - 1) + " Intentos");
                    intentos--;
                }
            }while(intentos != 0);
        }
    }
    public void menu(){
        String opcion;
        String opcion1;
        String opcion3;
        String numeroCuenta = "na";
        boolean pase = false;
        int numeroCuentaP;
        System.out.println("Bienvenido al Bancorriendo "+ clientes[numeroCliente].getNombre()+"!");
        do{
            System.out.println("1) Crear nueva cuenta");
            System.out.println("2) Listar Cuentas");
            System.out.println("3) Abonar Cuenta");
            System.out.println("4) Transferir dinero");
            System.out.println("5) Crear beneficiarios");
            System.out.println("6) Pagar");
            System.out.println("7) Extracto de cuentas");
            System.out.println("0) Cerrar sesion");
            opcion = teclado.nextLine();
            switch (opcion){
                case "1":
                    crearCuentaNueva();
                    break;
                case "2":
                    listarCuentas();
                    break;
                case "3":
                    abonarCuentas();
                    break;
                case "4":
                    transferenciaEntreCuentas();
                    break;
                case "5":
                    crearBeneficiarios();
                    break;
                case "6":
                    do{
                        System.out.println("1) Consumo tarjeta de credito");
                        System.out.println("2) Pago de servicios");
                        System.out.println("3) Colegiaturas");
                        System.out.println("0) Volver");
                        opcion1 = teclado.nextLine();
                        switch (opcion1){
                            case "1":
                                pagaTarjetasCredito();
                                break;
                            case "2":
                                do {
                                    System.out.println("1) Luz");
                                    System.out.println("2) Agua");
                                    System.out.println("3) Telecomuinicaciones");
                                    System.out.println("0) Volver");
                                    opcion3 = teclado.nextLine();
                                    switch (opcion3){
                                        case "1":
                                            pagarDeudas(t.luz, "numero de medidor", "CREE");
                                            break;
                                        case "2":
                                            pagarDeudas(t.agua, "numero de medidor", "SAGUAPAC");
                                            break;
                                        case "3":
                                            pagarDeudas(t.tele, "numero de celular", "TIGO");
                                            break;
                                        case "0":
                                            System.out.println("Volviendo...");
                                            break;
                                        default:
                                            System.out.println("Seleccion no valida!");
                                            break;
                                    }
                                }while (!opcion3.equals("0")&&!opcion3.equals("1")&&!opcion3.equals("2")&&!opcion3.equals("3"));
                                break;
                            case "3":
                                pagarColegiatura();
                                break;
                            case "0":
                                System.out.println("Volviendo al menu...");
                                break;
                            default:
                                System.out.println("Selecciones una opcion valida!");
                                break;
                        }
                    }while (!opcion1.equalsIgnoreCase("0"));
                    break;
                case "7":

                    do{
                        try{
                            pase = false;
                            listarCuentas();
                            System.out.println("De que cuenta quiere ver los extractos? (Ingrese numero de cuenta)");
                            numeroCuenta = teclado.nextLine();
                            numeroCuentaP = Integer.parseInt(numeroCuenta);
                        }catch (Exception e){
                            pase = true;
                            try{
                                throw new MiException("Ingrese un numero de tarjeta valido");
                            }catch (Exception E){
                                String k = teclado.nextLine();
                                E.printStackTrace();
                            }
                        }
                    }while (pase);

                    for (int i = 0; i < clientes[numeroCliente].getCuentas().length; i++) {
                        if(clientes[numeroCliente].getCuentas()[i]!=null){
                            if(clientes[numeroCliente].getCuentas()[i].getNumeroDeCuenta().equals(numeroCuenta)){
                                    listarExtractos(i);
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Opcion no valida!");
                    break;
                case "0":
                    System.out.println("Hasta pronto "+clientes[numeroCliente].getNombre());
                    numeroCliente = 101;
                    break;
            }
        }while (!opcion.equalsIgnoreCase("0"));
    }
    public void crearCuentaNueva(){
        String numeroDeCuenta;
        boolean moneda = false;
        double saldo = 0;
        String saldoP;
        boolean prueba = false;
        boolean tipoCuenta = false;
        String opcion;

        for (int i = 0; i < clientes[numeroCliente].getCuentas().length; i++) {
            if(clientes[numeroCliente].getCuentas()[i]==null){
                do{
                    System.out.println("Que tipo de cuenta desea?");
                    System.out.println("1) Credito");
                    System.out.println("2) Debito");
                    opcion = teclado.nextLine();
                    switch (opcion){
                        case "1":
                            tipoCuenta = true;
                            break;
                        case "2":
                            tipoCuenta  =false;
                            break;
                        default:
                            System.out.println("Opcion no valida");
                            break;

                    }
                }while (!opcion.equalsIgnoreCase("1")&&!opcion.equalsIgnoreCase("2"));

                do{
                    System.out.println("Que tipo de moneda desea?");
                    System.out.println("1) Dolares");
                    System.out.println("2) Bolivianos");
                    opcion = teclado.nextLine();
                    switch (opcion){
                        case "1":
                            moneda = true;
                            break;
                        case "2":
                            moneda  =false;
                            break;
                        default:
                            System.out.println("Opcion no valida");
                            break;

                    }
                }while (!opcion.equalsIgnoreCase("1")&&!opcion.equalsIgnoreCase("2"));
                if(!tipoCuenta){
                    do{
                        try{
                            prueba = false;
                            System.out.print("Monto inicial de la cuenta: ");
                            saldoP = teclado.nextLine();
                            saldo = Double.parseDouble(saldoP);
                        }catch (Exception e){
                            prueba = true;
                            try{throw new MiException("Ingrese un monto valido!");
                            }catch (Exception E){
                                E.printStackTrace();
                                String k = teclado.nextLine();
                            }
                        }
                    }while (prueba);
                }
                numeroDeCuenta = String.valueOf(numeroTarjeta++);
                clientes[numeroCliente].getCuentas()[i] = new Cuenta(moneda,numeroDeCuenta, saldo, tipoCuenta );
                break;
            }
        }

    }
    public void crearExtractoParaClientes(String abonado, String debitado, double monto, Date fecha, int numeroBeneficiado, int numeroCuentaDebitado, int numeroCuentaAbonado){
        crearExtracto(abonado, debitado, monto, fecha, numeroCuentaDebitado);
        for (int i = 0; i < clientes[numeroBeneficiado].getCuentas()[numeroCuentaAbonado].getTransacciones().length; i++) {
            if(clientes[numeroBeneficiado].getCuentas()[numeroCuentaAbonado].getTransacciones()[i] == null){
                clientes[numeroBeneficiado].getCuentas()[numeroCuentaAbonado].getTransacciones()[i] = new Transaccion(abonado, debitado, monto, fecha);
                break;
            }
        }
    }
    public void crearExtracto(String abonado, String debitado, double monto, Date fecha, int numeroCuentaDebitado){
        for (int i = 0; i < clientes[numeroCliente].getCuentas()[numeroCuentaDebitado].getTransacciones().length; i++) {
            if(clientes[numeroCliente].getCuentas()[numeroCuentaDebitado].getTransacciones()[i] == null){
                clientes[numeroCliente].getCuentas()[numeroCuentaDebitado].getTransacciones()[i] = new Transaccion(abonado, debitado, monto, fecha);
                break;
            }
        }
    }
    public void listarExtractos(int numeroCuenta){
        int contador=0;
        for (int i = 0; i < clientes[numeroCliente].getCuentas()[numeroCuenta].getTransacciones().length; i++) {
            if(clientes[numeroCliente].getCuentas()[numeroCuenta].getTransacciones()[i] != null){
                System.out.println(clientes[numeroCliente].getCuentas()[numeroCuenta].getTransacciones()[i]);
                contador++;
            }
        }
        if(contador == 0){
            System.out.println("No hay extractos!");
        }
    }
    public boolean listarCuentas(){
        int contador=0;
        for (int i = 0; i < clientes[numeroCliente].getCuentas().length; i++) {
            if(clientes[numeroCliente].getCuentas()[i]!=null){
                System.out.println(clientes[numeroCliente].getCuentas()[i]);
                contador++;
            }
        }
        if(contador == 0){
            System.out.println("No hay cuentas!");
            return false;
        }
        return true;
    }
    public void crearBeneficiarios(){
        String nombreCompleto = "na";
        String numeroDeCuenta = "na";
        String nombreBanco;
        String ci = "na";
        int cip;
        int numeroCuentaP;
        boolean pase = false;
        boolean cliente = false;

        do{
            try{
                pase = false;
                System.out.println("Ingrese numero de cuenta del beneficiado: ");
                numeroDeCuenta = teclado.nextLine();
                numeroCuentaP = Integer.parseInt(numeroDeCuenta);
            }catch (Exception e){
                pase = true;
                try{
                    throw new MiException("Ingrese un numero de cuenta valido!");
                }catch (Exception E){
                    E.printStackTrace();
                    String k = teclado.nextLine();
                }
            }
        }while (pase);

        System.out.print("Ingrese nombre del banco: ");
        nombreBanco = teclado.nextLine();

        if(nombreBanco.equalsIgnoreCase("Bancorriendo")){
            for (int i = 0; i < clientes.length; i++) {
                if(clientes[i]!=null){
                    for (int j = 0; j < clientes[i].getCuentas().length; j++) {
                        if(clientes[i].getCuentas()[j]!=null){
                            if(clientes[numeroCliente].getCuentas()[j]!=null){
                                if(clientes[numeroCliente].getCuentas()[j].getNumeroDeCuenta().equals(numeroDeCuenta)){
                                    System.out.println("No puedes beneficiario de ti mismo!");
                                    pase = true;
                                    break;
                                }
                            }
                            if (clientes[i].getCuentas()[j].getNumeroDeCuenta().equalsIgnoreCase(numeroDeCuenta)) {
                                if(clientes[i].getCuentas()[j].isTipoCuenta()){
                                    System.out.println("No puedes beneficiar a cuentas de credito!");
                                    pase = true;
                                    break;
                                }else{
                                    System.out.println("Encontramos al beneficiado!");
                                    nombreCompleto = clientes[i].getNombre();
                                    ci = clientes[i].getCi();
                                    cliente = true;
                                    pase = true;
                                    break;
                                }
                            }

                        }
                    }

                }
            }
        }
        if(!pase){
            System.out.print("Nombre completo del beneficiado: ");
            nombreCompleto = teclado.nextLine();

            do{
                System.out.print("Ci del beneficiado: ");
                ci = teclado.nextLine();
                if(!validarCarnet(ci)){
                    System.out.println("Carnet no valido!");
                }
            }while (!validarCarnet(ci));
        }
        for (int i = 0; i < clientes[numeroCliente].getBeneficiarios().length; i++) {
            if(clientes[numeroCliente].getBeneficiarios()[i] == null){
                clientes[numeroCliente].getBeneficiarios()[i] = new Beneficiario(nombreCompleto, numeroDeCuenta, nombreBanco, ci , cliente);
            }
        }
    }
    public void abonarCuentas(){
        String numeroDeCuenta = "na";
        int numeroCuentaP;
        double monto=0;
        String montoP;
        boolean pase;
        boolean pase1;
        int contador = 0;
        for (int i = 0; i < clientes[numeroCliente].getCuentas().length; i++) {
            if(clientes[numeroCliente].getCuentas()[i]!=null){
                if(!clientes[numeroCliente].getCuentas()[i].isTipoCuenta()){
                    System.out.println(clientes[numeroCliente].getCuentas()[i]);
                    contador++;
                }
            }
        }
        if(contador!= 0){
            do{
                try{
                    pase = false;
                    System.out.println("Ingrese numero de cuenta para abonar: ");
                    numeroDeCuenta = teclado.nextLine();
                    numeroCuentaP = Integer.parseInt(numeroDeCuenta);
                }catch (Exception e){
                    pase = true;
                    try{
                        throw new MiException("Ingrese un numero de cuenta valido!");
                    }catch (Exception E){
                        E.printStackTrace();
                        String k = teclado.nextLine();
                    }
                }
            }while (pase);
            for (int i = 0; i < clientes[numeroCliente].getCuentas().length; i++) {
                if(clientes[numeroCliente].getCuentas()[i]!=null){
                    if(clientes[numeroCliente].getCuentas()[i].getNumeroDeCuenta().equals(numeroDeCuenta)){
                        do{
                            try {
                                pase1 = false;
                                System.out.print("Monto para abonar: ");
                                montoP = teclado.nextLine();
                                monto = Double.parseDouble(montoP);
                            }catch (Exception e){
                                pase1 = true;
                                try{throw new MiException("Ingrese un monto valido!");
                                }catch (Exception E){
                                    E.printStackTrace();
                                    String k = teclado.nextLine();
                                }
                            }
                        }while (pase1);
                        clientes[numeroCliente].getCuentas()[i].abonar(monto);
                        break;
                    }
                }
            }
        }else{
            System.out.println("No hay cuentas de Debito!");
        }
    }
    public void pagarDeudas(Servicio[] servicios, String mensaje, String mensaje1){
        int posicion;
        System.out.print("Ingrese "+mensaje+":");
        boolean pase;
        String codigo="na";
        do {
            try {
                pase = false;
                codigo = teclado.nextLine();
                int codigoP = Integer.parseInt(codigo);
            } catch (Exception e) {
                pase = true;
                System.out.println("Ingrese un numero valido!");
            }
        }while (pase);
        boolean pase1 = true;
        for (int i = 0; i < servicios.length; i++) {
            if (servicios[i].getCodigo().equals(codigo)){
                posicion = i;
                System.out.println(servicios[i].getDeudas()[0]);
                pase1 = true;
                String opcion;
                do {
                    System.out.println("Desea pagar la deuda? ");
                    System.out.println("1) Si");
                    System.out.println("2) No");
                    opcion = teclado.nextLine();
                    switch (opcion){
                        case "1":
                            pagarSi(servicios, posicion, mensaje1);
                            break;
                        case "2":
                            System.out.println("OK");
                            break;
                        default:
                            System.out.println("Seleccione una opcion valida!");
                            break;
                    }
                }while (!opcion.equalsIgnoreCase("2")&&!opcion.equalsIgnoreCase("1"));
                break;
            } else {
                pase1 = false;
            }
        }
        if (!pase1){
            System.out.println("No se encontró lo que buscabas");
        }
    }
    public void pagarSi(Servicio[] servicios, int posicion, String mensaje){
        listarCuentas();
        boolean pase;
        String numeroDeCuenta = "na";
        int numeroCuentaP;
        Date fecha = new Date();
        if(listarCuentas()){
            do{
                try{
                    pase = false;
                    System.out.println("Ingrese numero de cuenta: ");
                    numeroDeCuenta = teclado.nextLine();
                    numeroCuentaP = Integer.parseInt(numeroDeCuenta);
                }catch (Exception e){
                    pase = true;
                    try{
                        throw new MiException("Ingrese un numero de cuenta valido!");
                    }catch (Exception E){
                        E.printStackTrace();
                        String k = teclado.nextLine();
                    }
                }
            }while (pase);
            for (int i = 0; i < clientes[numeroCliente].getCuentas().length; i++) {
                if (clientes[numeroCliente].getCuentas()[i] != null){
                    if (clientes[numeroCliente].getCuentas()[i].getNumeroDeCuenta().equalsIgnoreCase(numeroDeCuenta)){
                        if(clientes[numeroCliente].getCuentas()[i].isTipoCuenta()){
                            if(clientes[numeroCliente].getCuentas()[i].isMoneda()){
                                clientes[numeroCliente].getCuentas()[i].debitar(servicios[posicion].getDeudas()[0].getMonto()/6.91);
                                crearExtracto(mensaje, clientes[numeroCliente].getCuentas()[i].getNumeroDeCuenta(), servicios[posicion].getDeudas()[0].getMonto(), fecha, i);
                            }else{
                                clientes[numeroCliente].getCuentas()[i].debitar(servicios[posicion].getDeudas()[0].getMonto());
                                crearExtracto(mensaje, clientes[numeroCliente].getCuentas()[i].getNumeroDeCuenta(), servicios[posicion].getDeudas()[0].getMonto(), fecha, i);
                            }
                        }else{
                            if(clientes[numeroCliente].getCuentas()[i].isMoneda()){
                                if(clientes[numeroCliente].getCuentas()[i].debitar(servicios[posicion].getDeudas()[0].getMonto()/6.91)){
                                    System.out.println("Servicio pagado!");
                                    crearExtracto(mensaje, clientes[numeroCliente].getCuentas()[i].getNumeroDeCuenta(), servicios[posicion].getDeudas()[0].getMonto(), fecha, i);
                                }
                            }

                        }
                    }
                }
            }
        }else{
            System.out.println("No hay cuentas!");
        }
    }
    public void pagarColegiatura(){
        String opcion;
        String numeroDeCuenta = "na";
        boolean pase = false;
        String codigo= "na";
        Date fecha = new Date();
        if(listarCuentas()){

            do {
                try {
                    pase = false;
                    System.out.print("Escriba el numero de cuenta que utilizara: ");
                    numeroDeCuenta = teclado.nextLine();
                    int codigoP = Integer.parseInt(numeroDeCuenta);
                } catch (Exception e) {
                    pase = true;
                    System.out.println("Ingrese un numero valido!");
                }
            }while (pase);
            do {
                try {
                    pase = false;
                    System.out.print("Escriba codigo del estudiante: ");
                    codigo = teclado.nextLine();
                    int codigoP = Integer.parseInt(codigo);
                } catch (Exception e) {
                    pase = true;
                    System.out.println("Ingrese un numero valido!");
                }
            }while (pase);

            for (int i = 0; i < t.colegiaturas.length; i++) {
                if(t.colegiaturas[i].getCodigo().equals(codigo)){
                    for (int j = 0; j < clientes[numeroCliente].getCuentas().length; j++) {
                            if(clientes[numeroCliente].getCuentas()[j]!=null){
                                if(clientes[numeroCliente].getCuentas()[j].getNumeroDeCuenta().equals(numeroDeCuenta)){
                                    do{
                                        System.out.println("La deuda del estudiante es de: "+ t.colegiaturas[i].getDeuda()+"bs");
                                        System.out.println("Desea pagarlo?");
                                        System.out.println("1) Si");
                                        System.out.println("2) No");
                                        opcion = teclado.nextLine();
                                        switch (opcion){
                                            case "1":
                                                if(clientes[numeroCliente].getCuentas()[j].isMoneda()){
                                                    clientes[numeroCliente].getCuentas()[j].debitar(t.colegiaturas[i].getDeuda()/ 6.91);
                                                    crearExtracto("UPB", clientes[numeroCliente].getCuentas()[j].getNumeroDeCuenta(),t.colegiaturas[i].getDeuda()/6.91, fecha, j);
                                                    t.colegiaturas[i].setDeuda(0);
                                                }else{
                                                    if(clientes[numeroCliente].getCuentas()[j].debitar(t.colegiaturas[i].getDeuda())){
                                                        crearExtracto("UPB", clientes[numeroCliente].getCuentas()[j].getNumeroDeCuenta(),t.colegiaturas[i].getDeuda(), fecha, j);
                                                        t.colegiaturas[i].setDeuda(0);
                                                    }
                                                }
                                                break;
                                            case "2":
                                                System.out.println("Volviendo al menu...");
                                                break;
                                            default:
                                                System.out.println("Escriba una opcion valida!");
                                                break;
                                        }
                                    }while (!opcion.equalsIgnoreCase("1")&&!opcion.equalsIgnoreCase("2"));
                            }
                        }
                    }
                }
            }
        }
    }
    public void pagaTarjetasCredito(){
        Date fecha = new Date();
        int contador = 0;
        int contador1 = 0;
        int posicionCredito = -1;
        int posicionDebito = -1;
        String numeroCuentaC = "na";
        String numeroCuentaD = "na";
        boolean pase;
        for (int i = 0; i < clientes[numeroCliente].getCuentas().length; i++) {
            if(clientes[numeroCliente].getCuentas()[i]!=null){
                if(clientes[numeroCliente].getCuentas()[i].isTipoCuenta()){
                    System.out.println(clientes[numeroCliente].getCuentas()[i]);
                    contador++;
                }else{
                    contador1++;
                }
            }
        }
        if(contador!=0&&contador1!=0){
            do {
                try {
                    pase = false;
                    System.out.print("Ingrese numero de cuenta de Credito que desea pagar: ");
                    numeroCuentaC = teclado.nextLine();
                    int k = Integer.parseInt(numeroCuentaC);
                } catch (Exception e) {
                    pase = true;
                    System.out.println("Ingrese un numero valido!");
                }
            }while (pase);
            for (int i = 0; i < clientes[numeroCliente].getCuentas().length; i++) {
                if(clientes[numeroCliente].getCuentas()[i]!=null){
                    if(clientes[numeroCliente].getCuentas()[i].getNumeroDeCuenta().equalsIgnoreCase(numeroCuentaC)){
                        posicionCredito = i;
                        for (int j = 0; j < clientes[numeroCliente].getCuentas().length; j++) {
                            if(clientes[numeroCliente].getCuentas()[j]!=null){
                                if(!clientes[numeroCliente].getCuentas()[j].isTipoCuenta()){
                                    System.out.println(clientes[numeroCliente].getCuentas()[j]);
                                }
                            }
                        }
                        do {
                            try {
                                pase = false;
                                System.out.print("Ingrese numero de cuenta de Debito con la que desea pagar: ");
                                numeroCuentaD = teclado.nextLine();
                                int k = Integer.parseInt(numeroCuentaD);
                            } catch (Exception e) {
                                pase = true;
                                System.out.println("Ingrese un numero valido!");
                            }
                        }while (pase);
                        for (int j = 0; j < clientes[numeroCliente].getCuentas().length; j++) {
                            if(clientes[numeroCliente].getCuentas()[j]!=null){
                                if(clientes[numeroCliente].getCuentas()[j].getNumeroDeCuenta().equalsIgnoreCase(numeroCuentaD)){
                                    posicionDebito = j;
                                    if(clientes[numeroCliente].getCuentas()[posicionCredito].isMoneda()){
                                        if(clientes[numeroCliente].getCuentas()[posicionDebito].isMoneda()){
                                            if(clientes[numeroCliente].getCuentas()[posicionDebito].pagarDeuda(clientes[numeroCliente].getCuentas()[posicionCredito].getSaldo())){
                                                crearExtractoParaClientes(clientes[numeroCliente].getCuentas()[posicionCredito].getNumeroDeCuenta(), clientes[numeroCliente].getCuentas()[posicionDebito].getNumeroDeCuenta(),clientes[numeroCliente].getCuentas()[posicionCredito].getSaldo(),fecha,posicionCredito, numeroCliente, posicionDebito);
                                                clientes[numeroCliente].getCuentas()[posicionCredito].setSaldo(0);
                                            }
                                        }else{
                                            if(clientes[numeroCliente].getCuentas()[posicionDebito].pagarDeuda(clientes[numeroCliente].getCuentas()[posicionCredito].getSaldo()*6.91)){
                                                crearExtractoParaClientes(clientes[numeroCliente].getCuentas()[posicionCredito].getNumeroDeCuenta(), clientes[numeroCliente].getCuentas()[posicionDebito].getNumeroDeCuenta(),clientes[numeroCliente].getCuentas()[posicionCredito].getSaldo()*6.91,fecha,posicionCredito , numeroCliente,posicionDebito );
                                                clientes[numeroCliente].getCuentas()[posicionCredito].setSaldo(0);
                                            }
                                        }
                                    }else{
                                        if(clientes[numeroCliente].getCuentas()[posicionDebito].isMoneda()){
                                            if (clientes[numeroCliente].getCuentas()[posicionDebito].pagarDeuda(clientes[numeroCliente].getCuentas()[posicionCredito].getSaldo() / 6.91)) {
                                                crearExtractoParaClientes(clientes[numeroCliente].getCuentas()[posicionCredito].getNumeroDeCuenta(), clientes[numeroCliente].getCuentas()[posicionDebito].getNumeroDeCuenta(),clientes[numeroCliente].getCuentas()[posicionCredito].getSaldo()/6.91,fecha,posicionCredito , numeroCliente, posicionDebito);
                                                clientes[numeroCliente].getCuentas()[posicionCredito].setSaldo(0);
                                            }
                                        }else{
                                            if(clientes[numeroCliente].getCuentas()[posicionDebito].pagarDeuda(clientes[numeroCliente].getCuentas()[posicionCredito].getSaldo())){
                                                crearExtractoParaClientes(clientes[numeroCliente].getCuentas()[posicionCredito].getNumeroDeCuenta(), clientes[numeroCliente].getCuentas()[posicionDebito].getNumeroDeCuenta(),clientes[numeroCliente].getCuentas()[posicionCredito].getSaldo(),fecha,posicionCredito, numeroCliente,  posicionDebito);
                                                clientes[numeroCliente].getCuentas()[posicionCredito].setSaldo(0);
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }else{
            System.out.println("No hay tarjetas de credito y debito");
        }
    }
    public void transferenciaEntreCuentas(){
        boolean pase;
        String numeroCuentaD = "na";
        String numeroCuentaA = "na";
        int posicionD = -1;
        int posicionA = -1;
        double monto = -1;
        int contador =0;
        Date fecha = new Date();
        for (int i = 0; i < clientes[numeroCliente].getCuentas().length; i++) {
            if(clientes[numeroCliente].getCuentas()[i]!=null){
                if(!clientes[numeroCliente].getCuentas()[i].isTipoCuenta()){
                    contador++;
                }
            }
        }
        if(contador == 2){
            for (int i = 0; i < clientes[numeroCliente].getCuentas().length; i++) {
                if(clientes[numeroCliente].getCuentas()[i]!=null){
                    if(!clientes[numeroCliente].getCuentas()[i].isTipoCuenta()){
                        System.out.println(clientes[numeroCliente].getCuentas()[i]);
                    }
                }
            }
            do {
                try {
                    pase = false;
                    System.out.print("Ingrese numero de cuenta a debitar: ");
                    numeroCuentaD = teclado.nextLine();
                    int k = Integer.parseInt(numeroCuentaD);
                } catch (Exception e) {
                    pase = true;
                    System.out.println("Ingrese un numero valido!");
                }
            }while (pase);
            do {
                try {
                    pase = false;
                    System.out.print("Ingrese numero de cuenta a abonar: ");
                    numeroCuentaA = teclado.nextLine();
                    int k = Integer.parseInt(numeroCuentaA);
                } catch (Exception e) {
                    pase = true;
                    System.out.println("Ingrese un numero valido!");
                }
            }while (pase);

            if(!numeroCuentaD.equals(numeroCuentaA)){
                for (int i = 0; i < clientes[numeroCliente].getCuentas().length; i++) {
                    if(clientes[numeroCliente].getCuentas()[i]!=null){
                        if(!clientes[numeroCliente].getCuentas()[i].isTipoCuenta()){
                            if(clientes[numeroCliente].getCuentas()[i].getNumeroDeCuenta().equals(numeroCuentaD)){
                                posicionD = i;
                            }
                            if(clientes[numeroCliente].getCuentas()[i].getNumeroDeCuenta().equals(numeroCuentaA)){
                                posicionA = i;
                            }
                        }
                    }
                }
                do {
                    try {
                        pase = false;
                        System.out.print("Ingrese monto a transferir: ");
                        String montoP = teclado.nextLine();
                        monto = Double.parseDouble(montoP);
                    } catch (Exception e) {
                        pase = true;
                        System.out.println("Ingrese un numero valido!");
                    }
                }while (pase);

                if(clientes[numeroCliente].getCuentas()[posicionA].isMoneda()){
                    if(clientes[numeroCliente].getCuentas()[posicionD].isMoneda()){
                        clientes[numeroCliente].getCuentas()[posicionD].debitar(monto);
                        clientes[numeroCliente].getCuentas()[posicionA].abonar(monto);
                        crearExtractoParaClientes(clientes[numeroCliente].getCuentas()[posicionA].getNumeroDeCuenta(), clientes[numeroCliente].getCuentas()[posicionD].getNumeroDeCuenta(), monto,fecha, numeroCliente, posicionD,posicionA);
                    }else{
                        clientes[numeroCliente].getCuentas()[posicionD].debitar(monto*6.91);
                        clientes[numeroCliente].getCuentas()[posicionA].abonar(monto);
                        crearExtractoParaClientes(clientes[numeroCliente].getCuentas()[posicionA].getNumeroDeCuenta(), clientes[numeroCliente].getCuentas()[posicionD].getNumeroDeCuenta(), monto,fecha, numeroCliente, posicionD,posicionA);
                    }
                }else{
                    if(clientes[numeroCliente].getCuentas()[posicionD].isMoneda()){
                        clientes[numeroCliente].getCuentas()[posicionD].debitar(monto/6.91);
                        clientes[numeroCliente].getCuentas()[posicionA].abonar(monto);
                        crearExtractoParaClientes(clientes[numeroCliente].getCuentas()[posicionA].getNumeroDeCuenta(), clientes[numeroCliente].getCuentas()[posicionD].getNumeroDeCuenta(), monto,fecha, numeroCliente, posicionD,posicionA);
                    }else{
                        clientes[numeroCliente].getCuentas()[posicionD].debitar(monto);
                        clientes[numeroCliente].getCuentas()[posicionA].abonar(monto);
                        crearExtractoParaClientes(clientes[numeroCliente].getCuentas()[posicionA].getNumeroDeCuenta(), clientes[numeroCliente].getCuentas()[posicionD].getNumeroDeCuenta(), monto,fecha, numeroCliente, posicionD,posicionA);
                    }
                }

            }

        }else{
            System.out.println("No hay mas de dos cuentas de debito!");
        }
    }
    public void tranferirEntreCuentaBanco(){
        int contador = 0;
        boolean pase;
        String numeroCuentaD = "na";
        String numeroCuentaA = "na";
        int posicionD = -1;
        int posicionA = -1;
        int posicionAA = -1;
        double monto = -1;
        Date fecha = new Date();

        for (int i = 0; i < clientes[numeroCliente].getCuentas().length; i++) {
            if(clientes[numeroCliente].getCuentas()[i]!=null){
                if(!clientes[numeroCliente].getCuentas()[i].isTipoCuenta()){
                    contador++;
                }
            }
        }

        if(contador > 0){
            for (int i = 0; i < clientes[numeroCliente].getCuentas().length; i++) {
                if(clientes[numeroCliente].getCuentas()[i]!=null){
                    if(!clientes[numeroCliente].getCuentas()[i].isTipoCuenta()){
                        System.out.println(clientes[numeroCliente].getCuentas()[i]);
                    }
                }
            }
            do {
                try {
                    pase = false;
                    System.out.print("Ingrese numero de cuenta a debitar: ");
                    numeroCuentaD = teclado.nextLine();
                    int k = Integer.parseInt(numeroCuentaD);
                } catch (Exception e) {
                    pase = true;
                    System.out.println("Ingrese un numero valido!");
                }
            }while (pase);
            for (int i = 0; i < clientes[numeroCliente].getBeneficiarios().length; i++) {
                if(clientes[numeroCliente].getBeneficiarios()[i]!=null){
                    if(clientes[numeroCliente].getBeneficiarios()[i].isCliente()){
                        System.out.println(clientes[numeroCliente].getBeneficiarios()[i]);
                    }
                }
            }
            do {
                try {
                    pase = false;
                    System.out.print("Ingrese numero de cuenta a debitar: ");
                    numeroCuentaA = teclado.nextLine();
                    int k = Integer.parseInt(numeroCuentaA);
                } catch (Exception e) {
                    pase = true;
                    System.out.println("Ingrese un numero valido!");
                }
            }while (pase);
            for (int i = 0; i < clientes[numeroCliente].getCuentas().length; i++) {
                if(clientes[numeroCliente].getCuentas()[i]!=null){
                    if(clientes[numeroCliente].getCuentas()[i].getNumeroDeCuenta().equals(numeroCuentaD)){
                        posicionD = i;
                    }
                }
            }for (int i = 0; i < clientes.length; i++) {
                for (int j = 0; j < clientes[i].getCuentas().length; j++) {
                    if(clientes[i]!=null){
                        if(clientes[i].getCuentas()[j]!=null){
                            if(clientes[i].getCuentas()[j].getNumeroDeCuenta().equals(numeroCuentaA)){
                                posicionA = i;
                                posicionAA = j;
                            }
                        }
                    }
                }
            }

            do {
                try {
                    pase = false;
                    System.out.print("Ingrese monto a transferir: ");
                    String montoP = teclado.nextLine();
                    monto = Double.parseDouble(montoP);
                } catch (Exception e) {
                    pase = true;
                    System.out.println("Ingrese un numero valido!");
                }
            }while (pase);

            if(clientes[numeroCliente].getCuentas()[posicionD].isMoneda()){
                if(clientes[posicionA].getCuentas()[posicionAA].isMoneda()){
                   clientes[numeroCliente].getCuentas()[posicionD].debitar(monto);
                   clientes[posicionA].getCuentas()[posicionAA].abonar(monto);
                   crearExtractoParaClientes(clientes[posicionA].getCuentas()[posicionAA].getNumeroDeCuenta(), clientes[numeroCliente].getCuentas()[posicionD].getNumeroDeCuenta(), monto, fecha, posicionA, posicionD, posicionAA );
                }else{

                }
            }

        }else{
            System.out.println("No hay cuentas de debito!");
        }
    }
}