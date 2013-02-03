
package aputietorakenteet;

import org.junit.*;
import static org.junit.Assert.*;

public class LinkitettyListaTest {
    
    LinkitettyLista lista;
    
    public LinkitettyListaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        lista = new LinkitettyLista();
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void alussaListaTyhja() {
         assertTrue(lista.isEmpty());
     }
     
     @Test
     public void lisaysKasvattaaSolmumaaraaOikein() {
         String eka = "eka";
         String toka = "toka";
         lista.add(eka);
         lista.add(toka);
         assertEquals(2, lista.getSolmumaara());
     }   
     
     @Test
     public void poistoVahentaaSolmumaaraaOikein() {
         String eka = "eka";
         String toka = "toka";
         lista.add(eka);
         lista.add(toka);
         Listasolmu poistettava = lista.search(eka);
         lista.delete(poistettava);
         assertEquals(1, lista.getSolmumaara());
     }      
     
     @Test
     public void uudenSolmunNextOnNullJosOliEnsimmainenLisatty() {
         String eka = "eka";
         lista.add(eka);
         assertNull(lista.search(eka).getNext());
     }  
     
     @Test
     public void uusiSolmuLisataanListanAlkuun() {
         String eka = "eka";
         String toka = "toka";
         lista.add(eka);
         assertEquals(eka, lista.getAlkusolmu().getNext().getData());
         assertEquals(lista.getAlkusolmu(), lista.search(eka).getPrev());
         lista.add(toka);
         assertEquals(toka, lista.getAlkusolmu().getNext().getData());
         assertEquals(toka, lista.search(eka).getPrev().getData());
     }    
     
     @Test
     public void searchPalauttaaNullJosEiLoytynyt() {
         String eka = "eka";
         String toka = "toka";
         lista.add(eka);
         assertNull(lista.search(toka));
     }    
     
     @Test
     public void searchPalauttaaSolmunJosLoytyi() {
         String eka = "eka";
         String toka = "toka";
         lista.add(eka);
         lista.add(toka);
         assertEquals(toka, lista.search(toka).getData());
     }    
     
     @Test
     public void containsPalauttaaFalseJosEiLoytynyt() {
         String eka = "eka";
         String toka = "toka";
         lista.add(eka);
         assertFalse(lista.contains(toka));
     }    
     
     @Test
     public void containsPalauttaaTrueJosLoytyi() {
         String eka = "eka";
         String toka = "toka";
         lista.add(eka);
         lista.add(toka);
         assertTrue(lista.contains(toka));
     }   
     
     @Test
     public void lopustaPoistaminenToimii() {
         String eka = "eka";
         String toka = "toka";
         lista.add(eka);
         lista.add(toka);
         assertEquals(eka, lista.search(toka).getNext().getData());
         lista.delete(lista.search(eka));
         assertNull(lista.search(toka).getNext());
     }    
     
     @Test
     public void alustaPoistaminenToimii() {
         String eka = "eka";
         String toka = "toka";
         lista.add(eka);
         lista.add(toka);
         lista.delete(lista.search(toka));
         assertEquals(eka, lista.getAlkusolmu().getNext().getData());
         assertEquals(lista.getAlkusolmu(), lista.search(eka).getPrev());
     }      
     
     @Test
     public void keskeltaPoistaminenToimii() {
         String eka = "eka";
         String toka = "toka";
         String kolmas = "kolmas";
         lista.add(eka);
         lista.add(toka);
         lista.add(kolmas);
         assertEquals(eka, lista.search(toka).getNext().getData());
         assertEquals(kolmas, lista.search(toka).getPrev().getData());
         lista.delete(lista.search(toka));
         assertEquals(eka, lista.search(kolmas).getNext().getData());
         assertEquals(kolmas, lista.search(eka).getPrev().getData());
     }         
}
