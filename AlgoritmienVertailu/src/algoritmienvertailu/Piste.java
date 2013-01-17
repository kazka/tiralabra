
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
     * Konstruktori
     * 
     * @param x Sarake jolla piste sijaitsee
     * @param y Rivi jola piste sijaitsee
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

    public String getColor() {
        return color;
    }

    public int getDist() {
        return dist;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Piste getEdellinen(){
        return edellinen;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setEdellinen(Piste p){
        this.edellinen = p;
    }

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
