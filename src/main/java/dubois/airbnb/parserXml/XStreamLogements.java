package dubois.airbnb.parserXml;

import com.thoughtworks.xstream.XStream;
import dubois.airbnb.logements.Logement;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XStreamLogements {
    private final XStream xstream = new XStream();


    public void streamL(){
        try{

            byte[] xml = Files.readAllBytes(Paths.get("fichiers/logements/logements.xml"));
            //xstream.fromXML(new String(xml, StandardCharsets.UTF_8));
            System.out.println( xstream.fromXML(new String(xml, StandardCharsets.UTF_8)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
