# Journal des contributions de l'IA

Chaque contribution d'un assistant IA est tracée ici (charte « IA collaboratrice », section 3.3).
Les colonnes « Gardé » et « Rejeté / corrigé » sont remplies par le binôme après la revue de la PR.

| Date | Issue / PR | Outil | Demande faite à l'IA | Gardé | Rejeté / corrigé (pourquoi) |
|-------|------------|-------|----------------------|-------|-----------------------------|
| 27/11 | – / branche `mainclaude` | Claude Code | Ajouter dans chaque classe les méthodes CRUD (ajouter, chercher, modifier, supprimer) [IA autorisée] | *à compléter après revue* | Premier essai (commit f38abdd) annulé par l'IA elle-même (commit 13043e3) : il réécrivait des zones [Humain] (modèle du domaine, obtenirInfos, obtenirRole). CRUD refait sans toucher au code humain. *À compléter après revue.* |

## Notes pour la revue (CRUD du 27/11)

- Méthodes ajoutées en fin de classe, dans un bloc `// CRUD (IA)` : `ajouter`, `chercher`, `lister…`, `modifier`, `supprimer` (méthodes `static`, stockage dans une `LinkedHashMap` indexée par identifiant).
- `Personne` contient un registre commun : `Employe`, `Pilote` et `PersonnelCabine` s'appuient dessus (un identifiant est unique pour toutes les personnes).
- `Reservation.modifier` et `Vol.modifier` réutilisent `modifierReservation` et `modifierVol` écrits par le binôme.
- **`Passager` n'a pas de CRUD** : la classe est encore vide et n'hérite pas de `Personne`. Une fois écrite par le binôme, elle sera couverte par `Personne.ajouter/chercher/…`, ou un CRUD dédié pourra être ajouté.
- Le code ne compile pas encore à cause de parties [Humain] en cours (l'IA ne les a pas modifiées) : `Employe` doit être `abstract`, `Personne.obtenirInfo` → `obtenirInfos`, paramètre `adresss` dans le constructeur de `Personne`, doublon `affecterVol(Vol)` dans `Avion`, accolades du constructeur de `Reservation` et `IllegalAccessException`, champs ou getters manquants dans `Vol` (`reservations`, `etat`, `EtatVol`, `setAvion`, `getOrigine`…).
- Le CRUD a été testé sur une copie locale où ces points étaient corrigés provisoirement ; ces correctifs n'ont pas été commités.
