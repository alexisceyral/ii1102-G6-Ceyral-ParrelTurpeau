package org.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Personne {
    private String identifiant;
    private String nom;
    private String adresse;
    private String contact;

    // Registre commun à toutes les personnes (passagers, pilotes, personnel cabine...)
    // L'identifiant est donc unique pour toutes les personnes.
    private static final Map<String, Personne> personnes = new LinkedHashMap<>();

    protected Personne(String identifiant, String nom, String adresse, String contact) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
    }

    public String obtenirInfos() {
        return "[" + identifiant + "] " + nom + " | Adresse : " + adresse + " | Contact : " + contact;
    }

    // ===================== CRUD =====================

    /** CREATE : ajoute une personne. Refuse si null ou si l'identifiant existe déjà. */
    public static boolean ajouter(Personne personne) {
        if (personne == null || personne.identifiant == null || personnes.containsKey(personne.identifiant)) {
            return false;
        }
        personnes.put(personne.identifiant, personne);
        return true;
    }

    /** READ : cherche une personne par son identifiant (null si absente). */
    public static Personne chercher(String identifiant) {
        return personnes.get(identifiant);
    }

    /** READ : cherche les personnes dont le nom contient le texte donné (insensible à la casse). */
    public static List<Personne> chercherParNom(String nom) {
        List<Personne> resultat = new ArrayList<>();
        if (nom == null) {
            return resultat;
        }
        for (Personne p : personnes.values()) {
            if (p.nom != null && p.nom.toLowerCase().contains(nom.toLowerCase())) {
                resultat.add(p);
            }
        }
        return resultat;
    }

    /** READ : liste toutes les personnes. */
    public static List<Personne> listerPersonnes() {
        return new ArrayList<>(personnes.values());
    }

    /** READ : liste toutes les personnes d'un type donné (ex : Pilote.class). */
    protected static <T extends Personne> List<T> listerParType(Class<T> type) {
        List<T> resultat = new ArrayList<>();
        for (Personne p : personnes.values()) {
            if (type.isInstance(p)) {
                resultat.add(type.cast(p));
            }
        }
        return resultat;
    }

    /** UPDATE : modifie les informations d'une personne. Un paramètre null n'est pas modifié. */
    public static boolean modifier(String identifiant, String nom, String adresse, String contact) {
        Personne p = personnes.get(identifiant);
        if (p == null) {
            return false;
        }
        if (nom != null) p.nom = nom;
        if (adresse != null) p.adresse = adresse;
        if (contact != null) p.contact = contact;
        return true;
    }

    /** DELETE : supprime une personne par son identifiant. */
    public static boolean supprimer(String identifiant) {
        Personne p = personnes.get(identifiant);
        if (p == null) {
            return false;
        }
        p.avantSuppression();
        personnes.remove(identifiant);
        return true;
    }

    /** Point d'extension : nettoyage des liens avant suppression (redéfini dans Passager). */
    protected void avantSuppression() {
    }

    // ===================== Getters / Setters =====================

    public String getIdentifiant() {
        return identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
