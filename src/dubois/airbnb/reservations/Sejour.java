package dubois.airbnb.reservations;

import dubois.airbnb.logements.Logement;
import dubois.airbnb.outils.MaDate;
import java.util.Date;

public abstract class Sejour implements SejourInterface{
    protected MaDate mDateArrivee;
    protected int mNbNuits;
    protected Logement mLogement;
    protected int mNbVoyageurs;
    protected String mText;
    protected float mTarif;

    public Sejour(MaDate pDateArrivee,int pNbNuits, Logement pLogement, int pNbVoyageurs){
        mDateArrivee = pDateArrivee;
        mNbNuits = pNbNuits;
        mLogement = pLogement;
        mNbVoyageurs = pNbVoyageurs;
        miseAJourDuTarif();
    }
    @Override
    public boolean verificationDateArrivee() {
        return mDateArrivee.compareTo(new Date()) > 0 ? true : false;
    }
    @Override
    public boolean verificationNombreDeVoyageurs() {
        return mLogement.getNbVoyageursMax() >= mNbVoyageurs ? true : false;
    }

    @Override
    public abstract boolean verificationNombreDeNuits();
    public abstract void afficher();
    public abstract void miseAJourDuTarif();
}
