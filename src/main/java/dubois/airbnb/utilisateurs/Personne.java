package dubois.airbnb.utilisateurs;

import dubois.airbnb.outils.Compare;

public class Personne implements Comparable<Personne> {
    private static int id = 0;
    private int mIdentifiant;
    protected String mPrenom;
    protected String mNom;
    protected int mAge;

    public Personne(String pPrenom, String pNom, int pAge){
        mPrenom = pPrenom;
        mNom = pNom;
        mAge = pAge;
        mIdentifiant = ++id;
    }
    public int getIdentifiant(){
        return mIdentifiant;
    }
    public String getNom(){
        return mNom;
    }
    public String getPrenom(){
        return mPrenom;
    }
    public int getAge(){
        return mAge;
    }
    public void afficher(){
        System.out.print(mNom+ " "+mPrenom+ "  ("+ mAge+" ans) ");
    }


    @Override
    public int compareTo(Personne pers) {
        return mAge - pers.mAge ;
    }
}
