package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Reservation {
    private String numeroReservation;
    private LocalDate dateReservation;
    private StatutReservation statut;
    private Passager passager;
    private Vol vol;

    private static final Map<String, Reservation> reservations = new LinkedHashMap<>();

    public Reservation(String numeroReservation, LocalDate dateReservation, Passager passager, Vol vol) {
        this.numeroReservation = numeroReservation;
        this.dateReservation = dateReservation;
        this.passager = passager;
        this.vol = vol;
        this.statut = StatutReservation.EN_ATTENTE;
    }

    public void confirmerReservation() {
        statut = StatutReservation.CONFIRMEE;
    }

    public void annulerReservation() {
        statut = StatutReservation.ANNULEE;
    }

    // ===================== CRUD =====================

    /** CREATE : ajoute une réservation et la rattache à son vol. */
    public static boolean ajouter(Reservation reservation) {
        if (reservation == null || reservation.numeroReservation == null
                || reservations.containsKey(reservation.numeroReservation)
                || reservation.passager == null || reservation.vol == null) {
            return false;
        }
        reservations.put(reservation.numeroReservation, reservation);
        reservation.vol.ajouterReservation(reservation);
        return true;
    }

    /** READ : cherche une réservation par son numéro (null si absente). */
    public static Reservation chercher(String numeroReservation) {
        return reservations.get(numeroReservation);
    }

    /** READ : liste les réservations d'un passager. */
    public static List<Reservation> chercherParPassager(Passager passager) {
        List<Reservation> resultat = new ArrayList<>();
        for (Reservation r : reservations.values()) {
            if (r.passager == passager) {
                resultat.add(r);
            }
        }
        return resultat;
    }

    /** READ : liste les réservations d'un vol. */
    public static List<Reservation> chercherParVol(Vol vol) {
        List<Reservation> resultat = new ArrayList<>();
        for (Reservation r : reservations.values()) {
            if (r.vol == vol) {
                resultat.add(r);
            }
        }
        return resultat;
    }

    /** READ : liste toutes les réservations. */
    public static List<Reservation> lister() {
        return new ArrayList<>(reservations.values());
    }

    /** UPDATE : modifie une réservation. Un paramètre null n'est pas modifié. */
    public static boolean modifier(String numeroReservation, LocalDate dateReservation,
                                   StatutReservation statut, Vol vol) {
        Reservation r = reservations.get(numeroReservation);
        if (r == null) {
            return false;
        }
        if (dateReservation != null) r.dateReservation = dateReservation;
        if (statut != null) r.statut = statut;
        if (vol != null && vol != r.vol) {
            r.vol.retirerReservation(r);
            r.vol = vol;
            vol.ajouterReservation(r);
        }
        return true;
    }

    /** DELETE : supprime une réservation et la détache de son vol. */
    public static boolean supprimer(String numeroReservation) {
        Reservation r = reservations.remove(numeroReservation);
        if (r == null) {
            return false;
        }
        r.vol.retirerReservation(r);
        return true;
    }

    // ===================== Getters / Setters =====================

    public String getNumeroReservation() {
        return numeroReservation;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    public void setStatut(StatutReservation statut) {
        this.statut = statut;
    }

    public Passager getPassager() {
        return passager;
    }

    public Vol getVol() {
        return vol;
    }
}
