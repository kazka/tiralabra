
package algoritmienvertailu;

import java.util.ArrayList;
import java.util.PriorityQueue;


public class Dijkstra {
    private int[][] laby;
    private Piste[][] pisteet;
    private PriorityQueue<Piste> keko;
    private ArrayList<Piste> tutkitut;
 //   private String[][] color;
    
    public Dijkstra(int[][] laby){
        this.laby = laby;
        alusta();
        dijkstraa();
    }
    
    public final void alusta(){
        this.pisteet = new Piste[this.laby.length][this.laby[0].length];
        this.tutkitut = new ArrayList();
  //      this.color = new String[laby.length][laby[0].length];
        for (int i = 0; i < this.laby.length; i++){
            for (int j = 0; j > this.laby[0].length; j++){
                this.pisteet[i][j] = new Piste(j, i, "white", Integer.MAX_VALUE);
 //               color[i][j] = "white";
                if (this.laby[i][j] == 1){
                    keko.add(this.pisteet[i][j]);
                }
            }
        }
        this.pisteet[0][0].setDist(0);
    }

    public void dijkstraa() {
        while (!this.keko.isEmpty()){
            Piste u = keko.remove();
            u.setColor("gray");
            tutkiViereiset(u);
            u.setColor("black");
            tutkitut.add(u);
        }
    }

    public void tutkiViereiset(Piste u) {
        relax(u, pisteet[u.getX()-1][u.getY()]);
        relax(u, pisteet[u.getX()+1][u.getY()]);
        relax(u, pisteet[u.getX()][u.getY()-1]);
        relax(u, pisteet[u.getX()][u.getY()+1]);
    }
    
    public void relax(Piste u, Piste v){
          if (v.getDist() > u.getDist() + 1) {
               v.setDist(u.getDist() + 1);
               v.setEdellinen(u);
               tutkitut.add(v);
          }
    }
}
