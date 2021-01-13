package clonageDeList;

import java.util.ArrayList;

public class ListeDeStringBuilder implements Cloneable {
        ArrayList contenu;

        public ListeDeStringBuilder() {
            contenu = new ArrayList();
        }

        private String referenceDuContenu() {
            return contenu.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(contenu));
        }

        private String reference() {
            return getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(this));
        }

        private String references() {
            return reference() + "(" + referenceDuContenu() + ")";
        }

        public void remplacePar(ArrayList l) {
            contenu = l;
        }

        public void ajoute(StringBuilder s) {
            contenu.add(s);
        }

        public void supprimePremierCaractere(int i) {
            ((StringBuilder) contenu.get(i)).deleteCharAt(0);
        }

        public String toStringAvecReferences() {
            return reference() + "(" + referenceDuContenu() + ")" + contenu.toString();
        }

        @Override
        public String toString() {
            return contenu.toString();
        }

        @Override
        public ListeDeStringBuilder clone() {
            ListeDeStringBuilder listeDeStringBuilder = null;
            try {
                 listeDeStringBuilder = (ListeDeStringBuilder) super.clone();
                 listeDeStringBuilder.contenu = new ArrayList();
                for (Object s: contenu  ) {
                    listeDeStringBuilder.contenu.add(new StringBuilder((StringBuilder)s));
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return listeDeStringBuilder;
        }
    }

