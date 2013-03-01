
package aputietorakenteet;

/**
 * Linkitettyyn listaan tallennettavaa solmua kuvaava luokka.
 */
public class Listasolmu {
    /**
     * Objekti jonka perusteella listasolmu muodostetaan, algoritmeissa käytössä
     * piste tai kaari
     */
    private Object data;
    /**
     * Solmua seuraava solmu listassa
     */
    private Listasolmu next;
    /**
     * Solmua edeltävä solmu listassa
     */
    private Listasolmu prev;
    
    /**
     * Pääasiallinen konstruktori, muodostaa listasolmun halutusta sisällöstä/datasta ja
     * asettaa sille seuraavan solmun.
     * 
     * @param data Objekti josta listasolmu muodostetaan
     * @param next Solmua seuraava solmu listassa
     */
    public Listasolmu(Object data, Listasolmu next) {
        this.data = data;
        this.next = next;
    }
    
    /**
     * Konstruktori joka kutsuu toista konstruktoria muodostamaan uuden listasolmun
     * asettamatta solmulle seuraavaa solmua.
     * 
     * @param data Objekti josta listasolmu muodostetaan
     */
    public Listasolmu(Object data) {
        this(data, null);
    }
    
     /**
     * Palauttaa objektin josta solmu on muodostettu
     * 
     * @return objekti josta solmu on muodostettu
     */
    public Object getData() {
        return data;
    }

    /**
     * Palauttaa solmua seuraavan solmun
     * 
     * @return solmua seuraava solmu
     */
    public Listasolmu getNext() {
        return next;
    }

    /**
     * Palauttaa solmua edeltävän solmun
     * 
     * @return solmua edeltävän solmu
     */
    public Listasolmu getPrev() {
        return prev;
    }    
    
    /**
     * Asettaa solmussa käytettävän objektin
     * 
     * @param data Solmussa käytettävä objekti/data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Asettaa solmua seuraavan solmun
     * 
     * @param next Solmua seuraava solmu
     */
    public void setNext(Listasolmu next) {
        this.next = next;
    }
    
    /**
     * Asettaa solmua edeltävän solmun
     * 
     * @param prev Solmua edeltävä solmu
     */
    public void setPrev(Listasolmu prev) {
        this.prev = prev;
    }
}
