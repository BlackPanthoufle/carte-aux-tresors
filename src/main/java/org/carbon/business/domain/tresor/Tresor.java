package org.carbon.business.domain.tresor;

/**
 * Classe d'implémentation de l'objet trésor
 */
public class Tresor {

    /**
     * Nombre de trésors
     */
    private int nombreTresors;

    /**
     * Constructeur par défaut
     */
    public Tresor() {
        nombreTresors = 0;
    }

    /**
     * Constructeur paramétré
     *
     * @param pNombreTresors nombre de trésors
     */
    public Tresor(final int pNombreTresors) {
        nombreTresors = pNombreTresors;
    }

    /**
     * @return nombreTresors
     */
    public int getNombreTresors() {
        return nombreTresors;
    }

    /**
     * Décrémente le nombre de trésors
     */
    public void decrementeNombreTresors() {
        if (nombreTresors > 0) {
            nombreTresors--;
        }
    }

}
