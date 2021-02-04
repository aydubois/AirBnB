package dubois.airbnb.outils;

import java.util.ArrayList;


/**
 * Permet de comparer de 2 à n objets de même type possédant l'implémentation Comparable<T>
 * @param <T extends Comparable<T>>
 */
public class CompareGenericMultiple2<T extends Comparable<T>>{
    private ArrayList<T> arrayObjects = new ArrayList<>();
    private Class<?> type;

    /**
     * Constructor
     * @param pObject1
     * @param pObject2
     * Les deux paramètres doivent être de même type et implémenter Comparable<T>
     * @throws ClassCastException
     */
    public CompareGenericMultiple2(T pObject1, T pObject2) throws ClassCastException{
        arrayObjects.add(pObject1);
        arrayObjects.add(pObject2);
        type = pObject1.getClass();

    }

    /**
     * Constructor
     * @param pArrayObjects Arraylist<T> avec T extends Comparable
     * @throws NullPointerException si la liste est vide.
     */
    public CompareGenericMultiple2(ArrayList<T> pArrayObjects) throws NullPointerException{

        if(pArrayObjects != null){
            arrayObjects = pArrayObjects;
            type = pArrayObjects.get(0).getClass();
        }else{
            throw new NullPointerException();
        }
    }

    /**
     * Permet d'ajouter un Objet de même type à la liste comparative.
     * @param pObject
     */
    public void add(T pObject){
        arrayObjects.add(pObject);
    }

    /**
     * Permet de supprimer un Objet de la liste comparative.
     * @param pObject
     */
    public void remove(T pObject){
        arrayObjects.remove(pObject);
    }

    /**
     * @return ArrayList<T> comparative
     */
    public ArrayList<T> getListCompare(){
        return arrayObjects;
    }


    /**
     *
     * @param <T>
     * @return L'objet de la liste le plus "High". Dépend du type d'objet
     * @throws NullPointerException
     * @throws ClassCastException
     */
    public <T extends Comparable<T>> T getHigher() throws NullPointerException, ClassCastException {
        T max = (T) arrayObjects.get(0);

        for (int i = 0; i < arrayObjects.size()-1; i++) {
            T a = (T) arrayObjects.get(i+1);
            if(a.compareTo(max) > 0)
                max = a;
        }
        return max;
    }
    /**
     *
     * @param <T>
     * @return L'objet de la liste le plus "Low". Dépend du type d'objet
     * @throws NullPointerException
     * @throws ClassCastException
     */
    public <T extends Comparable<T>> T getLower() throws NullPointerException, ClassCastException {
        T min = (T) arrayObjects.get(0);

        for (int i = 0; i < arrayObjects.size()-1; i++) {
            T a = (T) arrayObjects.get(i+1);
            if(a.compareTo(min) < 0)
                min = a;
        }
        return min;
    }

}
