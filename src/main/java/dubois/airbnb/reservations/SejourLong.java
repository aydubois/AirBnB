package dubois.airbnb.reservations;

import dubois.airbnb.logements.Logement;
import dubois.airbnb.outils.MaDate;

import java.text.ParseException;

public class SejourLong extends Sejour{
    private final int PROMOTION_EN_POURCENTAGE = 20;
    private int mPromotion;
    public SejourLong(MaDate pDateArrivee, int pNbNuits, Logement pLogement, int pNbVoyageurs) {
        super(pDateArrivee, pNbNuits, pLogement, pNbVoyageurs);
        mText = createTextToDisplay();
    }

    @Override
    protected String createTextToDisplay(){
        String mTexta = "La date d'arrivée est le "+ mDateArrivee.toString() +" pour "+mNbNuits+ " nuits.";
        mTexta = mTexta+ "\nLe prix de ce séjour est de "+ mTarif +"€ ("+mPromotion+"€ de promotion).";
        return mTexta;
    }
    @Override
    public boolean verificationNombreDeNuits() {
        return mNbNuits >= 6 && mNbNuits < 32;
    }

    @Override
    public void miseAJourDuTarif(){
        mPromotion = mNbNuits * mLogement.getTarifParNuit()*PROMOTION_EN_POURCENTAGE / 100;
        mTarif =(mNbNuits * mLogement.getTarifParNuit() - mPromotion);
    }


    @Override
    public void afficher() {
        mLogement.afficher();
        System.out.println(mText);
    }
    @Override
    public Object clone() {
        SejourLong h = null;
        h = (SejourLong) super.clone();
        return h;
    }
}
