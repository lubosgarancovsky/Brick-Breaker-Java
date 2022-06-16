package sk.uniza.fri.prostredie;

import sk.uniza.fri.tvaryV3.Obdlznik;

/**
 *  PAD
 *  Vykreslenie a ovladanie padu
 */
public class Pad {
    private int velkost = 80;
    private int surX = 110;
    private final Obdlznik pad;
    public Pad() {
        this.pad = new Obdlznik();
        this.pad.zmenStrany(this.velkost, 5);
        this.pad.zmenFarbu("green");
        this.pad.posunVodorovne(-60 + this.surX);
        this.pad.posunZvisle(-50 + 285);
        this.pad.zobraz();
    }

    /**
     * Resetuje vlastnosti padu, potrebne pre restart hry
     */
    public void reset() {
        this.velkost = 80;
        this.pad.posunVodorovne(-this.surX + 110);
        this.surX = 110;
        this.pad.zmenStrany(this.velkost, 5);
        this.pad.zobraz();
    }

    public void posunVlavo() {
        if (this.surX > 0 ) {
            this.pad.posunVodorovne(-10);
            this.surX -= 10;
        }
    }

    public void posunVpravo() {
        if ((this.surX + this.velkost) < 300 ) {
            this.pad.posunVodorovne(10);
            this.surX += 10;
        }
    }

    public void zmenFarbu(String farba) {
        this.pad.zmenFarbu(farba);
        this.pad.zobraz();
    }

    public void setVelkost(int velkost) {
        this.pad.zmenStrany(velkost, 5);
        this.velkost = velkost;
        this.pad.zobraz();
    }

    public int getSurX() {
        return this.surX;
    }

    public int getVelkost() {
        return this.velkost;
    }
}
