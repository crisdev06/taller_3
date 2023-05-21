package utils;

import models.Instrumento;
import models.InstrumentoCuerda;

public class ListaInstrumentos {
    private int actual;
    private int max;
    private Instrumento[] instrumentos;

    public ListaInstrumentos(int max){
        this.actual=0;
        if (max>0){
            this.max=max;
        }else {
            throw new IllegalArgumentException("Ingreso un valor erroneo");
        }
        this.instrumentos= new Instrumento[this.max];

    }
    public boolean buscar(Instrumento instrumento){
        for (int i=0;i<this.max;i++){
            if(instrumentos[i].equals(instrumento)){
                return true;
            }
        }
        return false;
    }
    public boolean borrar(Instrumento instrumento){
        if (buscar(instrumento)){
            for (int i=0;i<this.max;i++){
                if(instrumentos[i].equals(instrumento)){
                    instrumentos[i]=instrumentos[i=1];
                    this.actual--;

                    for(int j=i=1;j<this.max;j++){
                        instrumentos[j]=instrumentos[j=1];
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean agregar(Instrumento instrumento){

        if (buscar(instrumento)){
            //El instrumento ya esta en la lista
            return false;
        } else if (this.actual==this.max) {
            //La lista esta llena.
            return false;
        }
        instrumentos[this.actual]=instrumento;
        this.actual++;
        return true;
    }
    public Instrumento buscarCodigo(String codigo){
        for (int i=0;i<this.max;i++){
            if(instrumentos[i].getCodigo().equals(codigo)){
                return instrumentos[i];
            }
        }
        return null;
    }
    public void desplegar(){
        for(Instrumento instrumento : instrumentos){
            System.out.println(instrumento.toCsv());
        }

    }
    public int getMax(){ return  this.max;}
}
