package org.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Avion {
    private String immatriculation;
    private String modele;
    private int capacite;
    private final List<Vol> vols = new ArrayList<>();

    private static final Map<String, Avion> avions = new LinkedHashMap<>();

    public Avion(String immatriculation, String modele, int capacite) {
        this.immatriculation = immatriculation;
        this.modele = modele;
        this.capacite = capacite;
    }

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

    void retirerVol(Vol vol) {
        if (vols.remove(vol) && vol.getAvion() == this) {
            vol.setAvion(null);
        }
    }

    // ===================== CRUD =====================

    /** CREATE : ajoute un avion. Refuse si null ou si l'immatriculation existe déjà. */
    public static boolean ajouter(Avion avion) {
        if (avion == null || avion.immatriculation == null || avions.containsKey(avion.immatriculation)) {
            return false;
        }
        avions.put(avion.immatriculation, avion);
        return true;
    }

    /** READ : cherche un avion par son immatriculation (null si absent). */
    public static Avion chercher(String immatriculation) {
        return avions.get(immatriculation);
    }

    /** READ : cherche les avions d'un modèle donné. */
    public static List<Avion> chercherParModele(String modele) {
        List<Avion> resultat = new ArrayList<>();
        for (Avion a : avions.values()) {
            if (a.modele != null && a.modele.equalsIgnoreCase(modele)) {
                resultat.add(a);
            }
        }
        return resultat;
    }

    /** READ : liste tous les avions. */
    public static List<Avion> lister() {
        return new ArrayList<>(avions.values());
    }

    /** UPDATE : modifie un avion. Un paramètre null n'est pas modifié. */
    public static boolean modifier(String immatriculation, String modele, Integer capacite) {
        Avion a = avions.get(immatriculation);
        if (a == null) {
            return false;
        }
        if (modele != null) a.modele = modele;
        if (capacite != null) a.capacite = capacite;
        return true;
    }

    /** DELETE : supprime un avion et le détache des vols qui lui étaient affectés. */
    public static boolean supprimer(String immatriculation) {
        Avion a = avions.remove(immatriculation);
        if (a == null) {
            return false;
        }
        for (Vol v : new ArrayList<>(a.vols)) {
            a.retirerVol(v);
        }
        return true;
    }

    // ===================== Getters / Setters =====================

    public String getImmatriculation() {
        return immatriculation;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public List<Vol> getVols() {
        return new ArrayList<>(vols);
    }
}
