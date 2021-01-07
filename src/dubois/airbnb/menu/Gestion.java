package dubois.airbnb.menu;

import dubois.airbnb.logements.Appartement;
import dubois.airbnb.logements.Maison;
import dubois.airbnb.outils.MaDate;
import dubois.airbnb.reservations.Reservation;
import dubois.airbnb.reservations.Sejour;
import dubois.airbnb.utilisateurs.Hote;
import dubois.airbnb.utilisateurs.Voyageur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Gestion {
    private static final String[] mType = {"hôte","logement","réservation","voyageur"};

    static void lister(String pType) throws Exception{
        if(!Arrays.asList(mType).contains(pType)){
            throw new Exception();
        }

        System.out.println("-------------------------\n" +
                "Liste des "+pType+"s \n" +
                "Saisir une option :\n" +
                "1 : Ajouter un "+pType+"\n" +
                "2 : Supprimer un "+pType+"\n" +
                "3 : Retour");
        switch (Menu.choix(3)){
            case 1:
                try{
                    if(pType == "logement"){
                        ajouterLogement();
                    }else{
                        ajouter(pType);
                    }
                }
                catch(Exception e){
                    System.out.println("Vous n'avez pas renseigné correctement les données.");
                    Menu.scanner.nextLine();
                    lister(pType);
                }
                break;
            case 2:
                try { supprimer(pType);}
                catch (Exception e){
                    System.out.println("Vous n'avez pas renseigné correctement les données.");
                    Menu.scanner.nextLine();
                    lister(pType);
                }
                break;
            case 3:
                Menu.listerMenu();
                break;
        }
    }

    private static void ajouterLogement() throws Exception{
        System.out.println("--------------------------\n" +
                "Veuillez choisir le type de logement : \n" +
                "1. Appartement \n" +
                "2. Maison");
        int reponse = Menu.choix(2);
        try{
            if(reponse == 1) {
                ajouter("appartement");
            }else{
                ajouter("maison");
            }
        }catch (Exception e){
            throw e;
        }
    }

    private static void ajouter(String pType) throws Exception{
        String[] attributs = getAttribut(pType);
        String[] valeursAttributs ={};
        int increment = 0;
        try{
            System.out.println("Veuillez saisir les informations suivantes : ");
            for (String attribut : attributs) {
                System.out.println(attribut+" :");
                Menu.scanner.nextLine();
                valeursAttributs[increment] = (Menu.scanner.nextLine());
                increment++;
            }
            setInstanceObjet(valeursAttributs, pType);

            System.out.println("Voici le/la nouvel(le) "+pType+" enregistré(e) : ");
            for (int i = 0; i < attributs.length; i++) {
                System.out.println(attributs[i]+" : "+valeursAttributs[i]);
            }
            lister(pType);
        }catch (Exception e){
            throw e;
        }
    }


    private static String[] getAttribut(String pType){
        switch (pType){
            case "hôte":
                return new String[]{"prénom", "nom", "âge", "délai de reponse"};
            case "appartement":
                return new String[]{"hôte", "tarif par nuit", "adress", "superficie", "nombre de voyageurs maximum", "superficie du balcon (en m2)", "numéro de l'étage"};
            case "maison":
                return new String[]{"hôte", "tarif par nuit", "adress", "superficie", "nombre de voyageurs maximum", "présence d'une piscine ? (true / false)", "superficie du jardin (en m2)"};
            case "réservation":
                return new String[]{"séjour", "voyageur", "date de réservation (dd/MM/yyyy)"};
            case "voyageur":
                return new String[]{"prénom", "nom", "âge"};
            default:
                return new String[]{};
        }
    }

    private static void setInstanceObjet(String[] pValeurs, String pType){
        try{
            Object objet;
            switch (pType){
                case "hôte":
                    objet = new Hote(pValeurs[0],pValeurs[1],Integer.parseInt(pValeurs[2]),Integer.parseInt(pValeurs[3]));
                    Menu.listeHote.add((Hote)objet);
                    break;
                case "appartement":
                    Hote hote = Menu.listeHote.get(Integer.parseInt(pValeurs[0])-1);

                    objet = new Appartement(hote,Integer.parseInt(pValeurs[1]),pValeurs[2],Integer.parseInt(pValeurs[3]),Integer.parseInt(pValeurs[4]),Integer.parseInt(pValeurs[5]),Integer.parseInt(pValeurs[6]));
                    Menu.listeAppartements.add((Appartement) objet);
                    break;
                case "maison":
                    Hote hote2 = Menu.listeHote.get(Integer.parseInt(pValeurs[0])-1);

                    objet = new Maison(hote2,Integer.parseInt(pValeurs[1]),pValeurs[2],Integer.parseInt(pValeurs[3]),Integer.parseInt(pValeurs[4]),Boolean.parseBoolean(pValeurs[5]),Integer.parseInt(pValeurs[6]));
                    Menu.listeMaison.add((Maison) objet);
                    break;
                case "réservation":
                    MaDate date = new MaDate("dd/MM/yyyy",pValeurs[2]);
                    Voyageur voyageur = Menu.listeVoyageurs.get(Integer.parseInt(pValeurs[1])-1);
                    Sejour sejour = Menu.listeSejours.get(Integer.parseInt(pValeurs[0])-1);

                    objet = new Reservation(sejour, voyageur, date);
                    Menu.listeReservations.add((Reservation)objet);
                    break;
                case "voyageur":
                    objet = new Voyageur(pValeurs[0], pValeurs[1], Integer.parseInt(pValeurs[2]));
                    Menu.listeVoyageurs.add((Voyageur)objet);
                    break;
            }
        }catch (Exception e){
            System.out.println("Oops, une erreur est survenue lors de la création de : "+pType);
        }
    }


    public static void supprimer(String pType) throws Exception{
        int increment = 0;
        if(pType == "hôte"){
            ArrayList<Hote> liste = new ArrayList<Hote>();
            liste = Menu.listeHote;
        }else if(pType == "appartement"){
            ArrayList<Appartement> liste = new ArrayList<Appartement>();
            liste = Menu.listeAppartements;
        }else if(pType == "maison"){
            ArrayList<Maison> liste = new ArrayList<Maison>();
            liste = Menu.listeMaison;
        }else if(pType == "reservation"){
            ArrayList<Reservation> liste = new ArrayList<Reservation>();
            liste = Menu.listeReservations;
        }else if(pType == "voyageur"){
            ArrayList<Voyageur> liste = new ArrayList<Voyageur>();
            liste = Menu.listeVoyageurs;
        }

        /*System.out.println("-------------------------");
        for (int i = 0; i < liste.toArray().length; i++) {
            System.out.print(i+" : ");
            liste.toArray()[i].afficher();
            increment = i;
        }

        System.out.println("Choisissez le/la "+pType+"à supprimer : ");

        int reponse = Menu.scanner.nextInt();
        if(reponse < 1 || reponse > increment){
            throw new Exception();
        }

        liste.remove(increment-1);
        System.out.println("Le/la "+pType+" n°"+increment+" a bien été supprimé(e).");
        lister(pType);*/
    }
}
