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

    /**
     * @param pDateArrivee (MaDate) - date of arrival in the housing
     * @param pNbNuits (int)
     * @param pLogement (Logement)
     * @param pNbVoyageurs (int)
     *
     */
    public Sejour(MaDate pDateArrivee,int pNbNuits, Logement pLogement, int pNbVoyageurs){
        mDateArrivee = (MaDate)pDateArrivee.clone();
        mNbNuits = pNbNuits;
        //no need clone because immutable
        mLogement = pLogement;
        mNbVoyageurs = pNbVoyageurs;
        miseAJourDuTarif();
    }

    /**
     * @return (boolean) date of arrival > current date ? false : true
     */
    @Override
    public boolean verificationDateArrivee(){
        return ((MaDate) mDateArrivee.clone()).after(new MaDate());
    }

    /**
     * @return (boolean) mLogement.getNbVoyageursMax() >= mNbVoyageurs && mNbVoyageurs > 0 ? true : false
     */
    @Override
    public boolean verificationNombreDeVoyageurs() {
        return mLogement.getNbVoyageursMax() >= mNbVoyageurs && mNbVoyageurs > 0;
    }

    /**
     * Creation of the text to be displayed (String) mText
     * Method called from the constructor
     * @return (String)
     */
    protected abstract String createTextToDisplay();

    /**
     * Displays an explanatory text on sejour
     */
    public abstract void afficher();

    /**
     * update price depending on if there is a promotion or not
     */
    public abstract void miseAJourDuTarif();

    /**
     * @return (Logement)
     */
    public Logement getLogement(){
        return mLogement;
    }

    /**
     * @return (MaDate) date of arrival in the housing
     */
    public MaDate getDateArrivee(){
        return (MaDate)mDateArrivee.clone();
    }

    /**
     * @return (int) number of nights
     */
    public int getNbNuits(){
        return mNbNuits;
    }

    /**
     * @return (int) number of travellers
     */
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

    /**
     * @param mDateArrivee (MaDate)
     * @throws IllegalArgumentException if mDateArrivee < current date
     */
    public void setDateArrivee(MaDate mDateArrivee) throws IllegalArgumentException{
        MaDate dateArriveeActuelle = this.mDateArrivee;
        this.mDateArrivee = (MaDate) mDateArrivee.clone();
        if(!verificationDateArrivee()){
            this.mDateArrivee = dateArriveeActuelle;
            throw new IllegalArgumentException("La date saisie est inférieure à la date du jour.");
        }
        mText = createTextToDisplay();
    }

    /**
     * @param mNbNuits (int)
     */
    public void setNbNuits(int mNbNuits) {
        this.mNbNuits = mNbNuits;
        mText = createTextToDisplay();

    }

    /**
     * @param mLogement (Logement)
     * @throws IllegalArgumentException if mLogement.nbMaxVoyageurs < this.nbVoyageurs
     */
    public void setLogement(Logement mLogement) throws IllegalArgumentException {
        Logement logementActuel = this.mLogement;
        this.mLogement = mLogement;
        if(!verificationNombreDeVoyageurs()){
            this.mLogement = logementActuel;
            throw new IllegalArgumentException("Le nombre de voyageurs maximum de ce logement est inférieur au nombre de voyageurs pour ce séjour.");
        }
        miseAJourDuTarif();
        mText = createTextToDisplay();

    }

    /**
     * @param mNbVoyageurs (int)
     * @throws IllegalArgumentException if this.logement.nbMaxVoyageurs < this.nbVoyageurs
     */
    public void setNbVoyageurs(int mNbVoyageurs) throws IllegalArgumentException{
        int nbVoyageursActuel = this.mNbVoyageurs;
        this.mNbVoyageurs = mNbVoyageurs;
        if(!verificationNombreDeVoyageurs()){
            this.mNbVoyageurs = nbVoyageursActuel;
            throw new IllegalArgumentException("Le nombre de voyageurs souhaité est trop important pour ce logement");
        }
        mText = createTextToDisplay();
    }

}
