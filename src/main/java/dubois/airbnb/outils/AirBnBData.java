package dubois.airbnb.outils;

import dubois.airbnb.logements.Logement;
import dubois.airbnb.reservations.Reservation;
import dubois.airbnb.reservations.Sejour;
import dubois.airbnb.utilisateurs.Hote;
import dubois.airbnb.utilisateurs.Voyageur;

import java.util.ArrayList;

public class AirBnBData {
        private static volatile AirBnBData instance = null;
        private ArrayList<Hote> listHotes = new ArrayList<>();
        private ArrayList<Logement> listLogements = new ArrayList<>();
        private ArrayList<Voyageur> listVoyageurs = new ArrayList<>();
        private ArrayList<Sejour> listSejours = new ArrayList<>();
        private ArrayList<Reservation> listReservations = new ArrayList<>();


        private AirBnBData() {
                ParseXml parseXml = new ParseXml.ParseBuilder("fichiers/hotes/hotes.xml", "fichiers/voyageurs/voyageurs.xml", "fichiers/logements/logements.xml")
                        .addXmlSejour("fichiers/sejours/sejours.xml").addXmlReservation("fichiers/reservations/reservations.xml").build();
                listHotes.addAll(parseXml.getHotes());
                listVoyageurs.addAll(parseXml.getVoyageurs());
                listLogements.addAll(parseXml.getLogements());
                listSejours.addAll(parseXml.getSejours());
                listReservations.addAll(parseXml.getReservations());

        }

        public static AirBnBData getInstance() {
            if (instance == null) {
                synchronized (AirBnBData.class) {
                    instance = new AirBnBData();
                }
            }
            return instance;
        }

    /**
     * Jeu de test (Hote)
     * @return (ArrayList<Hote>)
     */
    public ArrayList<Hote> getListHotes() {
        return listHotes;
    }

    /**
     * Jeu de test (Logement)
     * @return (ArrayList<Logement>)
     */
    public ArrayList<Logement> getListLogements() {
        return listLogements;
    }

    /**
     * Jeu de test (Voyageur)
     * @return (ArrayList<Voyageur>)
     */
    public ArrayList<Voyageur> getListVoyageurs() {
        return listVoyageurs;
    }

    /**
     * Jeu de test (Sejour)
     * @return (ArrayList<Sejour>)
     */
    public ArrayList<Sejour> getListSejour() {
        return listSejours;
    }

    /**
     * Jeu de test (Reservation)
     * @return (ArrayList<Reservation>)
     */
    public ArrayList<Reservation> getListReservations() {
        return listReservations;
    }
}
