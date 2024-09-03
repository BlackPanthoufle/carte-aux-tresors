package org.carbon.common.mapper;

import org.carbon.business.domain.aventurier.Aventurier;
import org.carbon.business.domain.carte.Carte;
import org.carbon.business.domain.cases.Case;
import org.carbon.business.domain.cases.impl.Montagne;
import org.carbon.business.domain.cases.impl.Plaine;
import org.carbon.business.domain.tresor.Tresor;
import org.carbon.common.enums.OrientationCardinale;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FichierMapperTest {

    Carte carte = new Carte();

    final int hauteur = 4;
    final int largeur = 3;
    final ArrayList<ArrayList<Case>> matrice = new ArrayList<>();

    @BeforeAll
    void initialiseMatriceRemplie() {
        for (int i = 0 ; i < hauteur ; i++) {
            final ArrayList<Case> ligne = new ArrayList<>();

            for (int j = 0 ; j < largeur ; j++) {
                ligne.add(new Plaine());
            }

            matrice.add(ligne);
        }

        matrice.get(0).set(1, new Montagne());
        matrice.get(1).set(2, new Montagne());
        matrice.get(3).get(2).setTresor(new Tresor(2));
        final Aventurier nathanDrake = new Aventurier("Drake", OrientationCardinale.NORD, 0, new LinkedList<>());
        matrice.get(1).get(1).setAventurier(nathanDrake);
    }

    @BeforeEach
    void initialiseCarte() {
        carte = new Carte();
    }

    @Test
    void carteVideEnEntree() {
        final ArrayList<String> listeLignes = FichierMapper.fichierMapping(carte);

        assertEquals(1, listeLignes.size());
        assertEquals("C-0-0", listeLignes.get(0));
    }

    @Test
    void carteRemplieEnEntree() {
        carte = new Carte(largeur, hauteur, matrice);
        final ArrayList<String> listeLignes = FichierMapper.fichierMapping(carte);

        assertEquals(5, listeLignes.size());
    }

}
