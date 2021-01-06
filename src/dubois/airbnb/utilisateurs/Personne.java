package dubois.airbnb.utilisateurs;

public class Personne {
    private String mPrenom;
    private String mNom;
    private int mAge;

    public Personne(String pPrenom, String pNom, int pAge){
        mPrenom = pPrenom;
        mNom = pNom;
        mAge = pAge;
    }

    public void afficher(){
        System.out.print(mNom+ " "+mPrenom+ "  ("+ mAge+" ans) ");
    }
}
