package dubois.airbnb.outils;

import dubois.airbnb.logements.Logement;
import dubois.airbnb.utilisateurs.Hote;
import dubois.airbnb.utilisateurs.Personne;

import java.util.ArrayList;

public class CompareGenericMultiple<T> {
    private ArrayList<T> arrayObjects = new ArrayList<>();
    private Class<?> type;
    public CompareGenericMultiple(T pObject1, T pObject2){
        arrayObjects.add(pObject1);
        arrayObjects.add(pObject2);
        type = pObject1.getClass();

    }
    public CompareGenericMultiple(ArrayList<T> pArrayObjects){
        arrayObjects = pArrayObjects;
        type = pArrayObjects.get(0).getClass();
    }
    public void add(T pObject){
        arrayObjects.add(pObject);
    }

    public void remove(T pObject){
        arrayObjects.remove(pObject);
    }


    public <T> T getHigher(){
        if(Logement.class.isAssignableFrom(type)){
            return (T)getHigherLogement((ArrayList<Logement>) arrayObjects);
        }
        if(Personne.class.isAssignableFrom(type)){

            return (T)getHigherPersonne((ArrayList<Personne>) arrayObjects);
        }
        if(Hote.class.isAssignableFrom(type)){
            System.out.println("Type Hote");
            return (T)getHigherHote((ArrayList<Hote>) arrayObjects);
        }
        return null;
    }
    public <T> T getLower(){
        if(type.isAssignableFrom(Logement.class)){
            return (T)getLowerLogement((ArrayList<Logement>) arrayObjects);
        }
        if(type.isAssignableFrom(Personne.class)){
            return (T)getLowerPersonne((ArrayList<Personne>) arrayObjects);
        }
        if(type.isAssignableFrom(Hote.class)){
            return (T)getLowerHote((ArrayList<Hote>) arrayObjects);
        }
        return null;
    }

    private Logement getHigherLogement(ArrayList<Logement> arrayObjects){
        int numHigh = -1;
        int maxTarif = -1;
        for (int i = 0; i < arrayObjects.size(); i++) {
            if(arrayObjects.get(i).getTarifParNuit() > maxTarif){
                maxTarif = arrayObjects.get(i).getTarifParNuit();
                numHigh = i;
            }
        }
        return arrayObjects.get(numHigh);
    }
    private Personne getHigherPersonne(ArrayList<Personne> arrayObjects){
        int numHigh = -1;
        int maxTarif = -1;
        for (int i = 0; i < arrayObjects.size(); i++) {
            if(arrayObjects.get(i).getAge() > maxTarif){
                maxTarif = arrayObjects.get(i).getAge();
                numHigh = i;
            }
        }
        return arrayObjects.get(numHigh);
    }
    private  Hote getHigherHote(ArrayList<Hote> arrayObjects){
        int numHigh = -1;
        int maxTarif = -1;
        for (int i = 0; i < arrayObjects.size(); i++) {
            if(arrayObjects.get(i).getDelaiDeReponse() > maxTarif){
                maxTarif = arrayObjects.get(i).getDelaiDeReponse();
                numHigh = i;
            }
        }
        return arrayObjects.get(numHigh);
    }

    private Logement getLowerLogement(ArrayList<Logement> arrayObjects){
        int numLow = -1;
        int minTarif = 100000000;
        for (int i = 0; i < arrayObjects.size(); i++) {
            if(arrayObjects.get(i).getTarifParNuit() < minTarif){
                minTarif = arrayObjects.get(i).getTarifParNuit();
                numLow = i;
            }
        }
        return arrayObjects.get(numLow);
    }
    private Personne getLowerPersonne(ArrayList<Personne> arrayObjects){
        int numLow = -1;
        int minTarif = 100000000;
        for (int i = 0; i < arrayObjects.size(); i++) {
            if(arrayObjects.get(i).getAge() < minTarif){
                minTarif = arrayObjects.get(i).getAge();
                numLow = i;
            }
        }
        return arrayObjects.get(numLow);
    }
    private Hote getLowerHote(ArrayList<Hote> arrayObjects){
        int numLow = -1;
        int minTarif = 100000000;
        for (int i = 0; i < arrayObjects.size(); i++) {
            if(arrayObjects.get(i).getDelaiDeReponse() < minTarif){
                minTarif = arrayObjects.get(i).getDelaiDeReponse();
                numLow = i;
            }
        }
        return arrayObjects.get(numLow);
    }
}
