package org.example;
import java.util.Map;
import java.util.LinkedHashMap;

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

    // ===================== CRUD (IA) =====================
    private static final Map<String, Avion> avions = new LinkedHashMap<>();

    /** CREATE : ajoute un avion. Refusé si null ou si l'immatriculation existe déjà. */
    public static boolean ajouter(Avion avion) {
        if (avion == null || avion.Immatriculation == null || avions.containsKey(avion.Immatriculation)) {
            return false;
        }
        avions.put(avion.Immatriculation, avion);
        return true;
    }

    /** READ : cherche un avion par son immatriculation (null si absent). */
    public static Avion chercher(String immatriculation) {
        return avions.get(immatriculation);
    }

    /** READ : liste tous les avions. */
    public static List<Avion> lister() {
        return new ArrayList<>(avions.values());
    }

    /** UPDATE : modifie un avion. Un paramètre null laisse la valeur actuelle. */
    public static boolean modifier(String immatriculation, String modele, String capacite) {
        Avion a = avions.get(immatriculation);
        if (a == null) {
            return false;
        }
        if (modele != null) a.Modèle = modele;
        if (capacite != null) a.Capacité = capacite;
        return true;
    }

    /** DELETE : supprime un avion. Refusé s'il est encore affecté à des vols. */
    public static boolean supprimer(String immatriculation) {
        Avion a = avions.get(immatriculation);
        if (a == null || !a.vols.isEmpty()) {
            return false;
        }
        avions.remove(immatriculation);
        return true;
    }
}
