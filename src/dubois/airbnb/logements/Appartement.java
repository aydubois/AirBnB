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
        String etage = mNumeroEtage+"ème étage.";
        if(mNumeroEtage == 0){ etage = "rez-de-chaussee.";}
        else if(mNumeroEtage == 1){ etage = "1er étage.";}
        mText = "Le logement est un appartement situé "+mAdresse+" au "+etage+"\nSuperficie : "+mSuperficie+"m2 \n";
        if(mSuperficieBalcon > 0){
            mText = mText + "Balcon : Oui ("+mSuperficieBalcon+"m2)";
        }else{
            mText = mText + "Balcon : Non";
        }
        System.out.println(mText);
    }

    @Override
    public int getIdentifiant() {
        return mIdentifiant;
    }
}
