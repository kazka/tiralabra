
package aputietorakenteet;

import algoritmienvertailu.Piste;

/**
 * Minimikeko Dijkstran aputietorakenteeksi.
 * Minimiarvona käytetään keossa olevan pisteen dist-arvoa.
 */
public class MinimiKeko {
    /** 
     * Kekoon liittyvä taulukko jossa kekoon lisätyt pisteet
     */
    private Piste[] taulukko;
    /** 
     * Pisteiden lukumäärä keossa
     */
    private int n = 0;
 
    /** 
     * Konstruktori, luo kekoon liittyvän taulukon
     * 
     * @param size Luotavan taulukon koko
     */
    public MinimiKeko(int size) {
        this.taulukko = new Piste[size];
    }
 
    /** 
     * Poistaa keosta ja palauttaa pisteen jolla on pienin dist-arvo
     */
    public Piste remove() {
        if(isEmpty()) {
            System.out.println("keko on tyhjä");
        }
 
        Piste min = taulukko[0];
        taulukko[0] = taulukko[n - 1];
        if(--n > 0){
            heapifyAlaspain(0);
        }
        return min;
    }
    
     /** 
      * Palauttaa pisteen jolla on pienin dist-arvo muttei poista sitä keosta
      */
    public Piste vilkaise() {
        if(isEmpty()) {
            System.out.println("keko on tyhjä");
        }
 
        return taulukko[0];
    }
    
    /** 
     * Tarkistaa onko keko tyhjä
     */
    public boolean isEmpty() {
        return n == 0;
    }
 
    /** 
     * Lisää kekoon uuden pisteen ja pitää yllä kekoehtoa alhaalta ylöspäin
     */
    public void add(Piste p) {
        if(n == taulukko.length) {
            System.out.println("keko on täynnä");
        }
 
        taulukko[n] = p;
        heapifyYlospain(n);
        n++;
    }
 
    /**
     * Varmistaa että kekoehto pysyy voimassa.
     * Kutsutaan kun kekoon lisätään uusi piste.
     */
    private void heapifyYlospain(int index) {
        if(index > 0) {
            int parent = (index - 1) / 2;
            if(taulukko[parent].getDist() > taulukko[index].getDist()) {
                vaihda(parent, index);
                heapifyYlospain(parent);
            }
        }
    }
 
    /**
     * Varmistaa että kekoehto pysyy voimassa.
     * Kutsutaan kun keosta poistetaan pienimmän dist-arvon omaava piste.
     */
    private void heapifyAlaspain(int index) {
        int vasen = 2 * index + 1;
        int oikea = 2 * index + 2;
 
        if(oikea >= n && vasen >= n){
            return;
        }
 
        int pieninlapsi = 
            taulukko[oikea].getDist() > taulukko[vasen].getDist() ? vasen : oikea;
 
        if(taulukko[index].getDist() > taulukko[pieninlapsi].getDist()) {
            vaihda(pieninlapsi, index);
            heapifyAlaspain(pieninlapsi);
        }
    }
    
    /**
     * Etsitään keosta piste jonka dist-arvo on muuttunut ja suoritetaan heapify
     * siitä pisteestä ylöspäin.
     */
    public void decKey(Piste p){
        for (int i = 0; i < n; i++){
            if (taulukko[i].equals(p)){
                heapifyYlospain(i);
            }
        }
    }
 
    /** 
     * Apumetodi jolla vaihdetaan kahden pisteen paikkaa keossa 
     */
    private void vaihda(int pienin, int index) {
        Piste apu = taulukko[pienin];
        taulukko[pienin] = taulukko[index];
        taulukko[index] = apu;
    }
    
    /** 
     * Tulostaa keossa olevat pisteet/niiden koordinaatit järjestyksessä
     */
    public void tulosta(){
        for (int i = 0; i < n; i++){
            System.out.print(taulukko[i].getX() + "," + taulukko[i].getY() + "..");
        }
    }
 
}
