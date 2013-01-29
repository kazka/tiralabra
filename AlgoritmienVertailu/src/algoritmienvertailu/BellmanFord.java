package algoritmienvertailu;

import aputietorakenteet.MinimiKeko;
import java.util.ArrayList;

/**
* Bellman-Fordin algoritmi.
* Käy läpi kaikki polut järjestyksessä useita kertoja kunnes tulos on valmis.
*/
public class BellmanFord {
    /**
     * Labyrintti kuvattuna 2-ulotteisena taulukkona
     */
    private int[][] laby;
    /**
     * Piste-olioista koostuva taulukko
     */
    private Piste[][] pisteet;
    /**
     * Lista jossa on kaikki labyrintin pisteiden väliset kaaret
     */
    private ArrayList<Kaari> kaaret;
    /**
     * Labyrintin polkujen pisteiden määrä yhteensä
     */
    private int solmumaara = 0;

    /**
     * Konstruktori, asettaa labyrinttinä käytettävän 2-ulotteisen taulukon,
     * luo 2-ulotteisen taulukon jossa pidetään kirjaa labyrinttiin liittyvistä 
     * Piste-olioista, ja kutsuu metodia joka alustaa tarvittavat toiminnot.
     * 
     * @param laby Labyrinttinä käytettävä taulukko
     */
    public BellmanFord(int[][] laby) {
        this.laby = laby;
        this.pisteet = new Piste[this.laby.length][this.laby[0].length];        
        alusta();
    }

    /**
     * Alustaa tarvittavat toiminnot.
     * Lisää piste-taulukkoon polun pisteisiin uudet pisteet etäisyytenä ääretön,
     * ja kasvattaa solmumäärän laskuria jokaisen löytyneen pisteen kohdalla.
     * Lopuksi asettaa alkupisteen dist-arvoksi 0, ja kutsuu metodia joka hakee
     * pisteiden väliset kaaret.
     */
    public final void alusta() {
        for (int i = 0; i < this.laby.length; i++) {
            for (int j = 0; j < this.laby[0].length; j++) {
                if (this.laby[i][j] == 1) {
                    this.pisteet[i][j] = new Piste(j, i, Integer.MAX_VALUE);
                    this.solmumaara++;
                }
            }
        }
        this.pisteet[0][0].setDist(0);
        haeKaaret();
    }
    
    /**
     * Kutsuu jokaiselle löytyneelle pisteelle metodia joka hakee kyseisestä pisteestä
     * lähtevät kaaret.
     */
    public void haeKaaret(){
        this.kaaret = new ArrayList<Kaari>();
        for (int i = 0; i < this.laby.length; i++) {
            for (int j = 0; j < this.laby[0].length; j++) {
                if (this.laby[i][j] == 1) {
                    etsiLahtevatKaaret(this.pisteet[i][j]);
                }
            }
        }  
    }
    
    /**
     * Kutsuu kaikille pisteen u viereisille pisteille tarkistaJaLisaaKaari-
     * metodia.
     * 
     * @param u Piste jonka viereisille pisteille metodia kutsutaan
     */
    public void etsiLahtevatKaaret(Piste u){
        tarkistaJaLisaaKaari(u, u.getX(), u.getY() - 1);
        tarkistaJaLisaaKaari(u, u.getX(), u.getY() + 1);
        tarkistaJaLisaaKaari(u, u.getX() - 1, u.getY());
        tarkistaJaLisaaKaari(u, u.getX() + 1, u.getY());
    }
    
    /**
     * Tarkistaa onko pisteen u viereinen piste tutkittavalla alueella ja lisää
     * kaarten listaan kaaren pisteestä u tähän viereiseen pisteeseen, jos on
     * 
     * @param u Piste josta lähtevät kaaret etsitään.
     * @param x Pisteen u nyt tutkittavan viereisen pisteen x-koordinaatti
     * @param y Pisteen u nyt tutkittavan viereisen pisteen y-koordinaatti
     */
    public void tarkistaJaLisaaKaari(Piste u, int x, int y) {
        if (onkoAlueella(x, y)) {
            this.kaaret.add(new Kaari(u, this.pisteet[y][x]));
        }
    }

    /**
     * Päämetodi jolla bellman-fordia kutsutaan.
     * Käy läpi kaikki kaaret solmujen määrän verran kertoja ja kutsuu kaarille
     * relax-metodia. Tämän jälkeen käy vielä läpi kaikki kaaret ja ilmoittaa jos
     * löytyi negatiivisen painoinen sykli.
     */
    public void bellman() {
        for (int i = 1; i <= this.solmumaara-1; i++){
            for (Kaari uv : this.kaaret) {
            //    System.out.println(uv.getLahde().getX() + " " + uv.getLahde().getY() + " " + uv.getKohde().getX() + " " + uv.getKohde().getY());
                relax(uv.getLahde(), uv.getKohde());
            }
        }   
        for (Kaari uv : this.kaaret) {
            if (uv.getLahde().getDist() + 1 < uv.getKohde().getDist()){
                System.out.println("löytyi negatiivinen sykli");
            }
        }            
    }

    /**
     * Relax-metodi joka tarkistaa onko nyt tutkittavan 
     * viereisen pisteen nykyinen dist-arvo korvattavissa pienemmällä arvolla, eli
     * löydettiinkö siihen tämän pisteen kautta lyhyempi reitti, kuin edellinen 
     * lyhin tunnettu reitti.
     * 
     * @param u Piste josta tultiin
     * @param v Pisteen u vieressä oleva piste johon etäisyyttä tutkitaan
     */
    public void relax(Piste u, Piste v) {
        int udist = u.getDist() + 1;
        if (u.getDist() == Integer.MAX_VALUE){
            udist = u.getDist();
        }
        if (v.getDist() > udist) {
            v.setDist(udist);
            v.setEdellinen(u);
        }
    }

    /**
     * Tarkistaa onko tietyssä sijainnissa oleva piste labyrintin alueella ja
     * meneekö pisteen kautta polku..
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
    public void tulostaPolku(){
        Piste maali = getMaalipiste();
        
        if (maali.getDist() == Integer.MAX_VALUE){
            System.out.println("maalia ei saavutettu");
            return;
        }
        
        System.out.println("Lyhin polku maalista lähtien:");
        
        System.out.print(maali.getX() + "," + maali.getY() + "...");
        Piste edellinen = maali.getEdellinen();
        while (edellinen.getEdellinen() != null) {
            System.out.print(edellinen.getX() + "," + edellinen.getY() + "...");
            edellinen = edellinen.getEdellinen();
        }
        
        System.out.println(edellinen.getX() + "," + edellinen.getY() + " pituus: " + maali.getDist());
    }
    
    /**
     * Palauttaa maalipisteen, eli labyrintin oikeassa alakulmassa olevan pisteen
     *
     * @return maalipiste
     */
    public Piste getMaalipiste() {
        return this.pisteet[this.pisteet.length - 1][this.pisteet[0].length - 1];
    }

    /**
     * Palauttaa 2-ulotteisen taulukon joka sisältää labyrintin pisteet
     *
     * @return taulukko jossa pisteet
     */
    public Piste[][] getPisteet() {
        return pisteet;
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
     * Palauttaa listan labyrinttiin liittyvistä kaarista
     *
     * @return lista kaarista
     */
    public ArrayList<Kaari> getKaaret() {
        return kaaret;
    }

    /**
     * Palauttaa labyrintin solmujen määrän eli polkujen pisteiden määrän yhteensä
     *
     * @return solmujen määrä
     */
    public int getSolmumaara() {
        return solmumaara;
    }
    
}
