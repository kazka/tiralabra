package algoritmienvertailu;

import aputietorakenteet.AstarMinimiKeko;
import aputietorakenteet.LinkitettyLista;
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
     * Linkitetty lista jossa kaikki tähän mennessä tutkitut pisteet
     */
    private LinkitettyLista tutkitut;

    /**
     * Konstruktori, asettaa labyrinttinä käytettävän 2-ulotteisen taulukon.
     * Luo 2-ulotteisen taulukon jossa pidetään kirjaa labyrinttiin liittyvistä 
     * AstarPiste-olioista, sekä aputietorakenteena käytettävän minimikeon ja 
     * listan jossa on jo tutkitut pisteet.
     * Kutsuu metodia joka alustaa tarvittavat toiminnot.
     *
     * @param laby Labyrinttinä käytettävä taulukko
     */
    public Astar(int[][] laby) {
        this.laby = laby;
        this.pisteet = new AstarPiste[this.laby.length][this.laby[0].length];
        this.keko = new AstarMinimiKeko(this.laby.length * this.laby[0].length);        
        this.tutkitut = new LinkitettyLista();       
        alusta();
    }

    /**
     * Alustaa tarvittavat toiminnot. Lisää piste-taulukkoon polun pisteisiin uudet pisteet
     * alkutilassa, etäisyytenä alkuun ääretön ja loppuun arvioitu etäisyys,
     * joka tulee kaavalla käyttämällä sarakkeiden ja rivien indeksejä. Lisää
     * samalla luodut pisteet kekoon.
     */
    public final void alusta() {
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
    }

    /**
     * Päämetodi jolla A staria kutsutaan. Jatketaan kunnes maalipiste lisätään
     * kekoon: Haetaan ensin keosta piste jolla on pienin arvio etäisyydestä
     * alkuun + loppuun, lisätään tämä piste tutkittuihin ja tutkitaan sen
     * viereiset pisteet.
     */
    public void astaraa() {
        while (!this.tutkitut.contains(getMaalipiste())) {
            AstarPiste u = this.keko.remove();
            this.tutkitut.add(u);
            tutkiViereiset(u);
        }
    }

    /**
     * Metodi jolla kutsutaan jokaiselle pisteen u viereiselle pisteelle
     * tarkistaJaRelaxoi-metodia.
     *
     * @param u Piste jonka viereiset pisteet tutkitaan
     */
    public void tutkiViereiset(AstarPiste u) {
        tarkistaJaRelaxoi(u, u.getX(), u.getY() - 1);
        tarkistaJaRelaxoi(u, u.getX(), u.getY() + 1);
        tarkistaJaRelaxoi(u, u.getX() - 1, u.getY());
        tarkistaJaRelaxoi(u, u.getX() + 1, u.getY());
    }
    
    /**
     * Metodi jolla tarkistetaan onko kyseinen pisteen u viereinen piste tutkittavalla
     * alueella ja kutsutaan relax-metodia jos on.
     *
     * @param u Piste jonka viereistä pistettä tutkitaan
     * @param x Sarake jolla kyseinen pisteen u viereinen piste sijaitsee
     * @param y Rivi jolla kyseinen pisteen u viereinen piste sijaitsee
     */ 
    public void tarkistaJaRelaxoi(AstarPiste u, int x, int y){
        if (onkoAlueella(x, y)) {
            relax(u, pisteet[y][x]);
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
        int udist = u.getDist() + 1;
        if (u.getDist() == Integer.MAX_VALUE){
            udist = u.getDist();
        }        
        if (v.getDist() > udist) {
            v.setDist(udist);
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
     * Liian pitkä metodi, ei tietoa miten tätä saisi vielä lyhennettyä.
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
    public void tulostaPolku(){     
        if (!saavutettiinkoMaali())return;
        
        System.out.println("Lyhin polku maalista lähtien:");
        Piste edellinen = getMaalipiste();
        while (edellinen.getEdellinen() != null) {
            System.out.print(edellinen.getX() + "," + edellinen.getY() + "...");
            edellinen = edellinen.getEdellinen();
        }
        System.out.println(edellinen.getX() + "," + edellinen.getY() + " \nPituus: " + getMaalipiste().getDist());
    }
    
    /**
     * Lopussa kutsuttava metodi joka tarkistaa saavutettiinko maalipiste.
     * 
     * @return false jos maalia ei saavutettu, muuten true
     */
    public boolean saavutettiinkoMaali() {
        if (getMaalipiste().getDist() == Integer.MAX_VALUE) {
            System.out.println("maalia ei saavutettu");
            return false;
        }
        return true;
    }

    /**
     * Palauttaa maalipisteen, eli labyrintin oikeassa alakulmassa olevan pisteen
     *
     * @return maalipiste
     */
    public AstarPiste getMaalipiste() {
        return this.pisteet[this.pisteet.length - 1][this.pisteet[0].length - 1];
    }

    /**
     * Palauttaa 2-ulotteisen taulukon joka sisältää labyrintin pisteet
     *
     * @return taulukko jossa pisteet
     */
    public AstarPiste[][] getPisteet() {
        return pisteet;
    }

    /**
     * Palauttaa A stariin liittyvän minimikeon
     *
     * @return keko
     */
    public AstarMinimiKeko getKeko() {
        return keko;
    }

    /**
     * Palauttaa labyrinttiin liittyvän 2-ulotteisen int-taulukon jossa listattu polut
     * ykkösillä ja poluttomat kohdat nollilla
     *
     * @return labyrintti int-taulukkona
     */
    public int[][] getLaby() {
        return laby;
    }

    /**
     * Palauttaa listan joka sisältää jo tutkitut pisteet
     *
     * @return tutkittujen pisteiden lista
     */
    public LinkitettyLista getTutkitut() {
        return tutkitut;
    }
}
