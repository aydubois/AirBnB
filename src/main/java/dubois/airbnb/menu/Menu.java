package dubois.airbnb.menu;

import dubois.airbnb.logements.Appartement;
import dubois.airbnb.logements.Logement;
import dubois.airbnb.logements.Maison;
import dubois.airbnb.reservations.Reservation;
import dubois.airbnb.reservations.Sejour;
import dubois.airbnb.utilisateurs.Hote;
import dubois.airbnb.utilisateurs.Voyageur;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static Scanner scanner;
    public static ArrayList<Hote> listeHote = new ArrayList<>();
    public static ArrayList<Appartement> listeAppartements = new ArrayList<>();
    public static ArrayList<Maison> listeMaison = new ArrayList<>();
    public static ArrayList<Voyageur> listeVoyageurs = new ArrayList<>();
    public static ArrayList<Sejour> listeSejours = new ArrayList<>();
    public static ArrayList<Reservation> listeReservations = new ArrayList<>();

    private static final GestionHotes gHotes = new GestionHotes();
    private static final GestionLogements gLogements = new GestionLogements();
    private static final GestionSejours gSejours = new GestionSejours();
    private static final GestionVoyageurs gVoyageurs = new GestionVoyageurs();
    private static final GestionReservations gReservations = new GestionReservations();


    public static void main(String[] args){
         getAllLists();
         System.out.println("Bienvenue chez AirBnB");
         scanner = new Scanner(System.in);
         listerMenu();
         scanner.close();
    }

    static  void getAllLists(){

    }

    static void listerMenu(){
        System.out.println("-------------------------\n" +
        "Saisir une option :\n" +
        "1 : Liste des hôtes\n" +
        "2 : Liste des logements\n" +
        "3 : Liste des voyageurs\n" +
        "4 : Liste des séjours\n" +
        "5 : Liste des réservations\n" +
        "6 : Fermer le programme");

        switch (choix(1,6)){
            case 1:
                gHotes.lister();
                break;
            case 2:
                gLogements.lister();
                break;
            case 3:
                gVoyageurs.lister();
                break;
            case 4:
                gSejours.lister();
                break;
            case 5:
                gReservations.lister();
                break;
            case 6:
                System.exit(0);
                break;


        }
    }

    public static int choix(int minInt,int maxInt){
        int reponse = 0;
        try {
            reponse = scanner.nextInt();
        }catch (InputMismatchException e){}

        if(!(reponse >= minInt && reponse <= maxInt)){
            System.out.println("Votre choix doit être un chiffre entre "+minInt+" et "+ maxInt + ". Veuillez réessayer : ");
            scanner.nextLine();
            choix(minInt,maxInt);
        }
        return reponse;
    }
}
