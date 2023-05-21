package utils;

import models.Instrumento;
import models.InstrumentoCuerda;
import services.BeatRhythmImpl;

public class Main {
    public static void main(String[] args) {

        BeatRhythmImpl systema = new BeatRhythmImpl();
        systema.lecturaArchivo();
        systema.menu();


    }
}