package sk.uniza.fri.prostredie;

import sk.uniza.fri.tvaryV3.Kruh;

/**
 * LOPTA
 * Vykreslenie a ovladanie lopty
 */
public class Lopta {
    private int smerX;
    private int smerY;
    private int surX = 145;
    private int surY = 145;

    private final Kruh lopta;
    private int rychlost = 5;

    /**
     * V konstruktore sa nakresli lopta
     */
    public Lopta() {
        int velkost = 10;

        this.lopta = new Kruh();
        this.lopta.zmenFarbu("white");
        this.lopta.zmenPriemer(velkost);
        this.lopta.posunVodorovne(-20 + this.surX);
        this.lopta.posunZvisle(-60 + this.surY);
        this.lopta.zobraz();

        //smery pre let lopty
        this.smerX = 1;
        this.smerY = -1;
    }

    /**
     * let lopty sa vyuziva v triede Hra v metode Tik na posun lopty v case
     */
    public void letiLopta() {
        this.lopta.posunVodorovne(this.smerX * this.rychlost);
        this.lopta.posunZvisle(this.smerY * this.rychlost);
        this.surX = this.surX + (this.smerX * this.rychlost);
        this.surY = this.surY + (this.smerY * this.rychlost);
    }

    /**
     * Resetuje vlastnosti lopty, potrebne pre restart hry
     */
    public void reset() {
        this.rychlost = 5;
        this.lopta.posunVodorovne(-this.surX + 145);
        this.lopta.posunZvisle(-this.surY + 145);
        this.surX = 145;
        this.surY = 145;
        this.smerX = 1;
        this.smerY = -1;
        this.lopta.zobraz();
    }

    public int getSurX() {
        return this.surX;
    }

    public int getSurY() {
        return this.surY;
    }

    public int getSmerX() {
        return this.smerX;
    }

    public int getSmerY() {
        return this.smerY;
    }

    public void setSmerX(int smerX) {
        this.smerX = smerX;
    }

    public void setSmerY(int smerY) {
        this.smerY = smerY;
    }

    public void setRychlost(int rychlost) {
        this.rychlost = rychlost;
    }
}

