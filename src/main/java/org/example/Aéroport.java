package org.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Aéroport {
    private String nom;
    private String ville;
    private String description;
    private final List<Vol> volsDepart = new ArrayList<>();
    private final List<Vol> volsArrivee = new ArrayList<>();

    private static final Map<String, Aéroport> aeroports = new LinkedHashMap<>();

    public Aéroport(String nom, String ville, String description) {
        this.nom = nom;
        this.ville = ville;
        this.description = description;
    }

    public boolean affecterVol(Vol vol) {
        if (vol == null || vol.getOrigine() == vol.getDestination()) {
            return false;
        }
        boolean affecte = false;
        if (vol.getOrigine() == this && !volsDepart.contains(vol)) {
            volsDepart.add(vol);
            affecte = true;
        }
        if (vol.getDestination() == this && !volsArrivee.contains(vol)) {
            volsArrivee.add(vol);
            affecte = true;
        }
        return affecte;
    }

    void retirerVol(Vol vol) {
        volsDepart.remove(vol);
        volsArrivee.remove(vol);
    }

    // ===================== CRUD =====================

    /** CREATE : ajoute un aéroport. Refuse si null ou si le nom existe déjà. */
    public static boolean ajouter(Aéroport aeroport) {
        if (aeroport == null || aeroport.nom == null || aeroports.containsKey(aeroport.nom)) {
            return false;
        }
        aeroports.put(aeroport.nom, aeroport);
        return true;
    }

    /** READ : cherche un aéroport par son nom (null si absent). */
    public static Aéroport chercher(String nom) {
        return aeroports.get(nom);
    }

    /** READ : cherche les aéroports d'une ville. */
    public static List<Aéroport> chercherParVille(String ville) {
        List<Aéroport> resultat = new ArrayList<>();
        for (Aéroport a : aeroports.values()) {
            if (a.ville != null && a.ville.equalsIgnoreCase(ville)) {
                resultat.add(a);
            }
        }
        return resultat;
    }

    /** READ : liste tous les aéroports. */
    public static List<Aéroport> lister() {
        return new ArrayList<>(aeroports.values());
    }

    /** UPDATE : modifie un aéroport. Un paramètre null n'est pas modifié. */
    public static boolean modifier(String nom, String ville, String description) {
        Aéroport a = aeroports.get(nom);
        if (a == null) {
            return false;
        }
        if (ville != null) a.ville = ville;
        if (description != null) a.description = description;
        return true;
    }

    /** DELETE : supprime un aéroport. Refusé si des vols partent ou arrivent encore à cet aéroport. */
    public static boolean supprimer(String nom) {
        Aéroport a = aeroports.get(nom);
        if (a == null || !a.volsDepart.isEmpty() || !a.volsArrivee.isEmpty()) {
            return false;
        }
        aeroports.remove(nom);
        return true;
    }

    // ===================== Getters / Setters =====================

    public String getNom() {
        return nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Vol> getVolsDepart() {
        return new ArrayList<>(volsDepart);
    }

    public List<Vol> getVolsArrivee() {
        return new ArrayList<>(volsArrivee);
    }
}
