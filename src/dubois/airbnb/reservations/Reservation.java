package dubois.airbnb.reservations;

import dubois.airbnb.utilisateurs.Voyageur;
import java.util.Date;

public class Reservation {

    private int mIdentifiant;
    private Sejour mSejour;
    private Voyageur mVoyageur;
    private boolean mEstValidee;
    private Date mDateDeReservation;

    public Reservation(Sejour pSejour,Voyageur pVoyageur, Date pDateDeReservation ){
        mSejour = pSejour;
        mVoyageur = pVoyageur;
        mDateDeReservation = pDateDeReservation;
    }

    public void afficher(){
        mVoyageur.afficher();
        System.out.print(" a fait une réservation chez ");
        mSejour.afficher();
    }
}
