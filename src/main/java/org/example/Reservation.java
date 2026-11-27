package org.example;

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
}
