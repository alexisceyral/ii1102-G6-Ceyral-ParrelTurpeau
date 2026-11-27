package org.example;
import java.util.Map;
import java.util.LinkedHashMap;

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


    public boolean chevauche(Vol autre) {
        return etat != EtatVol.ANNULE && autre.getEtat() != EtatVol.ANNULE
                && dateHeureDepart.isBefore(autre.getDateHeureArrivee())
                && dateHeureArrivee.isAfter(autre.getDateHeureDepart());
    }

    // ===================== CRUD (IA) =====================
    private static final Map<String, Vol> vols = new LinkedHashMap<>();

    /** CREATE : ajoute un vol. Refusé si null ou si le numéro de vol existe déjà. */
    public static boolean ajouter(Vol vol) {
        if (vol == null || vol.numeroVol == null || vols.containsKey(vol.numeroVol)) {
            return false;
        }
        vols.put(vol.numeroVol, vol);
        return true;
    }

    /** READ : cherche un vol par son numéro (null si absent). */
    public static Vol chercher(String numeroVol) {
        return vols.get(numeroVol);
    }

    /** READ : liste tous les vols. */
    public static List<Vol> lister() {
        return new ArrayList<>(vols.values());
    }

    /** UPDATE : modifie les horaires d'un vol (réutilise modifierVol). */
    public static boolean modifier(String numeroVol, LocalDateTime depart, LocalDateTime arrivee) {
        Vol v = vols.get(numeroVol);
        if (v == null || depart == null || arrivee == null || !arrivee.isAfter(depart)) {
            return false;
        }
        v.modifierVol(depart, arrivee);
        return true;
    }

    /** DELETE : supprime un vol par son numéro. */
    public static boolean supprimer(String numeroVol) {
        return vols.remove(numeroVol) != null;
    }
}
