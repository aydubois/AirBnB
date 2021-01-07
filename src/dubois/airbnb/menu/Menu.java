package dubois.airbnb.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static Scanner scanner;

    public static void main(String[] args){
         System.out.println("Bienvenue chez AirBnB");
         scanner = new Scanner(System.in);
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
