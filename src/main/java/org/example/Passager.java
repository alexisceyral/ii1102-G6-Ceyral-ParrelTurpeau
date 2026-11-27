package org.example;

import java.util.ArrayList;
import java.util.List;

public class Passager extends Personne {

    private String passeport;

    public Passager(String identifiant, String nom, String adresse, String contact, String passeport) {
        super(identifiant, nom, adresse, contact);
        this.passeport = passeport;
    }

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos() + " / Passeport : " + passeport;
    }

    /** Liste les réservations de ce passager. */
    public List<Reservation> obtenirReservations() {
        List<Reservation> resultat = new ArrayList<>();
        for (Reservation r : Reservation.lister()) {
            if (r.getPassager() == this) {
                resultat.add(r);
            }
        }
        return resultat;
    }

    /** Avant suppression d'un passager, on supprime ses réservations. */
    @Override
    protected void avantSuppression() {
        for (Reservation r : obtenirReservations()) {
            Reservation.supprimer(r.getNumeroReservation());
        }
    }

    // ===================== CRUD =====================

    /** CREATE : ajoute un passager. */
    public static boolean ajouter(Passager passager) {
        return Personne.ajouter(passager);
    }

    /** READ : cherche un passager par son identifiant (null si absent). */
    public static Passager chercher(String identifiant) {
        Personne p = Personne.chercher(identifiant);
        return (p instanceof Passager passager) ? passager : null;
    }

    /** READ : cherche un passager par son numéro de passeport. */
    public static Passager chercherParPasseport(String passeport) {
        for (Passager p : listerPassagers()) {
            if (p.passeport != null && p.passeport.equals(passeport)) {
                return p;
            }
        }
        return null;
    }

    /** READ : liste tous les passagers. */
    public static List<Passager> listerPassagers() {
        return listerParType(Passager.class);
    }

    /** UPDATE : modifie un passager. Un paramètre null n'est pas modifié. */
    public static boolean modifier(String identifiant, String nom, String adresse, String contact,
                                   String passeport) {
        Passager p = chercher(identifiant);
        if (p == null) {
            return false;
        }
        Personne.modifier(identifiant, nom, adresse, contact);
        if (passeport != null) p.passeport = passeport;
        return true;
    }

    /** DELETE : supprime un passager (et ses réservations) par son identifiant. */
    public static boolean supprimer(String identifiant) {
        return chercher(identifiant) != null && Personne.supprimer(identifiant);
    }

    // ===================== Getters / Setters =====================

    public String getPasseport() {
        return passeport;
    }

    public void setPasseport(String passeport) {
        this.passeport = passeport;
    }
}
