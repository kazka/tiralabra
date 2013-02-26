
package algoritmienvertailu;

import org.junit.*;
import static org.junit.Assert.*;

public class AlgoritmienVertailuTest {
    AlgoritmienVertailu av;
    
    public AlgoritmienVertailuTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        this.av = new AlgoritmienVertailu();
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void pieniLabyGeneroidaanOikein() {
         int[][] pienilaby = av.generoiLabyrintti("pieni");
         assertEquals(20, pienilaby.length);
         assertEquals(20, pienilaby[0].length);
     }
     
     @Test
     public void keskiLabyGeneroidaanOikein() {
         int[][] keskilaby = av.generoiLabyrintti("keski");
         assertEquals(100, keskilaby.length);
         assertEquals(100, keskilaby[0].length);
     }     
     
     @Test
     public void isoLabyGeneroidaanOikein() {
         int[][] isolaby = av.generoiLabyrintti("iso");
         assertEquals(400, isolaby.length);
         assertEquals(400, isolaby[0].length);
     }          
     
     @Test
     public void tyhjaLabyGeneroidaanOikein() {
         int[][] tyhjalaby = av.generoiLabyrintti("tyhja");
         boolean tyhja = true;
         for (int i = 0; i < tyhjalaby.length; i++) {
            for (int j = 0; j < tyhjalaby[0].length; j++) {
                if (tyhjalaby[i][j] == 0){
                    tyhja = false;
                }
            }
         }
         assertTrue(tyhja);
         assertEquals(200, tyhjalaby.length);
         assertEquals(200, tyhjalaby[0].length);
     }          
}
