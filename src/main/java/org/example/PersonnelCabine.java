package org.example;
import  java.time.LocalDate;

public class PersonnelCabine extends Employe {
    private String qualification;
    public PersonnelCabine(String identifiant, String nom, String adresse, String contact,
                           String numeroEmployé, LocalDate dateEmbauche,
                           String qualification) {
        super(identifiant, nom, adresse, contact, numeroEmployé, dateEmbauche);
        this.qualification = qualification;

    }

    @Override
    public String obtenirRole() {
        return "Je suis un personnel de cabine";
    }
    @Override
    public String obtenirInfos(){
        return super.obtenirInfos() + "/ Qualification : " + qualification;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
