package org.carbon.common.enums;

import org.carbon.common.constants.Constantes;

/**
 * Enumeration des mouvements
 */
public enum Mouvement {

    /**
     * Constante pour le mouvement en avant
     */
    AVANCER(Constantes.CODE_MOUVEMENT_CONSTANT, Constantes.DEGRE_MOUVEMENT_CONSTANT),

    /**
     * Constante pour la rotation à gauche
     */
    GAUCHE(Constantes.CODE_ROTATION_GAUCHE, -Constantes.DEGRE_MOUVEMENT_ROTATION),

    /**
     * Constante pour la rotation à droite
     */
    DROITE(Constantes.CODE_ROTATION_DROITE, Constantes.DEGRE_MOUVEMENT_ROTATION);

    /**
     * Mouvement
     */
    private final String codeMouvement;

    private final int degreRotation;

    /**
     * Constructeur de l'objet Mouvement
     *
     * @param pCodeMouvement code de mouvement
     */
    Mouvement(final String pCodeMouvement, final int pDegreRotation) {
        codeMouvement = pCodeMouvement;
        degreRotation = pDegreRotation;
    }

    public static Mouvement getMouvementFromCode(final String codeMouvement) {
        switch(codeMouvement) {
            case Constantes.CODE_MOUVEMENT_CONSTANT:
                return AVANCER;
            case Constantes.CODE_ROTATION_GAUCHE:
                return GAUCHE;
            case Constantes.CODE_ROTATION_DROITE:
                return DROITE;
            default:
                return null;
        }
    }

    /**
     * Récupère le code du mouvement
     *
     * @return codeMouvement
     */
    public String getCodeMouvement() {
        return codeMouvement;
    }

    /**
     * Récupère le degré de rotation associé au mouvement
     *
     * @return degreRotation
     */
    public int getDegreRotation() {
        return degreRotation;
    }

}
