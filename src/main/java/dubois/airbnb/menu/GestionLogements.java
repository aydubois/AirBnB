package dubois.airbnb.menu;

import dubois.airbnb.logements.Appartement;
import dubois.airbnb.logements.Maison;
import dubois.airbnb.utilisateurs.Hote;

public class GestionLogements extends Gestion{

    @Override
    public void lister() {
        System.out.println("-------------------------\n" +
                "Liste des logements \n" +
                "Saisir une option :\n" +
                "1 : Ajouter un logement\n" +
                "2 : Supprimer un logement\n" +
                "3 : Voir la liste de tous les logements\n"+
                "4 : Retour");
        switch (Menu.choix(1,4)){
            case 1:
                ajouter();
                break;
            case 2:
                supprimer();
                break;
            case 3:
                afficher("Maison");
                afficher("Appartement");
            case 4:
                Menu.listerMenu();
                break;
        }
    }

    @Override
    protected void ajouter() {
        System.out.println("Choisissez l'hôte : ");
        GestionHotes.afficher();
        int numHote = Menu.choix(1, Menu.listeHote.size());
        Hote unHote = Menu.listeHote.get(numHote-1);

        System.out.println("Indiquez le tarif pour une nuit : ");
        int tarifParNuit = Menu.scanner.nextInt();

        System.out.println("Veuillez saisir l'adresse : ");
        String adresse = Menu.scanner.nextLine();

        System.out.println("Veuillez saisir la superficie : ");
        int superficie = Menu.scanner.nextInt();

        System.out.println("Veuillez saisir le nombre de voyageurs maximum autorisés : ");
        int nbVoyageursMax = Menu.scanner.nextInt();

        System.out.println("Choisissez le type de logement : \n 1. Appartement \n 2. Maison");
        if (Menu.choix(1, 2) == 1) ajouterAppartement(unHote, tarifParNuit, adresse, superficie, nbVoyageursMax);
        else ajouterMaison(unHote, tarifParNuit, adresse, superficie, nbVoyageursMax);
    }
    private void ajouterMaison(Hote hote, int tarifParnuit, String adresse, int superficie, int nbVoyageursMax){
        System.out.println("La maison possède une piscine ? \n 1. Oui \n 2. Non ");
        boolean possedePiscine = Menu.choix(1, 2) == 1 ? true : false;

        System.out.println("Veuillez saisir la superficie du jardin: ");
        int superficieJardin = Menu.scanner.nextInt();

        Maison maison = new Maison(hote, tarifParnuit,adresse, superficie, nbVoyageursMax, possedePiscine, superficieJardin);
        Menu.listeMaison.add(maison);

        System.out.println("Voici la nouvelle maison enregistrée : ");
        maison.afficher();
        System.out.println("/////");

        lister();
    }

    private void ajouterAppartement(Hote hote, int tarifParnuit, String adresse, int superficie, int nbVoyageursMax){
        System.out.println("Veuillez saisir la superficie du balcon : ");
        int superficieBalcon = Menu.scanner.nextInt();
        System.out.println("Veuillez saisir le numéro de l'étage : ");
        int numeroEtage = Menu.scanner.nextInt();


        Appartement appartement = new Appartement(hote, tarifParnuit,adresse, superficie, nbVoyageursMax, superficieBalcon, numeroEtage);
        Menu.listeAppartements.add(appartement);

        System.out.println("Voici le nouvel appartement enregistré : ");
        appartement.afficher();
        System.out.println("/////");

        lister();
    }
    @Override
    protected void supprimer() {
        System.out.println("Choisissez le type de logement à supprimer : \n 1. Appartement \n 2. Maison");
        if (Menu.choix(1, 2) == 1) supprimerAppartement();
        else supprimerMaison();
    }

    private void supprimerMaison(){
        System.out.println("Choisissez la maison à supprimer : ");
        afficher("Maison");
        int numMaison= Menu.choix(1, Menu.listeMaison.size());
        Menu.listeMaison.remove(numMaison-1);

        System.out.println("La maison n°"+numMaison+" a bien été supprimée.");
        lister();
    }
    private void supprimerAppartement(){
        System.out.println("Choisissez l'appartement à supprimer : ");
        afficher("Appartement");
        int numAppart= Menu.choix(1, Menu.listeAppartements.size());
        Menu.listeAppartements.remove(numAppart-1);

        System.out.println("L'appartement n°"+numAppart+" a bien été supprimé.");
        lister();
    }
    static void afficher(String type) {
        if("Maison".equals(type)){
            for (int i = 0; i < Menu.listeMaison.size(); i++) {
                System.out.print(""+(i+1)+" : ");
                Menu.listeMaison.get(i).afficher();
                System.out.println("//////////");
            }
        }else{
            for (int i = 0; i < Menu.listeAppartements.size(); i++) {
                System.out.print(""+(i+1)+" : ");
                Menu.listeAppartements.get(i).afficher();
                System.out.println("//////////");
            }
        }
    }
}
