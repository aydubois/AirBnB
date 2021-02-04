package dubois.airbnb.outils;

import dubois.airbnb.logements.Appartement;
import dubois.airbnb.logements.Logement;
import dubois.airbnb.utilisateurs.Hote;
import dubois.airbnb.utilisateurs.Personne;

/**
 * Préférer utiliser CompareGenericMultiple2
 */
@Deprecated
public class CompareGeneric<T> {
    private T object1;
    private T object2;
    private Class<?> type;

    public CompareGeneric(T pObject1, T pObject2){

        object1 = pObject1;
        object2 = pObject2;
        type = object1.getClass();

    }
    public <T> T getHigher(){
        if(Logement.class.isAssignableFrom(type)){
            return (T)getHigherLogement((Logement) object1, (Logement) object2);
        }
        if(Personne.class.isAssignableFrom(type)){

            return (T)getHigherPersonne((Personne) object1, (Personne) object2);
        }
        if(Hote.class.isAssignableFrom(type)){
            return (T)getHigherHote((Hote) object1, (Hote) object2);
        }
        return null;
    }
    public <T> T getLower(){
        if(type.isAssignableFrom(Logement.class)){
            return (T)getLowerLogement((Logement) object1, (Logement) object2);
        }
        if(type.isAssignableFrom(Personne.class)){
            return (T)getLowerPersonne((Personne) object1, (Personne) object2);
        }
        if(type.isAssignableFrom(Hote.class)){
            return (T)getLowerHote((Hote) object1, (Hote) object2);
        }
        return null;
    }

    private <T extends Logement> T getHigherLogement(T object1, T object2){
        return object1.getTarifParNuit() - object2.getTarifParNuit() < 0 ? object2 : object1;
    }
    private <T extends Personne> T getHigherPersonne(T object1, T object2){
        return object1.getAge() - object2.getAge() < 0 ? object2 : object1;
    }
    private <T extends Hote> T getHigherHote(T object1, T object2){
        return object1.getDelaiDeReponse() - object2.getDelaiDeReponse() < 0 ? object2 : object1;
    }

    private <T extends Logement> T getLowerLogement(T object1, T object2){
        return object1.getTarifParNuit() - object2.getTarifParNuit() > 0 ? object2 : object1;
    }
    private <T extends Personne> T getLowerPersonne(T object1, T object2){
        return object1.getAge() - object2.getAge() > 0 ? object2 : object1;
    }
    private <T extends Hote> T getLowerHote(T object1, T object2){
        return object1.getDelaiDeReponse() - object2.getDelaiDeReponse() > 0 ? object2 : object1;
    }
}
