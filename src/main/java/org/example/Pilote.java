package org.example;

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
}

