package org.carbon.business.domain.aventurier;

import org.carbon.common.constants.Constantes;
import org.carbon.common.enums.Mouvement;
import org.carbon.common.enums.OrientationCardinale;

import java.util.LinkedList;

/**
 * Classe d'implémentation de l'objet aventurier
 */
public class Aventurier {

    /**
     * Nom de l'aventurier
     */
    private final String nom;

    /**
     * Orientation cardinale courante
     */
    private OrientationCardinale orientationCardinale;

    /**
     * Nombre de trésors ramassés par l'aventurier
     */
    private int tresorsRamasses;

    /**
     * Ordre de passage pour l'aventurier
     */
    private final int ordrePassage;

    /**
     * Séquence de mouvements à exécuter
     */
    private final LinkedList<Mouvement> sequenceMouvements;

    /**
     * Constructeur de la classe Aventurier
     *
     * @param pNom nom de l'aventurier
     * @param pOrientationCardinale orientation cardinale initiale
     * @param pOrdrePassage ordre de passage
     * @param pSequenceMouvements séquence de mouvement intiale
     */
    public Aventurier(final String pNom, final OrientationCardinale pOrientationCardinale,
            final int pOrdrePassage, final LinkedList<Mouvement> pSequenceMouvements) {
        nom = pNom;
        orientationCardinale = pOrientationCardinale;
        tresorsRamasses = 0;
        ordrePassage = pOrdrePassage;
        sequenceMouvements = pSequenceMouvements;
    }

    /**
     * Constructeur par défaut de la classe Aventurier
     */
    public Aventurier() {
        nom = "";
        orientationCardinale = null;
        tresorsRamasses = 0;
        ordrePassage = 0;
        sequenceMouvements = new LinkedList<>();
    }

    /**
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return orientationCardinale
     */
    public OrientationCardinale getOrientationCardinale() {
        return orientationCardinale;
    }

    /**
     * Modifie l'orientation cardinale de l'aventurier étant donné un mouvement à réaliser
     *
     * @param mouvement mouvement à effectuer pour l'aventurier
     */
    public void setOrientationCardinaleFromMouvement(final Mouvement mouvement) {
        final int nouveauDegreRotation = (orientationCardinale.getDegreOrientationCardinale() + mouvement.getDegreRotation()) % Constantes.TOUR_COMPLET;

        switch(nouveauDegreRotation) {
            case Constantes.DEGRE_NORD:
                orientationCardinale = OrientationCardinale.NORD;
                break;
            case Constantes.DEGRE_EST:
                orientationCardinale = OrientationCardinale.EST;
                break;
            case Constantes.DEGRE_SUD:
                orientationCardinale = OrientationCardinale.SUD;
                break;
            case Constantes.DEGRE_OUEST:
                orientationCardinale = OrientationCardinale.OUEST;
                break;
            default:
                break;
        }
    }

    /**
     * @return tresorsRamasses
     */
    public int getTresorsRamasses() {
        return tresorsRamasses;
    }

    /**
     * Incrémente le nombre de trésors ramassés par l'aventurier
     */
    public void incrementeTresorsRamasses() {
        tresorsRamasses++;
    }

    /**
     * @return sequenceMouvements
     */
    public LinkedList<Mouvement> getSequenceMouvements() {
        return sequenceMouvements;
    }

    /**
     * @return ordrePassage
     */
    public int getOrdrePassage() {
        return ordrePassage;
    }

}
