package org.example;
import java.util.List;
import java.util.ArrayList;
import  java.time.LocalDate;

public class PersonnelCabine extends Employe {
    private String qualification;
    public PersonnelCabine(String identifiant, String nom, String adresse, String contact,
                           String numeroEmployé, LocalDate dateEmbauche,
                           String qualification) {
        super(identifiant, nom, adresse, contact, numeroEmployé, dateEmbauche);
        this.qualification = qualification;

    }

    @Override
    public String obtenirRole() {
        return "Je suis un personnel de cabine";
    }
    @Override
    public String obtenirInfos(){
        return super.obtenirInfos() + "/ Qualification : " + qualification;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    // ===================== CRUD (IA) =====================

    /** CREATE : ajoute un membre du personnel cabine (dans le registre de Personne). */
    public static boolean ajouter(PersonnelCabine personnel) {
        return Personne.ajouter(personnel);
    }

    /** READ : cherche un membre du personnel cabine par son identifiant (null si absent). */
    public static PersonnelCabine chercher(String identifiant) {
        Personne p = Personne.chercher(identifiant);
        return (p instanceof PersonnelCabine) ? (PersonnelCabine) p : null;
    }

    /** READ : liste tout le personnel cabine. */
    public static List<PersonnelCabine> listerPersonnelCabine() {
        List<PersonnelCabine> resultat = new ArrayList<>();
        for (Personne p : Personne.listerPersonnes()) {
            if (p instanceof PersonnelCabine) {
                resultat.add((PersonnelCabine) p);
            }
        }
        return resultat;
    }

    /** UPDATE : modifie la qualification. Un paramètre null laisse la valeur actuelle. */
    public static boolean modifier(String identifiant, String qualification) {
        PersonnelCabine pc = chercher(identifiant);
        if (pc == null) {
            return false;
        }
        if (qualification != null) pc.qualification = qualification;
        return true;
    }

    /** DELETE : supprime un membre du personnel cabine par son identifiant. */
    public static boolean supprimer(String identifiant) {
        return chercher(identifiant) != null && Personne.supprimer(identifiant);
    }
}
