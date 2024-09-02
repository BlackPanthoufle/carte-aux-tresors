package org.carbon.business.domain.cases.impl;

import org.carbon.business.domain.aventurier.Aventurier;
import org.carbon.business.domain.cases.Case;
import org.carbon.business.domain.tresor.Tresor;

/**
 * Implémentation de l'interface {@link Case}
 */
public class Montagne implements Case {

    /**
     * Constructeur par défaut
     */
    public Montagne() {
        // Aucun attribut à instancier
    }

    @Override
    public boolean isFranchissable() {
        return false;
    }

    @Override
    public boolean hasAventurier() {
        return false;
    }

    @Override
    public void setAventurier(Aventurier aventurier) {
        // Aucune action à faire
    }

    @Override
    public Aventurier getAventurier() {
        return new Aventurier();
    }

    @Override
    public boolean hasTresor() {
        return false;
    }

    @Override
    public int getNbTresor() {
        return 0;
    }

    @Override
    public void setTresor(Tresor tresor) {
        // Aucune action à faire
    }

}
