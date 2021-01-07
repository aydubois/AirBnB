package dubois.airbnb.reservations;

import dubois.airbnb.logements.Logement;
import dubois.airbnb.outils.MaDate;


public class SejourCourt extends Sejour{

    public SejourCourt(MaDate pDateArrivee, int pNbNuits, Logement pLogement, int pNbVoyageurs){
        super(pDateArrivee, pNbNuits, pLogement, pNbVoyageurs);
    }


    @Override
    public boolean verificationNombreDeNuits() {
        return mNbNuits >= 1 && mNbNuits < 6 ? true : false;
    }
    @Override
    public void miseAJourDuTarif(){
        mTarif = mNbNuits * mLogement.getTarifParNuit();
    }

    @Override
    public void afficher() {
        mLogement.afficher();
        mText = "La date d'arrivée est le "+ mDateArrivee.toString() +" pour "+mNbNuits+ " nuits.\nLe prix de ce séjour est de "+ mTarif+"€.";
        System.out.println(mText);
    }
}
