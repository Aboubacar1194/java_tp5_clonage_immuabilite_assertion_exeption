package clonageDeList;

import java.util.ArrayList;
import java.util.Arrays;

public class TestClonage {
    public static void main(String[] args) {
        ListeNommeeDeStringBuilder liste1 = new ListeNommeeDeStringBuilder( "1");
        liste1.ajoute( new StringBuilder("Bonjour"));
        ListeDeStringBuilder liste2 = liste1.clone();

        if (! liste1.toString().equals("1 [Bonjour]")
                || ! liste2.toString().equals("1 [Bonjour]")) {
            System.err.println(liste1.toString());
            System.err.println(liste2.toString());
            throw new Error( "Revoir la methode clone !");
        }

        liste1.supprimePremierCaractere(0);
        liste1.ajoute( new StringBuilder("Salut"));
        if (! liste1.toString().equals("1 [onjour, Salut]")
                || ! liste2.toString().equals("1 [Bonjour]")) {
            System.err.println(liste1.toStringAvecReferences());
            System.err.println(liste2.toStringAvecReferences());
            throw new Error( "Revoir la methode clone !");
        }

        @SuppressWarnings("unchecked")
        ArrayList l = new ArrayList( Arrays.asList( new StringBuilder("Au revoir")));
        liste2.remplacePar(l);
        if (! liste1.toString().equals("1 [onjour, Salut]")
                || ! liste2.toString().equals("1 [Au revoir]")) {
            System.err.println(liste1.toStringAvecReferences());
            System.err.println(liste2.toStringAvecReferences());
            throw new Error( "Revoir la methode clone !");
        }
    }
}
