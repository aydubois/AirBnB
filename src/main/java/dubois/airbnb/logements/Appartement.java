package dubois.airbnb.logements;

import dubois.airbnb.utilisateurs.Hote;

public class Appartement extends Logement implements Cloneable{
    private final int mNumeroEtage;
    private final int mSuperficieBalcon;
    private final String mText;

    /**
     * Constructor
     * @param pHote : (Hote)
     * @param pTarifParNuit (int) in euros
     * @param pAdresse (String) full address (N°, street, postcode, city)
     * @param pSuperficie (int) in square meters
     * @param pNbVoyageursMax (int) maximum number of travellers who can come to the accommodation on the same booking
     * @param pSuperficieBalcon (int) in square meters - if no balcony put 0
     * @param pNumeroEtage (int) if the flat is on the ground floor put 0
     * Automatic creation of the name of the accommodation (String) mName : Logmt_nameHote_idLogement
     */
    public Appartement(Hote pHote, int pTarifParNuit, String pAdresse, int pSuperficie, int pNbVoyageursMax, int pSuperficieBalcon,int pNumeroEtage){
        super(pHote,pTarifParNuit,pAdresse,pSuperficie,pNbVoyageursMax);
        mNumeroEtage = pNumeroEtage;
        mSuperficieBalcon = pSuperficieBalcon;
        mText = createTextToDisplay();
    }

    /**
     * Creation of the text to be displayed (String) mText
     * Method called from the constructor
     * @return (String)
     */
    @Override
    protected String createTextToDisplay() {
        String etage = mNumeroEtage+"ème étage.";
        if(mNumeroEtage == 0){ etage = "rez-de-chaussee.";}
        else if(mNumeroEtage == 1){ etage = "1er étage.";}
        String mTexta = "Le logement est un appartement situé "+mAdresse+" au "+etage+"\nSuperficie : "+mSuperficie+"m2 \n";
        if(mSuperficieBalcon > 0){
            mTexta = mTexta + "Balcon : Oui ("+mSuperficieBalcon+"m2)";
        }else{
            mTexta = mTexta + "Balcon : Non";
        }
        return mTexta;
    }
    /**
     * Displays an explanatory text on housing
     */
    public void afficher(){
        mHote.afficher();
        System.out.println(mText);
    }
    public boolean isPossedeBalson(){
        return mSuperficieBalcon > 0;
    }
}
