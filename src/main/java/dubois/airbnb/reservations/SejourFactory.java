package dubois.airbnb.reservations;

import dubois.airbnb.logements.Logement;
import dubois.airbnb.outils.MaDate;

public class SejourFactory {
    /**
     *
     * @param dateArrivee (MaDate)
     * @param logement (Logement)
     * @param nbNuits (int)
     * @param nbVoyageurs (int)
     * @return number of nights < 6 ? (SejourCourt) : (SejourLong)
     */
    public static Sejour getSejour(MaDate dateArrivee, Logement logement, int nbNuits, int nbVoyageurs){
            return (nbNuits < 6 ) ? new SejourCourt(dateArrivee, nbNuits, logement, nbVoyageurs) : new SejourLong(dateArrivee, nbNuits, logement, nbVoyageurs);
    }
}
