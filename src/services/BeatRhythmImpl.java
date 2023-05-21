package services;
import java.io.*;

import java.util.Scanner;

import models.Instrumento;
import models.InstrumentoCuerda;
import models.InstrumentoPercucion;
import models.InstrumentoViento;
import utils.ListaInstrumentos;

public class BeatRhythmImpl implements BeatRhythm{

    private ListaInstrumentos lista;
    private boolean inicio;

    public BeatRhythmImpl(){

        this.lista= new ListaInstrumentos(1000);
        this.inicio=true;
    }

    public void menu(){

        while (this.inicio) {
            System.out.println("--BIENVENIDO A BEATTHERHYTHM--");
            System.out.println(" 1.- Mostrar Inventario.");
            //modificar archivos.
            System.out.println(" 2.- Agregar Al Inventario.");
            System.out.println(" 3.- Eliminar Del Inventario.");
            System.out.println(" 4.- Salir");
            System.out.println("Que opcion desea?:");
            Scanner sc = new Scanner(System.in);
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1" -> inventario();
                case "2" -> escrituraArchivo();
                case "3" -> eliminarInstrumento();
                case "4" -> cierre();
            }
        }

    }



    public void lecturaArchivo(){
        Instrumento instrumentoArchivo;
        String[] datos;
        //falta agregar el nombre del archivo.cvs o txt
        try(Scanner scFile = new Scanner(new File("./csv_prueba.csv"))) {
            while(scFile.hasNextLine()) {
                datos = scFile.nextLine().split(",");
                /**String codigo = datos[0];
                float precio = Float.parseFloat(datos[1]);
                int stock = Integer.parseInt(datos[2]);
                String nombre = datos[3];
                String material = datos[4];*/

                if (datos.length==5){
                    instrumentoArchivo = new InstrumentoViento(datos[0],Float.parseFloat(datos[1]),
                            Integer.parseInt(datos[2]),datos[3],datos[4]);
                    this.lista.agregar(instrumentoArchivo);

                } else if (datos.length==7) {
                    instrumentoArchivo = new InstrumentoPercucion(datos[0],Float.parseFloat(datos[1]),
                            Integer.parseInt(datos[2]),datos[3],datos[4],datos[5],Float.parseFloat(datos[6]));
                    this.lista.agregar(instrumentoArchivo);

                } else if (datos.length==8) {
                    instrumentoArchivo = new InstrumentoCuerda(datos[0],Float.parseFloat(datos[1]),
                            Integer.parseInt(datos[2]),datos[3],datos[4],Integer.parseInt(datos[5]),datos[6],datos[7]);
                    this.lista.agregar(instrumentoArchivo);
                }

            }
        }catch (Exception ex){
            System.out.println("algo hice mal");
        }

    }
    public void escrituraArchivo(){

        boolean op= true;
        while (op) {
            System.out.println("Cual es la catergoria del instrumento que desea agregar.");
            System.out.println("[1] Percucion.");
            System.out.println("[2] Viento.");
            System.out.println("[3] Cuerdas.");
            System.out.println("[4] volver atras.");
            Scanner sc = new Scanner(System.in);
            String opcionInstrumento = sc.nextLine();
            switch (opcionInstrumento){
                case "1" -> agregarInstrumentoPercucion();
                case "2" -> agregarInstrumentoViento();
                case "3" -> agregarInstrumentoCuerda();
                case "4" -> {System.out.println("Volviendo a atras...");
                            op=false;}
                default -> throw new IllegalStateException("valor inesperado: " + opcionInstrumento);
            }
        }
    }


    @Override
    public boolean agregarInstrumentoPercucion() {
        System.out.println("estoy en agregar percucion");
        Scanner sc = new Scanner(System.in);
        String salida = "";
        File file = new File("./csv_prueba.csv");

        System.out.println("Ingrese los datos del instrumento.");
        while (!salida.equals("no")) {

            System.out.println("Ingrese codigo.");
            String codigo = sc.nextLine();
            System.out.println("Ingrese precio.");
            float precio = sc.nextFloat();
            if (precio<0f){
                return false;
            }

            System.out.println("Ingrese stock.");
            int stock = sc.nextInt();
            if (stock<1){
                return false;
            }

            System.out.println("Ingrese nombre.");
            String nombre = sc.nextLine();

            System.out.println("");

            System.out.println("Ingrese material.");
            String material = sc.nextLine();

            System.out.println("Ingrese el tipo (acustico o electrico).");
            String tipo = sc.nextLine().toLowerCase();
            if(!tipo.equals("acustico") || !tipo.equals("electrico")){
                return false;
            }
            System.out.println("Ingrese la altura.");
            float altura = sc.nextFloat();
            InstrumentoPercucion instrumentoPercucion = new InstrumentoPercucion(codigo,precio,stock,nombre,
                    material,tipo,altura);

            this.lista.agregar(instrumentoPercucion);
            System.out.println("estoy en agregar percucion");

            try(FileWriter fw = new FileWriter(file)){
                fw.write(instrumentoPercucion.toCsv()+instrumentoPercucion.toCsvPercucion()+"\n");

            }catch (Exception ex){
                throw new IllegalArgumentException("algo salio mal en la escritura.");
            }
            System.out.println("quiere agregar otro instrumento?.");
            System.out.println("escriba NO para terminar.");
            salida = sc.nextLine().toLowerCase();
            if (salida.equals("no")){
                return true;
            }
        }

        return false;
    }
    @Override
    public boolean agregarInstrumentoViento() {
        Scanner sc = new Scanner(System.in);
        String salida = "";
        File file = new File("./csv_prueba.csv");

        System.out.println("Ingrese los datos del instrumento.");
        while (!salida.equals("no")) {

            System.out.println("Ingrese codigo.");
            String codigo = sc.nextLine();
            System.out.println("Ingrese precio.");
            float precio = sc.nextFloat();
            if (precio<0){
                return false;
            }
            System.out.println("Ingrese stock.");
            int stock = sc.nextInt();
            if (stock<1){
                return false;
            }
            System.out.println("Ingrese nombre.");
            String nombre = sc.nextLine();
            System.out.println("Ingrese material.");
            String material = sc.nextLine();
            InstrumentoViento instrumentoViento = new InstrumentoViento(codigo,precio,stock,nombre,material);

            this.lista.agregar(instrumentoViento);

            try(FileWriter fw = new FileWriter(file)){
                fw.write(instrumentoViento.toCsv()+"\n");
            }catch (Exception ex){
                throw new IllegalArgumentException("algo salio mal en la escritura.");
            }
            System.out.println("quiere agregar otro instrumento?.");
            System.out.println("escriba NO para terminar.");
            salida = sc.nextLine().toLowerCase();
            if (salida.equals("no")){
                return true;
            }
        }

        return false;
    }
    @Override
    public boolean agregarInstrumentoCuerda() {
        Scanner sc = new Scanner(System.in);
        String salida = "";
        File file = new File("./csv_prueba.csv");

        System.out.println("Ingrese los datos del instrumento.");
        while (!salida.equals("no")) {

            System.out.println("Ingrese codigo.");
            String codigo = sc.nextLine();
            System.out.println("Ingrese precio.");
            float precio = sc.nextFloat();
            if (precio<0){
                return false;
            }
            System.out.println("Ingrese stock.");
            int stock = sc.nextInt();
            if (stock<1){
                return false;
            }
            System.out.println("Ingrese nombre.");
            String nombre = sc.nextLine();
            System.out.println("Ingrese material.");
            String material = sc.nextLine();
            System.out.println("Ingrese tipo cuerda.");
            String cuerda = sc.nextLine();
            System.out.println("Ingrese el numero de cuerdas.");
            int numeroCuerdas = sc.nextInt();
            if (numeroCuerdas<1){
                return false;
            }
            System.out.println("Ingrese el tipo (acustico o electrico).");
            String tipo = sc.nextLine().toLowerCase();
            if(!tipo.equals("acustico") || !tipo.equals("electrico")){
                return false;
            }
            InstrumentoCuerda instrumentoCuerda = new InstrumentoCuerda(codigo,precio,stock,nombre,
                    cuerda,numeroCuerdas,material,tipo);

            this.lista.agregar(instrumentoCuerda);

            try(FileWriter fw = new FileWriter(file)){
                fw.write(instrumentoCuerda.toCsv()+instrumentoCuerda.toCsvCuerda()+"\n");
            }catch (Exception ex){
                throw new IllegalArgumentException("algo salio mal en la escritura.");
            }
            System.out.println("quiere agregar otro instrumento?.");
            System.out.println("escriba NO para terminar.");
            salida = sc.nextLine().toLowerCase();
            if (salida.equals("no")){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean eliminarInstrumento() {
        Scanner sc = new Scanner(System.in);
        System.out.println("ingrese el codigo del instrumento que desea eliminar.");
        String codigo = sc.nextLine();
        for (int i=0;i<this.lista.getMax();i++){
            if (lista.buscarCodigo(codigo).equals(null)){
                return false;
            } else{
                Instrumento instrumentonEliminado = lista.buscarCodigo(codigo);
                lista.borrar(instrumentonEliminado);
                return true;
            }
        }
        return false;
    }

    @Override
    public void inventario() {
        this.lista.desplegar();

    }

    @Override
    public void cierre(){
        this.inicio=false;
        System.out.println("Hasta Pronto.");
    }
}
