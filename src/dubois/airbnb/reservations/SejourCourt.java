package dubois.airbnb.reservations;

import dubois.airbnb.logements.Logement;
import dubois.airbnb.outils.MaDate;

import java.util.Date;

public class SejourCourt extends Sejour implements SejourInterface{
    private int mTarif;

    public SejourCourt(MaDate pDateArrivee, int pNbNuits, Logement pLogement, int pNbVoyageurs){
        super(pDateArrivee, pNbNuits, pLogement, pNbVoyageurs);
        mTarif =mNbNuits * mLogement.getTarifParNuit();
    }
    @Override
    public boolean verificationDateArrivee() {
        return mDateArrivee.compareTo(new Date()) > 0 ? true : false;
    }

    @Override
    public boolean verificationNombreDeNuits() {
        return mNbNuits >= 1 && mNbNuits < 6 ? true : false;
    }

    @Override
    public boolean verificationNombreDeVoyageurs() {
        return mLogement.getNbVoyageursMax() >= mNbVoyageurs ? true : false;
    }
    @Override
    public void afficher() {
        mLogement.afficher();
        mText = "La date d'arrivée est le "+ mDateArrivee.toString() +" pour "+mNbNuits+ " nuits.\nLe prix de ce séjour est de "+ mTarif+"€.";
        System.out.println(mText);
    }
}
