package org.carbon.common.mapper;

import org.carbon.business.domain.aventurier.Aventurier;
import org.carbon.business.domain.carte.Carte;
import org.carbon.business.domain.carte.Coordonnees;
import org.carbon.business.domain.cases.Case;
import org.carbon.business.domain.cases.impl.Montagne;
import org.carbon.business.domain.cases.impl.Plaine;
import org.carbon.business.domain.tresor.Tresor;
import org.carbon.common.constants.Constantes;
import org.carbon.common.enums.Mouvement;
import org.carbon.common.enums.OrientationCardinale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Classe de mapping Fichier -> Carte
 */
public class CarteMapper {

    /**
     * Mise en mémoire des positions des montagnes
     */
    private static final ArrayList<Coordonnees> listPositionsMontagnes = new ArrayList<>();

    /**
     * Mise en mémoire des positions des trésors et de l'objet associé
     */
    private static final HashMap<Coordonnees, Tresor> mapPositionsTresors = new HashMap<>();

    /**
     * Mise en mémoire des positions des aventuriers et de l'objet associé
     */
    private static final HashMap<Coordonnees, Aventurier> mapPositionsAventuriers = new HashMap<>();

    /**
     * Largeur de la matrice
     */
    private static int largeur = 0;

    /**
     * Hauteur de la matrice
     */
    private static int hauteur = 0;

    /**
     * Méthode de mapping Fichier -> Carte
     *
     * @param listeLignes lignes de fichiers
     * @return objet Carte
     */
    public static Carte carteMapping(final ArrayList<String> listeLignes) {
        for (final String ligne : listeLignes) {
            if (ligne.startsWith(Constantes.IDENTIFIANT_COMMENTAIRE_FICHIER)) {
                continue;
            }
            final String[] ligneSplit = ligne.split("-");
            genererObjets(ligneSplit);

        }

        final ArrayList<ArrayList<Case>> matrice = genererMatriceParDefaut();
        genererMontagnes(matrice);
        insererTresors(matrice);
        insererAventuriers(matrice);

        return new Carte(largeur, hauteur, matrice);
    }

    /**
     * Générateur d'objets pour créer un objet carte ou des objets à insérer dans la carte
     *
     * @param ligneSplit une ligne lue dans le fichier d'entrée
     */
    private static void genererObjets(final String[] ligneSplit) {
        Coordonnees coordonnees;

        switch(ligneSplit[0]) {
            case Constantes.IDENTIFIANT_CARTE_FICHIER:
                if (ligneSplit.length != 3) {
                    throw new RuntimeException("Nombre de paramètres incorrect pour les dimensions de la carte.");
                }

                if (largeur != 0 || hauteur != 0) {
                    throw new RuntimeException("Plusieurs lignes définissent les dimensions de la carte.");
                }

                largeur = Integer.parseInt(ligneSplit[Constantes.POSITION_LARGEUR_FICHIER]);
                hauteur = Integer.parseInt(ligneSplit[Constantes.POSITION_HAUTEUR_FICHIER]);
                break;
            case Constantes.IDENTIFIANT_MONTAGNE_FICHIER:
                if (ligneSplit.length != 3) {
                    throw new RuntimeException("Nombre de paramètres incorrect pour la définition d'une montagne.");
                }
                coordonnees = new Coordonnees(
                        Integer.parseInt(ligneSplit[Constantes.POSITION_HAUTEUR_FICHIER]),
                        Integer.parseInt(ligneSplit[Constantes.POSITION_LARGEUR_FICHIER]));
                listPositionsMontagnes.add(coordonnees);
                break;
            case Constantes.IDENTIFIANT_TRESOR_FICHIER:
                if (ligneSplit.length != 4) {
                    throw new RuntimeException("Nombre de paramètres incorrect pour la définition d'un trésor.");
                }
                coordonnees = new Coordonnees(
                        Integer.parseInt(ligneSplit[Constantes.POSITION_HAUTEUR_FICHIER]),
                        Integer.parseInt(ligneSplit[Constantes.POSITION_LARGEUR_FICHIER]));
                final Tresor tresor = new Tresor(Integer.parseInt(ligneSplit[Constantes.POSITION_NB_TRESORS_FICHIER]));
                mapPositionsTresors.put(coordonnees, tresor);
                break;
            case Constantes.IDENTIFIANT_AVENTURIER_FICHIER:
                if (ligneSplit.length != 6) {
                    throw new RuntimeException("Nombre de paramètres incorrect pour la définition d'un aventurier.");
                }
                coordonnees = new Coordonnees(
                        Integer.parseInt(ligneSplit[Constantes.POSITION_AVENTURIER_HAUTEUR_FICHIER]),
                        Integer.parseInt(ligneSplit[Constantes.POSITION_AVENTURIER_LARGEUR_FICHIER]));

                final OrientationCardinale orientationCardinale = genererOrientationCardinale(
                        ligneSplit[Constantes.POSITION_AVENTURIER_ORIENTATION_FICHIER]);
                final LinkedList<Mouvement> sequenceMouvements = genererSequenceMouvements(
                        ligneSplit[Constantes.POSITION_AVENTURIER_SEQUENCE_MOUVEMENT_FICHIER]);

                final Aventurier aventurier = new Aventurier(ligneSplit[Constantes.POSITION_AVENTURIER_NOM_FICHIER],
                        orientationCardinale,
                        mapPositionsAventuriers.size(),
                        sequenceMouvements);
                mapPositionsAventuriers.put(coordonnees, aventurier);
                break;
            default:
                throw new RuntimeException("Définition d'un objet inconnu dans le fichier.");
        }
    }

    /**
     * Générateur de matrice par défaut (avec des Plaines vides à chaque case)
     *
     * @return une matrice par défaut avec des Plaines
     */
    private static ArrayList<ArrayList<Case>> genererMatriceParDefaut() {
        if (largeur < 1 || hauteur < 1) {
            throw new RuntimeException("Dimensions de la carte incorrectes.");
        }

        final ArrayList<ArrayList<Case>> matrice = new ArrayList<>();

        for (int i = 0 ; i < hauteur ; i++) {
            final ArrayList<Case> ligne = new ArrayList<>();

            for (int j = 0 ; j < largeur ; j++) {
                ligne.add(new Plaine());
            }

            matrice.add(ligne);
        }

        return matrice;
    }

    /**
     * Générateur d'orientation cardinale à partir d'une chaîne de caractère
     *
     * @param orientationCardinaleRaw orientation cardinale sous forme de chaîne de caractère
     * @return une orientation cardinale
     */
    private static OrientationCardinale genererOrientationCardinale(final String orientationCardinaleRaw) {
        OrientationCardinale orientationCardinale = OrientationCardinale.getOrientationFromCode(orientationCardinaleRaw);

        if (orientationCardinale == null) {
            throw new RuntimeException("Code d'orientation cardinale inconnu pour un aventurier.");
        }

        return orientationCardinale;
    }

    /**
     * Générateur de séquence de mouvements à partir d'une chaîne de caractère
     *
     * @param sequenceMouvementsRaw une séquence de mouvements sous forme de chaîne de caractère
     * @return une liste de mouvements
     */
    private static LinkedList<Mouvement> genererSequenceMouvements(final String sequenceMouvementsRaw) {
        final LinkedList<Mouvement> sequenceMouvements = new LinkedList<>();

        for (int i = 0 ; i < sequenceMouvementsRaw.length() ; i++) {
            Mouvement mouvement = Mouvement.getMouvementFromCode(String.valueOf(sequenceMouvementsRaw.charAt(i)));
            if (mouvement == null) {
                throw new RuntimeException("Code de mouvement inconnu pour un aventurier.");
            }
            sequenceMouvements.add(mouvement);
        }

        return sequenceMouvements;
    }

    /**
     * Générateur d'objets Montagne
     *
     * @param matrice matrice à modifier pour y insérer des nouveaux objets Montagne
     */
    private static void genererMontagnes(ArrayList<ArrayList<Case>> matrice) {
        for (final Coordonnees coordonnees : listPositionsMontagnes) {
            if (coordonnees.getIndexHauteur() > hauteur || coordonnees.getIndexHauteur() < 0
                    || coordonnees.getIndexLargeur() > largeur || coordonnees.getIndexLargeur() < 0) {
                throw new RuntimeException("Position d'une montagne en dehors de la carte.");
            }

            // On vérifie s'il n'y a pas déjà une montagne sur cette position
            if (!matrice.get(coordonnees.getIndexHauteur()).get(coordonnees.getIndexLargeur()).isFranchissable()) {
                throw new RuntimeException("Au moins 2 montagnes ont été définies à la même position.");
            }

            matrice.get(coordonnees.getIndexHauteur()).set(coordonnees.getIndexLargeur(), new Montagne());
        }
    }

    /**
     * Insère les objets Tresor dans les cases renseignées dans le fichier
     *
     * @param matrice matrice à modifier pour y insérer des objets Tresor
     */
    private static void insererTresors(final ArrayList<ArrayList<Case>> matrice) {
        for (final Coordonnees coordonnees : mapPositionsTresors.keySet()) {
            if (coordonnees.getIndexHauteur() > hauteur || coordonnees.getIndexHauteur() < 0
                    || coordonnees.getIndexLargeur() > largeur || coordonnees.getIndexLargeur() < 0) {
                throw new RuntimeException("Position d'un trésor en dehors de la carte.");
            }

            // On vérifie s'il n'y a pas déjà une montagne sur cette position
            if (!matrice.get(coordonnees.getIndexHauteur()).get(coordonnees.getIndexLargeur()).isFranchissable()) {
                throw new RuntimeException("Définition d'un trésor sur une montagne.");
            }

            if (matrice.get(coordonnees.getIndexHauteur()).get(coordonnees.getIndexLargeur()).hasTresor()) {
                throw new RuntimeException("Définition d'un trésor sur une plaine contenant déjà un trésor.");
            }

            matrice.get(coordonnees.getIndexHauteur()).get(coordonnees.getIndexLargeur()).setTresor(mapPositionsTresors.get(coordonnees));
        }
    }

    /**
     * Insère les objets Aventurier dans les cases renseignées dans le fichier
     *
     * @param matrice matrice à modifier pour y insérer des objets Aventurier
     */
    private static void insererAventuriers(final ArrayList<ArrayList<Case>> matrice) {
        for (final Coordonnees coordonnees : mapPositionsAventuriers.keySet()) {
            if (coordonnees.getIndexHauteur() > hauteur || coordonnees.getIndexHauteur() < 0
                    || coordonnees.getIndexLargeur() > largeur || coordonnees.getIndexLargeur() < 0) {
                throw new RuntimeException("Position d'un aventurier en dehors de la carte.");
            }

            // On vérifie s'il n'y a pas déjà une montagne sur cette position
            if (!matrice.get(coordonnees.getIndexHauteur()).get(coordonnees.getIndexLargeur()).isFranchissable()) {
                throw new RuntimeException("Définition d'un aventurier sur une montagne.");
            }

            if (matrice.get(coordonnees.getIndexHauteur()).get(coordonnees.getIndexLargeur()).hasAventurier()) {
                throw new RuntimeException("Définition d'un aventurier sur une plaine contenant déjà un aventurier.");
            }

            matrice.get(coordonnees.getIndexHauteur()).get(coordonnees.getIndexLargeur()).setAventurier(mapPositionsAventuriers.get(coordonnees));
        }
    }

}
