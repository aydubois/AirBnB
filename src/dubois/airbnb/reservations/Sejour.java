package dubois.airbnb.reservations;

import dubois.airbnb.logements.Logement;
import dubois.airbnb.outils.MaDate;
import java.util.Date;

public abstract class Sejour{
    protected MaDate mDateArrivee;
    protected int mNbNuits;
    protected Logement mLogement;
    protected int mNbVoyageurs;
    protected String mText;

    public Sejour(MaDate pDateArrivee,int pNbNuits, Logement pLogement, int pNbVoyageurs){
        mDateArrivee = pDateArrivee;
        mNbNuits = pNbNuits;
        mLogement = pLogement;
        mNbVoyageurs = pNbVoyageurs;
    }

    public abstract void afficher();
}
