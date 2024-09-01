package org.carbon.business.domain.carte;

/**
 * Classe de modélisation des coordonnées d'une case
 */
public class Coordonnees {

    /**
     * Position dans l'axe de la hauteur
     */
    private final int indexHauteur;

    /**
     * Position dans l'axe de la largeur
     */
    private final int indexLargeur;

    /**
     * Constructeur paramétré
     *
     * @param pIndexHauteur index dans l'axe de la hauteur
     * @param pIndexLargeur index dans l'axe de la largeur
     */
    public Coordonnees(final int pIndexHauteur, final int pIndexLargeur) {
        indexHauteur = pIndexHauteur;
        indexLargeur = pIndexLargeur;
    }


    /**
     * @return indexHauteur
     */
    public int getIndexHauteur() {
        return indexHauteur;
    }

    /**
     * @return indexLargeur
     */
    public int getIndexLargeur() {
        return indexLargeur;
    }

}
