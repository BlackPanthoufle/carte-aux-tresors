package org.carbon.business.domain.carte;

import org.carbon.business.domain.cases.Case;

import java.util.ArrayList;

/**
 * Classe de modélisation de l'objet Carte
 */
public class Carte {

    /**
     * Longueur de la carte
     */
    private final int longueur;

    /**
     * Largeur de la carte
     */
    private final int largeur;

    /**
     * Matrice de stockage des cases, trésors et aventuriers
     */
    private final ArrayList<ArrayList<Case>> matrice;

    /**
     * Constructeur exhaustif de l'objet carte
     *
     * @param pLongueur longueur de la carte
     * @param pLargeur largeur de la carte
     * @param pMatrice matrice de la carte initialisée
     */
    public Carte(final int pLongueur, final int pLargeur, final ArrayList<ArrayList<Case>> pMatrice) {
        longueur = pLongueur;
        largeur = pLargeur;
        matrice = pMatrice;
    }

    /**
     * Constructeur par défaut
     */
    public Carte() {
        longueur = 0;
        largeur = 0;
        matrice = new ArrayList<>();
    }

    /**
     * @return longueur
     */
    public int getLongueur() {
        return longueur;
    }

    /**
     * @return largeur
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * @return matrice
     */
    public ArrayList<ArrayList<Case>> getMatrice() {
        return matrice;
    }

}
