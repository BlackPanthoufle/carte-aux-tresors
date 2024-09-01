package org.carbon.common.enums;

import org.carbon.common.constants.Constantes;

/**
 * Enumeration des orientations cardinales
 */
public enum OrientationCardinale {

    /**
     * Constante pour l'orientation nord
     */
    NORD(Constantes.CODE_NORD, Constantes.DEGRE_NORD),

    /**
     * Constante pour l'orientation est
     */
    EST(Constantes.CODE_EST, Constantes.DEGRE_EST),

    /**
     * Constante pour l'orientation sud
     */
    SUD(Constantes.CODE_SUD, Constantes.DEGRE_SUD),

    /**
     * Constante pour l'orientation ouest
     */
    OUEST(Constantes.CODE_OUEST, Constantes.DEGRE_OUEST);

    /**
     * Code de l'orientation cardinale
     */
    private final String codeOrientationCardinale;

    /**
     * Degré de l'orientation cardinale
     */
    private final int degreOrientationCardinale;

    /**
     * Constructeur de l'objet Orientation
     *
     * @param pCodeOrientationCardinale code d'orientation cardinale
     */
    OrientationCardinale(final String pCodeOrientationCardinale, final int pDegreOrientationCardinale) {
        codeOrientationCardinale = pCodeOrientationCardinale;
        degreOrientationCardinale = pDegreOrientationCardinale;
    }

    /**
     * Récupère le code de l'orientation cardinale
     *
     * @return codeOrientationCardinale
     */
    public String getCodeOrientationCardinale() {
        return codeOrientationCardinale;
    }

    /**
     * Récupère le degré de l'orientation cardinale
     *
     * @return degreOrientationCardinale
     */
    public int getDegreOrientationCardinale() {
        return degreOrientationCardinale;
    }

}
