package dubois.airbnb.reservations;

import dubois.airbnb.logements.Logement;
import dubois.airbnb.outils.MaDate;

import java.text.ParseException;


public class SejourCourt extends Sejour{

    public SejourCourt(MaDate pDateArrivee, int pNbNuits, Logement pLogement, int pNbVoyageurs) {
        super(pDateArrivee, pNbNuits, pLogement, pNbVoyageurs);
        mText = createTextToDisplay();
    }

    @Override
    protected String createTextToDisplay(){
        return "La date d'arrivée est le "+ mDateArrivee.toString() +" pour "+mNbNuits+ " nuits.\nLe prix de ce séjour est de "+ mTarif+"€.";
    }
    @Override
    public boolean verificationNombreDeNuits() {
        return mNbNuits >= 1 && mNbNuits < 6;
    }

    @Override
    public void miseAJourDuTarif(){
        mTarif = mNbNuits * mLogement.getTarifParNuit();
    }

    @Override
    public void afficher() {
        mLogement.afficher();
        System.out.println(mText);
    }
    @Override
    public Object clone() {
        SejourCourt s = null;
        s = (SejourCourt) super.clone();
        return s;
    }
}
