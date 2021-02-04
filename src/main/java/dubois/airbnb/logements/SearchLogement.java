package dubois.airbnb.logements;

import dubois.airbnb.outils.AirBnBData;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SearchLogement {
    private final  int nbVoyageurs;
    private final  int tarifMinNuit;
    private final  int tarifMaxNuit;
    private final  int possedePiscine;
    private final  int possedeJardin;
    private final  int possedeBalcon;

    private final int POSSEDE = 1;
    private final int NEPOSSEDEPAS = 0;
    private final int NIMPORTE = -1;

    private SearchLogement(SearchBuilder builder){
        this.nbVoyageurs = builder.nbVoyageurs;
        this.tarifMinNuit = builder.tarifMinNuit;
        this.tarifMaxNuit = builder.tarifMaxNuit;
        this.possedePiscine = builder.possedePiscine;
        this.possedeJardin = builder.possedeJardin;
        this.possedeBalcon = builder.possedeBalcon;
    }

    public int getNbVoyageurs() {
        return nbVoyageurs;
    }

    public int getTarifMinNuit() {
        return tarifMinNuit;
    }

    public int getTarifMaxNuit() {
        return tarifMaxNuit;
    }

    public int isPossedePiscine() {
        return possedePiscine;
    }

    public int isPossedeJardin() {
        return possedeJardin;
    }

    public int isPossedeBalcon() {
        return possedeBalcon;
    }

    public static class SearchBuilder{
        private final int nbVoyageurs;
        private int tarifMinNuit;
        private int tarifMaxNuit;
        private int possedePiscine;
        private int possedeJardin;
        private int possedeBalcon;

        public SearchBuilder(int nbVoyageurs){
            this.nbVoyageurs = nbVoyageurs;
            this.tarifMinNuit = build().NIMPORTE;
            this.tarifMaxNuit = build().NIMPORTE;
            this.possedePiscine = build().NIMPORTE;
            this.possedeJardin = build().NIMPORTE;
            this.possedeBalcon = build().NIMPORTE;
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
            this.possedePiscine = possedePiscine ? build().POSSEDE : build().NEPOSSEDEPAS;
            return this;
        }

        public SearchBuilder possedeJardin(boolean possedeJardin){
            this.possedeJardin = possedeJardin ? build().POSSEDE : build().NEPOSSEDEPAS;
            return this;
        }

        public SearchBuilder possedeBalcon(boolean possedeBalcon){
            this.possedeBalcon = possedeBalcon ? build().POSSEDE : build().NEPOSSEDEPAS;
            return this;
        }
        public SearchLogement build() {
            SearchLogement searchLogement =  new SearchLogement(this);
            return searchLogement;
        }
    }

    public ArrayList<Logement> result(){
        return AirBnBData.getInstance().getListLogements().stream().filter(logement -> {
            Boolean logementOK = nbVoyageurs <= logement.getNbVoyageursMax();
            if(tarifMinNuit != NIMPORTE)
                logementOK = tarifMinNuit <= logement.getTarifParNuit();
            if(tarifMaxNuit != NIMPORTE)
                logementOK = tarifMaxNuit >= logement.getTarifParNuit();
            if(possedePiscine == POSSEDE){
                logementOK = logement instanceof Maison && ((Maison) logement).isPossedePiscine();
            }
            if(possedePiscine == NEPOSSEDEPAS){
                logementOK = (logement instanceof Maison && !((Maison) logement).isPossedePiscine())||logement instanceof Appartement;
            }
            if(possedeBalcon == POSSEDE){
                logementOK = logement instanceof Appartement && ((Appartement) logement).isPossedeBalson();
            }
            if(possedeBalcon == NEPOSSEDEPAS){
                logementOK = (logement instanceof Appartement && !((Appartement) logement).isPossedeBalson())||logement instanceof Maison;
            }
            if(possedeJardin == POSSEDE){
                logementOK = logement instanceof Maison && ((Maison) logement).isPossedeJardin();
            }
            if(possedeJardin == NEPOSSEDEPAS){
                logementOK = (logement instanceof Maison && !((Maison) logement).isPossedeJardin())||logement instanceof Appartement;
            }
            return logementOK;
        }).collect(Collectors.toCollection(ArrayList::new));


    }
}
