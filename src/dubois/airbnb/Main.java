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
        JeuDeTest jdt2 = new JeuDeTest("maison","long");

        Reservation reserv1 = jdt.getReservation();
        Reservation reserv2 = jdt2.getReservation();

        reserv1.afficher();
        System.out.println("------------------------");
        System.out.println("------------------------");
        reserv2.afficher();
    }
}
