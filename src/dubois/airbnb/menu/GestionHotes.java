package dubois.airbnb.menu;

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
                supprimerHote();
                break;
            case 3:
                Menu.listerMenu();
                break;
        }
    }
    public static void ajouterHote() throws Exception{
        System.out.println("Veuillez saisir le nom : ");
        Menu.scanner.nextLine();
        String nom = Menu.scanner.nextLine();
        System.out.println("Veuillez saisir le prenom : ");
        String prenom = Menu.scanner.nextLine();
        System.out.println("Veuillez saisir l'âge : ");
        int age = Menu.scanner.nextInt();

        System.out.println("Voici le nouvel hôte enregistré : \n"
            +nom+" "+prenom+ " ("+age+").");
    }
    public static void supprimerHote(){

    }
}
