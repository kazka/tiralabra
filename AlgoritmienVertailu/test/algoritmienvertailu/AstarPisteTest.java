
package algoritmienvertailu;

import org.junit.*;
import static org.junit.Assert.*;


public class AstarPisteTest {
    AstarPiste p;
    
    public AstarPisteTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        p = new AstarPiste(2,1,"white",10,20);
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void koordinaatitAsetetaanOikeinAlussa() {
         assertEquals(2, p.getX());
         assertEquals(1, p.getY());
     }
     
     @Test
     public void variAsetetaanOikein() {
         assertEquals("white", p.getColor());
         p.setColor("black");
         assertEquals("black", p.getColor());
     }     
     
     @Test
     public void distLoppuunAsetetaanOikein() {
         assertEquals(20, p.getDistLoppuun());
         p.setDistLoppuun(10);
         assertEquals(10, p.getDistLoppuun());
     }  
     
     @Test
     public void edellinenAsetetaanOikein() {
         AstarPiste ed = new AstarPiste(0,0,"white",40,50);
         p.setEdellinen(ed);
         assertEquals(ed, p.getEdellinen());
     }  
     
     @Test
     public void alkuunPlusLoppuunLasketaanOikein() {
         assertEquals(30, p.getAlkuunPlusLoppuun());
     }     
     
     @Test
     public void alkuunPlusLoppuunLasketaanOikeinJosAareton() {
         p.setDist(Integer.MAX_VALUE);
         assertEquals(Integer.MAX_VALUE, p.getAlkuunPlusLoppuun());
     }       
}
