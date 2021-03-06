package algoritmienvertailu;

/**
 * Luokka joka laajentaa Piste-luokkaa A star-algoritmin tarpeisiin sopivaksi.
 */
public class AstarPiste extends Piste {
    /**
    * Arvio pisteen etäisyydestä maalipisteeseen
    */
    private int distLoppuun;
    /**
     * Piste jonka kautta pisteeseen tultiin
     */
    private AstarPiste edellinen;
    /**
     * Alkuun + loppuun etäisyyksien summa
     */
    private int alkuunPlusLoppuun;
    
    /**
     * Konstruktori
     * 
     * @param x Sarake jolla piste sijaitsee
     * @param y Rivi jolla piste sijaitsee
     * @param dist Pisteen etäisyys aloituspisteestä, aluksi kaikilla paitsi
     * aloituspisteellä Integer.MAX_VALUE
     * @param distLoppuun Arvio pisteen etäisyydestä maalipisteeseen
     */
    public AstarPiste(int x, int y, int dist, int distLoppuun) {
        super(x, y, "white", dist);
        this.distLoppuun = distLoppuun;
        paivitaAlkuunPlusLoppuun();
    }
    
    /**
     * Metodi joka päivittää etäisyyksien/arvioiden summan alkuun + loppuun.
     * Arvo jonka perusteella haetaan minimikeosta pienin.
     * 
     */
    public final void paivitaAlkuunPlusLoppuun(){
        if (super.getDist() == Integer.MAX_VALUE){
            this.alkuunPlusLoppuun = Integer.MAX_VALUE;
        } else {
            this.alkuunPlusLoppuun = this.distLoppuun + super.getDist();
        }
    }

    /**
     * Palauttaa arvion pisteen etäisyydestä maalipisteeseen
     *
     * @return arvio pisteen etäisyydestä maalipisteeseen
     */
    public int getDistLoppuun() {
        return distLoppuun;
    }
    
    /**
     * Palauttaa arvion pisteen etäisyydestä maalipisteeseen
     *
     * @return arvio pisteen etäisyydestä maalipisteeseen
     */
    public int getAlkuunPlusLoppuun() {
        return alkuunPlusLoppuun;
    }    

    /**
     * Asettaa pisteelle arvion etäisyydestä maalipisteeseen
     *
     * @param distLoppuun Arvio etäisyydestä maalipisteeseen
     */
    public void setDistLoppuun(int distLoppuun) {
        this.distLoppuun = distLoppuun;
    }
    
    /**
     * Palauttaa pistettä edeltävän Astar-pisteen lyhimmällä löydetyllä polulla
     *
     * @return edeltävä Astar-piste
     */
    @Override
    public AstarPiste getEdellinen(){
        return this.edellinen;
    }

    /**
     * Asettaa edeltäväksi pisteeksi Astar-pisteen josta tähän pisteeseen tultiin
     * lyhimmällä löydetyllä polulla
     *
     * @param edellinen Astar-piste josta tultiin
     */
    public void setEdellinen(AstarPiste edellinen) {
        this.edellinen = edellinen;
    }
    
}
