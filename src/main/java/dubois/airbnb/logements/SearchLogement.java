package dubois.airbnb.logements;

import dubois.airbnb.outils.AirBnBData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SearchLogement {
    private final  int nbVoyageurs;
    private final  int tarifMinNuit;
    private final  int tarifMaxNuit;
    private final  int possedePiscine;
    private final  int possedeJardin;
    private final  int possedeBalcon;

    private static final int POSSEDE = 1;
    private static final int NEPOSSEDEPAS = 0;
    private static final int NIMPORTE = -1;

    //Autres possibilités :
    //private static  enum State {POSSEDE, NEPOSSEDEPAS, NIMPORTE};
    ////////////////////////
    //private static  enum State {
    // POSSEDE(1,"YES"),
    // NEPOSSEDEPAS(0,"NO"),
    // NIMPORTE(2, "NEITHER");
    // private int value;
    // private String name;5
    // private State(int value, string name){
    //  this.value = value;
    //  this.name = name;
    // };
    ///////////////////////

    private SearchLogement(SearchBuilder builder){
        this.nbVoyageurs = builder.nbVoyageurs;
        this.tarifMinNuit = builder.tarifMinNuit;
        this.tarifMaxNuit = builder.tarifMaxNuit;
        this.possedePiscine = builder.possedePiscine;
        this.possedeJardin = builder.possedeJardin;
        this.possedeBalcon = builder.possedeBalcon;
    }

    // Removal of the getters because useless for the moment


    public static class SearchBuilder{
        private final int nbVoyageurs;
        private int tarifMinNuit;
        private int tarifMaxNuit;
        private int possedePiscine;
        private int possedeJardin;
        private int possedeBalcon;

        public SearchBuilder(int nbVoyageurs){
            this.nbVoyageurs = nbVoyageurs;
            this.tarifMinNuit = NIMPORTE;
            this.tarifMaxNuit = NIMPORTE;
            this.possedePiscine = NIMPORTE;
            this.possedeJardin = NIMPORTE;
            this.possedeBalcon = NIMPORTE;
        }

        public SearchBuilder tarifMinNuit(int tarifMinNuit){
            this.tarifMinNuit = tarifMinNuit;
            return this;
        }

        public SearchBuilder tarifMaxNuit(int tarifMaxNuit){
            this.tarifMaxNuit = tarifMaxNuit;
            return this;
        }

        public SearchBuilder possedePiscine(boolean possedePiscine){
            this.possedePiscine = possedePiscine ? POSSEDE : NEPOSSEDEPAS;
            return this;
        }

        public SearchBuilder possedeJardin(boolean possedeJardin){
            this.possedeJardin = possedeJardin ? POSSEDE : NEPOSSEDEPAS;
            return this;
        }

        public SearchBuilder possedeBalcon(boolean possedeBalcon){
            this.possedeBalcon = possedeBalcon ? POSSEDE : NEPOSSEDEPAS;
            return this;
        }
        public SearchLogement build() {
            SearchLogement searchLogement =  new SearchLogement(this);
            return searchLogement;
        }
    }
    private Predicate<Logement> predicateNbVoyageurs(){
        return logement -> nbVoyageurs <= logement.getNbVoyageursMax();
    }

    private Predicate<Logement> predicateTarifMinNuit(){
        return logement -> (tarifMinNuit == NIMPORTE || tarifMinNuit <= logement.getTarifParNuit());
    }
    private Predicate<Logement> predicateTarifMaxNuit(){
        return logement -> (tarifMaxNuit == NIMPORTE || tarifMaxNuit >= logement.getTarifParNuit());
    }
    private  Predicate<Logement> predicatePossedePiscine(){
        return logement ->  (possedePiscine == POSSEDE && logement instanceof Maison && ((Maison) logement).isPossedePiscine()) ||
                (possedePiscine == NEPOSSEDEPAS && ((logement instanceof Maison && !((Maison) logement).isPossedePiscine())||logement instanceof Appartement))
            || (possedePiscine == NIMPORTE);
    }
    private  Predicate<Logement> predicatePossedeBalcon(){
        return logement ->  (possedeBalcon == POSSEDE && logement instanceof Appartement && ((Appartement) logement).isPossedeBalcon()) ||
                (possedeBalcon == NEPOSSEDEPAS && ((logement instanceof Appartement && !((Appartement) logement).isPossedeBalcon())||logement instanceof Maison))
                || (possedeBalcon == NIMPORTE);
    }
    private  Predicate<Logement> predicatePossedeJardin(){
        return logement ->  (possedeJardin == POSSEDE && logement instanceof Maison && ((Maison) logement).isPossedeJardin()) ||
                (possedeJardin == NEPOSSEDEPAS && ((logement instanceof Maison && !((Maison) logement).isPossedeJardin())||logement instanceof Appartement))
                || (possedeJardin == NIMPORTE);
    }
    /**
     *
     * @return (ArrayList<Logement>) list of housing matching the search criteria
     */
    public ArrayList<Logement> result(){

        return AirBnBData.getInstance().getListLogements().stream()
                .filter(predicatePossedeBalcon())
                .filter(predicatePossedePiscine())
                .filter(predicatePossedeJardin())
                .filter(predicateTarifMaxNuit())
                .filter(predicateTarifMinNuit())
                .filter(predicateNbVoyageurs())
                .sorted()
                //.sorted(Comparator.comparing(Logement::getTarifParNuit).reversed())
                .collect(Collectors.toCollection(ArrayList::new));


       /* return AirBnBData.getInstance().getListLogements().stream().filter(logement -> {
            Boolean logementOK = nbVoyageurs <= logement.getNbVoyageursMax();
            if(!logementOK){ return false;}
            if(tarifMinNuit != NIMPORTE){
                logementOK = tarifMinNuit <= logement.getTarifParNuit();
                if(!logementOK){ return false;}
            }
            if(tarifMaxNuit != NIMPORTE){
                logementOK = tarifMaxNuit >= logement.getTarifParNuit();
                if(!logementOK){ return false;}
            }
            if(possedePiscine == POSSEDE){
                logementOK = logement instanceof Maison && ((Maison) logement).isPossedePiscine();
                if(!logementOK){ return false;}
            }
            if(possedePiscine == NEPOSSEDEPAS){
                logementOK = (logement instanceof Maison && !((Maison) logement).isPossedePiscine())||logement instanceof Appartement;
                if(!logementOK){ return false;}
            }
            if(possedeBalcon == POSSEDE){
                logementOK = logement instanceof Appartement && ((Appartement) logement).isPossedeBalcon();
                if(!logementOK){ return false;}
            }
            if(possedeBalcon == NEPOSSEDEPAS){
                logementOK = (logement instanceof Appartement && !((Appartement) logement).isPossedeBalcon())||logement instanceof Maison;
                if(!logementOK){ return false;}
            }
            if(possedeJardin == POSSEDE){
                logementOK = logement instanceof Maison && ((Maison) logement).isPossedeJardin();
                if(!logementOK){ return false;}
            }
            if(possedeJardin == NEPOSSEDEPAS){
                logementOK = (logement instanceof Maison && !((Maison) logement).isPossedeJardin())||logement instanceof Appartement;
                if(!logementOK){ return false;}
            }
            return true;



        }).collect(Collectors.toCollection(ArrayList::new));*/


    }
}
