
package algoritmienvertailu;

/**
 * Algoritmien vertailuun liittyvä pääluokka.
 * Algoritmien tarkoituksena on löytää lyhin reitti labyrintistä ulos,
 * eli vasemman yläkulman ruudusta oikean alakulman ruutuun.
 */
public class AlgoritmienVertailu {
    /**
     * Testilabyrintti 2-ulotteisena taulukkona
     */
    static int[][] labyrintti = new int[][] 
       {{1, 1, 0, 0, 0},
        {0, 1, 0, 0, 0},
        {1, 1, 1, 0, 0},
        {1, 0, 1, 1, 1},   
        {1, 1, 1, 0, 1}}; 
    
    /**
     * Konstruktori
     */
    public AlgoritmienVertailu() {
        
    }
    
    /**
     * Aloittaa algoritmien toiminnan
     */
    public void start(){
        aloitaJaTulostaDijkstra();
        aloitaJaTulostaAstar();
        aloitaJaTulostaBellman();        
    }

    /**
     * Aloittaa ja tulostaa Dijkstran
     */
    public void aloitaJaTulostaDijkstra() {
        Dijkstra dj = new Dijkstra(labyrintti);
        dj.dijkstraa();
        dj.tulosta();
        System.out.println("");
    }
    
    /**
     * Aloittaa ja tulostaa A starin
     */
    public void aloitaJaTulostaAstar() {
        Astar ast = new Astar(labyrintti);
        ast.astaraa();
        ast.tulosta();
        System.out.println("");
    }   
    
    /**
     * Aloittaa ja tulostaa Bellman-Fordin
     */
    public void aloitaJaTulostaBellman() {
        BellmanFord bf = new BellmanFord(labyrintti);
        bf.bellman();
        bf.tulosta();
        System.out.println("");
    } 
   
}
