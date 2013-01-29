
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
    private int pisteidenMaara = 0;
 
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
     * 
     * @return piste jolla pienin dist-arvo
     */
    public Piste remove() {
        tarkistaTyhjyys();
 
        Piste min = taulukko[0];
        taulukko[0] = taulukko[pisteidenMaara - 1];
        if(--pisteidenMaara > 0){
            heapifyAlaspain(0);
        }
        return min;
    }

    /** 
     * Tarkistaa onko keko tyhjä, jos on niin tulostaa siitä ilmoituksen
     */
    public void tarkistaTyhjyys() {
        if(isEmpty()) {
            System.out.println("keko on tyhjä");
        }
    }
    
     /** 
      * Palauttaa pisteen jolla on pienin dist-arvo muttei poista sitä keosta
      * 
      * @return piste jolla pienin dist-arvo
      */
    public Piste vilkaise() {
        tarkistaTyhjyys();
 
        return taulukko[0];
    }
    
    /** 
     * Tarkistaa onko keko tyhjä
     * 
     * @return true jos keko on tyhjä
     */
    public boolean isEmpty() {
        return pisteidenMaara == 0;
    }
 
    /** 
     * Lisää kekoon uuden pisteen ja pitää yllä kekoehtoa alhaalta ylöspäin
     * 
     * @param p Lisättävä piste
     */
    public void add(Piste p) {
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
     * 
     * @param index Indeksi josta ylöspäin (taulukossa alkuun päin) heapify suoritetaan
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
     * Kutsutaan kun keosta poistetaan piste jolla pienin dist-arvo.
     * 
     * @param index Indeksi josta alaspäin (taulukossa loppuun päin) heapify suoritetaan
     */
    private void heapifyAlaspain(int index) {
        int vasen = vasen(index);
        int oikea = oikea(index);
 
        if(!onkoEnempaaLapsia(oikea, vasen)) return;
 
        int pieninlapsi = 
            taulukko[oikea].getDist() > taulukko[vasen].getDist() ? vasen : oikea;
 
        if(taulukko[index].getDist() > taulukko[pieninlapsi].getDist()) {
            vaihda(pieninlapsi, index);
            heapifyAlaspain(pieninlapsi);
        }
    }
    
    /**
     * Tarkistaa onko pisteellä keossa vielä lapsia.
     * 
     * @param oikea Indeksi josta oikean lapsen pitäisi löytyä
     * @param vasen Indeksi josta vasemman lapsen pitäisi löytyä
     * 
     * @return true jos molemmat tai jompikumpi lapsista on olemassa,
     * false jos oikean ja vasemman lapsen indeksit menevät yli keon koon
     * eli pisteiden määrän
     */
    public boolean onkoEnempaaLapsia(int oikea, int vasen) {
        if (oikea >= pisteidenMaara && vasen >= pisteidenMaara) {
            return false;
        }
        return true;
    }
    
    /**
     * Palauttaa indeksin keossa oikeanpuoleiseen alkioon
     * 
     * @param index Indeksi jonka oikeanpuoleisen alkion indeksiä haetaan
     * 
     * @return indeksi oikeanpuoleiseen alkioon
     */
    public int oikea(int index){
        return 2 * index + 2;
    }
    
    /**
     * Palauttaa indeksin keossa vasemmanpuoleiseen alkioon
     * 
     * @param index Indeksi jonka vasemmanpuoleisen alkion indeksiä haetaan
     * 
     * @return indeksi vasemmanpuoleiseen alkioon
     */
    public int vasen(int index){
        return 2 * index + 1;
    }
    
    /**
     * Etsitään keosta piste jonka dist-arvo on muuttunut ja suoritetaan heapify
     * siitä pisteestä ylöspäin.
     * 
     * @param p Piste jonka dist-arvo on muuttunut
     */
    public void decKey(Piste p){
        for (int i = 0; i < pisteidenMaara; i++){
            if (taulukko[i].equals(p)){
                heapifyYlospain(i);
            }
        }
    }
 
    /** 
     * Apumetodi jolla vaihdetaan kahden pisteen paikkaa keossa 
     * 
     * @param pienin Indeksi pisteen lapsista siihen jolla on pienin dist-arvo
     * @param index Indeksi pisteeseen jonka paikkaa vaihdetaan pienimmän lapsensa kanssa
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
        for (int i = 0; i < pisteidenMaara; i++){
            System.out.print(taulukko[i].getX() + "," + taulukko[i].getY() + "..");
        }
    }

    /**
     * Palauttaa pisteiden määrän keossa tällä hetkellä
     *
     * @return pisteiden määrä
     */
    public int getPisteidenMaara() {
        return pisteidenMaara;
    }

    /**
     * Palauttaa minimikekoon liittyvän Piste-taulukon
     *
     * @return kekoon liittyvä taulukko
     */
    public Piste[] getTaulukko() {
        return taulukko;
    }
    
    
 
}
