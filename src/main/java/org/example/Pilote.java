package org.example;

import java.time.LocalDate;
import java.util.List;

public class Pilote extends Employe {

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
        return super.obtenirInfos() + " / License : " + license + " / Heures de vol : " + heuresDeVol;
    }

    // ===================== CRUD =====================

    /** CREATE : ajoute un pilote. */
    public static boolean ajouter(Pilote pilote) {
        return Personne.ajouter(pilote);
    }

    /** READ : cherche un pilote par son identifiant (null si absent). */
    public static Pilote chercher(String identifiant) {
        Personne p = Personne.chercher(identifiant);
        return (p instanceof Pilote pilote) ? pilote : null;
    }

    /** READ : cherche un pilote par son numéro de license. */
    public static Pilote chercherParLicense(String license) {
        for (Pilote p : listerPilotes()) {
            if (p.license != null && p.license.equals(license)) {
                return p;
            }
        }
        return null;
    }

    /** READ : liste tous les pilotes. */
    public static List<Pilote> listerPilotes() {
        return listerParType(Pilote.class);
    }

    /** UPDATE : modifie un pilote. Un paramètre null n'est pas modifié. */
    public static boolean modifier(String identifiant, String nom, String adresse, String contact,
                                   String license, Double heuresDeVol) {
        Pilote p = chercher(identifiant);
        if (p == null) {
            return false;
        }
        Personne.modifier(identifiant, nom, adresse, contact);
        if (license != null) p.license = license;
        if (heuresDeVol != null) p.heuresDeVol = heuresDeVol;
        return true;
    }

    /** DELETE : supprime un pilote par son identifiant. */
    public static boolean supprimer(String identifiant) {
        return chercher(identifiant) != null && Personne.supprimer(identifiant);
    }

    // ===================== Getters / Setters =====================

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
}
