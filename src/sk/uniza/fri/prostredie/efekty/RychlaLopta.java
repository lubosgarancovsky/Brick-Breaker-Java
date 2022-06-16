package sk.uniza.fri.prostredie.efekty;

import sk.uniza.fri.prostredie.Lopta;
import sk.uniza.fri.prostredie.Pad;

/**
 * Efekt, ktory zrychluje loptu, na obrazovke sa vykresli ako zeleny kruh
 * Potomok triedy Efekt
 */
public class RychlaLopta extends Efekt {
    public RychlaLopta(int x, int y) {
        super(x, y, "green");
    }

    //zmeni rychlost lopty z 5 na 10
    public void aktivuj(Lopta lopta, Pad pad) {
        lopta.setRychlost(10);
    }
}
