
package algoritmienvertailu;

/**
 * Pisteestä toiseen johtavaa kaarta kuvaava luokka.
 */
public class Kaari {
    /**
     * Piste josta kaari lähtee
     */
    private Piste lahde;
    /**
     * Piste johon kaari johtaa
     */
    private Piste kohde;
    
    /**
     * Konstruktori
     * 
     * @param lahde Piste josta kaari lähtee
     * @param kohde Piste johon kaari johtaa
     */
    public Kaari(Piste lahde, Piste kohde){
        this.lahde = lahde;
        this.kohde = kohde;
    }

    public Piste getKohde() {
        return kohde;
    }

    public Piste getLahde() {
        return lahde;
    }

    public void setKohde(Piste kohde) {
        this.kohde = kohde;
    }

    public void setLahde(Piste lahde) {
        this.lahde = lahde;
    }
    
    
}
