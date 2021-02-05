package dubois.airbnb;


import dubois.airbnb.logements.Appartement;
import dubois.airbnb.logements.Logement;
import dubois.airbnb.logements.Maison;
import dubois.airbnb.logements.SearchLogement;
import dubois.airbnb.outils.AirBnBData;
import dubois.airbnb.outils.CompareGeneric;
import dubois.airbnb.outils.CompareGenericMultiple2;
import dubois.airbnb.outils.MaDate;
import dubois.airbnb.parserXml.ParseXmlLogements;
import dubois.airbnb.reservations.*;
import dubois.airbnb.utilisateurs.Hote;
import dubois.airbnb.utilisateurs.Personne;
import dubois.airbnb.utilisateurs.Voyageur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class Main {
    private static ArrayList<Logement> allLogements= new ArrayList<>();
    private static ArrayList<Hote> allHotes= new ArrayList<>();
    public static void main(String[] args) throws Exception {
       /* *//*JeuDeTest jdt = new JeuDeTest();
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
        }*//*


        ParseXmlLogements parser = new ParseXmlLogements();
        parser.parse();
        allLogements = parser.getArrayLogements();
        allHotes = parser.getArrayHote();
        *//*for (int i = 0; i < allLogements.size(); i++) {
            allLogements.get(i).setName(i+"_blah_"+allLogements.get(i).getNameHote());
        }
        Optional<Appartement> optAppartement = getAppartementByName("4_blah_Jean");
        if(optAppartement.isEmpty()){
            System.out.println("Aucun appartement ne porte ce nom.");
        }else{
            Appartement appart = optAppartement.get();
            appart.afficher();
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

        CompareGenericMultiple2<Logement> monComparateur2 = new CompareGenericMultiple2<>(allLogements.get(1), allLogements.get(2));
        Logement logementLePlusCher2 = (Logement) monComparateur2.getHigher();
        System.out.println("Le logement le plus cher entre "+allLogements.get(1).getName() +" et "+ allLogements.get(2).getName() +" est : "+ logementLePlusCher2.getName());

        CompareGenericMultiple2<? extends Personne> monComparateur3 = new CompareGenericMultiple2<>(allHotes.get(0), allHotes.get(1));
        Hote hotelepluslent = (Hote) monComparateur3.getLower();
        System.out.println("L'hote le plus rapide entre "+allHotes.get(1).getNom() +" ("+allHotes.get(1).getDelaiDeReponse()+") et "+ allHotes.get(0).getNom() +" ("+allHotes.get(0).getDelaiDeReponse()+") est : "+ hotelepluslent.getNom() + " ("+hotelepluslent.getDelaiDeReponse()+")");
*//*
        MaDate dateArrivee = new MaDate("dd/MM/yyyy","01/05/2021");

        int nbNuits = 1;
        Logement logement= allLogements.get(0);
        Logement logement2= allLogements.get(1);
        Logement logement3= allLogements.get(3);
        int nbVoyageurs = 1;
        //Sejour sejour = new SejourLong(dateArrivee, nbNuits, logement, nbVoyageurs);
        //sejour.afficher();
        Voyageur voyageur;
        Voyageur voyageur1 = new Voyageur("Blah","Shlygly", 27);
        *//*SejourLong sejour = new SejourLong(dateArrivee, nbNuits, logement, nbVoyageurs);
        sejour.afficher();
        System.out.println("////////");
        //sejour.setLogement(null);
        //sejour.afficher();
        try{

        Reservation reservation1 = new Reservation(sejour, voyageur1, new MaDate());
        }catch (Exception e){
            e.printStackTrace();
        }*//*
         Sejour sejour3 = SejourFactory.getSejour(dateArrivee,logement, nbNuits,nbVoyageurs);
         sejour3.afficher();
         */
        /*AirBnBData datas = AirBnBData.getInstance();
        allHotes = datas.getListHotes();
        allLogements = datas.getListLogements();
        SearchLogement searchL= new SearchLogement.SearchBuilder(2).tarifMinNuit(500).build();
        ArrayList<Logement> logements = searchL.result();
        *//*logements.stream().forEach( l -> {
            l.afficher();
            System.out.println(l.getTarifParNuit());
        });*//*
        System.out.println("///////");
        logements.stream().forEach(Logement::afficher);


        String text = "Blah blah";
        // Creer une classe anonyme à partir d'une classe abstraite :
        Logement logementTest = new Logement(allHotes.get(0),100,"10 rue de la fleur 37000 Tours", 60, 3 ) {
            //Cette classe ne voit pas les attributs privés de la classe Logement.

            @Override
            public void afficher() {
                //blah blah
            }

            @Override
            protected String createTextToDisplay() {
            // par contre peut utiliser les objets présents en dehors
                return text;
            }
        };*/
        amusetoiaveclesliste();
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
    private static Optional<Appartement> getAppartementByName(String name){
        for (int i = 0; i < allLogements.size() ; i++) {
            if(allLogements.get(i).getClass() == Appartement.class && allLogements.get(i).getName().equals(name)){
                return  Optional.of((Appartement)allLogements.get(i));
            }
        }
        // A la place d'envoyer une Exception -> renvoyer un "Optional"
        Appartement appartement = null;
        return Optional.of(appartement);
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

    private static void amusetoiaveclesliste(){
        ArrayList<String> miri = new ArrayList<>();
        miri.add("Patate");
        miri.add("Pomme");
        miri.add("Poire");
        miri.add("Manger");
        miri.add("Dormir");
        miri.add("Boire");
        miri.add("Blanc");
        miri.add("Rouge");
        miri.add("Vert");
        Stream test = miri.stream()
                .sorted() // tri si Class implement Comparable
                .takeWhile(s -> !s.endsWith("e")) ; // recupere jusqu'à que la condition soit fausse.
        test.forEach(System.out::println);
        System.out.println("//////////");

        OptionalDouble test2 =  miri.stream()
                .mapToInt(s -> s.length()) // return (int)
                .average(); // moyenne
        if( test2.isPresent())
            System.out.println(test2);
        System.out.println("//////////");

        Stream test3 = miri.stream().filter(s -> s.length() >5).map(String::toUpperCase);
        test3.forEach(System.out::println);
        System.out.println("//////////");

        Stream test4 = miri.stream()
                .skip(2) // commence à miri[2]
                .limit(2) // fini à miri[2+2]
                .map(s -> s.contains("a")); // return boolean
        test4.forEach(System.out::println);
        System.out.println("//////////");
        miri.removeIf(s -> s.startsWith("B"));
        miri.forEach(System.out::println);
    }
}
