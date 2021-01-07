package dubois.airbnb.reservations;

import dubois.airbnb.logements.Logement;
import dubois.airbnb.outils.MaDate;

import java.util.Date;

public class SejourLong extends Sejour implements SejourInterface{
    private final int PROMOTION_EN_POURCENTAGE = 20;
    private int mPromotion;
    private int mTarif;

    public SejourLong(MaDate pDateArrivee, int pNbNuits, Logement pLogement, int pNbVoyageurs){
        super(pDateArrivee, pNbNuits, pLogement, pNbVoyageurs);
        mPromotion = mNbNuits * mLogement.getTarifParNuit()*(PROMOTION_EN_POURCENTAGE/100);
        mTarif = mNbNuits * mLogement.getTarifParNuit() - mPromotion;
    }
    @Override
    public boolean verificationDateArrivee() {
        return mDateArrivee.compareTo(new Date()) > 0 ? true : false;
    }

    @Override
    public boolean verificationNombreDeNuits() {
        return mNbNuits >= 6 && mNbNuits < 32 ? true : false;
    }

    @Override
    public boolean verificationNombreDeVoyageurs() {
        return mLogement.getNbVoyageursMax() >= mNbVoyageurs ? true : false;
    }
    @Override
    public void afficher() {
        mLogement.afficher();
        mText = "La date d'arrivée est le "+ mDateArrivee.toString() +" pour "+mNbNuits+ " nuits.";
        mText = mText+ "\nLe prix de ce séjour est de "+ mTarif+"€ ("+mPromotion+"€ de promotion).";
        System.out.println(mText);
    }
}
