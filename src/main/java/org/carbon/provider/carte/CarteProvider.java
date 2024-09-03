package org.carbon.provider.carte;

import org.carbon.business.domain.aventurier.Aventurier;
import org.carbon.business.domain.carte.Carte;
import org.carbon.business.domain.carte.Coordonnees;
import org.carbon.common.enums.Mouvement;
import org.carbon.common.enums.OrientationCardinale;

import java.util.HashMap;

/**
 * Classe de service d'interactions avec un objet Carte
 */
public class CarteProvider {

    /**
     * Mise en mémoire de l'ordre de passage des aventuriers
     */
    final private static HashMap<Integer, String> mapPriorite = new HashMap<>();

    /**
     * Mise en mémoire des positions des aventuriers pour un accès plus rapide
     */
    final private static HashMap<String, Coordonnees> mapPositionsAventuriers = new HashMap<>();

    /**
     * Service de lancement de l'exploration des aventuriers sur la carte
     *
     * @param carte objet Carte à explorer
     */
    public static void demarrerExplorations(final Carte carte) {
        miseEnMemoireAventurier(carte);

        final int nombreTotalAventuriers = mapPriorite.size();
        int prioriteEnCours = 0;

        while (!mapPriorite.isEmpty()) {
            if (mapPriorite.containsKey(prioriteEnCours)) {
                final String nomAventurierCourant = mapPriorite.get(prioriteEnCours);
                final Coordonnees coordonneesAventurierCourant = mapPositionsAventuriers.get(nomAventurierCourant);
                final Mouvement mouvementSuivant = getMouvementSuivantFromCoordonnees(carte, coordonneesAventurierCourant);

                if (mouvementSuivant != null && mouvementSuivant.equals(Mouvement.AVANCER)) {
                    final Aventurier aventurierCourant = getAventurierAtCoordonnees(carte, coordonneesAventurierCourant);
                    final Coordonnees nouvellesCoordonnees = nouvellesCoordonnees(coordonneesAventurierCourant,
                            aventurierCourant.getOrientationCardinale());
                    if (coordonneesValides(carte, nouvellesCoordonnees)) {
                        deplacerAventurier(carte, coordonneesAventurierCourant, nouvellesCoordonnees, aventurierCourant);
                        collecterTresor(carte, nouvellesCoordonnees);
                        mapPositionsAventuriers.replace(aventurierCourant.getNom(), nouvellesCoordonnees);
                    }
                } else if (mouvementSuivant != null
                        && (mouvementSuivant.equals(Mouvement.DROITE) || mouvementSuivant.equals(Mouvement.GAUCHE))) {
                    carte.getMatrice()
                            .get(coordonneesAventurierCourant.getIndexHauteur())
                            .get(coordonneesAventurierCourant.getIndexLargeur())
                            .getAventurier()
                            .setOrientationCardinaleFromMouvement(mouvementSuivant);
                } else {
                    mapPriorite.remove(prioriteEnCours);
                }
            }

            prioriteEnCours = (prioriteEnCours + 1) % nombreTotalAventuriers;
        }
    }

    /**
     * Méthode de mise en mémoire des aventuriers, de leurs positions et de leurs coordonnées dans des maps
     *
     * @param carte carte dont il faut extraire les données sur les aventuriers
     */
    private static void miseEnMemoireAventurier(final Carte carte) {
        for (int i = 0 ; i < carte.getHauteur() ; i++) {
            for (int j = 0 ; j < carte.getLargeur() ; j++) {
                if (carte.getMatrice().get(i).get(j).isFranchissable()
                        && carte.getMatrice().get(i).get(j).hasAventurier()) {
                    final Aventurier aventurier = carte.getMatrice().get(i).get(j).getAventurier();

                    isAventurierUnique(aventurier);

                    mapPriorite.put(aventurier.getOrdrePassage(), aventurier.getNom());
                    mapPositionsAventuriers.put(aventurier.getNom(), new Coordonnees(i, j));
                }
            }
        }
    }

    /**
     * Méthode vérifiant l'unicité du nom des aventuriers qui sert de clé pour les maps
     *
     * @param aventurier aventurier dont il faut vérifier l'unicité
     */
    private static void isAventurierUnique(final Aventurier aventurier) {
        if (mapPositionsAventuriers.containsKey(aventurier.getNom())) {
            throw new RuntimeException("Nom d'aventurier en doublon dans le fichier.");
        }
    }

    /**
     * Méthode permettant de récupérer le mouvement suivant pour un aventurier aux coordonnées données en entrée
     *
     * @param carte objet Carte
     * @param coordonnees coordonnées où se trouve l'aventurier
     * @return prochain mouvement de l'aventurier aux coordonnées entrées
     */
    private static Mouvement getMouvementSuivantFromCoordonnees(final Carte carte, final Coordonnees coordonnees) {
        return carte.getMatrice()
                .get(coordonnees.getIndexHauteur())
                .get(coordonnees.getIndexLargeur())
                .getAventurier()
                .getSequenceMouvements()
                .poll();
    }

    /**
     * Méthode permettant de récupérer l'aventurier aux coordonnées données en entrée
     *
     * @param carte objet Carte
     * @param coordonnees coordonnées où se trouve l'aventurier
     * @return l'aventurier aux coordonnées entrées
     */
    private static Aventurier getAventurierAtCoordonnees(final Carte carte, final Coordonnees coordonnees) {
        return carte.getMatrice()
                .get(coordonnees.getIndexHauteur())
                .get(coordonnees.getIndexLargeur())
                .getAventurier();
    }

    /**
     * Méthode de détermination de la prochaine position d'un aventurier après un changement de case
     *
     * @param coordonneesAventurierCourant coordonnées de l'aventurier à déplacer
     * @param orientationCardinale orientation cardinale de l'aventurier
     * @return nouvelles coordonnées calculées
     */
    private static Coordonnees nouvellesCoordonnees(final Coordonnees coordonneesAventurierCourant,
                                                    final OrientationCardinale orientationCardinale) {
        int indexHauteur = coordonneesAventurierCourant.getIndexHauteur();
        int indexLargeur = coordonneesAventurierCourant.getIndexLargeur();

        switch (orientationCardinale) {
            case NORD:
                indexHauteur--;
                break;
            case EST:
                indexLargeur++;
                break;
            case SUD:
                indexHauteur++;
                break;
            case OUEST:
                indexLargeur--;
                break;
        }

        return new Coordonnees(indexHauteur, indexLargeur);
    }

    /**
     * Méthode de validation de coordonnées pour y déplacer un aventurier
     *
     * @param carte objet Carte
     * @param nouvellesCoordonnees coordonnées à valider
     * @return "true" si un aventurier peut aller sur les coordonnées, "false" sinon
     */
    private static boolean coordonneesValides(final Carte carte, final Coordonnees nouvellesCoordonnees) {
        return nouvellesCoordonnees.getIndexLargeur() >= 0
                && nouvellesCoordonnees.getIndexHauteur() >= 0
                && nouvellesCoordonnees.getIndexHauteur() < carte.getHauteur()
                && nouvellesCoordonnees.getIndexLargeur() < carte.getLargeur()
                && carte.getMatrice().get(nouvellesCoordonnees.getIndexHauteur()).get(nouvellesCoordonnees.getIndexLargeur()).isFranchissable()
                && !carte.getMatrice().get(nouvellesCoordonnees.getIndexHauteur()).get(nouvellesCoordonnees.getIndexLargeur()).hasAventurier();
    }

    /**
     * Méthode de déplacement d'un aventurier d'une case d'entrée vers une case cible
     *
     * @param carte objet Carte
     * @param coordonneesInitiales coordonnées où se trouve l'aventurier à déplacer
     * @param coordonneesCibles coordonnées déplacer l'aventurier
     * @param aventurier objet Aventurier à copier
     */
    private static void deplacerAventurier(final Carte carte, final Coordonnees coordonneesInitiales,
                                           final Coordonnees coordonneesCibles, final Aventurier aventurier) {
        carte.getMatrice()
                .get(coordonneesInitiales.getIndexHauteur())
                .get(coordonneesInitiales.getIndexLargeur())
                .setAventurier(new Aventurier());

        carte.getMatrice()
                .get(coordonneesCibles.getIndexHauteur())
                .get(coordonneesCibles.getIndexLargeur())
                .setAventurier(aventurier);
    }

    /**
     * Méthode de récupération d'un trésor par un aventurier aux coordonnées données en entrée
     *
     * @param carte objet Carte
     * @param coordonnees coordonnées où la collection du trésor a lieu
     */
    private static void collecterTresor(final Carte carte, final Coordonnees coordonnees) {
        if (carte.getMatrice().get(coordonnees.getIndexHauteur()).get(coordonnees.getIndexLargeur()).hasTresor()) {
            carte.getMatrice()
                    .get(coordonnees.getIndexHauteur())
                    .get(coordonnees.getIndexLargeur())
                    .getAventurier()
                    .incrementeTresorsRamasses();
            carte.getMatrice()
                    .get(coordonnees.getIndexHauteur())
                    .get(coordonnees.getIndexLargeur())
                    .getTresor()
                    .decrementeNombreTresors();
        }
    }

}
