package org.example;

import java.time.LocalDate;
import java.util.List;

public class PersonnelCabine extends Employe {

    private String qualification;

    public PersonnelCabine(String identifiant, String nom, String adresse, String contact,
                           String numeroEmploye, LocalDate dateEmbauche, String qualification) {
        super(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.qualification = qualification;
    }

    @Override
    public String obtenirRole() {
        return "Je suis un membre du personnel cabine";
    }

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos() + " / Qualification : " + qualification;
    }

    // ===================== CRUD =====================

    /** CREATE : ajoute un membre du personnel cabine. */
    public static boolean ajouter(PersonnelCabine personnel) {
        return Personne.ajouter(personnel);
    }

    /** READ : cherche un membre du personnel cabine par son identifiant (null si absent). */
    public static PersonnelCabine chercher(String identifiant) {
        Personne p = Personne.chercher(identifiant);
        return (p instanceof PersonnelCabine pc) ? pc : null;
    }

    /** READ : liste tout le personnel cabine. */
    public static List<PersonnelCabine> listerPersonnelCabine() {
        return listerParType(PersonnelCabine.class);
    }

    /** UPDATE : modifie un membre du personnel cabine. Un paramètre null n'est pas modifié. */
    public static boolean modifier(String identifiant, String nom, String adresse, String contact,
                                   String qualification) {
        PersonnelCabine pc = chercher(identifiant);
        if (pc == null) {
            return false;
        }
        Personne.modifier(identifiant, nom, adresse, contact);
        if (qualification != null) pc.qualification = qualification;
        return true;
    }

    /** DELETE : supprime un membre du personnel cabine par son identifiant. */
    public static boolean supprimer(String identifiant) {
        return chercher(identifiant) != null && Personne.supprimer(identifiant);
    }

    // ===================== Getters / Setters =====================

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
