
package algoritmienvertailu;

import org.junit.*;
import static org.junit.Assert.*;


public class PisteTest {
    Piste p;
    
    public PisteTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        p = new Piste(2,1,"white",50);
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
     public void distAsetetaanOikein() {
         assertEquals(50, p.getDist());
         p.setDist(10);
         assertEquals(10, p.getDist());
     }  
     
     @Test
     public void edellinenAsetetaanOikein() {
         Piste ed = new Piste(0,0,"white",40);
         p.setEdellinen(ed);
         assertEquals(ed, p.getEdellinen());
     }      
}
