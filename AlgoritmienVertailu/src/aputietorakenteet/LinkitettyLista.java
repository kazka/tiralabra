
package aputietorakenteet;

/**
 * Kahteen suuntaan linkitetty lista.
 */
public class LinkitettyLista {
    
    /**
    * Listan ensimmäisenä solmuna käytettävä tyhjä solmu.
    */
    private Listasolmu alkusolmu;
    /**
    * Solmujen määrä listassa, lukuunottamatta alkusolmua.
    */
    private int solmumaara;
    
    /**
     * Konstruktori, luo listan alussa olevan tyhjän solmun.
     */
    public LinkitettyLista() {
        this.alkusolmu = new Listasolmu("");
    }
    
    /**
     * Testaa onko lista tyhjä
     * 
     * @return true jos tyhjä, muuten false
     */
    public boolean isEmpty() {
        return this.alkusolmu.getNext() == null;
    }    
    
    /**
     * Lisää listan alkuun uuden solmun
     * 
     * @param lisattava Sisalto joka lisätään
     */
    public void add(Object lisattava){
        Listasolmu uusisolmu = new Listasolmu(lisattava);
        uusisolmu.setNext(this.alkusolmu.getNext());
        if (uusisolmu.getNext() != null){
            uusisolmu.getNext().setPrev(uusisolmu);
        }
        uusisolmu.setPrev(this.alkusolmu);
        this.alkusolmu.setNext(uusisolmu);
        this.solmumaara++;
    }
    
    /**
     * Etsii listasta solmun jolla on toivottu sisältö
     * 
     * @param etsittava Etsittävä sisältö
     * @return solmu jolla on etsittävä sisältö
     */
    public Listasolmu search(Object etsittava){
        Listasolmu solmu = this.alkusolmu;
        while (solmu != null && !solmu.getData().equals(etsittava)){
            solmu = solmu.getNext();
        }
        return solmu;
    }
    
    /**
     * Tutkii sisältääkö lista haluttua solmua
     * 
     * @param etsittava Etsittävä sisältö
     * @return true jos solmu löytyi, muuten false
     */
    public boolean contains(Object etsittava){
        Listasolmu solmu = this.alkusolmu;
        while (solmu.getNext() != null){
            solmu = solmu.getNext();
            if (solmu.getData().equals(etsittava)){
                return true;
            }
        }
        return false;
    }    
    
    /**
     * Poistaa listasta halutun solmun
     * 
     * @param poistettava Solmu joka halutaan poistaa
     */
    public void delete(Listasolmu poistettava){
        Listasolmu prev = poistettava.getPrev();
        Listasolmu next = poistettava.getNext();
        if (!prev.equals(this.alkusolmu)){
            prev.setNext(next);
        } else {
            this.alkusolmu.setNext(next);
        }
        if (next != null){
            next.setPrev(prev);
        }
        this.solmumaara--;
    }
    
    /**
     * Palauttaa listan ensimmäisenä solmuna käytettävän tyhjän solmun.
     * 
     * @return tyhjä solmu listan alussa
     */
    public Listasolmu getAlkusolmu() {
        return alkusolmu;
    }
    
    /**
     * Palauttaa solmujen määrän listassa, lukuunottamatta alkusolmua.
     * 
     * @return solmujen määrä listassa
     */
    public int getSolmumaara() {
        return solmumaara;
    }
    
}
