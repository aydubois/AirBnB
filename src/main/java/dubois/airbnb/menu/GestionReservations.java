package dubois.airbnb.menu;

import dubois.airbnb.logements.Logement;
import dubois.airbnb.outils.MaDate;
import dubois.airbnb.reservations.Reservation;
import dubois.airbnb.reservations.Sejour;
import dubois.airbnb.reservations.SejourCourt;
import dubois.airbnb.reservations.SejourLong;
import dubois.airbnb.utilisateurs.Hote;
import dubois.airbnb.utilisateurs.Voyageur;

import java.util.Date;

public class GestionReservations extends Gestion{
    @Override
    public void lister() {
        System.out.println("-------------------------\n" +
                "Liste des réservations \n" +
                "Saisir une option :\n" +
                "1 : Ajouter un réservation\n" +
                "2 : Supprimer un réservation\n" +
                "3 : Voir la liste de tous les réservation\n" +
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
            MaDate dateDeReservation = new MaDate();

            System.out.println("Choisissez le séjour lié à cette réservation : ");
            GestionSejours.afficher();
            int numSejour = Menu.choix(1, Menu.listeSejours.size());
            Sejour sejour = Menu.listeSejours.get(numSejour-1);

            System.out.println("Choisissez le voyageur lié à cette réservation : ");
            GestionVoyageurs.afficher();
            int numVoyageur = Menu.choix(1, Menu.listeVoyageurs.size());
            Voyageur voyageur = Menu.listeVoyageurs.get(numVoyageur-1);

            try{
                Reservation reservation = new Reservation(sejour, voyageur, dateDeReservation);
                Menu.listeReservations.add(reservation);
                System.out.println("Voici la réservation ajoutée : ");
                reservation.afficher();
            }catch (Exception e){
                System.out.println("La date d'arrivée du séjour est erronée ou le nombre de voyageurs est incorrect.");
                System.out.println("Veuillez supprimer le séjour correspondant.");

            }

            lister();
        }catch (Exception e){
            System.out.println("Vous n'avez pas renseigné correctement les données.");
            Menu.scanner.nextLine();
            lister();
        }
    }

    @Override
    protected void supprimer() {
        System.out.println("-------------------------");
        System.out.println("Choisissez la réservation  à supprimer : ");
        afficher();
        int reponse = Menu.choix(1, Menu.listeReservations.size());

        Menu.listeReservations.remove(reponse-1);
        System.out.println("La réservation n°"+reponse+" a bien été supprimée.");
        lister();
    }

    static void afficher(){
        for (int i = 0; i < Menu.listeReservations.size(); i++) {
            System.out.print(""+(i+1)+" : ");
            Menu.listeReservations.get(i).afficher();
            System.out.println("//////////");
        }
    }

}
