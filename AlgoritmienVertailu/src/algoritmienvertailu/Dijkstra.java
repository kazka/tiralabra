
package algoritmienvertailu;

import aputietorakenteet.MinimiKeko;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Dijkstra {
    private int[][] laby;
    private Piste[][] pisteet;
    private MinimiKeko keko;
    private ArrayList<Piste> tutkitut;
    
    public Dijkstra(int[][] laby){
        this.laby = laby;
        alusta();
    }
    
    public final void alusta(){
        this.pisteet = new Piste[this.laby.length][this.laby[0].length];
        this.tutkitut = new ArrayList();
        this.keko = new MinimiKeko(this.laby.length * this.laby[0].length);
        for (int i = 0; i < this.laby.length; i++){
            for (int j = 0; j < this.laby[0].length; j++){             
                if (this.laby[i][j] == 1){
                    this.pisteet[i][j] = new Piste(j, i, "white", Integer.MAX_VALUE);
                    keko.add(this.pisteet[i][j]);
                }
            }
        }
        this.pisteet[0][0].setDist(0);
    }

    public void dijkstraa() {
        while (!this.keko.isEmpty()){
            keko.tulosta();
            Piste u = keko.vilkaise();
            System.out.println(u.getDist());
            u.setColor("gray");
            tutkiViereiset(u);
            u.setColor("black");
            keko.remove();
            tutkitut.add(u);
        }
    }

    public void tutkiViereiset(Piste u) {
        if (onkoAlueella(u.getX(), u.getY()-1)){
            relax(u, pisteet[u.getY()-1][u.getX()]);
        }
        if (onkoAlueella(u.getX(), u.getY()+1)){
            relax(u, pisteet[u.getY()+1][u.getX()]);
        }
        if (onkoAlueella(u.getX()-1, u.getY())){
            relax(u, pisteet[u.getY()][u.getX()-1]);
        }
        if (onkoAlueella(u.getX()+1, u.getY())){
            relax(u, pisteet[u.getY()][u.getX()+1]);
        }
    }
    
    public void relax(Piste u, Piste v){
        if (v.getDist() > u.getDist() + 1) {
            v.setDist(u.getDist() + 1);
            v.setEdellinen(u);
            //tutkitut.add(v);
        }
    }
    
    public boolean onkoAlueella(int x, int y){
        if(x < 0 || x >= pisteet[0].length || y < 0 || y >= pisteet.length || laby[y][x] == 0 || pisteet[y][x].getColor().equals("black")){
            return false;
        }
        return true;
    }
    
    public void tulosta(){
        for (int i = 0; i < this.laby.length; i++){
            for (int j = 0; j < this.laby[0].length; j++){             
                if (laby[i][j] == 1){
                    if (this.pisteet[i][j].getDist() == Integer.MAX_VALUE){
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
    }    
}
