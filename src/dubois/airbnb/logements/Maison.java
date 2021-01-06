package dubois.airbnb.logements;

import dubois.airbnb.utilisateurs.Hote;

public class Maison extends Logement{
    private int mSuperficieJardin;
    private boolean mPossedePiscine;

    public Maison(Hote pHote, int pTarifParNuit, String pAdresse, int pSuperficie, int pNbVoyageursMax, boolean pPossedePiscine, int pSuperficieJardin){
        super(pHote,pTarifParNuit,pAdresse,pSuperficie,pNbVoyageursMax);
        mSuperficieJardin = pSuperficieJardin;
        mPossedePiscine = pPossedePiscine;
    }

    @Override
    public void afficher() {
        mHote.afficher();
        mText = "Le logement est une maison situé "+mAdresse+".\nSuperficie : "+mSuperficie+"m2 \n";
        if(mSuperficieJardin > 0){
            mText = mText +"Jardin : Oui ("+mSuperficieJardin+"m2)\n";
        }else{
            mText = mText +"Jardin : Non\n";
        }
        if(mPossedePiscine){
            mText = mText +"Piscine : Oui";
        }else{
            mText = mText +"Piscine : Non";
        }
        System.out.println(mText);
    }
}
