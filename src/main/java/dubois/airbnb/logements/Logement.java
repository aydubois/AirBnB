package dubois.airbnb.logements;

import dubois.airbnb.outils.Compare;
import dubois.airbnb.utilisateurs.Hote;
import dubois.airbnb.utilisateurs.Personne;

public abstract class Logement  implements Comparable<Logement> {
    private static int id = 0;
    protected int mIdentifiant;
    private String mName;
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
        mName = "";

    }
    public String getNameHote(){return mHote.getNom();}
    public int getTarifParNuit() {
        return mTarifParNuit;
    }
    public int getNbVoyageursMax() {
        return mNbVoyageursMax;
    }
    public int getSuperficie() {
        return mSuperficie;
    }
    public String getAdresse() {
        return mAdresse;
    }
    public abstract void afficher();
    public abstract int getIdentifiant();
    public void setName(String pName){
        mName = pName;
    }
    public String getName(){
        return mName;
    }

    @Override
    public int compareTo(Logement pers) {
        return mTarifParNuit - pers.mTarifParNuit ;
    }
}
