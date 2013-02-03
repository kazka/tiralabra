
package aputietorakenteet;

import org.junit.*;
import static org.junit.Assert.*;

public class ListasolmuTest {
    
    Listasolmu solmu;
    
    public ListasolmuTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        Listasolmu seuraava = new Listasolmu("toka");
        solmu = new Listasolmu("eka", seuraava);
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void dataAsetetaanOikein() {
         assertEquals("eka", solmu.getData());
     }
     
     @Test
     public void nextAsetetaanOikeinKunNull() {
         assertNull(solmu.getNext().getNext());
     }     
     
     @Test
     public void nextAsetetaanOikeinKunEiNull() {
         assertEquals("toka", solmu.getNext().getData());
     }        
     
     @Test
     public void setDataToimiiOikein() {
         int uusidata = 9;
         solmu.setData(uusidata);
         assertEquals(9, solmu.getData());
     }      
     
     @Test
     public void setNextToimiiOikein() {
         Listasolmu uusisolmu = new Listasolmu("uusi");
         solmu.setNext(uusisolmu);
         assertEquals(uusisolmu, solmu.getNext());
     }       
     
     @Test
     public void setPrevToimiiOikein() {
         Listasolmu uusisolmu = new Listasolmu("uusi");
         solmu.setPrev(uusisolmu);
         assertEquals(uusisolmu, solmu.getPrev());
     }     
}
