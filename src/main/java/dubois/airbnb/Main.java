package dubois.airbnb;

import dubois.airbnb.parserXml.ParseXmlLogements;
import dubois.airbnb.parserXml.XStreamLogements;
import dubois.airbnb.reservations.Reservation;


public class Main {
    public static void main(String[] args) throws Exception {
        /*JeuDeTest jdt = new JeuDeTest();
        //JeuDeTest jdt2 = new JeuDeTest("maison","long");

        try{
            Reservation reserv1 = jdt.getReservation();
        //Reservation reserv2 = jdt2.getReservation();
        reserv1.afficher();
        System.out.println("------------------------");
        //System.out.println("------------------------");
        //reserv2.afficher();
        }catch (Exception e){
            System.out.println("Une erreur est survenue avec la réservation");
            e.printStackTrace();
        }*/


        //ParseXmlLogements parser = new ParseXmlLogements();
        //parser.parse();


        XStreamLogements test = new XStreamLogements();
        test.streamL();
    }
}
