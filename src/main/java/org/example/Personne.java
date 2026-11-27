package org.example;
import java.util.Map;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.ArrayList;

import java.util.Scanner;

public class Personne {
    private String identifiant;
    private String nom;
    private String adresse;
    private String contact;

    protected Personne(String identifiant, String nom, String adresss, String contact) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;

    }

    public String obtenirInfo() {
        return "[" + identifiant + "] " + nom + " | Adresse : " + adresse + " | Contact : " + contact;
    }

    // ===================== CRUD (IA) =====================
    // Registre commun à toutes les personnes (pilotes, personnel cabine, passagers...).
    // L'identifiant est unique pour toutes les personnes.
    private static final Map<String, Personne> personnes = new LinkedHashMap<>();

    /** CREATE : ajoute une personne. Refusé si null ou si l'identifiant existe déjà. */
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

    /** READ : liste toutes les personnes. */
    public static List<Personne> listerPersonnes() {
        return new ArrayList<>(personnes.values());
    }

    /** UPDATE : modifie une personne. Un paramètre null laisse la valeur actuelle. */
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
        return personnes.remove(identifiant) != null;
    }
}
