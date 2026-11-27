package org.example;

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
}
