package dubois.airbnb.menu;

import dubois.airbnb.logements.Logement;
import dubois.airbnb.outils.MaDate;
import dubois.airbnb.reservations.SejourCourt;
import dubois.airbnb.reservations.SejourLong;

public class GestionSejours extends Gestion{
    @Override
    public void lister() {
        System.out.println("-------------------------\n" +
                "Liste des séjours \n" +
                "Saisir une option :\n" +
                "1 : Ajouter un séjour\n" +
                "2 : Supprimer un séjour\n" +
                "3 : Voir la liste de tous les séjours\n" +
                "4 : Retour");
        switch (Menu.choix(1,4)){
            case 1:
                ajouter();
                break;
            case 2:
                supprimer();
                break;
            case 3:
                afficher();
            case 4:
                Menu.listerMenu();
                break;
        }
    }

    @Override
    protected void ajouter() {
        try{
            System.out.println("Veuillez saisir la date d'arrivée (dd/mm/YYYY : ");

            MaDate dateArrivee = new MaDate("dd/mm/YYYY", Menu.scanner.nextLine());

            System.out.println("Veuillez saisir le nombre de nuits réservées : ");
            int nbNuits = Menu.scanner.nextInt();
            System.out.println("Veuillez saisir le nombre de voyageurs prévus : ");
            int nbVoyageurs = Menu.scanner.nextInt();

            System.out.println("Choisissez le type de logement réservé: \n 1. Appartement \n 2. Maison");

            Logement logement = getLogement();


            if(nbNuits < 6){
                SejourCourt sejour = new SejourCourt(dateArrivee, nbNuits, logement, nbVoyageurs);
                Menu.listeSejours.add(sejour);
                System.out.println("Voici le nouveau séjour ajouté : ");
                sejour.afficher();
            }else{
                SejourLong sejour = new SejourLong(dateArrivee, nbNuits, logement, nbVoyageurs);
                Menu.listeSejours.add(sejour);
                System.out.println("Voici le nouveau séjour ajouté : ");
                sejour.afficher();
            }

            lister();
        }catch (Exception e){
            System.out.println("Vous n'avez pas renseigné correctement les données.");
            Menu.scanner.nextLine();
            lister();
        }
    }
    private Logement getLogement(){
        if(Menu.choix(1,2) == 1){
            GestionLogements.afficher("Appartement");
            int numLog = Menu.choix(1,Menu.listeAppartements.size());
            return Menu.listeAppartements.get(numLog - 1);
        }else{
            GestionLogements.afficher("Maison");
            int numLog = Menu.choix(1,Menu.listeMaison.size());
            return Menu.listeMaison.get(numLog - 1);
        }
    }

    @Override
    protected void supprimer() {
        System.out.println("-------------------------");
        System.out.println("Choisissez le séjour à supprimer : ");
        afficher();
        int reponse = Menu.choix(1, Menu.listeSejours.size());

        Menu.listeSejours.remove(reponse-1);
        System.out.println("Le séjour n°"+reponse+" a bien été supprimé.");
        lister();
    }

    static void afficher(){
        for (int i = 0; i < Menu.listeSejours.size(); i++) {
            System.out.print(""+(i+1)+" : ");
            Menu.listeSejours.get(i).afficher();
            System.out.println("//////////");
        }
    }
}
