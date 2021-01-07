package dubois.airbnb.menu;

import dubois.airbnb.utilisateurs.Hote;

public class GestionHotes {
    static void listerHotes(){
        System.out.println("-------------------------\n" +
                "Liste des hôtes \n" +
                "Saisir une option :\n" +
                "1 : Ajouter un hôte\n" +
                "2 : Supprimer un hôte\n" +
                "3 : Retour");
        switch (Menu.choix(3)){
            case 1:
                try{ajouterHote();}
                catch(Exception e){
                    System.out.println("Vous n'avez pas renseigné correctement les données.");
                    Menu.scanner.nextLine();
                    listerHotes();
                }
                break;
            case 2:
                try { supprimerHote();}
                catch (Exception e){
                    System.out.println("Vous n'avez pas renseigné correctement les données.");
                    Menu.scanner.nextLine();
                    listerHotes();
                }
                break;
            case 3:
                Menu.listerMenu();
                break;
        }
    }
    public static void ajouterHote()throws Exception{
        try{
            System.out.println("Veuillez saisir le nom : ");
            Menu.scanner.nextLine();
            String nom = Menu.scanner.nextLine();

            System.out.println("Veuillez saisir le prenom : ");
            String prenom = Menu.scanner.nextLine();

            System.out.println("Veuillez saisir l'âge : ");
            int age = Menu.scanner.nextInt();

            System.out.println("Veuillez saisir le delai de réponse (en jours): ");
            int delai = Menu.scanner.nextInt();

            Hote hote = new Hote(prenom, nom, age, delai);
            Menu.listeHote.add(hote);

            System.out.println("Voici le nouvel hôte enregistré : \n"
                +nom+" "+prenom+ " ("+age+" ans ). Son délai de réponse est de "+delai+" jours.");

            listerHotes();
        }catch (Exception e){
            throw e;
        }
    }

    public static void supprimerHote() throws Exception{
        int increment = 0;

        System.out.println("-------------------------");
        for (Hote unHote : Menu.listeHote) {
            increment++;
            System.out.print(increment+" : ");
            unHote.afficher();
        }
        System.out.println("Choisissez l'hôte à supprimer : ");

        int reponse = Menu.scanner.nextInt();
        if(reponse < 1 || reponse > increment){
            throw new Exception();
        }

        Menu.listeHote.remove(increment-1);
        System.out.println("L'hôte n°"+increment+" a bien été supprimé.");
        listerHotes();
    }
}
