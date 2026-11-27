package org.example;

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
}
