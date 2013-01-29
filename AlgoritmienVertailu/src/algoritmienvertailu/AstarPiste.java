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
     * Konstruktori
     * 
     * @param x Sarake jolla piste sijaitsee
     * @param y Rivi jolla piste sijaitsee
     * @param color Pisteen väri, aluksi kaikilla "white"
     * @param dist Pisteen etäisyys aloituspisteestä, aluksi kaikilla paitsi
     * aloituspisteellä Integer.MAX_VALUE
     * @param distLoppuun Arvio pisteen etäisyydestä maalipisteeseen
     */
    public AstarPiste(int x, int y, String color, int dist, int distLoppuun) {
        super(x, y, color, dist);
        this.distLoppuun = distLoppuun;
    }
    
    /**
     * Metodi joka palauttaa etäisyyksien/arvioiden summan alkuun + loppuun.
     * Arvo jonka perusteella haetaan minimikeosta pienin.
     * 
     * @return Etäisyyksien summa alkuun + loppuun
     */
    public int getAlkuunPlusLoppuun(){
        if (super.getDist() == Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        } else {
            return this.distLoppuun + super.getDist();
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
