package sk.uniza.fri.prostredie.efekty;

import sk.uniza.fri.prostredie.Lopta;
import sk.uniza.fri.prostredie.Pad;

/**
 * Efekt, ktory zpomaluje loptu, na obrazovke sa vykresli ako cerveny kruh
 * Potomok triedy Efekt
 */
public class PomalaLopta extends Efekt {
    public PomalaLopta(int x, int y) {
        super(x, y, "red");
    }

    //zmeni rychlost lopty z 5 na 2
    public void aktivuj(Lopta lopta, Pad pad) {
        lopta.setRychlost(2);
    }
}
