package algoritmienvertailu;

import aputietorakenteet.AstarMinimiKeko;
import java.util.ArrayList;

/**
 * A* algoritmi. Etsii labyrintistä lyhimmän polun aloituspisteestä maaliin
 * käyttämällä hyväksi arvioita etäisyyksistä.
 */
public class Astar {

    /**
     * Labyrintti kuvattuna 2-ulotteisena taulukkona
     */
    private int[][] laby;
    /**
     * Piste-olioista koostuva taulukko
     */
    private AstarPiste[][] pisteet;
    /**
     * Aputietorakenteena käytettävä minimikeko
     */
    private AstarMinimiKeko keko;
    /**
     * ArrayList jossa kaikki tähän mennessä tutkitut pisteet
     */
    private ArrayList<AstarPiste> tutkitut;

    /**
     * Konstruktori, asettaa labyrinttinä käytettävän 2-ulotteisen taulukon
     * jakutsuu metodia joka alustaa tarvittavat toiminnot.
     *
     * @param laby Labyrinttinä käytettävä taulukko
     */
    public Astar(int[][] laby) {
        this.laby = laby;
        alusta();
    }

    /**
     * Alustaa tarvittavat toiminnot. Luo 2-ulotteisen taulukon jossa pidetään
     * kirjaa labyrinttiin liittyvistä AstarPiste-olioista, sekä
     * aputietorakenteena käytettävän minimikeon ja listan jossa on jo tutkitut
     * pisteet. Lisää piste-taulukkoon polun pisteisiin uudet pisteet
     * alkutilassa, etäisyytenä alkuun ääretön ja loppuun arvioitu etäisyys,
     * joka tulee kaavalla käyttämällä sarakkeiden ja rivien indeksejä. Lisää
     * samalla luodut pisteet kekoon. Lopuksi asettaa alkupisteen dist-arvoksi
     * 0.
     */
    public final void alusta() {
        this.pisteet = new AstarPiste[this.laby.length][this.laby[0].length];
        this.keko = new AstarMinimiKeko(this.laby.length * this.laby[0].length);
        this.tutkitut = new ArrayList();
        for (int i = 0; i < this.laby.length; i++) {
            for (int j = 0; j < this.laby[0].length; j++) {
                if (this.laby[i][j] == 1) {
                    int distLoppuunArvio = Math.abs((i - (this.laby.length - 1)) + (j - (this.laby[0].length - 1)));
                    this.pisteet[i][j] = new AstarPiste(j, i, "white", Integer.MAX_VALUE, distLoppuunArvio);
                    this.keko.add(this.pisteet[i][j]);
                }
            }
        }
        this.pisteet[0][0].setDist(0);
        getMaalipiste().setDistLoppuun(0);
    }

    /**
     * Päämetodi jolla A staria kutsutaan. Jatketaan kunnes maalipiste lisätään
     * kekoon: Haetaan ensin keosta piste jolla on pienin arvio etäisyydestä
     * alkuun + loppuun, lisätään tämä piste tutkittuihin ja tutkitaan sen
     * viereiset pisteet.
     */
    public void astaraa() {
        while (!this.tutkitut.contains(getMaalipiste())) {
            //  keko.tulosta();
            AstarPiste u = this.keko.remove();
            this.tutkitut.add(u);
            tutkiViereiset(u);
        }
    }

    /**
     * Metodi jolla tutkitaan kaikki pisteen viereiset pisteet, mikäli kyseinen
     * viereinen piste on tutkittavalla alueella.
     *
     * @param u Piste jonka viereiset pisteet tutkitaan
     */
    public void tutkiViereiset(AstarPiste u) {
        if (onkoAlueella(u.getX(), u.getY() - 1)) {
            relax(u, pisteet[u.getY() - 1][u.getX()]);
        }
        if (onkoAlueella(u.getX(), u.getY() + 1)) {
            relax(u, pisteet[u.getY() + 1][u.getX()]);
        }
        if (onkoAlueella(u.getX() - 1, u.getY())) {
            relax(u, pisteet[u.getY()][u.getX() - 1]);
        }
        if (onkoAlueella(u.getX() + 1, u.getY())) {
            relax(u, pisteet[u.getY()][u.getX() + 1]);
        }
    }

    /**
     * Relax-metodi joka tarkistaa onko nyt tutkittavan viereisen pisteen
     * nykyinen dist-arvo korvattavissa pienemmällä arvolla.
     *
     * @param u Piste josta tultiin
     * @param v Pisteen u vieressä oleva piste johon etäisyyttä tutkitaan
     */
    public void relax(AstarPiste u, AstarPiste v) {
        if (v.getDist() >= u.getDist() + 1) {
            v.setDist(u.getDist() + 1);
            this.keko.decKey(v);
            v.setEdellinen(u);
        }
    }

    /**
     * Tarkistaa onko tietyssä sijainnissa oleva piste labyrintin alueella ja
     * meneekö pisteen kautta polku.
     *
     * @param x Sarake jolla piste sijaitsee
     * @param y Rivi jolla piste sijaitsee
     */
    public boolean onkoAlueella(int x, int y) {
        if (x < 0 || x >= pisteet[0].length || y < 0 || y >= pisteet.length || laby[y][x] == 0) {
            return false;
        }
        return true;
    }

    /**
     * Tulostetaan labyrintti ja polun pisteisiin liittyvät dist-arvot.
     */
    public void tulosta() {
        for (int i = 0; i < this.laby.length; i++) {
            for (int j = 0; j < this.laby[0].length; j++) {
                if (laby[i][j] == 1) {
                    if (this.pisteet[i][j].getDist() == Integer.MAX_VALUE) {
                        System.out.print("x ");
                    } else {
                        System.out.print(this.pisteet[i][j].getDist() + " ");
                    }
                } else {
                    System.out.print("# ");
                }
            }
            System.out.println("");
        }

        tulostaPolku();
    }

    /**
     * Tulostetaan lyhin polku jota pitkin päästiin alkupisteestä maaliin.
     */
    public void tulostaPolku() {
        AstarPiste maali = getMaalipiste();

        if (maali.getDist() == Integer.MAX_VALUE) {
            System.out.println("maalia ei saavutettu");
            return;
        }

        System.out.println("Lyhin polku maalista lähtien:");

        System.out.print(maali.getX() + "," + maali.getY() + "...");
        AstarPiste edellinen = maali.getEdellinen();
        while (edellinen.getEdellinen() != null) {
            System.out.print(edellinen.getX() + "," + edellinen.getY() + "...");
            edellinen = edellinen.getEdellinen();
        }

        System.out.println(edellinen.getX() + "," + edellinen.getY() + " pituus: " + maali.getDist());
    }

    public AstarPiste getMaalipiste() {
        return this.pisteet[this.pisteet.length - 1][this.pisteet[0].length - 1];
    }

    public AstarPiste[][] getPisteet() {
        return pisteet;
    }

    public AstarMinimiKeko getKeko() {
        return keko;
    }

    public int[][] getLaby() {
        return laby;
    }

    public ArrayList<AstarPiste> getTutkitut() {
        return tutkitut;
    }
}
