
package algoritmienvertailu;

/**
 * Yhtä labyrintin pistettä kuvaava luokka.
 */
public class Piste implements Comparable<Piste> {
    /**
     * Sarake jolla piste sijaitsee
     */
    private int x;
    /**
     * Rivi jolla piste sijaitsee
     */
    private int y;
    /**
     * Pisteen väri, vaihtoehdot "white", "gray" tai "black".
     * Kuvaavat sitä onko piste kyseisellä hetkellä käsiteltävänä.
     * White = ei vielä käsiteltävänä
     * Gray = käsiteltävänä
     * Black = käsitelty loppuun
     */
    private String color;
    /**
     * Etäisyys aloituspisteestä, tähän mennessä pienin löydetty etäisyys
     */
    private int dist;
    /**
     * Piste jonka kautta pisteeseen tultiin
     */
    private Piste edellinen;
    
    /**
     * Dijkstran (ja osittain A starin) käyttämä konstruktori
     * 
     * @param x Sarake jolla piste sijaitsee
     * @param y Rivi jolla piste sijaitsee
     * @param color Pisteen väri, aluksi kaikilla "white"
     * @param dist Pisteen etäisyys aloituspisteestä, aluksi kaikilla paitsi
     * aloituspisteellä Integer.MAX_VALUE
     */
    public Piste(int x, int y, String color, int dist){
        this.x = x;
        this.y = y;
        this.color = color;
        this.dist = dist;
    }

    /**
     * Bellman-Fordin käyttämä konstruktori, toimii ilman värin määritelyä.
     * 
     * @param x Sarake jolla piste sijaitsee
     * @param y Rivi jolla piste sijaitsee
     * @param dist Pisteen etäisyys aloituspisteestä, aluksi kaikilla paitsi
     * aloituspisteellä Integer.MAX_VALUE
     */
    public Piste(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }    
    
    /**
     * Palauttaa pisteen värin
     *
     * @return pisteen väri
     */
    public String getColor() {
        return color;
    }

    /**
     * Palauttaa pisteen etäisyyden aloituspisteestä, lyhin tähän mennessä löydetty
     *
     * @return etäisyys aloituspisteestä
     */
    public int getDist() {
        return dist;
    }

    /**
     * Palauttaa sen sarakkeen numeron jolla piste sijaitsee
     *
     * @return sarake
     */
    public int getX() {
        return x;
    }

    /**
     * Palauttaa sen rivin numeron jolla piste sijaitsee
     *
     * @return rivi
     */
    public int getY() {
        return y;
    }
    
    /**
     * Palauttaa pistettä edeltävän pisteen lyhimmällä löydetyllä polulla
     *
     * @return edeltävä piste
     */
    public Piste getEdellinen(){
        return edellinen;
    }

    /**
     * Asettaa pisteelle värin
     *
     * @param color Asetettava väri
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Asettaa pisteelle etäisyyden aloituspisteeseen
     *
     * @param dist Etäisyys aloituspisteeseen
     */
    public void setDist(int dist) {
        this.dist = dist;
    }

    /**
     * Asettaa sarakkeen jolla piste sijaitsee
     *
     * @param x Sarakkeeksi asetettavan sarakkeen numero
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Asettaa rivin jolla piste sijaitsee
     *
     * @param y Riviksi asetettavan rivin numero
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Asettaa edeltäväksi pisteeksi pisteen josta tähän pisteeseen tultiin
     * lyhimmällä löydetyllä polulla
     *
     * @param p Piste josta tultiin
     */
    public void setEdellinen(Piste p){
        this.edellinen = p;
    }

    /**
     * Pisteiden vertailussa käytettävä metodi.
     * Piste, jolla on pienempi dist-arvo, tulee järjestyksessä ennen suurempaa dist-arvoa.
     *
     * @param o Piste johon verrataan
     * 
     * @return Vertailun tulos: joko 0 (dist-arvot yhtä suuret),
     * -1 (jos tällä pisteellä suurempi dist-arvo kuin verrattavalla)
     * tai 1 (jos verrattavalla suurempi dist-arvo kuin tällä pisteellä).
     */
    @Override
    public int compareTo(Piste o) {
        if(this.dist == o.getDist()) {
            return 0;
        } else if (this.dist > o.getDist()) {
            return -1;
        } else {
            return 1;
        }
    }

}
