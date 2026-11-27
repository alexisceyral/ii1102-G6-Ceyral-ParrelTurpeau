package org.example;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Vol {
    private String numeroVol;
    private Aéroport Origine;
    private Aéroport Destination;
    private LocalDateTime DateHeureDepart;
    private LocalDateTime DateHeureArrivee;
    private String Etat;

    public void planifierVol(Avion avion,LocalDateTime depart,LocalDateTime arrivee){

    }
    public void annulerVol(){
        Etat = "Annulé";
    }

    public void modifierVol(LocalDateTime depart,LocalDateTime arrivee){
        DateHeureArrivee = arrivee;
        DateHeureDepart = depart;
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






}
