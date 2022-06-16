package sk.uniza.fri.prostredie.tehly;

import sk.uniza.fri.tvaryV3.Obdlznik;

/**
 * Abstraktna trieda
 * Rodic pre tehly
 * vykresluje obdlzniky a ovlada celkove spravanie tehly
 */
public abstract class Tehla {
    private final Obdlznik tehla;
    private final int surX;
    private final int surY;
    private int zivoty;

    //V konstruktore sa nakresli tehla
    public Tehla(String farba, int x, int y, int zivoty) {
        //suradnice tehal na hracej ploche
        this.surX = x * 30;
        this.surY = y * 15;

        this.zivoty = zivoty;

        //vykreslenie tehly
        this.tehla = new Obdlznik();
        this.tehla.zmenFarbu(farba);
        this.tehla.zmenStrany(30, 15);
        this.tehla.posunVodorovne(-60 + this.surX);
        this.tehla.posunZvisle(-50 + this.surY);
        this.tehla.zobraz();
    }

    //vymazanie tehly z obrazovky
    public void zmaz() {
        this.tehla.skry();
    }

    //odrata zivot tehly
    public void odratajZivot() {
        this.zivoty = this.zivoty - 1;
    }

    //gettery
    public int getSurX() {
        return this.surX;
    }

    public int getSurY() {
        return this.surY;
    }

    public int getZivoty() {
        return this.zivoty;
    }
}
