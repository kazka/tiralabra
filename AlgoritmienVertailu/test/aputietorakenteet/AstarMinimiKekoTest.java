
package aputietorakenteet;

import algoritmienvertailu.Astar;
import algoritmienvertailu.AstarPiste;
import algoritmienvertailu.Dijkstra;
import algoritmienvertailu.Piste;
import org.junit.*;
import static org.junit.Assert.*;


public class AstarMinimiKekoTest {
    AstarMinimiKeko keko;
    
    static int[][] laby = new int[][]
        {{1, 1, 0, 0, 0},
        {0, 1, 0, 0, 0},
        {1, 1, 1, 0, 0},
        {1, 0, 1, 1, 1},
        {1, 1, 1, 0, 1}};    
    
    public AstarMinimiKekoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        keko = new AstarMinimiKeko(9);
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void tyhjaKekoOnTyhja() {
         assertTrue(keko.isEmpty());
     }    
     
     @Test
     public void tayteenKekoonEiVoiLisata() {
         keko = new AstarMinimiKeko(1);
         AstarPiste p1 = new AstarPiste(0,0,10,20);
         AstarPiste p2 = new AstarPiste(0,0,20,20);
         keko.add(p1);
         keko.add(p2);
         assertEquals(keko.remove(), p1);
     }    
     
     @Test
     public void kekoehtoVoimassaAlussa() {
         Astar ast = new Astar(laby);
         AstarPiste[] kekotaulukko = ast.getKeko().getTaulukko();
         
         boolean voimassa = onkoVoimassa(kekotaulukko);

         assertTrue(voimassa);
     }     
     
     @Test
     public void kekoehtoVoimassaKahdenPoistonJalkeen() {
         Astar ast = new Astar(laby);
         AstarPiste[] kekotaulukko = ast.getKeko().getTaulukko();
         ast.getKeko().remove();
         ast.getKeko().remove();
         
         boolean voimassa = onkoVoimassa(kekotaulukko);

         assertTrue(voimassa);
     }      
     
     @Test
     public void kekoehtoVoimassaKahdenDecKeynJalkeen() {
         Astar ast = new Astar(laby);
         AstarPiste[] kekotaulukko = ast.getKeko().getTaulukko();
         kekotaulukko[6].setDistLoppuun(0);
         ast.getKeko().decKey(kekotaulukko[6]);
         kekotaulukko[8].setDistLoppuun(1);
         ast.getKeko().decKey(kekotaulukko[8]);         
         
         boolean voimassa = onkoVoimassa(kekotaulukko);

         assertTrue(voimassa);
     }      
     
     @Test
     public void kekoehtoVoimassaUseammanPoistonJaDecKeynJalkeen() {
         Astar ast = new Astar(laby);
         AstarPiste[] kekotaulukko = ast.getKeko().getTaulukko();
         ast.getKeko().remove();
         kekotaulukko[6].setDist(0);
         ast.getKeko().decKey(kekotaulukko[6]);
         ast.getKeko().remove();
         ast.getKeko().remove();
         ast.getKeko().remove();
         
         boolean voimassa = onkoVoimassa(kekotaulukko);

         assertTrue(voimassa);
     }      
     
    public boolean onkoVoimassa(AstarPiste[] kekotaulukko) {
        boolean voimassa = true;
        int i = 0;
        while (kekotaulukko[2 * i + 2] != null || kekotaulukko[2 * i + 1] != null){
            int oikea = 2 * i + 2;
            int vasen = 2 * i + 1;
            if (kekotaulukko[oikea] == null){
                if (kekotaulukko[i].getAlkuunPlusLoppuun() > kekotaulukko[vasen].getAlkuunPlusLoppuun()){
                    voimassa = false;
                    break;
                }
            } else {
                if (kekotaulukko[i].getAlkuunPlusLoppuun() > kekotaulukko[oikea].getAlkuunPlusLoppuun() || kekotaulukko[i].getAlkuunPlusLoppuun() > kekotaulukko[vasen].getAlkuunPlusLoppuun()){
                    voimassa = false;
                    break;
                }
            }
            i++;
        }
        return voimassa;
    }
}
