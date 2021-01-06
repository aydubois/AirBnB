package dubois.airbnb.logements;

import dubois.airbnb.utilisateurs.Hote;

public abstract class Logement {
    protected Hote mHote;
    private int mTarifParNuit;
    protected String mAdresse;
    protected int mSuperficie;
    private int mNbVoyageursMax;

    public Logement(Hote pHote, int pTarifParNuit, String pAdresse, int pSuperficie, int pNbVoyageursMax){
        mHote = pHote;
        mTarifParNuit = pTarifParNuit;
        mAdresse = pAdresse;
        mSuperficie = pSuperficie;
        mNbVoyageursMax = pNbVoyageursMax;
    }

    public int getTarifParNuit() {
        return mTarifParNuit;
    }
    public abstract void afficher();
}
