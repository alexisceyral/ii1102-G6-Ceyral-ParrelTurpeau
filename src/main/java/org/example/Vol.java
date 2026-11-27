package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Vol {
    private String numeroVol;
    private Aéroport origine;
    private Aéroport destination;
    private LocalDateTime dateHeureDepart;
    private LocalDateTime dateHeureArrivee;
    private EtatVol etat;
    private Avion avion;
    private final List<Reservation> reservations = new ArrayList<>();

    private static final Map<String, Vol> vols = new LinkedHashMap<>();

    public Vol(String numeroVol, Aéroport origine, Aéroport destination,
               LocalDateTime dateHeureDepart, LocalDateTime dateHeureArrivee) {
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = EtatVol.PLANIFIE;
    }

    public void planifierVol(Avion avion, LocalDateTime depart, LocalDateTime arrivee) {

    }

    public void annulerVol() {
        etat = EtatVol.ANNULE;
    }

    public void modifierVol(LocalDateTime depart, LocalDateTime arrivee) {
        dateHeureArrivee = arrivee;
        dateHeureDepart = depart;
    }

    public List<Passager> listingPassagers() {
        List<Passager> passagers = new ArrayList<>();
        for (Reservation reservation : reservations) {
            Passager passager = reservation.getPassager();
            if (reservation.getStatut() == StatutReservation.CONFIRMEE && !passagers.contains(passager)) {
                passagers.add(passager);
            }
        }
        passagers.sort(Comparator.comparing(Passager::getNom));
        return passagers;
    }

    public boolean chevauche(Vol autre) {
        return etat != EtatVol.ANNULE && autre.getEtat() != EtatVol.ANNULE
                && dateHeureDepart.isBefore(autre.getDateHeureArrivee())
                && dateHeureArrivee.isAfter(autre.getDateHeureDepart());
    }

    void ajouterReservation(Reservation reservation) {
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
        }
    }

    void retirerReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    // ===================== CRUD =====================

    /** CREATE : ajoute un vol et l'enregistre auprès de ses aéroports d'origine et de destination. */
    public static boolean ajouter(Vol vol) {
        if (vol == null || vol.numeroVol == null || vols.containsKey(vol.numeroVol)
                || vol.origine == null || vol.destination == null || vol.origine == vol.destination
                || vol.dateHeureDepart == null || vol.dateHeureArrivee == null
                || !vol.dateHeureArrivee.isAfter(vol.dateHeureDepart)) {
            return false;
        }
        vols.put(vol.numeroVol, vol);
        vol.origine.affecterVol(vol);
        vol.destination.affecterVol(vol);
        return true;
    }

    /** READ : cherche un vol par son numéro (null si absent). */
    public static Vol chercher(String numeroVol) {
        return vols.get(numeroVol);
    }

    /** READ : cherche les vols entre deux aéroports. */
    public static List<Vol> chercherParTrajet(Aéroport origine, Aéroport destination) {
        List<Vol> resultat = new ArrayList<>();
        for (Vol v : vols.values()) {
            if (v.origine == origine && v.destination == destination) {
                resultat.add(v);
            }
        }
        return resultat;
    }

    /** READ : liste tous les vols. */
    public static List<Vol> lister() {
        return new ArrayList<>(vols.values());
    }

    /** UPDATE : modifie les horaires et/ou l'état d'un vol. Un paramètre null n'est pas modifié. */
    public static boolean modifier(String numeroVol, LocalDateTime depart, LocalDateTime arrivee, EtatVol etat) {
        Vol v = vols.get(numeroVol);
        if (v == null) {
            return false;
        }
        LocalDateTime nouveauDepart = (depart != null) ? depart : v.dateHeureDepart;
        LocalDateTime nouvelleArrivee = (arrivee != null) ? arrivee : v.dateHeureArrivee;
        if (!nouvelleArrivee.isAfter(nouveauDepart)) {
            return false;
        }
        v.modifierVol(nouveauDepart, nouvelleArrivee);
        if (etat != null) v.etat = etat;
        return true;
    }

    /** DELETE : supprime un vol, ses réservations et ses liens avec l'avion et les aéroports. */
    public static boolean supprimer(String numeroVol) {
        Vol v = vols.remove(numeroVol);
        if (v == null) {
            return false;
        }
        for (Reservation r : new ArrayList<>(v.reservations)) {
            Reservation.supprimer(r.getNumeroReservation());
        }
        if (v.avion != null) {
            v.avion.retirerVol(v);
        }
        v.origine.retirerVol(v);
        v.destination.retirerVol(v);
        return true;
    }

    // ===================== Getters / Setters =====================

    public String getNumeroVol() {
        return numeroVol;
    }

    public Aéroport getOrigine() {
        return origine;
    }

    public Aéroport getDestination() {
        return destination;
    }

    public LocalDateTime getDateHeureDepart() {
        return dateHeureDepart;
    }

    public LocalDateTime getDateHeureArrivee() {
        return dateHeureArrivee;
    }

    public EtatVol getEtat() {
        return etat;
    }

    public void setEtat(EtatVol etat) {
        this.etat = etat;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }
}
