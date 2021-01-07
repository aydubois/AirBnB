package dubois.airbnb.reservations;

import dubois.airbnb.logements.Logement;
import dubois.airbnb.outils.MaDate;

public class SejourLong extends Sejour{
    private final int PROMOTION_EN_POURCENTAGE = 20;
    private float mPromotion;

    public SejourLong(MaDate pDateArrivee, int pNbNuits, Logement pLogement, int pNbVoyageurs){
        super(pDateArrivee, pNbNuits, pLogement, pNbVoyageurs);
    }


    @Override
    public boolean verificationNombreDeNuits() {
        return mNbNuits >= 6 && mNbNuits < 32 ? true : false;
    }
    @Override
    public void miseAJourDuTarif(){
        mPromotion = mNbNuits * mLogement.getTarifParNuit()*PROMOTION_EN_POURCENTAGE/100;
        mTarif =(mNbNuits * mLogement.getTarifParNuit() - mPromotion);
    }


    @Override
    public void afficher() {
        mLogement.afficher();
        mText = "La date d'arrivée est le "+ mDateArrivee.toString() +" pour "+mNbNuits+ " nuits.";
        mText = mText+ "\nLe prix de ce séjour est de "+ mTarif +"€ ("+mPromotion+"€ de promotion).";
        System.out.println(mText);
    }
}
