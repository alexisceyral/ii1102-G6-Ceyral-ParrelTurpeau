package org.example;

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
}
