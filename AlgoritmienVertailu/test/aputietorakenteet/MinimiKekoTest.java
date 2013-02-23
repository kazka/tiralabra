
package aputietorakenteet;

import algoritmienvertailu.Dijkstra;
import algoritmienvertailu.Piste;
import org.junit.*;
import static org.junit.Assert.*;


public class MinimiKekoTest {
    MinimiKeko keko;
    
    static int[][] laby = new int[][]
        {{1, 1, 0, 0, 0},
        {0, 1, 0, 0, 0},
        {1, 1, 1, 0, 0},
        {1, 0, 1, 1, 1},
        {1, 1, 1, 0, 1}};    
    
    public MinimiKekoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        keko = new MinimiKeko(9);
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void tyhjaKekoOnTyhja() {
         assertTrue(keko.isEmpty());
     }
     
     @Test
     public void vilkaisuPalauttaaEkanMutteiPoistaSita() {
         Piste p1 = new Piste(0,0,"white",10);
         keko.add(p1);
         Piste p2 = keko.vilkaise();
         assertEquals(p1, p2);
         assertEquals(keko.vilkaise(), p1);
     }     
     
     @Test
     public void tayteenKekoonEiVoiLisata() {
         keko = new MinimiKeko(1);
         Piste p1 = new Piste(0,0,"white",10);
         Piste p2 = new Piste(0,0,"white",20);
         keko.add(p1);
         keko.add(p2);
         assertEquals(keko.vilkaise(), p1);
     }    
     
     @Test
     public void kekoehtoVoimassaAlussa() {
         Dijkstra dj = new Dijkstra(laby);
         Piste[] kekotaulukko = dj.getKeko().getTaulukko();
         
         boolean voimassa = onkoVoimassa(kekotaulukko);

         assertTrue(voimassa);
     }     
     
     @Test
     public void kekoehtoVoimassaKahdenPoistonJalkeen() {
         Dijkstra dj = new Dijkstra(laby);
         Piste[] kekotaulukko = dj.getKeko().getTaulukko();
         dj.getKeko().remove();
         dj.getKeko().remove();
         
        boolean voimassa = onkoVoimassa(kekotaulukko);

         assertTrue(voimassa);
     }      
     
     @Test
     public void kekoehtoVoimassaKahdenDecKeynJalkeen() {
         Dijkstra dj = new Dijkstra(laby);
         Piste[] kekotaulukko = dj.getKeko().getTaulukko();
         kekotaulukko[6].setDist(0);
         dj.getKeko().decKey(kekotaulukko[6]);
         kekotaulukko[8].setDist(1);
         dj.getKeko().decKey(kekotaulukko[8]);         
         
         boolean voimassa = onkoVoimassa(kekotaulukko);

         assertTrue(voimassa);
     }      
     
     @Test
     public void kekoehtoVoimassaUseammanPoistonJaDecKeynJalkeen() {
         Dijkstra dj = new Dijkstra(laby);
         Piste[] kekotaulukko = dj.getKeko().getTaulukko();
         dj.getKeko().remove();
         kekotaulukko[6].setDist(0);
         dj.getKeko().decKey(kekotaulukko[6]);
         dj.getKeko().remove();
         dj.getKeko().remove();
         dj.getKeko().remove();
         
         boolean voimassa = onkoVoimassa(kekotaulukko);

         assertTrue(voimassa);
     }      
     
    public boolean onkoVoimassa(Piste[] kekotaulukko) {
        boolean voimassa = true;
        int i = 0;
        while (kekotaulukko[2 * i + 2] != null || kekotaulukko[2 * i + 1] != null){
            int oikea = 2 * i + 2;
            int vasen = 2 * i + 1;
            if (kekotaulukko[oikea] == null){
                if (kekotaulukko[i].getDist() > kekotaulukko[vasen].getDist()){
                    voimassa = false;
                    break;
                }
            } else {
                if (kekotaulukko[i].getDist() > kekotaulukko[oikea].getDist() || kekotaulukko[i].getDist() > kekotaulukko[vasen].getDist()){
                    voimassa = false;
                    break;
                }
            }
            i++;
        }
        return voimassa;
    }
}
