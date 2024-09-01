package org.carbon.business.domain.carte;

import org.carbon.business.domain.cases.Case;

import java.util.ArrayList;

/**
 * Classe de modélisation de l'objet Carte
 */
public class Carte {

    /**
     * Largeur de la carte
     */
    private final int largeur;

    /**
     * Hauteur de la carte
     */
    private final int hauteur;

    /**
     * Matrice de stockage des cases, trésors et aventuriers
     */
    private final ArrayList<ArrayList<Case>> matrice;

    /**
     * Constructeur exhaustif de l'objet carte
     *
     * @param pLargeur largeur de la carte
     * @param pHauteur hauteur de la carte
     * @param pMatrice matrice de la carte initialisée
     */
    public Carte(final int pLargeur, final int pHauteur, final ArrayList<ArrayList<Case>> pMatrice) {
        largeur = pLargeur;
        hauteur = pHauteur;
        matrice = pMatrice;
    }

    /**
     * Constructeur par défaut
     */
    public Carte() {
        largeur = 0;
        hauteur = 0;
        matrice = new ArrayList<>();
    }

    /**
     * @return largeur
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * @return hauteur
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * @return matrice
     */
    public ArrayList<ArrayList<Case>> getMatrice() {
        return matrice;
    }

}
