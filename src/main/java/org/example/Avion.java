package org.example;

import java.util.ArrayList;
import java.util.List;

public class Avion {
    private String Immatriculation;
    private String Modèle;
    private String Capacité;

    public void affecterVol(Vol vol){

    }
    private final List<Vol> vols = new ArrayList<>();

    public boolean verifierDisponibilite(Vol nouveauVol) {
        for (Vol vol : vols) {
            if (vol.chevauche(nouveauVol)) {
                return false;
            }
        }
        return true;
    }

    public boolean affecterVol(Vol vol) {
        if (vol == null || vols.contains(vol) || !verifierDisponibilite(vol)) {
            return false;
        }
        vols.add(vol);
        vol.setAvion(this);
        return true;
    }

}
