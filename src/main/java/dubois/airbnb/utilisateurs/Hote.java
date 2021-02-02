package dubois.airbnb.utilisateurs;

import dubois.airbnb.outils.Compare;

public class Hote extends Personne{
    private int mDelaiDeReponse;

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
}
