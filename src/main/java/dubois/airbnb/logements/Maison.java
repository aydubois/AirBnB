package dubois.airbnb.logements;

import dubois.airbnb.utilisateurs.Hote;

public class Maison extends Logement implements Cloneable{
    private final int mSuperficieJardin;
    private final boolean mPossedePiscine;
    protected final String mText;

    /**
     * Constructor
     * @param pHote : (Hote)
     * @param pTarifParNuit (int) in euros
     * @param pAdresse (String) full address (N°, street, postcode, city)
     * @param pSuperficie (int) in square meters
     * @param pNbVoyageursMax (int) maximum number of travellers who can come to the accommodation on the same booking
     * @param pPossedePiscine (boolean)
     * @param pSuperficieJardin (int) in square meters - if no gardin, put 0
     * Automatic creation of the name of the accommodation (String) mName : Logmt_nameHote_idLogement
     */
    public Maison(Hote pHote, int pTarifParNuit, String pAdresse, int pSuperficie, int pNbVoyageursMax, boolean pPossedePiscine, int pSuperficieJardin){
        super(pHote,pTarifParNuit,pAdresse,pSuperficie,pNbVoyageursMax);
        mSuperficieJardin = pSuperficieJardin;
        mPossedePiscine = pPossedePiscine;
        mText = createTextToDisplay();
    }

    /**
     * Creation of the text to be displayed (String) mText
     * Method called from the constructor
     * @return (String)
     */
    @Override
    protected String createTextToDisplay(){
        String mTexta = "Le logement est une maison situé "+mAdresse+".\nSuperficie : "+mSuperficie+"m2 \n";
        if(mSuperficieJardin > 0){
            mTexta = mTexta +"Jardin : Oui ("+mSuperficieJardin+"m2)\n";
        }else{
            mTexta = mTexta +"Jardin : Non\n";
        }
        if(mPossedePiscine){
            mTexta = mTexta +"Piscine : Oui";
        }else{
            mTexta = mTexta +"Piscine : Non";
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

    public boolean isPossedePiscine() {
        return mPossedePiscine;
    }
    public boolean isPossedeJardin() {
        return mSuperficieJardin > 0;
    }
}
