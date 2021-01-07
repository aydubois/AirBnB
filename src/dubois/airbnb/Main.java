package dubois.airbnb;

import dubois.airbnb.logements.Maison;
import dubois.airbnb.outils.MaDate;
import dubois.airbnb.reservations.Reservation;
import dubois.airbnb.reservations.Sejour;
import dubois.airbnb.utilisateurs.Hote;
import dubois.airbnb.utilisateurs.Voyageur;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        JeuDeTest jdt = new JeuDeTest();

        //MaDate dateChoisie = new MaDate("dd/MM/yyyy","01/07/2021" );
        //MaDate dateReserv = new MaDate("dd/MM/yyyy","01/01/2021");

        //Hote ponyo = new Hote("Ponyo", "Poppy", 28, 10);
        //Voyageur noman = new Voyageur("Noman", "Sky", 39);
        //Maison log1 = new Maison(ponyo, 100, "10 rue des marguerites, 37000 Tours", 150, 5, false, 500);
        //Sejour sejour1 = new Sejour(dateChoisie, 10, log1, 3);
        //Reservation reserv1 = new Reservation(sejour1, noman, dateReserv);
        //log1.afficher();
        //sejour1.afficher();
        //reserv1.afficher();
        Reservation reserv2 = jdt.getReservation();
        reserv2.afficher();
    }
}
