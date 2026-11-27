package org.example;

import java.time.LocalDate;
import java.util.List;

public abstract class Employe extends Personne {
    private String numeroEmploye;
    private LocalDate dateEmbauche;

    protected Employe(String identifiant, String nom, String adresse, String contact, String numeroEmploye, LocalDate dateEmbauche) {
        super(identifiant, nom, adresse, contact);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }

    public abstract String obtenirRole();

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos() + " / N° employé : " + numeroEmploye
                + " / Embauché le : " + dateEmbauche + " / Rôle : " + obtenirRole();
    }

    // ===================== CRUD =====================
    // Employe est abstraite : la création se fait via Pilote.ajouter / PersonnelCabine.ajouter.

    /** READ : cherche un employé par son identifiant (null si absent ou si ce n'est pas un employé). */
    public static Employe chercher(String identifiant) {
        Personne p = Personne.chercher(identifiant);
        return (p instanceof Employe e) ? e : null;
    }

    /** READ : cherche un employé par son numéro d'employé. */
    public static Employe chercherParNumero(String numeroEmploye) {
        for (Employe e : listerEmployes()) {
            if (e.numeroEmploye != null && e.numeroEmploye.equals(numeroEmploye)) {
                return e;
            }
        }
        return null;
    }

    /** READ : liste tous les employés. */
    public static List<Employe> listerEmployes() {
        return listerParType(Employe.class);
    }

    /** UPDATE : modifie les informations d'un employé. Un paramètre null n'est pas modifié. */
    public static boolean modifier(String identifiant, String nom, String adresse, String contact,
                                   String numeroEmploye, LocalDate dateEmbauche) {
        Employe e = chercher(identifiant);
        if (e == null) {
            return false;
        }
        Personne.modifier(identifiant, nom, adresse, contact);
        if (numeroEmploye != null) e.numeroEmploye = numeroEmploye;
        if (dateEmbauche != null) e.dateEmbauche = dateEmbauche;
        return true;
    }

    /** DELETE : supprime un employé par son identifiant. */
    public static boolean supprimer(String identifiant) {
        return chercher(identifiant) != null && Personne.supprimer(identifiant);
    }

    // ===================== Getters / Setters =====================

    public String getNumeroEmploye() {
        return numeroEmploye;
    }

    public void setNumeroEmploye(String numeroEmploye) {
        this.numeroEmploye = numeroEmploye;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }
}
