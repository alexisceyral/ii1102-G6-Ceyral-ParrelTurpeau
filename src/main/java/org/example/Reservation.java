package org.example;
import java.util.Map;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.ArrayList;

import java.time.LocalDateTime;

public class Reservation {
    private static int compteur = 1;

    private String numeroReservation;
    private LocalDateTime dateReservation;
    private StatutReservation statut;

    private Passager passager;
    private Vol vol;

    Reservation(Passager passager, Vol vol) {
        if (passager == null || vol == null ) {
            throw new IllegalAccessException("Une réservation exige un passager et un vol.");
        }
    }
    this.numeroReservation = "RES-" + compteur;
    compteur++;
    this.dateReservation = LocalDateTime.now();
    this.statut = StatutReservation.EN_ATTENTE;
    this.passager = passager;
    this.vol = vol;

   public void confirmerReservation(){
        if (statut != StatutReservation.EN_ATTENTE){
            throw new IllegalStateException("Seulement les réservation en attente peut etre confirmée.");
        }
        statut = StatutReservation.CONFIRMEE;
   }
   public void annulerReservation () {
       if (statut == StatutReservation.ANNULEE){
           throw new IllegalStateException("Cette réservation est déjà annulée.");

       }
       statut = StatutReservation.ANNULEE;
   }
    public void modifierReservation(Vol nouveauVol) {
        if (nouveauVol == null) {
            throw new IllegalArgumentException("Le nouveau vol ne peut pas être nul.");
        }
        if (statut == StatutReservation.ANNULEE) {
            throw new IllegalStateException("Impossible de modifier une réservation annulée.");
        }
        this.vol = nouveauVol;
        this.statut = StatutReservation.EN_ATTENTE;   // il faudra la reconfirmer
    }

    // ===== Getters (pas de setters : on passe par les méthodes ci-dessus) =====
    public String getNumeroReservation() { return numeroReservation; }
    public LocalDateTime getDateReservation() { return dateReservation; }
    public StatutReservation getStatut() { return statut; }
    public Passager getPassager() { return passager; }
    public Vol getVol() { return vol; }

    @Override
    public String toString() {
        return numeroReservation + " | " + passager.getNom() + " | " + statut;
    }

    // ===================== CRUD (IA) =====================
    private static final Map<String, Reservation> reservations = new LinkedHashMap<>();

    /** CREATE : enregistre une réservation. Refusé si null ou si le numéro existe déjà. */
    public static boolean ajouter(Reservation reservation) {
        if (reservation == null || reservation.numeroReservation == null
                || reservations.containsKey(reservation.numeroReservation)) {
            return false;
        }
        reservations.put(reservation.numeroReservation, reservation);
        return true;
    }

    /** READ : cherche une réservation par son numéro (null si absente). */
    public static Reservation chercher(String numeroReservation) {
        return reservations.get(numeroReservation);
    }

    /** READ : liste toutes les réservations. */
    public static List<Reservation> lister() {
        return new ArrayList<>(reservations.values());
    }

    /** UPDATE : change le vol d'une réservation (réutilise modifierReservation). */
    public static boolean modifier(String numeroReservation, Vol nouveauVol) {
        Reservation r = reservations.get(numeroReservation);
        if (r == null) {
            return false;
        }
        r.modifierReservation(nouveauVol);
        return true;
    }

    /** DELETE : supprime une réservation par son numéro. */
    public static boolean supprimer(String numeroReservation) {
        return reservations.remove(numeroReservation) != null;
    }
}
