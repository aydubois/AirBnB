package dubois.airbnb;

import dubois.airbnb.logements.Appartement;
import dubois.airbnb.logements.Maison;
import dubois.airbnb.outils.MaDate;
import dubois.airbnb.reservations.Reservation;
import dubois.airbnb.reservations.Sejour;
import dubois.airbnb.utilisateurs.Hote;
import dubois.airbnb.utilisateurs.Voyageur;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class JeuDeTest {
    private static final String[] prenom = {"Adam","Alex","Alexandre","Alexis","Anthony","Antoine","Benjamin","Cédric","Charles","Christopher","David","Dylan","Édouard","Elliot","Émile","Étienne","Félix","Gabriel","Guillaume","Hugo","Benoîst","Jacob","Jérémy","Jonathan"};
    private static final String[] nom = {"Dupond","Durand","Dubois","Desbois","Martin","Moreau","Richard","Devanneaux","Lawniczak","Dumas","Lacroix","Perrot","Marchal","Leclerc","Laffont","Vidal","Corona","Fontaine","Marty","Poiret","Brun","Laporte","Adam","Martinez"};
    private static final String[] adresse = {"31 allée de la fontaine, 37000 Tours","2 avenue Margot, 38000 Grenoble","10 rue du sac, 37000 Tours","453 champ des meilleurs vignerons, 47000 Bordeaux","21 rue de la porte, 37100 Tours Nord"};
    private static final String[] dateDeReservation = {"12/04/2021","28/01/2020","15/06/2021","01/01/2041","31/12/2021","28/07/2025","01/11/2022","31/04/2021"};

    private static String getPrenom(){
        return prenom[new Random().nextInt(prenom.length)];
    }
    private static String getNom(){
        return nom[new Random().nextInt(nom.length)];
    }
    private static String getAdresse(){
        return adresse[new Random().nextInt(adresse.length)];
    }
    public static String getDateDeReservation(){
        return dateDeReservation[new Random().nextInt(dateDeReservation.length)];
    }
    private static boolean getPiscine(){
        if(randomInt(0,1) == 0){
            return false;
        }else{
            return true;
        }
    }
    private static int randomInt(int min,int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static Hote getHote(){
        return new Hote(getPrenom(),getNom(), randomInt(2,100), randomInt(1,60));
    }
    public static Voyageur getVoyageur(){
        return new Voyageur(getPrenom(),getNom(), randomInt(2,100));
    }
    public static Maison getMaison(){
        return new Maison(getHote(), randomInt(50,1500), getAdresse(), randomInt(20,5000), randomInt(1,15), getPiscine(),randomInt(50,8000));
    }
    public static Appartement getAppartement(){
        return new Appartement(getHote(), randomInt(50,1500), getAdresse(), randomInt(20,500), randomInt(1,15), randomInt(1,50),randomInt(0,20));
    }
    public static Sejour getSejour(){
        MaDate dateN = new MaDate("dd/MM/yyyy",getDateDeReservation());
        if(randomInt(0,1) == 0){
            return new Sejour(dateN, randomInt(1,365), getAppartement(), randomInt(1,15));
        }else{
            return new Sejour(dateN, randomInt(1,365), getMaison(), randomInt(1,15));
        }
    }
    public static Reservation getReservation(){
        MaDate dateN = new MaDate("dd/MM/yyyy",getDateDeReservation());
        return new Reservation(getSejour(), getVoyageur(), dateN);
    }

}
