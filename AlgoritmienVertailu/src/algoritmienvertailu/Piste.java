
package algoritmienvertailu;

/**
 * Yhtä labyrintin pistettä kuvaava luokka.
 */
public class Piste implements Comparable<Piste> {
    private int x;
    private int y;
    private String color;
    private int dist;
    private Piste edellinen;
    
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
