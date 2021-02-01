package dubois.airbnb.parserXml;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;

import dubois.airbnb.logements.Appartement;
import dubois.airbnb.logements.Logement;
import dubois.airbnb.logements.Maison;
import dubois.airbnb.utilisateurs.Hote;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

public class ParseXmlLogements {
    private ArrayList  hotesObject =new ArrayList <Hote>();
    private ArrayList  logementsObject =new ArrayList <Logement>();

    public void parse(){

        try {

            File fileLogement = new File("fichiers/logements/logements.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fileLogement);

            doc.getDocumentElement().normalize();

            getAppartements(doc);
            getMaisons(doc);

            printList();


        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de la configuration du parser.");
            e.printStackTrace();
        }
    }

    private  void printList(){
        System.out.println("HOTES");
        for (int i = 0; i < hotesObject.toArray().length; i++) {
            Hote unHote = (Hote)hotesObject.get(i);
            unHote.afficher();
            System.out.println("<------------------------->");
        }
        System.out.println("/////////////");
        System.out.println("/////////////");
        System.out.println("LOGEMENTS");
        for (int i = 0; i < logementsObject.toArray().length; i++) {
            Logement unLog = (Logement)logementsObject.get(i);
            unLog.afficher();
            System.out.println("<------------------------->");

        }
    }
    private void getAppartements(Document doc) throws Exception {
        // Liste des appartements
        NodeList nListAppart = doc.getElementsByTagName("Appartement");

        for (int temp = 0; temp < nListAppart.getLength(); temp++) {

            Node nNodeAppart = nListAppart.item(temp);
            //System.out.println("\nCurrent Element :" + nNodeAppart.getNodeName());

            if (nNodeAppart.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNodeAppart;
                int tarifParNuit = Integer.parseInt(eElement.getElementsByTagName("tarifParNuit").item(0).getTextContent());
                String adresse = eElement.getElementsByTagName("adresse").item(0).getTextContent();
                int superficie = Integer.parseInt(eElement.getElementsByTagName("superficie").item(0).getTextContent());
                int nbVoyageursMax = Integer.parseInt(eElement.getElementsByTagName("nbVoyageursMax").item(0).getTextContent());
                int numeroEtage = Integer.parseInt(eElement.getElementsByTagName("numeroEtage").item(0).getTextContent());
                int superficieBalcon = Integer.parseInt(eElement.getElementsByTagName("superficieBalcon").item(0).getTextContent());
                try{
                    Hote unHote = getHote(eElement);
                    createAppart(unHote, tarifParNuit, adresse, superficie, nbVoyageursMax, superficieBalcon, numeroEtage);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private Hote getHote(Element eElement) throws Exception{
        if(eElement.getElementsByTagName("hote").item(0).getNodeType() != Node.ELEMENT_NODE){
            throw new Exception("Il manque un hôte pour ce logement.");
        }
        Hote unHote;
        Element eElementHote = (Element) eElement.getElementsByTagName("hote").item(0);
        String nom = eElementHote.getElementsByTagName("nom").item(0).getTextContent();
        String prenom = eElementHote.getElementsByTagName("prenom").item(0).getTextContent();
        int age = Integer.parseInt(eElementHote.getElementsByTagName("age").item(0).getTextContent());
        int delaiReponse = Integer.parseInt(eElementHote.getElementsByTagName("delaiReponse").item(0).getTextContent());

        boolean alreadyExist = false;
        if(!hotesObject.isEmpty()){
            for (int i = 0; i <hotesObject.toArray().length ; i++) {
                unHote = (Hote) hotesObject.get(i);
                if(unHote.getPrenom().equals(prenom)  && unHote.getNom().equals(nom) && unHote.getAge() == age && unHote.getDelaiDeReponse() == delaiReponse){
                    alreadyExist = true;
                    return unHote;
                }
            }
        }
        if(!alreadyExist){
             unHote = new Hote(prenom, nom, age, delaiReponse);
            hotesObject.add(unHote);
            return unHote;
        }

        throw new Exception("Il y a un problème avec l'hôte.");

    }
    private void getMaisons(Document doc){
        // Liste des maisons
        NodeList nListMaison = doc.getElementsByTagName("Maison");

        for (int temp = 0; temp < nListMaison.getLength(); temp++) {

            Node nNodeMaison = nListMaison.item(temp);

            if (nNodeMaison.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNodeMaison;
                int tarifParNuit = Integer.parseInt(eElement.getElementsByTagName("tarifParNuit").item(0).getTextContent());
                String adresse = eElement.getElementsByTagName("adresse").item(0).getTextContent();
                int superficie = Integer.parseInt(eElement.getElementsByTagName("superficie").item(0).getTextContent());
                int nbVoyageursMax = Integer.parseInt(eElement.getElementsByTagName("nbVoyageursMax").item(0).getTextContent());
                int superficieJardin = Integer.parseInt(eElement.getElementsByTagName("superficieJardin").item(0).getTextContent());
                boolean possedePiscine = Boolean.valueOf(eElement.getElementsByTagName("possedePiscine").item(0).getTextContent());

                try{
                    Hote unHote = getHote(eElement);
                    createMaison(unHote, tarifParNuit, adresse, superficie, nbVoyageursMax, possedePiscine, superficieJardin);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }
    }

    private void createAppart(Hote hote, int tarifParNuit,String adresse, int superficie, int nbVoyageursMax, int superficieBalcon,int numeroEtage){
        Appartement unLogement = new Appartement(hote, tarifParNuit, adresse,superficie, nbVoyageursMax, superficieBalcon, numeroEtage);
        logementsObject.add(unLogement);
    }
    private void createMaison(Hote hote, int tarifParNuit,String adresse, int superficie, int nbVoyageursMax, boolean possedePiscine,int superficieJardin){
        Maison unLogement = new Maison(hote, tarifParNuit, adresse,superficie, nbVoyageursMax, possedePiscine, superficieJardin);
        logementsObject.add(unLogement);
    }
}

