package algoritmienvertailu;

import aputietorakenteet.MinimiKeko;

public class Dijkstra {

    private int[][] laby;
    private Piste[][] pisteet;
    private MinimiKeko keko;

    public Dijkstra(int[][] laby) {
        this.laby = laby;
        alusta();
    }

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

    public void relax(Piste u, Piste v) {
        if (v.getDist() > u.getDist() + 1) {
            v.setDist(u.getDist() + 1);
            this.keko.decKey(v);
            v.setEdellinen(u);
        }
    }

    public boolean onkoAlueella(int x, int y) {
        if (x < 0 || x >= pisteet[0].length || y < 0 || y >= pisteet.length || laby[y][x] == 0 || pisteet[y][x].getColor().equals("black")) {
            return false;
        }
        return true;
    }

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
    
    public void tulostaPolku(){
        Piste maali = this.pisteet[this.pisteet.length - 1][this.pisteet[0].length - 1];
        
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
}
