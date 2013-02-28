
package algoritmienvertailu;

import java.util.Random;
import java.util.Scanner;

/**
 * Algoritmien vertailuun liittyvä pääluokka josta käynnistetään ohjelman toiminta.
 * Algoritmien tarkoituksena on löytää lyhin reitti labyrintistä ulos,
 * eli vasemman yläkulman ruudusta oikean alakulman ruutuun.
 */
public class AlgoritmienVertailu {
    /**
     * Lukija
     */
    private Scanner lukija;
    
    /**
     * TEstilabyrintti, jonka lyhimmällä polulla on vaikeakulkuista maastoa (vuori).
     */
    static int[][] labyVuori = new int[][]
        {{1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 2},
        {1, 1, 1, 2, 2, 3, 3, 3, 4},
        {2, 2, 2, 2, 3, 4, 4, 4, 4},
        {2, 2, 3, 3, 3, 4, 4, 5, 4},
        {2, 2, 2, 2, 3, 4, 4, 4, 4},
        {1, 1, 1, 2, 2, 3, 3, 3, 4},
        {1, 1, 1, 1, 1, 1, 1, 1, 2},
        {1, 1, 1, 1, 1, 1, 1, 1, 1}};    
    
    /**
     * Konstruktori
     */
    public AlgoritmienVertailu() {
        
    }
    
    /**
     * Aloittaa algoritmien toiminnan.
     * Kutsuu metodeita joilla selvitetään mitä algoritmia ja minkä kokoista
     * syötettä halutaan käyttää.
     */
    public void start(){
        System.out.println("Tervetuloa testaamaan algoritmeja :>");
        this.lukija = new Scanner(System.in);
        int[][] laby = kysyLabyrintinKoko();
        String valittu = kysyKaytettavaAlgoritmi();
        while (!valittu.equals("exit")){
            haeValittuAlgoritmi(valittu, laby);  
            valittu = kysyKaytettavaAlgoritmi();
        }
    }
    
    /**
     * Kysyy mitä algoritmia halutaan testata.
     * 
     * @return valittu algoritmi stringinä
     */
    public String kysyKaytettavaAlgoritmi(){
        String valittu = "";
        while(!(valittu.equals("dj") || valittu.equals("ast") || valittu.equals("bf") || valittu.equals("exit"))){
            System.out.println("Mitä algoritmia testataan?");
            System.out.println("Vaihtoehdot: dj (Dijkstra), ast (A*), bf (Bellman-Ford), exit (lopetus)");
            valittu = lukija.nextLine();
        }
        return valittu;
    }
    
    /**
     * Kysyy minkä kokoista syötettä halutaan käyttää.
     * 
     * @return testilabyrintti joka vastaa valittua kokoa
     */
    public int[][] kysyLabyrintinKoko(){
        String koko = "";
        while(!(koko.equals("pieni") || koko.equals("keski") || koko.equals("iso") || koko.equals("tyhja") || koko.equals("vuori"))){
            System.out.println("Minkä kokoisella labyrintilla testataan?");
            System.out.println("Vaihtoehdot: pieni (20x20 ruutua), keski (100x100 ruutua), iso (400x400 ruutua), tyhja (200x200 ruutua, seinätön)");
            koko = lukija.nextLine();
        }
        return generoiLabyrintti(koko);
    }
    
    /**
     * Generoi halutun kokoisen labyrintin
     * 
     * @param koko Labyrintin haluttu koko
     * 
     * @return testilabyrintti joka vastaa valittua kokoa
     */
    public int[][] generoiLabyrintti(String koko){
        int[][] laby;
        if (koko.equals("tyhja")){
            return generoiTyhjaLaby();
        } else if (koko.equals("vuori")){
            return labyVuori;
        } else if (koko.equals("pieni")){
            laby = new int[20][20];
        } else if (koko.equals("keski")){
            laby = new int[100][100];
        } else {
            laby = new int[400][400];
        }
        generoiSeinat(laby);
        return laby;
    }
    
    public int[][] generoiTyhjaLaby(){
        int[][] laby = new int[200][200];
        for (int i = 0; i < laby.length; i++) {
            for (int j = 0; j < laby[0].length; j++) {
                laby[i][j] = 1;
            }
        }
        return laby;
    }
    
    /**
     * Generoi labyrinttiin esteet. Ruudussa on este todennäköisyydellä 0.3.
     * 
     * @param laby Valitun kokoinen labyrintti jonne esteet luodaan
     */
    public void generoiSeinat(int[][] laby){
        Random rnd = new Random();
        for (int i = 0; i < laby.length; i++) {
            for (int j = 0; j < laby[0].length; j++) {
                if(Math.random() < 0.7) {
                    laby[i][j] = rnd.nextInt(9)+1;
                }
            }
        }
        laby[0][0] = 1;
        laby[laby.length-1][laby[0].length-1] = 1;
    }
    
    /**
     * Hakee valitun algoritmin ja kutsuu siihen algoritmiin liittyviä metodeita.
     * 
     * @param algo Valittu algoritmi stringinä, joko "dj", "ast" tai "bf"
     * @param laby Valitun kokoinen testilabyrintti
     */
    public void haeValittuAlgoritmi(String algo, int[][] laby){
        if (algo.equals("dj")){
            aloitaJaTulostaDijkstra(laby);
        } else if (algo.equals("ast")){
            aloitaJaTulostaAstar(laby);
        } else {
            aloitaJaTulostaBellman(laby);
        }
    }

    /**
     * Aloittaa ja tulostaa Dijkstran
     * 
     * @param laby Valitun kokoinen testilabyrintti
     */
    public void aloitaJaTulostaDijkstra(int[][] laby) {
        long aloitusaika = System.currentTimeMillis();
        Dijkstra dj = new Dijkstra(laby);
        dj.dijkstraa();
        long lopetusaika = System.currentTimeMillis();
        System.out.println("~Dijkstran algoritmi~");
        dj.tulosta();
        System.out.println("Aikaa kului (ms): " + (lopetusaika - aloitusaika) + "\n");
    }
    
    /**
     * Aloittaa ja tulostaa A starin
     * 
     * @param laby Valitun kokoinen testilabyrintti
     */
    public void aloitaJaTulostaAstar(int[][] laby) {
        long aloitusaika = System.currentTimeMillis();
        Astar ast = new Astar(laby);
        ast.astaraa();
        long lopetusaika = System.currentTimeMillis();
        System.out.println("~A* algoritmi~");
        ast.tulosta();
        System.out.println("Aikaa kului (ms): " + (lopetusaika - aloitusaika) + "\n");
    }   
    
    /**
     * Aloittaa ja tulostaa Bellman-Fordin
     * 
     * @param laby Valitun kokoinen testilabyrintti
     */
    public void aloitaJaTulostaBellman(int[][] laby) {
        long aloitusaika = System.currentTimeMillis();
        BellmanFord bf = new BellmanFord(laby);
        bf.bellman();
        long lopetusaika = System.currentTimeMillis();
        System.out.println("~Bellman-Fordin algoritmi~");
        bf.tulosta();
        System.out.println("Aikaa kului (ms): " + (lopetusaika - aloitusaika) + "\n");
    } 
   
}
