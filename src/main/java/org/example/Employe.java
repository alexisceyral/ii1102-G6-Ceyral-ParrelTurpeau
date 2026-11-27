package org.example;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;


public class Employe extends Personne {
    private String numeroEmploye;
    private LocalDate dateEmbauche;

    protected Employe(String identifiant, String nom, String adresse, String contact, String numeroEmploye, LocalDate dateEmbauche) {
        super(identifiant, nom, adresse, contact);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }

    public abstract String obtenirRole ();

    @Override
    public String obtenirInfos () {
        return super.obtenirInfos()+ "N° employé : " + numeroEmploye
                + "/ Embauché le : " + dateEmbauche + " / Rôle :" + obtenirRole();

    }

    // ===================== CRUD (IA) =====================
    // Les employés sont stockés dans le registre de Personne ;
    // la création passe par Pilote.ajouter ou PersonnelCabine.ajouter.

    /** READ : cherche un employé par son identifiant (null si absent ou si ce n'est pas un employé). */
    public static Employe chercher(String identifiant) {
        Personne p = Personne.chercher(identifiant);
        return (p instanceof Employe) ? (Employe) p : null;
    }

    /** READ : liste tous les employés. */
    public static List<Employe> listerEmployes() {
        List<Employe> resultat = new ArrayList<>();
        for (Personne p : Personne.listerPersonnes()) {
            if (p instanceof Employe) {
                resultat.add((Employe) p);
            }
        }
        return resultat;
    }

    /** UPDATE : modifie les informations d'employé. Un paramètre null laisse la valeur actuelle. */
    public static boolean modifier(String identifiant, String numeroEmploye, LocalDate dateEmbauche) {
        Employe e = chercher(identifiant);
        if (e == null) {
            return false;
        }
        if (numeroEmploye != null) e.numeroEmploye = numeroEmploye;
        if (dateEmbauche != null) e.dateEmbauche = dateEmbauche;
        return true;
    }

    /** DELETE : supprime un employé par son identifiant. */
    public static boolean supprimer(String identifiant) {
        return chercher(identifiant) != null && Personne.supprimer(identifiant);
    }
}
