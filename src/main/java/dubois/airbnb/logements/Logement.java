package dubois.airbnb.logements;

import dubois.airbnb.utilisateurs.Hote;

public abstract class Logement  implements Comparable<Logement> {
    private static int id = 0;
    protected final int mIdentifiant;
    private final String mName;
    protected final Hote mHote;
    private final int mTarifParNuit;
    protected final String mAdresse;
    protected final int mSuperficie;
    private final int mNbVoyageursMax;

    /**
     * Constructor
     * @param pHote - final (Hote)
     * @param pTarifParNuit - final (int) in euros
     * @param pAdresse - final (String) full address (N°, street, postcode, city)
     * @param pSuperficie - final (int) in square meters
     * @param pNbVoyageursMax - final (int) maximum number of travellers who can come to the accommodation on the same booking
     * Automatic creation of the name of the accommodation (String) mName : Logmt_nameHote_idLogement
     */
    public Logement(Hote pHote, int pTarifParNuit, String pAdresse, int pSuperficie, int pNbVoyageursMax){
        mIdentifiant = ++id;
        mHote = (Hote)pHote.clone(); //Avoids unwanted changes outside the class
        mTarifParNuit = pTarifParNuit;
        mAdresse = pAdresse;
        mSuperficie = pSuperficie;
        mNbVoyageursMax = pNbVoyageursMax;
        mName = "Logmt_"+getNameHote()+"_"+mIdentifiant;
    }

    /**
     * Creation of the text to be displayed (String) mText
     * Method called from the constructor
     * @return (String)
     */
    protected abstract String createTextToDisplay();

    /**
     * Displays an explanatory text on housing
     */
    public abstract void afficher();

    /**
     * @return (String) hostName
     */
    public String getNameHote(){
        return mHote.getNom();
    }

    /**
     * @return (int) priceForOneNight - in euros
     */
    public int getTarifParNuit() {
        return mTarifParNuit;
    }

    /**
     * @return (int) maximumNumberOfTravellers
     */
    public int getNbVoyageursMax() {
        return mNbVoyageursMax;
    }

    /**
     * @return (int) surface - in square meters
     */
    public int getSuperficie() { return mSuperficie; }

    /**
     * @return (String) address
     */
    public String getAdresse() {return mAdresse;}

    /**
     * @return (int) id
     */
    public int getIdentifiant(){return mIdentifiant; }

    /**
     * @return (String)
     */
    public String getName(){
        return mName;
    }

    /**
     * @param logement : (Logement) housing that you want to compare with the current housing.
     * Comparison made on the price for one night.
     * @return (int) result of :  this.tarif - logement.tarif
     */
    @Override
    public int compareTo(Logement logement) {
        return mTarifParNuit - logement.mTarifParNuit ;
    }

}
