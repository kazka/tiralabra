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
     * Konstruktori, asettaa labyrinttinä käytettävän 2-ulotteisen taulukon
     * jakutsuu metodia joka alustaa tarvittavat toiminnot.
     * 
     * @param laby Labyrinttinä käytettävä taulukko
     */
    public Dijkstra(int[][] laby) {
        this.laby = laby;
        alusta();
    }

    /**
     * Alustaa tarvittavat toiminnot.
     * Luo 2-ulotteisen taulukon jossa pidetään kirjaa labyrinttiin liittyvistä 
     * Piste-olioista, sekä aputietorakenteena käytettävän minimikeon.
     * Lisää piste-taulukkoon polun pisteisiin uudet pisteet alkutilassa,
     * eli värinä white ja etäisyytenä ääretön. Lisää samalla luodut pisteet kekoon.
     * Lopuksi asettaa alkupisteen dist-arvoksi 0.
     */
    public final void alusta() {
        this.pisteet = new Piste[this.laby.length][this.laby[0].length];
        this.keko = new MinimiKeko(this.laby.length * this.laby[0].length);
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
            //  keko.tulosta();
            Piste u = keko.vilkaise();
            u.setColor("gray");
            tutkiViereiset(u);
            u.setColor("black");
            keko.remove();
        }
    }

    /**
     * Metodi jolla tutkitaan kaikki pisteen viereiset pisteet, mikäli
     * kyseinen viereinen piste on tutkittavalla alueella.
     * 
     * @param u Piste jonka viereiset pisteet tutkitaan
     */
    public void tutkiViereiset(Piste u) {
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
    
    public Piste getMaalipiste(){
        return this.pisteet[this.pisteet.length - 1][this.pisteet[0].length - 1];
    }

    public Piste[][] getPisteet() {
        return pisteet;
    }

    public MinimiKeko getKeko() {
        return keko;
    }

    public int[][] getLaby() {
        return laby;
    }
    
}
