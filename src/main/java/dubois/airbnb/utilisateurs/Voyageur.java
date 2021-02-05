package dubois.airbnb.utilisateurs;

public class Voyageur extends Personne implements Cloneable{

    /**
     * @param pPrenom - final (String)
     * @param pNom - final (String)
     * @param pAge - final (int)
     */
    public Voyageur(String pPrenom, String pNom, int pAge){
        super( pPrenom, pNom, pAge);
    }


    public Object clone() {
        Voyageur h = null;
        try {
            h = (Voyageur) super.clone();

        } catch (CloneNotSupportedException e) {} // Won't happen
        return h;
    }
}
