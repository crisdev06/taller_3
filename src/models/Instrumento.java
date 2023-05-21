package models;

public class Instrumento {
    private String codigo;
    private float precio;
    private int stock;
    private String nombre;
    private String material;

    public Instrumento( String codigo, float precio, int stock, String nombre, String material){
        this.codigo=codigo;
        this.precio=precio;
        this.stock=stock;
        this.nombre=nombre;
        this.material=material;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int  isStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String toCsv(){
        return this.codigo+","+this.precio+","+this.stock+","+this.nombre+","+this.material;
    }
}
