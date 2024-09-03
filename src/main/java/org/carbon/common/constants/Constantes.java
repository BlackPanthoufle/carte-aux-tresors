package org.carbon.common.constants;

import java.io.File;

/**
 * Classe de constantes
 */
public class Constantes {

    /**
     *
     */
    public static final String REPERTOIRE_ENTREE = "cartes" + File.separator;

    /**
     * Constante pour avoir le modulo d'une rotation complète
     */
    public static final int TOUR_COMPLET = 360;

    /**
     * Constante du degré pour le point cardinal nord
     */
    public static final int DEGRE_NORD = 0;

    /**
     * Code pour le point cardinal nord
     */
    public static final String CODE_NORD = "N";

    /**
     * Constante du degré pour le point cardinal est
     */
    public static final int DEGRE_EST = 90;

    /**
     * Code pour le point cardinal est
     */
    public static final String CODE_EST = "E";

    /**
     * Constante du degré pour le point cardinal sud
     */
    public static final int DEGRE_SUD = 180;

    /**
     * Code pour le point cardinal sud
     */
    public static final String CODE_SUD = "S";

    /**
     * Constante du degré pour le point cardinal ouest
     */
    public static final int DEGRE_OUEST = 270;

    /**
     * Code pour le point cardinal ouest
     */
    public static final String CODE_OUEST = "O";

    /**
     * Constante du déplacement en degrés pour un mouvement de rotation à droite
     */
    public static final int DEGRE_MOUVEMENT_ROTATION_DROITE = 90;

    /**
     * Constante du déplacement en degrés pour un mouvement de rotation à gauche
     */
    public static final int DEGRE_MOUVEMENT_ROTATION_GAUCHE = 270;

    /**
     * Code pour une rotation vers la gauche
     */
    public static final String CODE_ROTATION_GAUCHE = "G";

    /**
     * Code pour une rotation vers la droite
     */
    public static final String CODE_ROTATION_DROITE = "D";

    /**
     * Constante du déplacement en degrés pour un mouvement constant
     */
    public static final int DEGRE_MOUVEMENT_CONSTANT = 0;

    /**
     * Code pour un mouvement constant
     */
    public static final String CODE_MOUVEMENT_CONSTANT = "A";

    /**
     * Suffixe pour le fichier de sortie
     */
    public static final String EXTENSION_FICHIER_SORTIE = "-sortie";

    /**
     * Identifiant pour les informations sur la carte dans un fichier
     */
    public static final String IDENTIFIANT_CARTE_FICHIER = "C";

    /**
     * Identifiant pour les informations d'une montagne dans un fichier
     */
    public static final String IDENTIFIANT_MONTAGNE_FICHIER = "M";

    /**
     * Identifiant pour les informations d'un trésor dans un fichier
     */
    public static final String IDENTIFIANT_TRESOR_FICHIER = "T";

    /**
     * Identifiant pour les informations d'un aventurier dans un fichier
     */
    public static final String IDENTIFIANT_AVENTURIER_FICHIER = "A";

    /**
     * Identifiant pour les commentaires dans un fichier
     */
    public static final String IDENTIFIANT_COMMENTAIRE_FICHIER = "#";

    /**
     * Position dans une ligne de fichier de l'identifiant d'un object
     */
    public static final int POSITION_IDENTIFIANT_OBJET_FICHIER = 0;

    /**
     * Position dans une ligne de fichier de la largeur de la carte
     * ou position dans la largeur de la carte
     */
    public static final int POSITION_LARGEUR_FICHIER = 1;

    /**
     * Position dans une ligne de fichier de la hauteur de la carte
     * ou position dans la hauteur de la carte
     */
    public static final int POSITION_HAUTEUR_FICHIER = 2;

    /**
     * Position dans une ligne de fichier du nombre de trésors dans une case avec un trésor
     */
    public static final int POSITION_NB_TRESORS_FICHIER = 3;

    /**
     * Position dans une ligne de fichier du nom d'un aventurier
     */
    public static final int POSITION_AVENTURIER_NOM_FICHIER = 1;

    /**
     * Position dans une ligne de fichier de la position d'un aventurier
     * dans la largeur de la carte
     */
    public static final int POSITION_AVENTURIER_LARGEUR_FICHIER = 2;

    /**
     * Position dans une ligne de fichier de la position d'un aventurier
     * dans la hauteur de la carte
     */
    public static final int POSITION_AVENTURIER_HAUTEUR_FICHIER = 3;

    /**
     * Position dans une ligne de fichier de l'orientation cardinale d'un aventurier
     */
    public static final int POSITION_AVENTURIER_ORIENTATION_FICHIER = 4;

    /**
     * Position dans une ligne de fichier de la séquence de mouvement d'un aventurier
     */
    public static final int POSITION_AVENTURIER_SEQUENCE_MOUVEMENT_FICHIER = 5;

    /**
     * Constructeur par défaut
     */
    private Constantes() {}

}
