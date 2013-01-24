package algoritmienvertailu;

/**
 * Luokka joka laajentaa Piste-luokkaa A star-algoritmin tarpeisiin sopivaksi.
 */
public class AstarPiste extends Piste {
    /**
    * Arvio pisteen etäisyydestä maalipisteeseen.
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

    public int getDistLoppuun() {
        return distLoppuun;
    }

    public void setDistLoppuun(int distLoppuun) {
        this.distLoppuun = distLoppuun;
    }
    
    @Override
    public AstarPiste getEdellinen(){
        return this.edellinen;
    }

    public void setEdellinen(AstarPiste edellinen) {
        this.edellinen = edellinen;
    }
    
}
