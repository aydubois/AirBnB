package dubois.airbnb.logements;

import dubois.airbnb.utilisateurs.Hote;

public abstract class Logement {
    private static int id = 0;
    protected int mIdentifiant;
    protected Hote mHote;
    private int mTarifParNuit;
    protected String mAdresse;
    protected int mSuperficie;
    private int mNbVoyageursMax;
    protected String mText;

    public Logement(Hote pHote, int pTarifParNuit, String pAdresse, int pSuperficie, int pNbVoyageursMax){
        mHote = pHote;
        mTarifParNuit = pTarifParNuit;
        mAdresse = pAdresse;
        mSuperficie = pSuperficie;
        mNbVoyageursMax = pNbVoyageursMax;
        mIdentifiant = ++id;

    }

    public int getTarifParNuit() {
        return mTarifParNuit;
    }
    public int getNbVoyageursMax() {
        return mNbVoyageursMax;
    }
    public int getmSuperficie() {
        return mSuperficie;
    }
    public String getAdresse() {
        return mAdresse;
    }
    public abstract void afficher();
    public abstract int getIdentifiant();
}
