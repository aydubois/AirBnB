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
    protected int mTarif;

    public Sejour(MaDate pDateArrivee,int pNbNuits, Logement pLogement, int pNbVoyageurs){
        mDateArrivee = pDateArrivee;
        mNbNuits = pNbNuits;
        mLogement = pLogement;
        mNbVoyageurs = pNbVoyageurs;
        miseAJourDuTarif();
    }
    @Override
    public boolean verificationDateArrivee() {
        return mDateArrivee.after(new Date());
    }
    @Override
    public boolean verificationNombreDeVoyageurs() {
        return mLogement.getNbVoyageursMax() >= mNbVoyageurs && mNbVoyageurs  > 0 ? true : false;
    }

    public abstract void afficher();
    public abstract void miseAJourDuTarif();
    public Logement getLogement(){
        return mLogement;
    }
    public MaDate getDateArrivee(){
        return mDateArrivee;
    }
    public int getNbNuits(){
        return mNbNuits;
    }
    public int getNbVoyageurs(){
        return mNbVoyageurs;
    }
}
