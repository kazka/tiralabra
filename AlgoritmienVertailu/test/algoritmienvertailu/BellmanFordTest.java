package algoritmienvertailu;

import org.junit.*;
import static org.junit.Assert.*;

public class BellmanFordTest {

    BellmanFord bfIso;
    BellmanFord bfPieni;
    BellmanFord bfMaaliton;
    
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

    public BellmanFordTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        bfIso = new BellmanFord(labyIso);
        bfPieni = new BellmanFord(labyPieni);
        bfMaaliton = new BellmanFord(labyMaaliton);
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
                    if (bfPieni.getPisteet()[i][j].getDist() != 0) {
                        alkupisteenEtaisyysNolla = false;
                    }
                } else {
                    if (labyPieni[i][j] == 1 && bfPieni.getPisteet()[i][j].getDist() != Integer.MAX_VALUE) {
                        aareton = false;
                    }
                }

            }
        }
        assertTrue(aareton);
        assertTrue(alkupisteenEtaisyysNolla);
    }

    @Test
    public void lopussaSaavuttamattomillaPisteillaEtaisyysAareton() {
        bfMaaliton.bellman();
        boolean aareton = true;
        if (bfMaaliton.getPisteet()[0][2].getDist() != Integer.MAX_VALUE || bfMaaliton.getPisteet()[2][2].getDist() != Integer.MAX_VALUE) {
            aareton = false;
        }
        assertTrue(aareton);
    }

    @Test
    public void etaisyysMaaliinLasketaanOikeinPienessa() {
        bfPieni.bellman();
        assertEquals(4, bfPieni.getMaalipiste().getDist());
    }

    @Test
    public void etaisyysMuualleLasketaanOikeinPienessa() {
        bfPieni.bellman();
        assertEquals(2, bfPieni.getPisteet()[2][0].getDist());
        assertEquals(2, bfPieni.getPisteet()[0][2].getDist());
    }

    @Test
    public void etaisyysMaaliinLasketaanOikeinIsossa() {
        bfIso.bellman();
        assertEquals(8, bfIso.getMaalipiste().getDist());
    }

    @Test
    public void etaisyysMuualleLasketaanOikeinIsossa() {
        bfIso.bellman();
        assertEquals(6, bfIso.getPisteet()[4][0].getDist());
        assertEquals(7, bfIso.getPisteet()[4][1].getDist());
        assertEquals(6, bfIso.getPisteet()[4][2].getDist());
    }
    
    @Test
    public void solmumaaraLasketaanOikein(){
        assertEquals(5, bfMaaliton.getSolmumaara());
        assertEquals(8, bfPieni.getSolmumaara());
        assertEquals(14, bfIso.getSolmumaara());
    }    
    
    @Test
    public void haeKaaretHakeeOikeanMaaranKaaria(){
        assertEquals(4, bfMaaliton.getKaaret().size());
        assertEquals(16, bfPieni.getKaaret().size());
    }
    
//    @Test
//    public void haeKaaretHakeeOikeatKaaret(){
//
//    }    
}
