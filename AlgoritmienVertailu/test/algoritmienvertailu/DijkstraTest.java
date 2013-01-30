package algoritmienvertailu;

import org.junit.*;
import static org.junit.Assert.*;

public class DijkstraTest {

    Dijkstra djIso;
    Dijkstra djPieni;
    Dijkstra djMaaliton;
    
    static int[][] labyIso = new int[][]
        {{1, 1, 0, 0, 0},
        {0, 1, 0, 0, 0},
        {1, 1, 1, 0, 0},
        {1, 0, 1, 1, 1},
        {1, 1, 1, 0, 1}};
    
    static int[][] labyPieni = new int[][]
        {{1, 1, 1},
        {1, 0, 1},
        {1, 1, 1}};
    
    static int[][] labyMaaliton = new int[][]
        {{1, 0, 1},
        {1, 0, 0},
        {1, 0, 1}};

    public DijkstraTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        djIso = new Dijkstra(labyIso);
        djPieni = new Dijkstra(labyPieni);
        djMaaliton = new Dijkstra(labyMaaliton);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void alussaKaikillaPaitsoLahtopisteellaEtaisyysAareton() {
        boolean aareton = true;
        boolean alkupisteenEtaisyysNolla = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0 && j == 0) {
                    if (djPieni.getPisteet()[i][j].getDist() != 0) {
                        alkupisteenEtaisyysNolla = false;
                    }
                } else {
                    if (labyPieni[i][j] == 1 && djPieni.getPisteet()[i][j].getDist() != Integer.MAX_VALUE) {
                        aareton = false;
                    }
                }

            }
        }
        assertTrue(aareton);
        assertTrue(alkupisteenEtaisyysNolla);
    }

    @Test
    public void alussaKaikillaPisteillaVariWhite() {
        boolean white = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0 && j == 0) {
                    if (!djPieni.getPisteet()[i][j].getColor().equals("white")) {
                        white = false;
                    }
                }
            }
        }
        assertTrue(white);
    }

    @Test
    public void lopussaKaikillaKaydyillaPisteillaVariBlack() {
        djPieni.dijkstraa();
        boolean black = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (labyPieni[i][j] == 1 && !djPieni.getPisteet()[i][j].getColor().equals("black")) {
                    black = false;
                }
            }
        }
        assertTrue(black);
    }

    @Test
    public void lopussaSaavuttamattomillaPisteillaEtaisyysAareton() {
        djMaaliton.dijkstraa();
        boolean aareton = true;
        if (djMaaliton.getPisteet()[0][2].getDist() != Integer.MAX_VALUE || djMaaliton.getPisteet()[2][2].getDist() != Integer.MAX_VALUE) {
            aareton = false;
        }
        assertTrue(aareton);
    }

    @Test
    public void etaisyysMaaliinLasketaanOikeinPienessa() {
        djPieni.dijkstraa();
        assertEquals(4, djPieni.getMaalipiste().getDist());
    }

    @Test
    public void etaisyysMuualleLasketaanOikeinPienessa() {
        djPieni.dijkstraa();
        assertEquals(2, djPieni.getPisteet()[2][0].getDist());
        assertEquals(2, djPieni.getPisteet()[0][2].getDist());
    }

    @Test
    public void etaisyysMaaliinLasketaanOikeinIsossa() {
        djIso.dijkstraa();
        assertEquals(8, djIso.getMaalipiste().getDist());
    }

    @Test
    public void etaisyysMuualleLasketaanOikeinIsossa() {
        djIso.dijkstraa();
        assertEquals(6, djIso.getPisteet()[4][0].getDist());
        assertEquals(7, djIso.getPisteet()[4][1].getDist());
        assertEquals(6, djIso.getPisteet()[4][2].getDist());
    }

    @Test
    public void OnkoAlueellaPalauttaaFalseJosEiAlueella() {
        assertFalse(djPieni.onkoAlueella(-1, 0));
        assertFalse(djPieni.onkoAlueella(0, -1));
        assertFalse(djPieni.onkoAlueella(0, 3));
        assertFalse(djPieni.onkoAlueella(3, 0));
    }

    @Test
    public void OnkoAlueellaPalauttaaTrueJosAlueellaJaPolulla() {
        assertTrue(djPieni.onkoAlueella(0, 0));
        assertTrue(djPieni.onkoAlueella(0, 2));
        assertTrue(djPieni.onkoAlueella(2, 0));
        assertTrue(djPieni.onkoAlueella(2, 2));
    }

    @Test
    public void OnkoAlueellaPalauttaaFalseJosAlueellaJaEiPolulla() {
        assertFalse(djPieni.onkoAlueella(1, 1));
    }

    @Test
    public void OnkoAlueellaPalauttaaFalseJosAlueellaJaPolullaJaBlack() {
        djPieni.dijkstraa();
        assertFalse(djPieni.onkoAlueella(2, 2));
    }
    
    @Test
    public void relaxToimiiOikeinKunDistYhtaSuurempi(){
        Piste p1 = new Piste(0,0,"white",10);
        Piste p2 = new Piste(0,1,"white",11);
        Piste p3 = new Piste(1,0,"white",15);
        p2.setEdellinen(p3);
        djIso.relax(p1,p2);
        
        assertEquals(11, p2.getDist());
        assertEquals(p3, p2.getEdellinen());
    }
    
    @Test
    public void relaxToimiiOikeinKunDistUseammanSuurempi(){
        Piste p1 = new Piste(0,0,"white",10);
        Piste p2 = new Piste(0,1,"white",12);
        Piste p3 = new Piste(1,0,"white",15);
        p1.setEdellinen(p3);
        djIso.relax(p1,p2);
        
        assertEquals(11, p2.getDist());
        assertEquals(p1, p2.getEdellinen());
    }    
    
    @Test
    public void relaxToimiiOikeinKunMolempienDistAareton(){
        Piste p1 = new Piste(0,0,Integer.MAX_VALUE);
        Piste p2 = new Piste(0,1,Integer.MAX_VALUE);
        Piste p3 = new Piste(1,0,15);
        p2.setEdellinen(p3);
        djIso.relax(p1,p2);
        
        assertEquals(Integer.MAX_VALUE, p2.getDist());
        assertEquals(p3, p2.getEdellinen());
    }      
}
