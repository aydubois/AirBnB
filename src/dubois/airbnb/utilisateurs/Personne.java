package dubois.airbnb.utilisateurs;

public class Personne {
    private static int id = 0;
    private int mIdentifiant;
    private String mPrenom;
    private String mNom;
    private int mAge;

    public Personne(String pPrenom, String pNom, int pAge){
        mPrenom = pPrenom;
        mNom = pNom;
        mAge = pAge;
        mIdentifiant = ++id;
    }
    public int getIdentifiant(){
        return mIdentifiant;
    }
    public void afficher(){
        System.out.print(mNom+ " "+mPrenom+ "  ("+ mAge+" ans) ");
    }
}
