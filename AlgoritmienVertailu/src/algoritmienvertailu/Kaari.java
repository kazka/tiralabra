
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

    /**
     * Palauttaa pisteen johon kaari johtaa
     *
     * @return piste johon kaari johtaa
     */
    public Piste getKohde() {
        return kohde;
    }

    /**
     * Palauttaa pisteen josta kaari lähtee
     *
     * @return piste josta kaari lähtee
     */
    public Piste getLahde() {
        return lahde;
    }

    /**
     * Asettaa pisteen johon kaari johtaa
     *
     * @param kohde Piste johon kaari johtaa
     */
    public void setKohde(Piste kohde) {
        this.kohde = kohde;
    }

    /**
     * Asettaa pisteen josta kaari lähtee
     *
     * @param lahde Piste josta kaari lähtee
     */
    public void setLahde(Piste lahde) {
        this.lahde = lahde;
    }
    
    
}
