package org.example;
import java.time.LocalDate;


public class Employe extends Personne {
    private String numeroEmploye;
    private LocalDate dateEmbauche;

    protected Employe(String identifiant, String nom, String adresse, String contact, String numeroEmploye, LocalDate dateEmbauche) {
        super(identifiant, nom, adresse, contact);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }

    public abstract String obtenirRole ();

    @Override
    public String obtenirInfos () {
        return super.obtenirInfos()+ "N° employé : " + numeroEmploye
                + "/ Embauché le : " + dateEmbauche + " / Rôle :" + obtenirRole();

    }
}
