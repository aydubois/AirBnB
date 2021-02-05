package dubois.airbnb.outils;

import dubois.airbnb.logements.Appartement;
import dubois.airbnb.logements.Logement;
import dubois.airbnb.logements.Maison;
import dubois.airbnb.reservations.Reservation;
import dubois.airbnb.reservations.Sejour;
import dubois.airbnb.reservations.SejourFactory;
import dubois.airbnb.utilisateurs.Hote;
import dubois.airbnb.utilisateurs.Voyageur;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

 class ParseXml {
    private final ArrayList<Hote> hotes;
    private final ArrayList<Logement> logements ;
    private final ArrayList<Voyageur> voyageurs ;
    private final ArrayList<Sejour> sejours ;
    private final ArrayList<Reservation> reservations ;

    private ParseXml(ParseBuilder builder) {
        this.hotes = builder.hotes;
        this.logements = builder.logements;
        this.voyageurs = builder.voyageurs;
        this.sejours = builder.sejours;
        this.reservations = builder.reservations;
    }

    public ArrayList<Hote> getHotes() {
        return hotes;
    }

    public ArrayList<Logement> getLogements() {
        return logements;
    }

    public ArrayList<Voyageur> getVoyageurs() {
        return voyageurs;
    }

    public ArrayList<Sejour> getSejours() {
        return sejours;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
    public void printList(ParseBuilder.types type){
        switch (type){
            case HOTE -> hotes.forEach(hote -> hote.afficher());
            case SEJOUR -> sejours.forEach(sejour -> sejour.afficher());
            case LOGEMENT -> logements.forEach(logement -> logement.afficher());
            case VOYAGEUR -> voyageurs.forEach(voyageur -> voyageur.afficher());
            case RESERVATION -> reservations.forEach(reservation -> reservation.afficher());
        }
    }
    public static class ParseBuilder {
        private final ArrayList<Hote> hotes;
        private final ArrayList<Voyageur> voyageurs;
        private final ArrayList<Logement> logements;
        private ArrayList<Sejour> sejours;
        private ArrayList<Reservation> reservations;

        public enum types {HOTE, VOYAGEUR, LOGEMENT, SEJOUR, RESERVATION}

        ;

        public ParseBuilder(String filenameXmlHote, String filenameXmlVoyageur, String filenameXmlLogement) {
            this.hotes = (ArrayList<Hote>) parse(filenameXmlHote, types.HOTE);
            this.voyageurs = (ArrayList<Voyageur>) parse(filenameXmlVoyageur, types.VOYAGEUR);
            this.logements = (ArrayList<Logement>) parse(filenameXmlLogement, types.LOGEMENT);
            this.sejours = new ArrayList<Sejour>();
            this.reservations = new ArrayList<Reservation>();
        }

        public ParseXml build() {
            ParseXml parsexml = new ParseXml(this);
            return parsexml;
        }

        public ParseBuilder addXmlSejour(String filenameXmlSejour) {
            this.sejours = (ArrayList<Sejour>) parse(filenameXmlSejour, types.SEJOUR);
            return this;
        }

        public ParseBuilder addXmlReservation(String filenameXmlReservation) {
            this.reservations = (ArrayList<Reservation>) parse(filenameXmlReservation, types.RESERVATION);
            return this;
        }


        private ArrayList<?> parse(String filename, types type) {
            try {

                File fileLogement = new File(filename);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fileLogement);

                doc.getDocumentElement().normalize();
                return switchType(type, doc);
            } catch (Exception e) {
                System.out.println("Une erreur est survenue lors de la configuration du parser.");
                e.printStackTrace();
            }
            return null;
        }

        private ArrayList<?> switchType(types typeCla, Document doc) {
            switch (typeCla) {
                case HOTE:
                    return getAllHote(doc);
                case VOYAGEUR:
                    return getAllVoyageur(doc);

                case LOGEMENT:
                    return getAllLogement(doc);

                case SEJOUR:
                    return getAllSejour(doc);
                case RESERVATION:
                    return getAllReservation(doc);
                default:
                    return null;

            }
        }

        private static ArrayList<Hote> getAllHote(Document doc) {
            ArrayList<Hote> hotesObject = new ArrayList<>();
            NodeList nListHotes = doc.getElementsByTagName("Hote");
            for (int temp = 0; temp < nListHotes.getLength(); temp++) {

                Node nNodeHote = nListHotes.item(temp);

                if (nNodeHote.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElementHote = (Element) nNodeHote;
                    Hote unHote;
                    String nom = eElementHote.getElementsByTagName("nom").item(0).getTextContent();
                    String prenom = eElementHote.getElementsByTagName("prenom").item(0).getTextContent();
                    int age = Integer.parseInt(eElementHote.getElementsByTagName("age").item(0).getTextContent());
                    int delaiReponse = Integer.parseInt(eElementHote.getElementsByTagName("delaiReponse").item(0).getTextContent());

                    unHote = new Hote(prenom, nom, age, delaiReponse);
                    hotesObject.add(unHote);

                }
            }
            return hotesObject;
        }


        private static ArrayList<Voyageur> getAllVoyageur(Document doc) {
            ArrayList<Voyageur> voyageurObject = new ArrayList<>();
            NodeList nListVoy = doc.getElementsByTagName("Voyageur");
            for (int temp = 0; temp < nListVoy.getLength(); temp++) {

                Node nNodeVoy = nListVoy.item(temp);

                if (nNodeVoy.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElementVoy = (Element) nNodeVoy;
                    Voyageur Voy;
                    String nom = eElementVoy.getElementsByTagName("nom").item(0).getTextContent();
                    String prenom = eElementVoy.getElementsByTagName("prenom").item(0).getTextContent();
                    int age = Integer.parseInt(eElementVoy.getElementsByTagName("age").item(0).getTextContent());

                    Voy = new Voyageur(prenom, nom, age);
                    voyageurObject.add(Voy);
                }
            }
            return voyageurObject;
        }

        private static ArrayList<Logement> getAllLogement(Document doc) {

            ArrayList<Logement> logementsObject = new ArrayList<>();
            NodeList nListMaison = doc.getElementsByTagName("Logement");
            Hote unHote = null;
            for (int temp = 0; temp < nListMaison.getLength(); temp++) {
                Node nNodeMaison = nListMaison.item(temp);
                if (nNodeMaison.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNodeMaison;
                    Node house = eElement.getElementsByTagName("Maison").item(0);
                    Node flat = eElement.getElementsByTagName("Appartement").item(0);
                    int tarifParNuit = Integer.parseInt(eElement.getElementsByTagName("tarifParNuit").item(0).getTextContent());
                    String adresse = eElement.getElementsByTagName("adresse").item(0).getTextContent();
                    int superficie = Integer.parseInt(eElement.getElementsByTagName("superficie").item(0).getTextContent());
                    int nbVoyageursMax = Integer.parseInt(eElement.getElementsByTagName("nbVoyageursMax").item(0).getTextContent());
                    try {
                        unHote = getAllHote(doc).get(temp);
                    } catch (Exception e) {
                        System.out.println("Un soucis est apparu lor de la recherche d'un hôte pour le logement.Vérifier le Xml.");
                        return null;
                    }
                    if (house != null) {
                        int superficieJardin = Integer.parseInt(eElement.getElementsByTagName("superficieJardin").item(0).getTextContent());
                        boolean possedePiscine = Boolean.parseBoolean(eElement.getElementsByTagName("possedePiscine").item(0).getTextContent());
                        Maison unLogement = new Maison(unHote, tarifParNuit, adresse, superficie, nbVoyageursMax, possedePiscine, superficieJardin);
                        logementsObject.add(unLogement);
                    }
                    if (flat != null) {
                        int numeroEtage = Integer.parseInt(eElement.getElementsByTagName("numeroEtage").item(0).getTextContent());
                        int superficieBalcon = Integer.parseInt(eElement.getElementsByTagName("superficieBalcon").item(0).getTextContent());
                        Appartement unLogement = new Appartement(unHote, tarifParNuit, adresse, superficie, nbVoyageursMax, superficieBalcon, numeroEtage);
                        logementsObject.add(unLogement);
                    }

                }

            }
            return logementsObject;
        }

        private static ArrayList<Sejour> getAllSejour(Document doc) {
            NodeList nListSejour = doc.getElementsByTagName("Sejour");
            Logement unLogement = null;
            ArrayList<Sejour> sejourObject = new ArrayList<>();
            for (int temp = 0; temp < nListSejour.getLength(); temp++) {

                Node nNodeSejour = nListSejour.item(temp);

                if (nNodeSejour.getNodeType() == Node.ELEMENT_NODE) {

                    MaDate dateArrivee = null;
                    Element eElement = (Element) nNodeSejour;
                    try {
                        dateArrivee = new MaDate(eElement.getElementsByTagName("dateArrivee").item(0).getTextContent());
                    } catch (ParseException e) {
                        System.out.println("Une erreur est présente dans le xml des Séjours. Impossible d'instancier la date d'arrivée");
                        return null;
                    }
                    try {

                        unLogement = getAllLogement(doc).get(temp);
                    } catch (Exception e) {
                        System.out.println("Un soucis est apparu lor de la recherche d'un logement.Vérifier le Xml.");
                        return null;
                    }
                    int nbNuits = Integer.parseInt(eElement.getElementsByTagName("nbNuits").item(0).getTextContent());
                    int nbVoyageurs = Integer.parseInt(eElement.getElementsByTagName("nbVoyageursMax").item(0).getTextContent());
                    Sejour unsejour = SejourFactory.getSejour(dateArrivee, unLogement, nbNuits, nbVoyageurs);
                    sejourObject.add(unsejour);
                }
            }
            return sejourObject;
        }

        private static ArrayList<Reservation> getAllReservation(Document doc) {
            NodeList nListReservation = doc.getElementsByTagName("Reservation");
            Sejour unSejour = null;
            Voyageur unVoyageur = null;
            ArrayList<Reservation> reservationObject = new ArrayList<>();
            for (int temp = 0; temp < nListReservation.getLength(); temp++) {

                Node nNodeReservation = nListReservation.item(temp);

                if (nNodeReservation.getNodeType() == Node.ELEMENT_NODE) {
                    MaDate dateReservation = null;
                    Element eElement = (Element) nNodeReservation;
                    try {
                        unSejour = getAllSejour(doc).get(temp);
                    } catch (Exception e) {
                        System.out.println("Un soucis est apparu lor de la recherche d'un sejour.Vérifier le Xml.");
                        return null;
                    }
                    try {
                        unVoyageur = getAllVoyageur(doc).get(temp);
                    } catch (Exception e) {
                        System.out.println("Un soucis est apparu lor de la recherche d'un voyageur.Vérifier le Xml.");
                        return null;
                    }
                    try {
                        dateReservation = new MaDate(eElement.getElementsByTagName("dateDeReservation").item(0).getTextContent());
                    } catch (ParseException e) {
                        System.out.println("Une erreur est présente dans le xml des Réservations. Impossible d'instancier la date de réservation");
                        return null;
                    }
                    try {

                        Reservation reservation = new Reservation(unSejour, unVoyageur, dateReservation);
                        reservationObject.add(reservation);
                    } catch (Exception e) {
                        System.out.println("Impossible d'instancier la réservation.Vérifier le Xml.");
                        return null;
                    }
                }
            }
            return reservationObject;
        }
    }


}