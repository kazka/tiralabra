
package aputietorakenteet;

import algoritmienvertailu.AstarPiste;
import algoritmienvertailu.Piste;

/**
 * Minimikeko A starin aputietorakenteeksi.
 * Minimiarvona käytetään keossa olevan pisteen etäisyyksien summaa alkuun + loppuun.
 */
public class AstarMinimiKeko {
    /** 
     * Kekoon liittyvä taulukko jossa kekoon lisätyt pisteet
     */
    private AstarPiste[] taulukko;
    /** 
     * Pisteiden lukumäärä keossa
     */
    private int pisteidenMaara = 0;
 
    /** 
     * Konstruktori, luo kekoon liittyvän taulukon
     * 
     * @param size Luotavan taulukon koko
     */
    public AstarMinimiKeko(int size) {
        this.taulukko = new AstarPiste[size];
    }
 
    /** 
     * Poistaa keosta ja palauttaa pisteen jolla on pienin alkuun + loppuun -arvo
     */
    public AstarPiste remove() {
        if(isEmpty()) {
            System.out.println("keko on tyhjä");
        }
 
        AstarPiste min = taulukko[0];
        taulukko[0] = taulukko[pisteidenMaara - 1];
        if(--pisteidenMaara > 0){
            heapifyAlaspain(0);
        }
        return min;
    }
    
    /** 
     * Tarkistaa onko keko tyhjä
     */
    public boolean isEmpty() {
        return pisteidenMaara == 0;
    }
 
    /** 
     * Lisää kekoon uuden pisteen ja pitää yllä kekoehtoa alhaalta ylöspäin
     */
    public void add(AstarPiste p) {
        if(pisteidenMaara == taulukko.length) {
            System.out.println("keko on täynnä");
            return;
        }
 
        taulukko[pisteidenMaara] = p;
        heapifyYlospain(pisteidenMaara);
        pisteidenMaara++;
    }
 
    /**
     * Varmistaa että kekoehto pysyy voimassa.
     * Kutsutaan kun kekoon lisätään uusi piste.
     */
    private void heapifyYlospain(int index) {
        if(index > 0) {
            int parent = (index - 1) / 2;
            if(taulukko[parent].getAlkuunPlusLoppuun() > taulukko[index].getAlkuunPlusLoppuun()) {
                vaihda(parent, index);
                heapifyYlospain(parent);
            }
        }
    }
 
    /**
     * Varmistaa että kekoehto pysyy voimassa.
     * Kutsutaan kun keosta poistetaan piste jolla pienin alkuun + loppuun -arvo.
     */
    private void heapifyAlaspain(int index) {
        int vasen = vasen(index);
        int oikea = oikea(index);
 
        if(oikea >= pisteidenMaara && vasen >= pisteidenMaara){
            return;
        }
 
        int pieninlapsi = 
            taulukko[oikea].getAlkuunPlusLoppuun() > taulukko[vasen].getAlkuunPlusLoppuun() ? vasen : oikea;
 
        if(taulukko[index].getAlkuunPlusLoppuun() > taulukko[pieninlapsi].getAlkuunPlusLoppuun()) {
            vaihda(pieninlapsi, index);
            heapifyAlaspain(pieninlapsi);
        }
    }
    
    /**
     * Palauttaa indeksin keossa oikeanpuoleiseen alkioon
     */
    public int oikea(int index){
        return 2 * index + 2;
    }
    
    /**
     * Palauttaa indeksin keossa vasemmanpuoleiseen alkioon
     */
    public int vasen(int index){
        return 2 * index + 1;
    }
    
    /**
     * Etsitään keosta piste jonka avainarvo on muuttunut ja suoritetaan heapify
     * siitä pisteestä ylöspäin.
     */
    public void decKey(AstarPiste p){
        for (int i = 0; i < pisteidenMaara; i++){
            if (taulukko[i].equals(p)){
                heapifyYlospain(i);
            }
        }
    }
 
    /** 
     * Apumetodi jolla vaihdetaan kahden pisteen paikkaa keossa 
     */
    private void vaihda(int pienin, int index) {
        AstarPiste apu = taulukko[pienin];
        taulukko[pienin] = taulukko[index];
        taulukko[index] = apu;
    }
    
    /** 
     * Tulostaa keossa olevat pisteet/niiden koordinaatit järjestyksessä
     */
    public void tulosta(){
        for (int i = 0; i < pisteidenMaara; i++){
            System.out.print(taulukko[i].getX() + "," + taulukko[i].getY() + "/" + taulukko[i].getAlkuunPlusLoppuun() + "..");
        }
    }

    public int getPisteidenMaara() {
        return pisteidenMaara;
    }

    public AstarPiste[] getTaulukko() {
        return taulukko;
    }
}
