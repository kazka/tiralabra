
package algoritmienvertailu;

/**
 * Algoritmien vertailuun liittyvä pääluokka.
 * Algoritmien tarkoituksena on löytää lyhin reitti labyrintistä ulos,
 * eli vasemman yläkulman ruudusta oikean alakulman ruutuun.
 */
public class AlgoritmienVertailu {


    /**
     * Konstruktori
     */
    public AlgoritmienVertailu() {
        
    }
    
    /**
     * Aloittaa algoritmien toiminnan
     */
    public void start(){
        Dijkstra dj = new Dijkstra(labyrintti);
        dj.dijkstraa();
        dj.tulosta();
        System.out.println("");
        Astar ast = new Astar(labyrintti);
        ast.astaraa();
        ast.tulosta();        
    }
    
    static int[][] labyrintti = new int[][] 
       {{1, 1, 0, 0, 0},
        {0, 1, 0, 0, 0},
        {1, 1, 1, 0, 0},
        {1, 0, 1, 1, 1},   
        {1, 1, 1, 0, 1}}; 
}
