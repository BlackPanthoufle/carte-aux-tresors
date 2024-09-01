package org.carbon.business.domain.carte;

/**
 * Classe de modélisation des coordonnées d'une case
 */
public class Coordonnees {

    /**
     * Position dans l'axe de la longueur
     */
    private final int i;

    /**
     * Position dans l'axe de la largeur
     */
    private final int j;

    /**
     * Constructeur paramétré
     *
     * @param pI index de la longueur
     * @param pJ index de la largeur
     */
    public Coordonnees(final int pI, final int pJ) {
        i = pI;
        j = pJ;
    }


    /**
     * @return i
     */
    public int getI() {
        return i;
    }

    /**
     * @return j
     */
    public int getJ() {
        return j;
    }

}
