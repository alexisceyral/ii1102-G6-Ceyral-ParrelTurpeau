package org.example;
import java.util.Map;
import java.util.LinkedHashMap;

import java.util.ArrayList;
import java.util.List;

public class Aéroport {
    private String Nom;
    private String Ville;
    private String Description;

    public String getDescription() {
        return Description;
    }

    public String getVille() {
        return Ville;
    }

    public String getNom() {
        return Nom;
    }

    public void affecterVol(String description,String ville,String nom) {
        Description = description;
        Ville = ville;
        Nom = nom;
    }
    private final List<Vol> volsDepart = new ArrayList<>();
    private final List<Vol> volsArrivee = new ArrayList<>();

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

    // ===================== CRUD (IA) =====================
    private static final Map<String, Aéroport> aeroports = new LinkedHashMap<>();

    /** CREATE : ajoute un aéroport. Refusé si null ou si le nom existe déjà. */
    public static boolean ajouter(Aéroport aeroport) {
        if (aeroport == null || aeroport.Nom == null || aeroports.containsKey(aeroport.Nom)) {
            return false;
        }
        aeroports.put(aeroport.Nom, aeroport);
        return true;
    }

    /** READ : cherche un aéroport par son nom (null si absent). */
    public static Aéroport chercher(String nom) {
        return aeroports.get(nom);
    }

    /** READ : liste tous les aéroports. */
    public static List<Aéroport> lister() {
        return new ArrayList<>(aeroports.values());
    }

    /** UPDATE : modifie un aéroport. Un paramètre null laisse la valeur actuelle. */
    public static boolean modifier(String nom, String ville, String description) {
        Aéroport a = aeroports.get(nom);
        if (a == null) {
            return false;
        }
        if (ville != null) a.Ville = ville;
        if (description != null) a.Description = description;
        return true;
    }

    /** DELETE : supprime un aéroport. Refusé si des vols y partent ou y arrivent encore. */
    public static boolean supprimer(String nom) {
        Aéroport a = aeroports.get(nom);
        if (a == null || !a.volsDepart.isEmpty() || !a.volsArrivee.isEmpty()) {
            return false;
        }
        aeroports.remove(nom);
        return true;
    }
}
