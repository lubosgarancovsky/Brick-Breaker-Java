package sk.uniza.fri.prostredie.efekty;


import sk.uniza.fri.prostredie.Lopta;
import sk.uniza.fri.prostredie.Pad;

/**
 * Resetuje vlastnosti lopty a padu
 * tj. zrusi vsetly aktivovane efekty
 * Na obrazovke sa vykresli ako ruzovy kruh
 */
public class BezEfektov extends Efekt {
    public BezEfektov(int x, int y) {
        super(x, y, "magenta");
    }

    public void aktivuj(Lopta lopta, Pad pad) {
        lopta.setRychlost(5);
        pad.setVelkost(80);
    }
}
