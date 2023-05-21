package models;

public class InstrumentoPercucion extends Instrumento{

    private String tipo;
    private float altura;

    public InstrumentoPercucion(String codigo, float precio, int stock, String nombre, String material, String tipo, float altura ) {
        super(codigo, precio, stock, nombre, material);

        this.tipo=tipo;
        if (altura>0){
            this.altura=altura;
        }else {
            throw new IllegalArgumentException("Ingreso un valor incorrecto.");
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String toCsvPercucion(){
        return ","+this.tipo+","+this.altura;
    }
}
