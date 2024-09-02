package org.carbon.business.domain.cases;

import org.carbon.business.domain.aventurier.Aventurier;
import org.carbon.business.domain.tresor.Tresor;

/**
 * Interface de modélisation pour une case dans une carte
 */
public interface Case {

    /**
     * @return "true" si la case est franchissable (si elle peut accueillir un aventurier ou un trésor), sinon "false"
     */
    boolean isFranchissable();

    /**
     * @return "true" si un aventurier est déjà présent sur la case, sinon "false"
     */
    boolean hasAventurier();

    /**
     * @param pAventurier aventurier à attribuer à la case
     */
    void setAventurier(final Aventurier pAventurier);

    /**
     * Renvoie l'aventurier présent s'il y en a un sur la case
     */
    Aventurier getAventurier();

    /**
     * @return "true" s'il reste des trésors sur la case, sinon "false"
     */
    boolean hasTresor();

    /**
     * @return le nombre de trésors sur la case
     */
    int getNbTresor();

    /**
     * @param pTresor trésor à attribuer à la case
     */
    void setTresor(final Tresor pTresor);

    /**
     * Renvoie le trésor présent s'il y en a un sur la case
     */
    Tresor getTresor();

}
