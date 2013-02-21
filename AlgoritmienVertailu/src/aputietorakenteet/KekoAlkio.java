
package aputietorakenteet;

import algoritmienvertailu.AstarPiste;
import algoritmienvertailu.Piste;

/**
 * Luokka joka kuvaa yhtä kekoon lisättävää alkiota.
 */
public class KekoAlkio {
    
    private Object object;
    private int arvo;
    
    public KekoAlkio(Object object, int arvo){
        this.object = object;
        this.arvo = arvo;
    }
    
    public Piste palautaPiste(){
        return (Piste) this.object;
    }
    
    public AstarPiste palautaAstarPiste(){
        return (AstarPiste) this.object;
    }    

    public int getArvo() {
        return arvo;
    }

    public Object getObject() {
        return object;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }

    public void setObject(Object object) {
        this.object = object;
    }
    
}
