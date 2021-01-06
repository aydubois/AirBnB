package dubois.airbnb.outils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MaDate extends Date {
    private SimpleDateFormat sdf;

    /**
     * @param pFormat  : Format que l'on veut donner à la date
     * <ul>
     * <li>G -> Era</li>
     * <li>y -> Année</li>
     * <li>M -> Mois</li>
     * <li>w -> Semaine dans l'année</li>
     * <li>W -> Semaine dans le mois</li>
     * <li>D -> Jour dans l'année</li>
     * <li>d -> jour dans le mois</li>
     * <li>F -> Jour de la semaine dans le mois</li>
     * <li>E -> Jour de la semaine en lettre</li>
     * <li>a -> Marqueur AM/PM</li>
     * <li>H -> Heure (0-23)</li>
     * <li>k -> Heure (1-24)</li>
     * <li>K -> Heure en AM/PM (0-11)</li>
     * <li>h -> Heure en AM/PM (1-12)</li>
     * <li>m -> Minutes</li>
     * <li>s -> Secondes</li>
     * <li>S -> Millisecondes</li>
     * </ul>
     * Exemples : "dd/MM/yyyy", "dd MMMMM yyyy GGG, hh:mm aaa","hh:mm a, zzzz"
     *
     * @param pDateString :
     * Date écrite en chaîne de caractères de la même façon que le format précisé
     * Exemple : pformat = "dd/MM/yyyy", pDateString = "21/01/2023"
     */
    public MaDate(String pFormat, String pDateString){
        super(parse(pDateString));
        sdf = new SimpleDateFormat(pFormat);
    }

    /**
     * @return String correspondant aux données reçues dans le constructeur
     */
    @Override
    public String toString(){
        return sdf.format(this);
   }
}
