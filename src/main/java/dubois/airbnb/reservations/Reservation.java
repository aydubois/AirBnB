package dubois.airbnb.reservations;

import dubois.airbnb.outils.MaDate;
import dubois.airbnb.utilisateurs.Voyageur;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class Reservation {
    private static int id = 0;
    private final int mIdentifiant;
    private final Sejour mSejour;
    private final Voyageur mVoyageur;
    private  boolean mEstValidee;
    private  MaDate mDateDeReservation;

    public Reservation(Sejour pSejour,Voyageur pVoyageur, Date pDateDeReservation ) throws Exception{
        if(pSejour == null || pVoyageur == null){
            throw new NullPointerException("Le séjour et le voyageur doivent être instanciés.");
        }
        mSejour = (Sejour)pSejour.clone();
        mVoyageur = (Voyageur) pVoyageur.clone();
        mDateDeReservation = (MaDate) pDateDeReservation.clone();
        mIdentifiant = ++id;
        try{
            mEstValidee = estValide();
        }catch(Exception e){
            throw new Exception("La date d'arrivée du séjour est erronée ou le nombre de voyageurs est incorrect.");
        }
        creerFichierReservation();
    }

    public void afficher(){
        mVoyageur.afficher();
        System.out.print(" a fait une réservation chez ");
        mSejour.afficher();
    }

    private boolean estValide() throws Exception {
         return mSejour.verificationDateArrivee() && mSejour.verificationNombreDeVoyageurs() ;
        // TODO : rajour vérif. un seul sejour par logement pour une même date
    }
    private void creerFichierReservation(){
        new File("fichiers/reservations/"+mIdentifiant+"_reservation.txt");
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
    public Sejour getSejour(){
        return (Sejour) mSejour.clone();
    }
    public void setEstValidee(boolean mEstValidee) {
        try{
            if(mEstValidee == estValide()){
                this.mEstValidee = mEstValidee;
            }
        }catch (Exception e){
            //TODO
        }
    }

    public void setDateDeReservation(MaDate mDateDeReservation) {
        this.mDateDeReservation = (MaDate) mDateDeReservation.clone();
    }
}
