
package algoritmienvertailu;

import org.junit.*;
import static org.junit.Assert.*;


public class KaariTest {
    Kaari kaari;
    Piste lahde;
    Piste kohde;
    
    public KaariTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        lahde = new Piste(0,0,10);
        kohde = new Piste(0,1,11);
        kaari = new Kaari(lahde,kohde);
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void lahdeJaKohdeAsetetaanOikeinAlussa() {
         assertEquals(lahde, kaari.getLahde());
         assertEquals(kohde, kaari.getKohde());
     }
     
     @Test
     public void lahdeJaKohdeAsetetaanOikeinSetterissa() {
         kaari.setLahde(kohde);
         kaari.setKohde(lahde);
         assertEquals(lahde, kaari.getKohde());
         assertEquals(kohde, kaari.getLahde());
     }     
}
