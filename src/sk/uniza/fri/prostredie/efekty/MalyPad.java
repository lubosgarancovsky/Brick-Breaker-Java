package sk.uniza.fri.prostredie.efekty;

import sk.uniza.fri.prostredie.Lopta;
import sk.uniza.fri.prostredie.Pad;

/**
 * Efekt, ktory zmensuje velkost padu, na obrazovke sa vykresli ako cerveny kruh
 * Potomok triedy Efekt
 */
public class MalyPad extends Efekt {
    public MalyPad (int x, int y) {
        super(x, y, "red");
    }

    //pad sa zmensi o polovicu, z 80 na 40
    public void aktivuj(Lopta lopta, Pad pad) {
        pad.setVelkost(40);
    }
}
