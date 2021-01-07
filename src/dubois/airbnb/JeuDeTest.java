package dubois.airbnb;

import dubois.airbnb.logements.Appartement;
import dubois.airbnb.logements.Maison;
import dubois.airbnb.outils.MaDate;
import dubois.airbnb.reservations.Reservation;
import dubois.airbnb.reservations.Sejour;
import dubois.airbnb.reservations.SejourCourt;
import dubois.airbnb.reservations.SejourLong;
import dubois.airbnb.utilisateurs.Hote;
import dubois.airbnb.utilisateurs.Voyageur;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class JeuDeTest {
    private  final String[] listePrenom = {"Amélie","Angélique","Corinne","Juliette","Stéphanie","Marjorie","Adam","Alex","Alexandre","Alexis","Anthony","Antoine","Benjamin","Cédric","Charles","Christopher","David","Dylan","Édouard","Elliot","Émile","Étienne","Félix","Gabriel","Guillaume","Hugo","Benoîst","Jacob","Jérémy","Jonathan"};
    private  final String[] listeNom = {"Dupond","Durand","Dubois","Desbois","Martin","Moreau","Richard","Devanneaux","Lawniczak","Dumas","Lacroix","Perrot","Marchal","Leclerc","Laffont","Vidal","Corona","Fontaine","Marty","Poiret","Brun","Laporte","Adam","Martinez"};
    private  final String[] listeAdresse = {"31 allée de la fontaine, 37000 Tours","2 avenue Margot, 38000 Grenoble","10 rue du sac, 37000 Tours","453 champ des meilleurs vignerons, 47000 Bordeaux","21 rue de la porte, 37100 Tours Nord"};
    private  final String[] listeDateDeReservation = {"12/04/2021","28/01/2020","15/06/2021","01/01/2041","31/12/2021","28/07/2025","01/11/2022","31/04/2021"};

    private Hote hote;
    private Voyageur voyageur;
    private Appartement appartement;
    private Maison maison;
    private Sejour sejour;
    private Reservation reservation;

    /**
     * Mise en place aléatoire d'un jeu de test : hote/voyageur/logement/sejour/reservation
     */
    public JeuDeTest(){
        hote = new Hote(getListePrenom(),getListeNom(), randomInt(2,100), randomInt(1,60));
        voyageur = new Voyageur(getListePrenom(),getListeNom(), randomInt(2,100));
        maison =  new Maison(hote, randomInt(50,1500), getListeAdresse(), randomInt(20,5000), randomInt(1,15), getPiscine(),randomInt(50,8000));
        appartement = new Appartement(hote, randomInt(50,1500), getListeAdresse(), randomInt(20,500), randomInt(1,15), randomInt(1,50),randomInt(0,20));

        MaDate dateN = new MaDate("dd/MM/yyyy",getListeDateDeReservation());
        sejour = createSejour(dateN, "", "");
        dateN = new MaDate("dd/MM/yyyy",getListeDateDeReservation());

        reservation = new Reservation(sejour, voyageur, dateN);

    }

    /**
     * Mise en place aléatoire d'un jeu de test : hote/voyageur/logement/sejour/reservation
     * @param pTypeLogement => "maison" OU "appartement". Si une autre valeur -> automatiquement "appartement"
     * @param pTypeDuree => "court" OU "long". Si une autre valeur -> automatiquement "long"
     */
    public JeuDeTest(String pTypeLogement, String pTypeDuree){
        hote = new Hote(getListePrenom(),getListeNom(), randomInt(2,100), randomInt(1,60));
        voyageur = new Voyageur(getListePrenom(),getListeNom(), randomInt(2,100));
        maison =  new Maison(hote, randomInt(50,1500), getListeAdresse(), randomInt(20,5000), randomInt(1,15), getPiscine(),randomInt(50,8000));
        appartement = new Appartement(hote, randomInt(50,1500), getListeAdresse(), randomInt(20,500), randomInt(1,15), randomInt(1,50),randomInt(0,20));

        MaDate dateN = new MaDate("dd/MM/yyyy",getListeDateDeReservation());
        sejour = createSejour(dateN, pTypeLogement, pTypeDuree);
        dateN = new MaDate("dd/MM/yyyy",getListeDateDeReservation());

        reservation = new Reservation(sejour, voyageur, dateN);

    }
    private String getListePrenom(){
        return listePrenom[new Random().nextInt(listePrenom.length)];
    }
    private String getListeNom(){
        return listeNom[new Random().nextInt(listeNom.length)];
    }
    private String getListeAdresse(){
        return listeAdresse[new Random().nextInt(listeAdresse.length)];
    }
    private String getListeDateDeReservation(){
        return listeDateDeReservation[new Random().nextInt(listeDateDeReservation.length)];
    }
    private boolean getPiscine(){
        return randomInt(0,1) == 0 ? false : true;
    }
    private int randomInt(int min,int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    private Sejour createSejour(MaDate pdate, String pTypeLogement, String pDuree){
        String logement = "";
        if(pTypeLogement == ""){
            logement = randomInt(0,1) == 0 ? "appartement" : "maison";
        }else{
            logement = pTypeLogement;
        }
        String duree = "";
        if(pDuree == ""){
            duree = randomInt(0,1) == 0 ? "court" : "long";
        }else{
            duree = pDuree;
        }
        if(logement == "maison"){
            if(duree == "court"){
                sejour = new SejourCourt(pdate, randomInt(1,5), maison, randomInt(1,15));
            }else{
                sejour = new SejourLong(pdate, randomInt(6,31), maison, randomInt(1,15));
            }
        }else{
            if(duree == "court"){
                sejour = new SejourCourt(pdate, randomInt(1,5), appartement, randomInt(1,15));
            }else{
                sejour = new SejourLong(pdate, randomInt(6,31), appartement, randomInt(1,15));
            }
        }
        return sejour;
    }

    public Hote getHote(){ return hote;}
    public Voyageur getVoyageur(){return voyageur;}
    public Maison getMaison(){return maison;}
    public Appartement getAppartement(){return appartement;}
    public Sejour getSejour(){return sejour;}
    public Reservation getReservation(){return reservation;}

}
