package dubois.airbnb.outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utile {
    private static final String mFormat = "dd/MM/yyyy";
    private static SimpleDateFormat mSdf;

    /**
     * Retourne une date à partir d'une chaîne de caratères et de son format.
     * @param pDate String d'une date
     * @param pFormat format de pDate, exemple "dd/MM/yyyy"
     * @return Date
     * @throws Exception
     */
    public static Date getDateToString(String pDate, String pFormat) throws Exception{
        // Vérification que le format existe sinon Exception levée
        try{
            mSdf = new SimpleDateFormat(pFormat);
        }catch(Exception e){
            throw e;
        }
        // vérification que pDate correspond bien au format sinon Exception levée
        try {
            Date date1 = mSdf.parse(pDate);
            return date1;
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * Retourne une date à partir d'un numéro de jour, mois et année.
     * @param pJour numéro du jour (01 à 31)
     * @param pMois numéro du mois (01 à 12)
     * @param pAnnee année
     * @return Date
     * @throws Exception
     */
    public static Date getDateToNumbers(int pJour, int pMois, int pAnnee) throws Exception{
        String date = String.valueOf(pJour)+"/"+String.valueOf(pMois)+"/"+String.valueOf(pAnnee);
        try {
            return new SimpleDateFormat(mFormat).parse(date);
        }catch(ParseException e){
            throw e;
        }
    }

    /**
     * Retourne une chaîne de caractères au format "dd/MM/yyyy" à partir d'une date.
     * @param pDate Date
     * @return String
     */
    public static String getStringToDate(Date pDate){
        return new SimpleDateFormat(mFormat).format(pDate);
    }

}
