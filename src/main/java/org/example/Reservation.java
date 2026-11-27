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
       if (statut == StatutReservation.ANNULEE)
   }
}
