package org.carbon.business.domain.cases.impl;

import org.carbon.business.domain.aventurier.Aventurier;
import org.carbon.business.domain.cases.Case;
import org.carbon.business.domain.tresor.Tresor;

/**
 * Implémentation de l'interface {@link Case}
 */
public class Plaine implements Case {

    /**
     * Emplacement de stockage pour un aventurier
     */
    private Aventurier aventurier;

    /**
     * Emplacement de stockage pour un trésor
     */
    private Tresor tresor;

    /**
     * Constructeur par défaut
     */
    public Plaine() {
        aventurier = new Aventurier();
        tresor = new Tresor();
    }

    @Override
    public boolean isFranchissable() {
        return true;
    }

    @Override
    public boolean hasAventurier() {
        return aventurier.getNom() != null && !aventurier.getNom().isEmpty();
    }

    @Override
    public void setAventurier(Aventurier pAventurier) {
        aventurier = pAventurier;
    }

    @Override
    public Aventurier getAventurier() {
        return aventurier;
    }

    @Override
    public boolean hasTresor() {
        return tresor.getNombreTresors() > 0;
    }

    @Override
    public int getNbTresor() {
        return tresor.getNombreTresors();
    }

    @Override
    public void setTresor(Tresor pTresor) {
        tresor = pTresor;
    }

    @Override
    public Tresor getTresor() {
        return tresor;
    }

}
