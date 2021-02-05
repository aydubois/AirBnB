package dubois.airbnb.parserXml;

import dubois.airbnb.utilisateurs.Hote;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

@Deprecated
public class ParseXmlHotes {
    private final ArrayList<Hote>  hotesObject =new ArrayList <>();

    /**
     * Parse file "hotes.xml" and create new Hotes
     * Print Lists hotesObject
     */
    public void parse(){

        try {

            File fileLogement = new File("fichiers/hotes/hotes.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fileLogement);

            doc.getDocumentElement().normalize();
            getHote(doc);

            //printList();


        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de la configuration du parser.");
            e.printStackTrace();
        }
    }

    /**
     * @return ArrayList<Hote>
     */
    public ArrayList<Hote> getArrayHote(){
        return hotesObject;
    }
    /**
     * Print Lists hotesObject && logementsObject
     */
    private  void printList(){
        System.out.println("HOTES");
        for (int i = 0; i < hotesObject.toArray().length; i++) {
            Hote unHote = hotesObject.get(i);
            unHote.afficher();
            System.out.println("<------------------------->");
        }
    }


    /**
     * Retrieves the information of the "hote" corresponding to the node and checks if it already exists in the list hotesObject. If not, adds it to the list.
     * @param doc : xml parsed in document
     * @return Hote
     */
    private void getHote(Document doc){
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

                boolean alreadyExist = false;
                if (!hotesObject.isEmpty()) {
                    for (int i = 0; i < hotesObject.toArray().length; i++) {
                        unHote = hotesObject.get(i);
                        if (unHote.getPrenom().equals(prenom) && unHote.getNom().equals(nom) && unHote.getAge() == age && unHote.getDelaiDeReponse() == delaiReponse) {
                            alreadyExist = true;
                        }
                    }
                }
                if (!alreadyExist) {
                    unHote = new Hote(prenom, nom, age, delaiReponse);
                    hotesObject.add(unHote);
                }

            }
        }
    }


}

