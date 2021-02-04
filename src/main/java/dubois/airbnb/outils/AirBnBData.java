package dubois.airbnb.outils;

import dubois.airbnb.JeuDeTest;
import dubois.airbnb.logements.Logement;
import dubois.airbnb.utilisateurs.Hote;
import dubois.airbnb.utilisateurs.Voyageur;

import java.util.ArrayList;

public class AirBnBData {
        private static volatile AirBnBData instance = null;
        private ArrayList<Hote> listHotes = new ArrayList<>();
        private ArrayList<Logement> listLogements = new ArrayList<>();
        private ArrayList<Voyageur> listVoyageurs = new ArrayList<>();


        private AirBnBData() {
            for (int i = 0; i < 5; i++) {
                JeuDeTest jdt = new JeuDeTest();
                listHotes.add(jdt.getHote());
                listLogements.add(i / 2 != 0 ? jdt.getAppartement() : jdt.getMaison());
                listVoyageurs.add(jdt.getVoyageur());
            }

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
}
