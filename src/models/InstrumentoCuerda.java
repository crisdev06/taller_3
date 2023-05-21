package models;

public class InstrumentoCuerda extends Instrumento{

    private String cuerda;
    private int numeroCuerdas;
    private String tipo;



    public InstrumentoCuerda(String codigo, float precio, int stock, String nombre, String cuerda,
                                int numeroCuerdas, String material, String tipo) {
        super(codigo, precio, stock, nombre,material);



        this.cuerda=cuerda;
        if (numeroCuerdas>1){
            this.numeroCuerdas=numeroCuerdas;
        }else{
            throw new IllegalArgumentException("tiene que tener al menos una cuerda");
        }

        this.tipo=tipo;

    }


    public String getCuerda() {
        return cuerda;
    }

    public void setCuerda(String cuerda) {
        this.cuerda = cuerda;
    }

    public int getNumeroCuerdas() {
        return numeroCuerdas;
    }

    public void setNumeroCuerdas(int numeroCuerdas) {
        this.numeroCuerdas = numeroCuerdas;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String toCsvCuerda(){
        return ","+this.cuerda+","+this.numeroCuerdas+","+this.tipo;
    }
}
