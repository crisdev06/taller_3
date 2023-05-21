package services;

import models.Instrumento;

public interface BeatRhythm {

    boolean agregarInstrumentoPercucion();
    boolean agregarInstrumentoViento();
    boolean agregarInstrumentoCuerda();
    boolean eliminarInstrumento();
    void inventario();
    void cierre();

}
