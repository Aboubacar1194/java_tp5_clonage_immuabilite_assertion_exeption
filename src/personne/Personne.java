package personne;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Personne {

    /**
     * Constante qui represente l'annee courante.
     */
    private static final int ANNEE_COURANTE = new GregorianCalendar().get(Calendar.YEAR);

    /**
     * Nombre d'instances de Personne.
     */
    private static int nombreInstances = 0;

    /**
     * Numero de la personne correspondant a l'ordre de creation.
     * Utilise dans les constructeurs.
     */
    private int numero;

    /**
     * Nom de la personne.
     */
    private String nom;

    /**
     * Prenom de la personne.
     */
    private final String prenom;

    /**
     * Nom d'usage de la personne. Lorsque la personne est celibataire,
     * le nom d'usage est reduit a son nom. Lorsqu'elle est mariee, le
     * nom d'usage est forme du nom de la personne et du nom de son conjoint ,
     * separes par un tiret et ranges dans l'ordre alphabetique.
     * Utilise par les methodes {@link #changerStatut(Personne)},
     * {@link #getTitrePrenomNomDUsage()},
     * {@link #equals(Object)} et {@link #hashCode()}.
     */
    private String nomDUsage;

    /**
     * Annee de naissance de la personne.
     */
    private final int anneeNaissance;

    /**
     * Indique si la personne est celibataire.
     */
    private boolean celibataire;

    /**
     * Sexe de la personne : true pour masculin,
     * false pour feminin.
     */
    private final boolean sexeMasculin;

    /**
     * Variable utilisee dans les descriptions textuelles de la personne
     * pour accorder le genre des adjectifs. Utilisee par les methodes {@link #toString()},
     * {@link #afficheAge(int)} et {@link #afficheAge()}.
     */
    private String terminaison;

    /**
     * Conjoint de la personne.
     */
    private Personne conjoint;

    /**
     * Pere de la personne.
     */
    private final Personne pere;

    /**
     * Mere de la personne.
     */
    private final Personne mere;

    /**
     * Cree une personne en precisant son nom, son prenom, son annee de naissance,
     * son sexe (true pour masculin, false pour feminin), son pere et sa mere. Le nom
     * et le prenom ne peuvent pas etre null sinon le constructeur lance une
     * NullPointerException. L'annee de naissance doit etre inferieure ou egale a l'annee
     * courante, sinon c'est l'annee courante qui devient l'annee de naissance de la personne
     * creee.
     * @param unNom nom de la personne creee
     * @param unPrenom prenom de la personne creee
     * @param annee annee de naissance de la personne creee
     * @param masculin sexe de la personne creee
     * @param papa pere de la personne creee
     * @param maman mere de la personne creee
     */
    public Personne (String unNom, String unPrenom, int annee, boolean masculin,
                     Personne papa, Personne maman) {
        Objects.requireNonNull(unNom, "Le nom ne doit pas etre null");
        Objects.requireNonNull(unPrenom, "Le prenom ne doit pas etre null");
        if (annee > ANNEE_COURANTE) {
            annee = ANNEE_COURANTE;
        }
        nombreInstances++;
        numero = nombreInstances;
        nom = unNom;
        nomDUsage = unNom;
        prenom = unPrenom;
        anneeNaissance = annee;
        conjoint = null;
        celibataire = ( conjoint == null );
        sexeMasculin = masculin;
        terminaison = sexeMasculin ? "" : "e";
        pere = papa;
        mere = maman;
        System.out.println("Naissance de la " + numero
                + (numero==1?"ere":"eme")+ " personne.");
    }

    /**
     * Cree une personne sans pere ni mere, en precisant son nom, son prenom,
     * son annee de naissance et son sexe (true pour masculin, false pour feminin).
     * Le nom et le prenom ne peuvent pas etre nuqnetull sinon le constructeur lance une
     * NullPointerException. L'annee de naissance doit etre inferieure ou egale a l'annee
     * courante, sinon c'est l'annee courante qui devient l'annee de naissance de la personne
     * creee.
     * @param unNom nom de la personne creee
     * @param unPrenom prenom de la personne creee
     * @param annee annee de naissance de la personne creee
     * @param masculin sexe de la personne creee
     */
    public Personne (String unNom, String unPrenom, int annee, boolean masculin) {
        this( unNom, unPrenom, annee, masculin, null, null);
    }

    /**
     * Compare la personne avec une autre personne selon leur age. Dans le cas
     * de personnes d'age different, la valeur absolue de la valeur retournee
     * correspond a la difference d'age.
     * @param autrePersonne la personne dont l'age est compare avec celui de
     * la personne courante.
     * @return un entier strictement negatif si la personne courante est
     * plus jeune que la personne en parametre, 0 si elles ont le meme age,
     * un entier strictement positif si la personne courante est
     * plus vieille que la personne en parametre.
     */
    public int compareTo( Personne autrePersonne) {
        return getAge() - autrePersonne.getAge();
    }

    /**
     * Compare deux personnes selon leur age. Dans le cas de personnes
     * d'age different, la valeur absolue de la valeur retournee correspond
     * a la difference d'age.
     * @param p1 la personne a comparer avec celle en deuxieme parametre.
     * @param p2 la personne a comparer avec celle en premier parametre.
     * @return un entier strictement negatif si p1 est plus jeune que p2,
     * 0 si p1 et p2 ont le meme age, un entier strictement positif si p1 est
     * plus vieille que p2.
     */
    static public int compareTo( Personne p1, Personne p2) {
        return p1.compareTo(p2);
    }

    /**
     * Change le statut d'une personne de celibataire
     * a marie. Met a jour les variables conjoint, celibataire
     * et nomDUsage de la personne. Cette methoduqnete doit
     * systematiquement etre appeleee pour les deux conjoints.
     * Elle est utilisee par la methode {@link #marier(Personne)}.
     * @param fiance le conjoint de la personne.
     */
    private void changerStatut(Personne fiance) {
        conjoint = fiance;
        celibataire = false;
        if (nom.compareTo( conjoint.nom) < 0) {
            nomDUsage = nom + "-" + conjoint.nom;
        } else {
            nomDUsage = conjoint.nom + "-" + nom;
        }
    }

    /**
     * Marie la personne a une autre. Si l'une des personnes est deja mariee,
     * la methode ne fait rien.
     * @param fiance
     */
    public void marier(Personne fiance) throws ExceptionPersonneDejaMariee {
        if (conjoint == null && fiance.conjoint == null) {
            changerStatut(fiance);
            fiance.changerStatut(this);
        }else throw new ExceptionPersonneDejaMariee("Cette personne est deja mariee");
    }

    /**
     * Renvoie true si la personne est un ancetre (direct ou indirect)
     * de la personne en parametre.
     * @param petitfils personne dont on veut verifier qu'elle est un descendant de la personne courante.
     * @return true si la personne courante est un ancetre de petitFils, false sinon.
     */
    public boolean estAncetre(Personne petitfils) {
        if (petitfils == null) {
            return false;
        }
        return ( (petitfils.pere == this) || (petitfils.mere == this)
                || estAncetre( petitfils.pere) || estAncetre( petitfils.mere));
    }

    /**
     * Renvoie une chaine de caracteres formee du prenom et du nom (de naissance)
     * de la personne.
     * Utilisee par la methode {@link #afficheAieux(int)}.
     * @return la chaine de caracteres formee du prenom et du nom de la personne.
     */
    private String getPrenomNom() {
        return prenom + " " + nom ;
    }

    /**
     * Affiche la partie de l'arbre genealogique d'une personne pour les ancetres de la
     * personne de rang donne en parametre. A chaque rang correspond une indentation en
     * nombre de tabulations.
     * Methode utilisee par {@link #afficheAieux()}.
     */
    private void afficheAieux(int generation) {
        if ( generation == 0) {
            System.out.println( getPrenomNom());
        }
        if (pere != null) {
            for( int g = 0; g < generation; g++) {
                System.out.print( "\t");
            }
            System.out.print( "|___");
            System.out.print( "de pere " + pere.getPrenomNom());
            if (! pere.celibataire) {
                System.out.print( " epoux de " + pere.conjoint.getPrenomNom() );
            }
            System.out.println();
            pere.afficheAieux( generation + 1);
        }
        if ( mere != null) {
            for( int g = 0; g < generation; g++) {
                System.out.print( "\t");
            }
            System.out.print( "|___");
            System.out.print( "de mere " + mere.getPrenomNom());
            if (! mere.celibataire) {
                System.out.print( " epouse de " + mere.conjoint.getPrenomNom() );
            }
            System.out.println();
            mere.afficheAieux( generation + 1);
        }

    }

    /**
     * Affiche l'arbre genealogique de la personne sous une forme textuelle en augmentant
     * l'indentation a chaque generation. Par exemple, dans l'arbre suivant de la personne
     * Enguerrand Terieur, les grands parents sont Alain Terieur, Felicie Tassion et
     * Sarah Tatouille :
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Enguerrand Terieur
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|___de pere Aeropos Terieur
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     * |___de pere Alain Terieur epoux de  Felicie Tassion
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|___de pere Alex TÃ©rieur epoux de Anne Homalie
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|___de mere Anne Homalie epouse de Alex Terieur
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     * |___de mere Felicie Tassion epouse de Alain Terieur
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|___de mere Lara Tatouille
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     * |___de mere Sarah Tatouille<br>
     * @see #afficheAieux(int)
     */
    public void afficheAieux() {
        afficheAieux(0);
    }

    /**
     * Methode utilisee pour construire les descriptions textuelles de la personne, appelee
     * par les methode {@link #toString()}, {@link #afficheAge(int)} et {@link #afficheAge()}.
     * @return la decription textuelle de la personne commencant par son titre de civilite et son prenom
     * et se terminant par son nom de famille si la personne n'est pas mariee, ou son nom de famille et celui
     * de son conjoint si la personne est mariee.
     */
    private String getTitrePrenomNomDUsage() {
        String resultat = "";
        String civilite = sexeMasculin ? Civilite.M.toString()
                : ( celibataire ? Civilite.MLLE.toString() : Civilite.MME.toString());
        resultat += civilite + " ";
        resultat += prenom + " " + nomDUsage ;
        if (! celibataire ) {
            resultat += " ne" ;
            if (! sexeMasculin) {
                resultat +="e";
            }
            resultat += " " + nom;
        }
        return resultat;
    }

    /**
     * Renvoie une description textuelle de la personne de la forme suivante:
     *  "M. Harry Cover ne en 1976, celibataire."
     *  "Mlle Felicie Tassion nee en 1982, celibataire."
     *  "M. Harry Cover-Tassion ne en 1976, marie."
     *  "Mme Felicie Cover-Tassion nee en 1982, mariee."
     *  Lorsque la personne est mariee, le nom dans la description est le nom d'usage forme
     *  des noms de la personne et de son conjoint ranges dans l'ordre alphabetique. Le premier mot
     *  (le titre de civilite) change selon le sexe et le statut (marie ou celibataire) de la personne.
     *  Les adjectifs ne et marie s'accordent en fonction du genre.
     */
    @Override
    public String toString() {
        String resultat = getTitrePrenomNomDUsage();
        resultat += ", ne" + terminaison + " en " + anneeNaissance;
        resultat += celibataire ? ", celibataire." : ", marie" + terminaison +".";
        return resultat ;
    }

    /**
     * Affiche une phrase donnant l'age que la personne possede a la date donnee en parametre.
     * La phrase est de l'une des fomes suivantes, en fonction de la date en parametre :
     * "M. Harry Cover avait 1 an en 1977."
     * "M. Harry Cover avait 26 ans en 2002."
     * "M. Harry Cover aura 46 ans en 2022."
     * "M. Harry Cover a 44 ans en 2020."
     * "M. Harry Cover est ne en 1976."
     * "M. Harry Cover n'etait pas ne en 1900."
     * @param annee l'annee a laquelle l'age de la personne est calcule.
     */
    public void afficheAge(int annee) {
        int age = annee - anneeNaissance;
        String verbe, ans;

        System.out.print(getTitrePrenomNomDUsage());
        if (age < 0) {
            System.out.print( " n'etait pas ne" + terminaison);
        } else if (age == 0) {
            System.out.print( " est ne" + terminaison);
        } else {
            verbe = ( annee < ANNEE_COURANTE ) ? new String ("avait")
                    : ( ( annee == ANNEE_COURANTE ) ? new String ("a"): new String ("aura"));
            if (age > 1) {
                ans = "ans";
            } else {
                ans = "an";
            }
            System.out.print( " " + verbe + " " + age + " " + ans);
        }
        System.out.println( " en " + annee + ".");
    }

    /**
     * Affiche une phrase donnant l'age que la personne possede aujourd'hui.
     * La phrase est de l'une des formes suivantes :
     * "M. Harry Cover a 44 ans en 2020."
     * "Mlle Marion Nette est nee en 2020."
     */
    public void afficheAge() {
        afficheAge(ANNEE_COURANTE);
    }

    /**
     * Renvoie l'age de la personne. L'age est calcule par differenciation
     * entre l'annee de naissance et l'anne courante sans tenir compte du
     * mois de naissance. Ainsi les personnes nees le 01/01 et le 31/12
     * de la meme annee ont le meme age quelle que soit la date courante.
     * @return l'age de la personne.
     */
    public int getAge() {
        return ANNEE_COURANTE - anneeNaissance;
    }

    /**
     * Renvoie le nombre de personnes creees.
     * @return le nombre de personnes creees.
     */
    public static int getNombreInstances() {
        return nombreInstances;
    }

    /**
     * Teste l'egalite de la personne courante avec une autre instance.
     * Les instances sont differentes si o est null ou n'est pas une personne.
     * Elles sont considerees egales si ces ont deux personnes de meme nom,
     * meme prenom, meme annee de naissance, meme sexe et meme nom de conjoint,
     * sans verifier si elles ont les memes parents.
     * @param o l'objet compare a la personne courante.
     * @return true si la personne courante est egale a o, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if( this == o) {
            return true;
        }
        if ( ! (o instanceof Personne)) {
            return false;
        }
        return  nom.equals(((Personne)o).nom)
                && prenom.equals(((Personne)o).prenom)
                && anneeNaissance == ((Personne)o).anneeNaissance
                && sexeMasculin == ((Personne)o).sexeMasculin
                && nomDUsage.equals(((Personne)o).nomDUsage);
    }

    /**
     * Renvoie un entier qui resume la personne en utilisant son nom,
     * son prenom, son annee de naissance, son sexe et le nom de son conjoint.
     * Cet entier represente une valeur de hachage compatible avec equals : lorsque
     * deux personnes sont egales, elles ont necessairement la meme valeur de hachage.
     * En revanche, losrque deux personnes sont differentes, elles peuvent avoir la
     * meme valeur de hachage ou pas.
     * NB : Les personnes ne sont pas des objets immuables : leur statut (celibataire
     * ou marie) peut changer et leur valeur de hachage peut par consequent evoluer.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, anneeNaissance, sexeMasculin, nomDUsage);
    }

    public enum Civilite {
        MME("Mme"), M("M."), MLLE("Mlle");

        /**
         * Description textuelle du titre de civilite.
         */
        private String texte;

        /**
         * Creation du titre de civilite en precisant sa description textuelle.
         * @param s la description textuelle associee au titre de civilite.
         */
        private Civilite(String s) {
            texte = s;
        }

        /**
         * Description textuelle du titre de civilite.
         * @return la description textuelle du titre de civilite.
         */
        public String toString() {
            return texte;
        }

}}
