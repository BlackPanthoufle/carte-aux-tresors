package org.carbon.business.domain;

import org.carbon.business.domain.aventurier.Aventurier;
import org.carbon.common.enums.Mouvement;
import org.carbon.common.enums.OrientationCardinale;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AventurierTest {

    Aventurier aventurier;

    @BeforeAll
    void instanceAventurier() {
        aventurier = new Aventurier("nom", OrientationCardinale.NORD, 0, new LinkedList<>());
    }

    @Test
    void tourCompletGauche() {
        aventurier.setOrientationCardinaleFromMouvement(Mouvement.GAUCHE);
        aventurier.setOrientationCardinaleFromMouvement(Mouvement.GAUCHE);
        aventurier.setOrientationCardinaleFromMouvement(Mouvement.GAUCHE);
        aventurier.setOrientationCardinaleFromMouvement(Mouvement.GAUCHE);

        assertEquals(OrientationCardinale.NORD, aventurier.getOrientationCardinale());
    }

    @Test
    void tourCompletDroite() {
        aventurier.setOrientationCardinaleFromMouvement(Mouvement.DROITE);
        aventurier.setOrientationCardinaleFromMouvement(Mouvement.DROITE);
        aventurier.setOrientationCardinaleFromMouvement(Mouvement.DROITE);
        aventurier.setOrientationCardinaleFromMouvement(Mouvement.DROITE);

        assertEquals(OrientationCardinale.NORD, aventurier.getOrientationCardinale());
    }

}
