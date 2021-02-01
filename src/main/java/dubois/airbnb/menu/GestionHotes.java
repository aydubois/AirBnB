package dubois.airbnb.menu;

import dubois.airbnb.utilisateurs.Hote;

public class GestionHotes extends Gestion{
    @Override
    public void lister(){
        System.out.println("-------------------------\n" +
                "Liste des hôtes \n" +
                "Saisir une option :\n" +
                "1 : Ajouter un hôte\n" +
                "2 : Supprimer un hôte\n" +
                "3 : Voir la liste de tous les hôtes\n" +
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

            System.out.println("Veuillez saisir le delai de réponse (en jours): ");
            int delai = Menu.scanner.nextInt();

            Hote hote = new Hote(prenom, nom, age, delai);
            Menu.listeHote.add(hote);

            System.out.println("Voici le nouvel hôte enregistré : \n"
                +nom+" "+prenom+ " ("+age+" ans ). Son délai de réponse est de "+delai+" jours.");

            lister();
        }catch (Exception e){
            System.out.println("Vous n'avez pas renseigné correctement les données.");
            Menu.scanner.nextLine();
            lister();
        }
    }

    @Override
    public void supprimer(){
        int increment = 0;

        System.out.println("-------------------------");
        System.out.println("Choisissez l'hôte à supprimer : ");
        afficher();
        int reponse = Menu.choix(1, Menu.listeHote.size());

        Menu.listeHote.remove(reponse-1);
        System.out.println("L'hôte n°"+reponse+" a bien été supprimé.");
        lister();
    }

    static void afficher(){
        for (int i = 0; i < Menu.listeHote.size(); i++) {
            System.out.print(""+(i+1)+" : ");
            Menu.listeHote.get(i).afficher();
            System.out.println("//////////");
        }
    }
}
