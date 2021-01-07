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
    public static ArrayList<Hote> listeHote;
    public static ArrayList<Logement> listeLogements;
    public static ArrayList<Appartement> listeAppartements;
    public static ArrayList<Maison> listeMaison;
    public static ArrayList<Voyageur> listeVoyageurs;
    public static ArrayList<Sejour> listeSejours;
    public static ArrayList<Reservation> listeReservations;




    public static void main(String[] args){
         System.out.println("Bienvenue chez AirBnB");
         scanner = new Scanner(System.in);
         listeHote = new ArrayList<>();
         listerMenu();
         scanner.close();
    }

    static void listerMenu(){
        System.out.println("-------------------------\n" +
        "Saisir une option :\n" +
        "1 : Liste des hôtes\n" +
        "2 : Liste des logements\n" +
        "3 : Liste des voyageurs\n" +
        "4 : Liste des réservations\n" +
        "5 : Fermer le programme");
        int optionChoisie = choix(5);
        if(optionChoisie == 1){
            GestionHotes.listerHotes();
        }
        switch (choix(5)){
            case 1:
                GestionHotes.listerHotes();
                break;
            case 2:
                GestionLogements.listerLogements();
                break;
            case 3:
                GestionVoyageurs.listerVoyageurs();
                break;
            case 4:
                GestionReservations.listerReservations();
                break;
            case 5:
                System.exit(0);
                break;


        }
    }

    public static int choix(int maxInt){
        int reponse = 0;
        try {
            reponse = scanner.nextInt();
        }catch (InputMismatchException e){}

        if(!(reponse >= 1 && reponse <= maxInt)){
            System.out.println("Votre choix doit être un chiffre entre 1 et "+ maxInt + ". Veuillez réessayer : ");
            scanner.nextLine();
            choix(5);
        }
        return reponse;
    }
}
