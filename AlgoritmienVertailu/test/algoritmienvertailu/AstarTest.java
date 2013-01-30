package algoritmienvertailu;

import org.junit.*;
import static org.junit.Assert.*;

public class AstarTest {

    Astar astIso;
    Astar astPieni;
    Astar astMaaliton;
    static int[][] labyIso = new int[][]{
        {1, 1, 0, 0, 0},
        {0, 1, 0, 0, 0},
        {1, 1, 1, 0, 0},
        {1, 0, 1, 1, 1},
        {1, 1, 1, 0, 1}};
    static int[][] labyPieni = new int[][]{
        {1, 1, 1},
        {1, 0, 1},
        {1, 1, 1}};
    static int[][] labyMaaliton = new int[][]{
        {1, 0, 1},
        {1, 0, 0},
        {1, 0, 1}};

    public AstarTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        astIso = new Astar(labyIso);
        astPieni = new Astar(labyPieni);
        astMaaliton = new Astar(labyMaaliton);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void alussaKaikillaPaitsoLahtopisteellaDistAareton() {
        boolean aareton = true;
        boolean alkupisteenEtaisyysNolla = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0 && j == 0) {
                    if (astPieni.getPisteet()[i][j].getDist() != 0) {
                        alkupisteenEtaisyysNolla = false;
                    }
                } else {
                    if (labyPieni[i][j] == 1 && astPieni.getPisteet()[i][j].getDist() != Integer.MAX_VALUE) {
                        aareton = false;
                    }
                }

            }
        }
        assertTrue(aareton);
        assertTrue(alkupisteenEtaisyysNolla);
    }

    @Test
    public void distLoppuunArvioAsetetaanOikein() {
        assertEquals(4, astPieni.getPisteet()[0][0].getDistLoppuun());
        assertEquals(4, astIso.getPisteet()[2][2].getDistLoppuun());
    }

    @Test
    public void etaisyysMaaliinLasketaanOikeinPienessa() {
        astPieni.astaraa();
        assertEquals(4, astPieni.getMaalipiste().getDist());
    }

    @Test
    public void etaisyysMuualleLasketaanOikeinPienessa() {
        astPieni.astaraa();
        assertEquals(2, astPieni.getPisteet()[2][0].getDist());
        assertEquals(2, astPieni.getPisteet()[0][2].getDist());
    }

    @Test
    public void etaisyysMaaliinLasketaanOikeinIsossa() {
        astIso.astaraa();
        assertEquals(8, astIso.getMaalipiste().getDist());
    }
    
    @Test
    public void relaxToimiiOikeinKunDistYhtaSuurempi(){
        AstarPiste p1 = new AstarPiste(0,0,"white",10,10);
        AstarPiste p2 = new AstarPiste(0,1,"white",11,20);
        AstarPiste p3 = new AstarPiste(1,0,"white",15,30);
        p2.setEdellinen(p3);
        astIso.relax(p1,p2);
        
        assertEquals(11, p2.getDist());
        assertEquals(p3, p2.getEdellinen());
    }
    
    @Test
    public void relaxToimiiOikeinKunDistUseammanSuurempi(){
        AstarPiste p1 = new AstarPiste(0,0,"white",10,10);
        AstarPiste p2 = new AstarPiste(0,1,"white",12,20);
        AstarPiste p3 = new AstarPiste(1,0,"white",15,30);
        p1.setEdellinen(p3);
        astIso.relax(p1,p2);
        
        assertEquals(11, p2.getDist());
        assertEquals(p1, p2.getEdellinen());
    }     
}
