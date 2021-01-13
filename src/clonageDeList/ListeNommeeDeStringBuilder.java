package clonageDeList;

public class ListeNommeeDeStringBuilder extends ListeDeStringBuilder {
    String nom;
    public ListeNommeeDeStringBuilder(String n) {
        super();
        nom = n;
    }
    @Override
    public String toString() {
        return nom + " " + super.toString();
    }

}
