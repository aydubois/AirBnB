package dubois.airbnb.utilisateurs;

public class Personne implements Comparable<Personne> {
    private static int id = 0;
    private final int mIdentifiant;
    protected final String mPrenom;
    protected final String mNom;
    protected final int mAge;

    /**
     * Creates a new Person
     * @param pPrenom
     * @param pNom
     * @param pAge
     */
    public Personne(String pPrenom, String pNom, int pAge){
        mPrenom = pPrenom;
        mNom = pNom;
        mAge = pAge;
        mIdentifiant = ++id;
    }

    /**
     * @return (int) id
     */
    public int getIdentifiant(){
        return mIdentifiant;
    }

    /**
     * @return (String) lastname
     */
    public String getNom(){
        return mNom;
    }

    /**
     * @return (String) firstname
     */
    public String getPrenom(){
        return mPrenom;
    }

    /**
     * @return (int) age
     */
    public int getAge(){
        return mAge;
    }

    /**
     * Displays an explanatory text on person
     */
    public void afficher(){
        System.out.print(mNom+ " "+mPrenom+ "  ("+ mAge+" ans) ");
    }

    /**
     * @param pers : (Personne) person that you want to compare with the current person.
     * Comparison made on the age.
     * @return (int) result of :  this.age - pers.age
     */
    @Override
    public int compareTo(Personne pers) {
        return mAge - pers.mAge ;
    }
}
