package dubois.airbnb;


import dubois.airbnb.logements.Appartement;
import dubois.airbnb.logements.Logement;
import dubois.airbnb.logements.Maison;
import dubois.airbnb.outils.CompareGeneric;
import dubois.airbnb.outils.CompareGenericMultiple2;
import dubois.airbnb.parserXml.ParseXmlLogements;
import dubois.airbnb.utilisateurs.Hote;

import java.util.ArrayList;

public class Main {
    private static ArrayList<Logement> allLogements= new ArrayList<>();
    private static ArrayList<Hote> allHotes= new ArrayList<>();
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


        ParseXmlLogements parser = new ParseXmlLogements();
        parser.parse();
        allLogements = parser.getArrayLogements();
        allHotes = parser.getArrayHote();
        for (int i = 0; i < allLogements.size(); i++) {
            allLogements.get(i).setName(i+"_blah_"+allLogements.get(i).getNameHote());
        }

        try{
            Appartement maisonJean =  getLogementByNameWithGenericity("4_blah_Jean");
            System.out.println(maisonJean.getName());
        }catch(ClassCastException e){
            System.out.println("Le type de logement ne correspond pas.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        CompareGeneric monComparateur = new CompareGeneric(allLogements.get(1), allLogements.get(2));
        Logement logementLePlusCher = (Logement) monComparateur.getHigher();
        System.out.println("Le logement le plus cher entre "+allLogements.get(1).getName() +" et "+ allLogements.get(2).getName() +" est : "+ logementLePlusCher.getName());

        CompareGenericMultiple2 monComparateur2 = new CompareGenericMultiple2(allLogements.get(1), allLogements.get(2));
        Logement logementLePlusCher2 = (Logement) monComparateur2.getHigher();
        System.out.println("Le logement le plus cher entre "+allLogements.get(1).getName() +" et "+ allLogements.get(2).getName() +" est : "+ logementLePlusCher2.getName());

        CompareGenericMultiple2 monComparateur3 = new CompareGenericMultiple2(allHotes.get(2), allHotes.get(0));
        Hote hotelepluslent = (Hote) monComparateur3.getLower();
        System.out.println("L'hote le plus rapide entre "+allHotes.get(2).getNom() +" ("+allHotes.get(0).getDelaiDeReponse()+") et "+ allHotes.get(2).getNom() +" ("+allHotes.get(2).getDelaiDeReponse()+") est : "+ hotelepluslent.getNom() + " ("+hotelepluslent.getDelaiDeReponse()+")");

    }


    /// TP 8 : Deux méthodes sans généricité pour récupérer Maison ou Appart à partir de leur nom
    private static Maison getMaisonByName(String name) throws Exception{
        for (int i = 0; i < allLogements.size() ; i++) {
            if(allLogements.get(i).getClass() == Maison.class && allLogements.get(i).getName().equals(name)){
                return (Maison) allLogements.get(i);
            }
        }
        throw new Exception("Aucune maison ne porte ce nom.");
    }
    private static Appartement getAppartementByName(String name) throws Exception{
        for (int i = 0; i < allLogements.size() ; i++) {
            if(allLogements.get(i).getClass() == Appartement.class && allLogements.get(i).getName().equals(name)){
                return (Appartement) allLogements.get(i);
            }
        }
        throw new Exception("Aucun appartement ne porte ce nom.");
    }

    /// TP 8 : Une seule méthode mais retourne un Logement -> Oblige a caster le résultat/ Peut provoquer des erreurs après compilation
    private static Logement getLogementByName(String name) throws Exception {
        for (int i = 0; i < allLogements.size(); i++) {
            if (allLogements.get(i).getName().equals(name)) {
                return allLogements.get(i);
            }
        }
        throw new Exception("Aucun logement ne porte ce nom.");
    }

    /// TP 8 : Une seule méthode utilisant la généricité
    private static <T extends Logement> T getLogementByNameWithGenericity(String  name) throws Exception{
        for (int i = 0; i < allLogements.size() ; i++) {
            if(allLogements.get(i).getName().equals(name)){
                return (T) allLogements.get(i);
            }
        }
        throw new Exception("Aucune logement ne porte ce nom.");
    }
}
