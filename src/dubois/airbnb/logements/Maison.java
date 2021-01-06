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
        System.out.println("Le logement est une maison situé "+mAdresse+".");
        System.out.println("Superficie : "+mSuperficie+"m2");
        if(mSuperficieJardin > 0){
            System.out.println("Jardin : Oui ("+mSuperficieJardin+"m2)");
        }else{
            System.out.println("Jardin : Non");
        }
        if(mPossedePiscine){
            System.out.println("Piscine : Oui");
        }else{
            System.out.println("Piscine : Non");
        }
    }
}
