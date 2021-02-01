package dubois.airbnb.menu;

import dubois.airbnb.utilisateurs.Hote;
import dubois.airbnb.utilisateurs.Voyageur;

public class GestionVoyageurs extends Gestion{
    @Override
    public void lister(){
        System.out.println("-------------------------\n" +
                "Liste des voyageurs \n" +
                "Saisir une option :\n" +
                "1 : Ajouter un voyageur\n" +
                "2 : Supprimer un voyageur\n" +
                "3 : Voir la liste de tous les voyageurs\n" +
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
    public void ajouter(){
        try{
            System.out.println("Veuillez saisir le nom : ");
            Menu.scanner.nextLine();
            String nom = Menu.scanner.nextLine();

            System.out.println("Veuillez saisir le prénom : ");
            String prenom = Menu.scanner.nextLine();

            System.out.println("Veuillez saisir l'âge : ");
            int age = Menu.scanner.nextInt();


            Voyageur voyageur = new Voyageur(prenom, nom, age);
            Menu.listeVoyageurs.add(voyageur);

            System.out.println("Voici le nouveau voyageur enregistré : \n"
                    +nom+" "+prenom+ " ("+age+" ans ).");

            lister();
        }catch (Exception e){
            System.out.println("Vous n'avez pas renseigné correctement les données.");
            Menu.scanner.nextLine();
            lister();
        }
    }

    @Override
    public void supprimer(){
        System.out.println("-------------------------");
        System.out.println("Choisissez le voyageur à supprimer : ");
        afficher();
        int reponse = Menu.choix(1, Menu.listeVoyageurs.size());

        Menu.listeVoyageurs.remove(reponse-1);
        System.out.println("Le voyageur n°"+reponse+" a bien été supprimé.");
        lister();
    }

    static void afficher(){
        for (int i = 0; i < Menu.listeVoyageurs.size(); i++) {
            System.out.print(""+(i+1)+" : ");
            Menu.listeVoyageurs.get(i).afficher();
            System.out.println("//////////");
        }
    }
}
