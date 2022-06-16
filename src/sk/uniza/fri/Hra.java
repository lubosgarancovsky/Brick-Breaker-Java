package sk.uniza.fri;

import sk.uniza.fri.prostredie.Lopta;
import sk.uniza.fri.prostredie.Pad;
import sk.uniza.fri.prostredie.efekty.*;
import sk.uniza.fri.prostredie.tehly.Tehla;
import sk.uniza.fri.prostredie.tehly.ModraTehla;
import sk.uniza.fri.prostredie.tehly.ZltaTehla;
import sk.uniza.fri.prostredie.tehly.ZelenaTehla;
import sk.uniza.fri.prostredie.tehly.FialovaTehla;
import sk.uniza.fri.tvaryV3.Manazer;
import sk.uniza.fri.tvaryV3.Obdlznik;

import java.util.ArrayList;
import java.util.Random;

/**
 * Trieda ovladata posun lopty, padu, kolizie, generovanie a nicenie tehal, efekty a ukoncenie hry
 * Obsahuje metody z manazera
 * ==> tik()
 * SPACE - pozastavenie hry
 * ESC - ukoncenie hry
 * LEFT - posun padu vlavo
 * RIGHT - posun padu vpravo
 */
public class Hra {
    private final Pad pad;
    private final Lopta lopta;

    private boolean hraBezi = false;

    private ArrayList<Tehla> tehly;
    private final ArrayList<Efekt> efekty = new ArrayList<>();
    private Random generator;

    public Hra() {
        //inicializacia pozadia, lopty, padu, manazera a vykreslenie tehal na obrazovku
        Obdlznik pozadie = new Obdlznik();
        pozadie.posunVodorovne(-60);
        pozadie.posunZvisle(-50);
        pozadie.zmenStrany(300, 300);
        pozadie.zmenFarbu("black");
        pozadie.zobraz();

        this.pad = new Pad();
        this.lopta = new Lopta();
        this.pad.zmenFarbu("red");
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);

        this.nakresliTehly();
    }

    /**
     * Metoda tik() z manazera
     */
    public void tik() {
        //pokial hra stale bezi, kontroluju sa kolizie a let lopty
        if (this.hraBezi) {
            this.lopta.letiLopta();
            this.koliduje();
        } else {
            this.pad.zmenFarbu("red");
        }

        if(this.tehly.isEmpty()) {
            this.resetujHru();
        }
    }

    /**
     * Da hru do povodneho stavu, moze sa hrat odznova
     */
    private void resetujHru() {
        this.hraBezi = true;
        for (Tehla tehla : this.tehly) {
            this.tehly.remove(tehla);
            tehla.zmaz();
        }

        for (Efekt efekt : this. efekty) {
            this.efekty.remove(efekt);
            efekt.vymaz();
        }

        this.nakresliTehly();
        this.lopta.reset();
        this.pad.reset();
        this.hraBezi = false;
    }

    /**
     * Metoda kontroluje vsetky kolizie
     */
    private void koliduje() {
        //kolidacia lopty a padu
        if (
                this.lopta.getSurY() + 10 >= 285 && this.lopta.getSurX() >= this.pad.getSurX()
                && this.lopta.getSurX() + 10 <= this.pad.getSurX() + this.pad.getVelkost()
        ) {
            this.lopta.setSmerY(this.lopta.getSmerY() * (-1));
        }
        //koliduje so stenami
        if (this.lopta.getSurX() <= 0 || this.lopta.getSurX() + 10 >= 300) {
            this.lopta.setSmerX(this.lopta.getSmerX() * (-1));
        }
        //koliduje so stropom
        if (this.lopta.getSurY() <= 0) {
            this.lopta.setSmerY(this.lopta.getSmerY() * (-1));
        }
        //koliduje s podlahou
        if (this.lopta.getSurY() >= 300) {
            this.hraBezi = false;
            this.resetujHru();
        }

        //koliduje s tehlou
        int smerX = this.lopta.getSmerX();
        int smerY = this.lopta.getSmerY();
        int loptaX = this.lopta.getSurX();
        int loptaY = this.lopta.getSurY();
        //prehaldava tehly v ArrayListe
        for (Tehla t: this.tehly) {
            if (
                //podmienka pre koliziu so spodnou hranou
                    (smerY == -1 && (loptaX + 5 >= t.getSurX() && loptaX + 5 <= t.getSurX() + 30)
                    && (loptaY <= t.getSurY() + 15)) ||

                //kolizia s vrchnou hranou
                    (smerY == 1 && (loptaX + 5 >= t.getSurX() && loptaX + 5 <= t.getSurX() + 30)
                    && (loptaY + 10 >= t.getSurY()) && (loptaY + 10 <= t.getSurY() + 5))
            ) {
                //otoci smer lopty a odrata zivot tehle, ktoru lopta trafila
                this.lopta.setSmerY(this.lopta.getSmerY() * (-1));
                t.odratajZivot();
                //ak tehla uz nema ziady zivot, vymaze sa z obrazovky
                if (t.getZivoty() == 0) {
                    if (this.efekty.size() < 2) {
                        this.generujEfekt(t.getSurX(), t.getSurY());
                    }
                    this.tehly.remove(t);
                    t.zmaz();
                }
            }
            if (
                //kolizia s lavou stranou
                    (smerX == -1 && (loptaY + 5 >= t.getSurY() && loptaY + 5 <= t.getSurY() + 30)
                    && (loptaX <= t.getSurX() + 30 && loptaX >= t.getSurX() + 25)) ||

               //kolizia s pravou straou
                    (smerX == 1 && (loptaY + 5 >= t.getSurY() && loptaY + 5 <= t.getSurY() + 30)
                    && (loptaX + 10 >= t.getSurX()) && (loptaX + 10 <= t.getSurX() + 5))
            ) {
                this.lopta.setSmerX(this.lopta.getSmerX() * (-1));
                t.odratajZivot();
                if (t.getZivoty() == 0) {
                    if (this.efekty.size() < 2) {
                        this.generujEfekt(t.getSurX(), t.getSurY());
                    }
                    this.tehly.remove(t);
                    t.zmaz();
                }
            }
        }

        //kolidacia Efektu a padu, ak efekt s padom nekoliduje, bude padat dole
        if (!this.efekty.isEmpty() && this.hraBezi) {
            for (Efekt efekt : this.efekty) {
                if (
                        (efekt.getSurX() >= this.pad.getSurX() && efekt.getSurX() <= this.pad.getSurX() + this.pad.getVelkost())
                        && (efekt.getSurY() + 5 > 285 && efekt.getSurY() <= 290)
                ) {
                    this.efekty.remove(efekt);
                    efekt.aktivuj(this.lopta, this.pad);
                    efekt.vymaz();
                } else {
                    efekt.padaj();
                }
            }
        }
    }

    /**
     * vykreslenie tehal na obrazovku
     * tehly s mensim poctom zivotov maju vacsiu sancu na to, ze sa vykreslia
     */
    private void nakresliTehly() {
        this.tehly = new ArrayList<>();
        for (int x = 1; x < 9; x++) {
            for (int y = 2; y < 5; y ++) {
                this.generator = new Random();
                int randTehla = this.generator.nextInt(9);
                switch (randTehla) {
                    case 0, 1, 2:
                        this.tehly.add(new ZelenaTehla(x, y));
                        break;
                    case 3, 4, 5:
                        this.tehly.add(new ZltaTehla(x, y));
                        break;
                    case 6, 7:
                        this.tehly.add(new FialovaTehla(x, y));
                        break;
                    case 8:
                        this.tehly.add(new ModraTehla(x, y));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * generuje efekty po niceni tehly (20% sanca, ze sa vygeneruje efekt a kazdy z nich ma sancu 1/5)
     * @param x x-ova suradnica znicenej tehly, na nej sa generuje efekt
     * @param y y-ova suradnica znicenej tehly, na nej sa generuje efekt
     */
    private void generujEfekt(int x, int y) {
        this.generator = new Random();
        int sanca = this.generator.nextInt(100); //generuje 20% sancu pre spawn efektu

        if (sanca <= 20) {
            int genEfekt = this.generator.nextInt(5); //vyberie, ktory efekt sa spawne
            switch(genEfekt) {
                case 0:
                    this.efekty.add(new PomalaLopta(x, y));
                    break;
                case 1:
                    this.efekty.add(new RychlaLopta(x, y));
                    break;
                case 2:
                    this.efekty.add(new VelkyPad(x, y));
                    break;
                case 3:
                    this.efekty.add(new MalyPad(x, y));
                    break;
                case 4 :
                    this.efekty.add(new BezEfektov(x, y));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Pomocou medzernika sa da pozastavit hra
     * Pozastavenu hru indikuje zmena farby padu zo zelenej na cervenu
     */
    public void aktivuj() {
        if (this.hraBezi) {
            this.hraBezi = false;
            this.pad.zmenFarbu("red");
        } else {
            this.hraBezi = true;
            this.pad.zmenFarbu("green");
        }

        if (this.tehly.isEmpty()) {
            this.resetujHru();
        }
    }

    /**
     * ESC ukonci hru a zatvori okno
     */
    public void zrus() {
        System.exit(0);
    }

    /**
     * Ovladanie padu pomocou sipiek vlavo a vpravo
     */
    public void posunVlavo() {
        if (this.hraBezi) {
            this.pad.posunVlavo();
        }
    }

    public void posunVpravo() {
        if (this.hraBezi) {
            this.pad.posunVpravo();
        }
    }
}
