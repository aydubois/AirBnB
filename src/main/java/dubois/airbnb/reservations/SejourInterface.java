package dubois.airbnb.reservations;

import java.text.ParseException;

public interface SejourInterface {
    public boolean verificationDateArrivee() throws ParseException;
    public boolean verificationNombreDeNuits();
    public boolean verificationNombreDeVoyageurs();
    public void afficher();
}
