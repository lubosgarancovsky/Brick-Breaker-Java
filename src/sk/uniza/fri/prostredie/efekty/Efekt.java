package sk.uniza.fri.prostredie.efekty;

import sk.uniza.fri.prostredie.Lopta;
import sk.uniza.fri.prostredie.Pad;
import sk.uniza.fri.tvaryV3.Kruh;

/**
 * Abstraktna trieda, ktora sluzi ako rodic pre rozne efekty
 * Obsahuje abstraktnu metodu aktivuj
 * Metoda aktivuj, robi v kazdom potomkovi nieco ine
 * Trieda vykresluje efekty na prislusnej pozicii s prislusnou farbou a ovlada ich padanie smerom k padu
 */
public abstract class Efekt {
    private final Kruh efekt;
    private final int surX;
    private int surY;

    //V konstruktore sa vykresli mala gulicka, ktora reprezentuje specialny efekt
    public Efekt (int x, int y, String farba) {
        this.surX = x;
        this.surY = y;

        this.efekt = new Kruh();
        this.efekt.zmenPriemer(10);
        this.efekt.zmenFarbu(farba);
        this.efekt.posunVodorovne(-20 + this.surX);
        this.efekt.posunZvisle(-60 + this.surY);
        this.efekt.zobraz();
    }

    //abstraktna metoda, ktory vyuzivaju potomkovia tejto triedy k aktivacii svojho efektu na pad alebo loptu
    public abstract void aktivuj(Lopta lopta, Pad pad);

    //efekt pada smerom dole
    public void padaj() {
        this.efekt.posunZvisle(3);
        this.surY += 3;
        this.efekt.zobraz();
    }

    //zmazanie efektu z obrazovky
    public void vymaz() {
        this.efekt.skry();
    }

    //gettery na suradnice
    public int getSurX() {
        return this.surX;
    }

    public int getSurY() {
        return this.surY;
    }
}
