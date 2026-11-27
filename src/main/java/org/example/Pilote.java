package org.example;
import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;

public class Pilote extends Employe{

    private String license;
    private double heuresDeVol;

    public Pilote(String identifiant, String nom, String adresse, String contact,
                  String numeroEmployee, LocalDate dateEmbauche,
                  String license, double heuresDeVol) {
        super(identifiant, nom, adresse, contact, numeroEmployee, dateEmbauche);
        this.license = license;
        this.heuresDeVol = heuresDeVol;

    }
    @Override
    public String obtenirRole() {
        return "Je suis un Pilote";
    }
    @Override
    public String obtenirInfos() {
        return super.obtenirInfos() + "/ License :" + license + " / Heures de vol : " + heuresDeVol;

    }
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public double getHeuresDeVol() {
        return heuresDeVol;
    }

    public void setHeuresDeVol(double heuresDeVol) {
        this.heuresDeVol = heuresDeVol;
    }

    // ===================== CRUD (IA) =====================

    /** CREATE : ajoute un pilote (dans le registre de Personne). */
    public static boolean ajouter(Pilote pilote) {
        return Personne.ajouter(pilote);
    }

    /** READ : cherche un pilote par son identifiant (null si absent). */
    public static Pilote chercher(String identifiant) {
        Personne p = Personne.chercher(identifiant);
        return (p instanceof Pilote) ? (Pilote) p : null;
    }

    /** READ : liste tous les pilotes. */
    public static List<Pilote> listerPilotes() {
        List<Pilote> resultat = new ArrayList<>();
        for (Personne p : Personne.listerPersonnes()) {
            if (p instanceof Pilote) {
                resultat.add((Pilote) p);
            }
        }
        return resultat;
    }

    /** UPDATE : modifie un pilote. Un paramètre null laisse la valeur actuelle. */
    public static boolean modifier(String identifiant, String license, Double heuresDeVol) {
        Pilote p = chercher(identifiant);
        if (p == null) {
            return false;
        }
        if (license != null) p.license = license;
        if (heuresDeVol != null) p.heuresDeVol = heuresDeVol;
        return true;
    }

    /** DELETE : supprime un pilote par son identifiant. */
    public static boolean supprimer(String identifiant) {
        return chercher(identifiant) != null && Personne.supprimer(identifiant);
    }
}
