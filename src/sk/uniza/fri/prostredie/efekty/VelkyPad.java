package sk.uniza.fri.prostredie.efekty;

import sk.uniza.fri.prostredie.Lopta;
import sk.uniza.fri.prostredie.Pad;

/**
 * Efekt, ktory zvacsuje velkost padu, na obrazovke sa vykresli ako zeleny kruh
 * Potomok triedy Efekt
 */
public class VelkyPad extends Efekt {
    public VelkyPad (int x, int y) {
        super(x, y, "green");
    }

    //zvacsi pad z 80 na 120
    public void aktivuj(Lopta lopta, Pad pad) {
        pad.setVelkost(120);
    }
}
