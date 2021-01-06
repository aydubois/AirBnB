package dubois.airbnb.outils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MaDate extends Date {
    private String mFormat;
    private Date mDate;
    private String mDateString;

    /**
     * @param pFormat  : Format que l'on veut donner à la date
     * G -> Era
     * y -> Année
     * M -> Mois
     * w -> Semaine dans l'année
     * W -> Semaine dans le mois
     * D -> Jour dans l'année
     * d -> jour dans le mois
     * F -> Jour de la semaine dans le mois
     * E -> Jour de la semaine en lettre
     * a -> Marqueur AM/PM
     * H -> Heure (0-23)
     * k -> Heure (1-24)
     * K -> Heure en AM/PM (0-11)
     * h -> Heure en AM/PM (1-12)
     * m -> Minutes
     * s -> Secondes
     * S -> Millisecondes
     * Exemples : "dd/MM/yyyy", "dd MMMMM yyyy GGG, hh:mm aaa","hh:mm a, zzzz"
     *
     * @param pDateString :
     * Date écrite en chaîne de caractères de la même façon que le format précisé
     * Exemple : pformat = "dd/MM/yyyy", pDateString = "21/01/2023"
     *
     * @throws Exception
     */
    public MaDate(String pFormat, String pDateString) throws Exception{
        mFormat = pFormat;
        mDateString = pDateString;
        mDate = toDate();
    }
    /**
     * @param pFormat : Format que l'on veut donner à la date
     * G -> Era
     * y -> Année
     * M -> Mois
     * w -> Semaine dans l'année
     * W -> Semaine dans le mois
     * D -> Jour dans l'année
     * d -> jour dans le mois
     * F -> Jour de la semaine dans le mois
     * E -> Jour de la semaine en lettre
     * a -> Marqueur AM/PM
     * H -> Heure (0-23)
     * k -> Heure (1-24)
     * K -> Heure en AM/PM (0-11)
     * h -> Heure en AM/PM (1-12)
     * m -> Minutes
     * s -> Secondes
     * S -> Millisecondes
     * Exemples : "dd/MM/yyyy", "dd MMMMM yyyy GGG, hh:mm aaa","hh:mm a, zzzz"
     *
     * @param pDate : Date
     */
    public MaDate(String pFormat, Date pDate){
        mFormat = pFormat;
        mDate= pDate;
        mDateString= toString();
    }

    /**
     * @return Date correspondant aux données reçues dans le constructeur
     * @throws Exception
     */
    public Date toDate() throws Exception{
        return new SimpleDateFormat(mFormat).parse(mDateString);
    }

    /**
     * @return String correspondant aux données reçues dans le constructeur
     * @throws Exception
     */
    @Override
    public String toString(){
        return new SimpleDateFormat(mFormat).format(mDate);
    }
}
