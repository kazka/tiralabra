package algoritmienvertailu;

import aputietorakenteet.MinimiKeko;

/**
* Dijkstran algoritmi.
* Etsii labyrintistä lyhimmän polun aloituspisteestä maaliin ja samalla lyhimmät
* etäisyydet aloituspisteestä kaikkiin pisteisiin.
*/
public class Dijkstra {
    /**
     * Labyrintti kuvattuna 2-ulotteisena taulukkona
     */
    private int[][] laby;
    /**
     * Piste-olioista koostuva taulukko
     */
    private Piste[][] pisteet;
    /**
     * Aputietorakenteena käytettävä minimikeko
     */
    private MinimiKeko keko;

    /**
     * Konstruktori, asettaa labyrinttinä käytettävän 2-ulotteisen taulukon.
     * Luo 2-ulotteisen taulukon jossa pidetään kirjaa labyrinttiin liittyvistä 
     * Piste-olioista, sekä aputietorakenteena käytettävän minimikeon.
     * Kutsuu metodia joka alustaa tarvittavat toiminnot.
     * 
     * @param laby Labyrinttinä käytettävä taulukko
     */
    public Dijkstra(int[][] laby) {
        this.laby = laby;
        this.pisteet = new Piste[this.laby.length][this.laby[0].length];
        this.keko = new MinimiKeko(this.laby.length * this.laby[0].length);        
        alusta();
    }

    /**
     * Alustaa tarvittavat toiminnot.
     * Lisää piste-taulukkoon polun pisteisiin uudet pisteet alkutilassa,
     * eli värinä white ja etäisyytenä ääretön. Lisää samalla luodut pisteet kekoon.
     * Lopuksi asettaa alkupisteen dist-arvoksi 0.
     */
    public final void alusta() {
        for (int i = 0; i < this.laby.length; i++) {
            for (int j = 0; j < this.laby[0].length; j++) {
                if (this.laby[i][j] == 1) {
                    this.pisteet[i][j] = new Piste(j, i, "white", Integer.MAX_VALUE);
                    keko.add(this.pisteet[i][j]);
                }
            }
        }
        this.pisteet[0][0].setDist(0);
    }

    /**
     * Päämetodi jolla dijkstraa kutsutaan.
     * Käy läpi vuorollaan kaikki pisteet keosta, asettaa väriksi gray,
     * kutsuu metodia joka tutkii viereiset pisteet, asettaa väriksi black
     * ja lopuksi poistaa tutkitun pisteen keosta.
     */
    public void dijkstraa() {
        while (!this.keko.isEmpty()) {
            Piste u = keko.vilkaise();
            u.setColor("gray");
            tutkiViereiset(u);
            u.setColor("black");
            keko.remove();
        }
    }

    /**
     * Metodi jolla kutsutaan jokaiselle pisteen u viereiselle pisteelle
     * tarkistaJaRelaxoi-metodia.
     *
     * @param u Piste jonka viereiset pisteet tutkitaan
     */
    public void tutkiViereiset(Piste u) {
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
    public void tarkistaJaRelaxoi(Piste u, int x, int y){
        if (onkoAlueella(x, y)) {
            relax(u, pisteet[y][x]);
        }
    }

    /**
     * Dijkstraan liittyvä relax-metodi joka tarkistaa onko nyt tutkittavan 
     * viereisen pisteen nykyinen dist-arvo korvattavissa pienemmällä arvolla, eli
     * löydettiinkö siihen tämän pisteen kautta lyhyempi reitti, kuin edellinen 
     * lyhin tunnettu reitti.
     * 
     * @param u Piste josta tultiin
     * @param v Pisteen u vieressä oleva piste johon etäisyyttä tutkitaan
     */
    public void relax(Piste u, Piste v) {
        if (v.getDist() > u.getDist() + 1) {
            v.setDist(u.getDist() + 1);
            this.keko.decKey(v);
            v.setEdellinen(u);
        }
    }

    /**
     * Tarkistaa onko tietyssä sijainnissa oleva piste labyrintin alueella,
     * meneekö pisteen kautta polku ja onko piste vielä tutkimatta eli väri ei ole black.
     * 
     * @param x Sarake jolla piste sijaitsee
     * @param y Rivi jolla piste sijaitsee
     */
    public boolean onkoAlueella(int x, int y) {
        if (x < 0 || x >= pisteet[0].length || y < 0 || y >= pisteet.length || laby[y][x] == 0 || pisteet[y][x].getColor().equals("black")) {
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
        if (!saavutettiinkoMaali())return;
        
        System.out.println("Lyhin polku maalista lähtien:");
        Piste edellinen = getMaalipiste();
        while (edellinen.getEdellinen() != null) {
            System.out.print(edellinen.getX() + "," + edellinen.getY() + "...");
            edellinen = edellinen.getEdellinen();
        }
        System.out.println(edellinen.getX() + "," + edellinen.getY() + " pituus: " + getMaalipiste().getDist());
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
     * Palauttaa Dijkstraan liittyvän minimikeon
     *
     * @return keko
     */
    public MinimiKeko getKeko() {
        return keko;
    }

    
}
