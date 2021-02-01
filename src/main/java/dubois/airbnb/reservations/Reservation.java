package dubois.airbnb.reservations;

import dubois.airbnb.menu.Menu;
import dubois.airbnb.utilisateurs.Voyageur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class Reservation {
    private static int id = 0;
    private int mIdentifiant;
    private Sejour mSejour;
    private Voyageur mVoyageur;
    private boolean mEstValidee;
    private Date mDateDeReservation;

    public Reservation(Sejour pSejour,Voyageur pVoyageur, Date pDateDeReservation ) throws Exception{
        mSejour = pSejour;
        mVoyageur = pVoyageur;
        mDateDeReservation = pDateDeReservation;
        mIdentifiant = ++id;
        mEstValidee = estValide();
        if(!mEstValidee){
            throw new Exception("La date d'arrivée du séjour est erronée ou le nombre de voyageurs est incorrect.");
        }
        creerFichierReservation();
    }

    public void afficher(){
        mVoyageur.afficher();
        System.out.print(" a fait une réservation chez ");
        mSejour.afficher();
    }

    private boolean estValide(){
         return mSejour.verificationDateArrivee() && mSejour.verificationNombreDeVoyageurs() ;
        // TODO : rajour vérif. un seul sejour par logement pour une même date
    }
    private void creerFichierReservation(){
        File fichier = new File("fichiers/reservations/"+mIdentifiant+"_reservation.txt");
        try{
            FileWriter fileW = new FileWriter("fichiers/reservations/"+mIdentifiant+"_reservation.txt");
            fileW.write(
            "Numéro du voyageur : "+mVoyageur.getIdentifiant()+"\n"+
                "Numéro du logement : "+mSejour.getLogement().getIdentifiant()+"\n"+
                "Date d'arrivée ("+ mSejour.getDateArrivee().getFormat()+") : "+ mSejour.getDateArrivee().toString()+"\n"+
                "Nombre de nuits : "+ mSejour.getNbNuits()+"\n"+
                "Nombre de personnes : "+mSejour.getNbVoyageurs()
            );
        } catch (IOException e) {
            System.out.println("Oops, le fichier ne veut pas s'écrire.");
            e.printStackTrace();
        }
    }
}