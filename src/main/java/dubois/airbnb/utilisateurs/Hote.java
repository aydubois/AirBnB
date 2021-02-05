package dubois.airbnb.utilisateurs;

public class Hote extends Personne implements Cloneable{
    private final int mDelaiDeReponse;

    /**
     *
     * @param pPrenom - final (String)
     * @param pNom - final (String)
     * @param pAge - final (int)
     * @param pDelaiDeReponse - final (int) in hours
     */
    public Hote(String pPrenom, String pNom, int pAge, int pDelaiDeReponse){
        super( pPrenom, pNom, pAge);
        mDelaiDeReponse = pDelaiDeReponse;
    }

    /**
     * Displays an explanatory text on host
     */
    @Override
    public void afficher() {
        super.afficher();
        if (mDelaiDeReponse == 1){
            System.out.println("qui s'engage à répondre dans l'heure. ");
        }else {
            System.out.println("qui s'engage à répondre dans les "+mDelaiDeReponse+" heures. ");
        }
    }

    /**
     * @return (int) response time in hours
     */
    public int getDelaiDeReponse() {
        return mDelaiDeReponse;
    }

    /**
     * @param o : (Hote) host that you want to compare with the current host.
     * Comparison made on the response time.
     * @return (int) result of :  this.delaiDeReponse - o.delaiDeReponse
     */
    @Override
    public int compareTo(Personne o) {
        Hote pers = (Hote) o;
        return mDelaiDeReponse - pers.mDelaiDeReponse ;
    }

    public Object clone() {
        Hote h = null;
        try {
            h = (Hote)super.clone();

        } catch (CloneNotSupportedException e) {} // Won't happen
        return h;
    }
}
