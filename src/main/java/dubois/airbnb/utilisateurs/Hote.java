package dubois.airbnb.utilisateurs;

public class Hote extends Personne implements Cloneable{
    private final int mDelaiDeReponse;

    public Hote(String pPrenom, String pNom, int pAge, int pDelaiDeReponse){
        super( pPrenom, pNom, pAge);
        mDelaiDeReponse = pDelaiDeReponse;
    }

    @Override
    public void afficher() {
        super.afficher();
        if (mDelaiDeReponse == 1){
            System.out.println("qui s'engage à répondre dans l'heure. ");
        }else {
            System.out.println("qui s'engage à répondre dans les "+mDelaiDeReponse+" heures. ");
        }
    }

    public int getDelaiDeReponse() {
        return mDelaiDeReponse;
    }

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
