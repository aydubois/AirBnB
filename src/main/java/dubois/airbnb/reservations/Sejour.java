package dubois.airbnb.reservations;

import dubois.airbnb.logements.Logement;
import dubois.airbnb.outils.MaDate;

import java.text.ParseException;

public abstract class Sejour implements SejourInterface, Cloneable{
    protected MaDate mDateArrivee;
    protected int mNbNuits;
    protected Logement mLogement;
    protected int mNbVoyageurs;
    protected String mText;
    protected int mTarif;

    public Sejour(MaDate pDateArrivee,int pNbNuits, Logement pLogement, int pNbVoyageurs){
        mDateArrivee = (MaDate)pDateArrivee.clone();
        mNbNuits = pNbNuits;
        //no need clone because immutable
        mLogement = pLogement;
        mNbVoyageurs = pNbVoyageurs;
        miseAJourDuTarif();
    }
    @Override
    public boolean verificationDateArrivee() throws ParseException{
        return new MaDate(mDateArrivee.toString()).after(new MaDate());
    }
    @Override
    public boolean verificationNombreDeVoyageurs() {
        return mLogement.getNbVoyageursMax() >= mNbVoyageurs && mNbVoyageurs > 0;
    }
    protected abstract String createTextToDisplay();
    public abstract void afficher();
    public abstract void miseAJourDuTarif();
    public Logement getLogement(){
        return mLogement;
    }
    public MaDate getDateArrivee(){
        return (MaDate)mDateArrivee.clone();
    }
    public int getNbNuits(){
        return mNbNuits;
    }
    public int getNbVoyageurs(){
        return mNbVoyageurs;
    }
    public Object clone() {
        Sejour h = null;
        try {
            h = (Sejour) super.clone();

        } catch (CloneNotSupportedException e) {
            System.out.println("C'est pas censé arriver mais le clonage de Séjour à planté ...");
        }
        return h;
    }

    public void setDateArrivee(MaDate mDateArrivee) throws Exception{
        MaDate dateArriveeActuelle = this.mDateArrivee;
        this.mDateArrivee = (MaDate) mDateArrivee.clone();
        if(!verificationDateArrivee()){
            this.mDateArrivee = dateArriveeActuelle;
            throw new IllegalArgumentException("La date saisie est inférieure à la date du jour.");
        }
        mText = createTextToDisplay();
    }

    public void setNbNuits(int mNbNuits) {
        this.mNbNuits = mNbNuits;
        mText = createTextToDisplay();

    }

    public void setLogement(Logement mLogement)throws IllegalArgumentException {
        Logement logementActuel = this.mLogement;
        this.mLogement = mLogement;
        if(!verificationNombreDeVoyageurs()){
            this.mLogement = logementActuel;
            throw new IllegalArgumentException("Le nombre de voyageurs maximum de ce logement est inférieur au nombre de voyageurs pour ce séjour.");
        }
        miseAJourDuTarif();
        mText = createTextToDisplay();

    }

    public void setNbVoyageurs(int mNbVoyageurs) {
        this.mNbVoyageurs = mNbVoyageurs;
        mText = createTextToDisplay();

    }

}
