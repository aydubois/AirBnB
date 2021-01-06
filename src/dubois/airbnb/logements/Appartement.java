package dubois.airbnb.logements;

import dubois.airbnb.utilisateurs.Hote;

public class Appartement extends Logement{
    private int mNumeroEtage;
    private int mSuperficieBalcon;

    public Appartement(Hote pHote, int pTarifParNuit, String pAdresse, int pSuperficie, int pNbVoyageursMax, int pSuperficieBalcon,int pNumeroEtage){
        super(pHote,pTarifParNuit,pAdresse,pSuperficie,pNbVoyageursMax);
        mNumeroEtage = pNumeroEtage;
        mSuperficieBalcon = pSuperficieBalcon;
    }
    @Override
    public void afficher() {
        mHote.afficher();
        System.out.print("Le logement est une maison situé "+mAdresse+" au ");
        String etage = mNumeroEtage+"ème étage.";
        if(mNumeroEtage == 0){ etage = "rez-de-chaussee.";}
        else if(mNumeroEtage == 1){ etage = "1er étage.";}
        System.out.println(etage);
        System.out.println("Superficie : "+mSuperficie+"m2");
        if(mSuperficieBalcon > 0){
            System.out.println("Balcon : Oui ("+mSuperficieBalcon+"m2)");
        }else{
            System.out.println("Balcon : Non");
        }
    }
}
