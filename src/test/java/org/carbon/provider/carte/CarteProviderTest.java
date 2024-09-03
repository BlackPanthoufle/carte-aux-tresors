package org.carbon.provider.carte;

import org.carbon.business.domain.aventurier.Aventurier;
import org.carbon.business.domain.carte.Carte;
import org.carbon.business.domain.cases.Case;
import org.carbon.business.domain.cases.impl.Montagne;
import org.carbon.business.domain.cases.impl.Plaine;
import org.carbon.business.domain.tresor.Tresor;
import org.carbon.common.enums.Mouvement;
import org.carbon.common.enums.OrientationCardinale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarteProviderTest {

    Carte carte = new Carte();

    final int hauteur = 4;

    final int largeur = 3;

    ArrayList<ArrayList<Case>> matrice;

    @BeforeEach
    void initialiseCarte() {
        carte = new Carte();
        matrice = new ArrayList<>();

        for (int i = 0 ; i < hauteur ; i++) {
            final ArrayList<Case> ligne = new ArrayList<>();

            for (int j = 0 ; j < largeur ; j++) {
                ligne.add(new Plaine());
            }

            matrice.add(ligne);
        }

        matrice.get(0).set(1, new Montagne());
        matrice.get(1).set(2, new Montagne());
        matrice.get(3).get(0).setTresor(new Tresor(1));
        matrice.get(3).get(1).setTresor(new Tresor(2));
        final LinkedList<Mouvement> sequenceMouvementLaraCroft = new LinkedList<>();
        // Avance vers une montagne
        sequenceMouvementLaraCroft.add(Mouvement.AVANCER);
        sequenceMouvementLaraCroft.add(Mouvement.GAUCHE);
        // Avance vers la hauteur -1
        sequenceMouvementLaraCroft.add(Mouvement.AVANCER);
        sequenceMouvementLaraCroft.add(Mouvement.GAUCHE);
        // Avance vers la largeur -1
        sequenceMouvementLaraCroft.add(Mouvement.AVANCER);
        sequenceMouvementLaraCroft.add(Mouvement.GAUCHE);
        sequenceMouvementLaraCroft.add(Mouvement.AVANCER);
        final Aventurier laraCroft = new Aventurier("Lara", OrientationCardinale.EST, 0, sequenceMouvementLaraCroft);
        final LinkedList<Mouvement> sequenceMouvementNathanDrake = new LinkedList<>();
        sequenceMouvementNathanDrake.add(Mouvement.DROITE);
        sequenceMouvementNathanDrake.add(Mouvement.DROITE);
        sequenceMouvementNathanDrake.add(Mouvement.AVANCER);
        sequenceMouvementNathanDrake.add(Mouvement.AVANCER);
        // Avance vers une hauteur en dehors de la carte
        sequenceMouvementNathanDrake.add(Mouvement.AVANCER);
        sequenceMouvementNathanDrake.add(Mouvement.GAUCHE);
        sequenceMouvementNathanDrake.add(Mouvement.AVANCER);
        // Avance vers une largeur en dehors de la carte
        sequenceMouvementNathanDrake.add(Mouvement.AVANCER);
        final Aventurier nathanDrake = new Aventurier("Drake", OrientationCardinale.NORD, 1, sequenceMouvementNathanDrake);
        matrice.get(0).get(0).setAventurier(laraCroft);
        matrice.get(1).get(1).setAventurier(nathanDrake);
    }

    @Test
    void prioritesAventurierDoublon() {
        final Aventurier indianaJones = new Aventurier("Indiana", OrientationCardinale.NORD, 0, new LinkedList<>());
        matrice.get(1).get(0).setAventurier(indianaJones);

        carte = new Carte(largeur, hauteur, matrice);
        assertThrows(RuntimeException.class, () -> CarteProvider.demarrerExplorations(carte));

        matrice.get(1).get(0).setAventurier(new Aventurier());
    }

    @Test
    void nomsAventurierDoublon() {
        final Aventurier laraCroft = new Aventurier("Lara", OrientationCardinale.NORD, 3, new LinkedList<>());
        matrice.get(1).get(0).setAventurier(laraCroft);

        carte = new Carte(largeur, hauteur, matrice);
        assertThrows(RuntimeException.class, () -> CarteProvider.demarrerExplorations(carte));

        matrice.get(1).get(0).setAventurier(new Aventurier());
    }



    @Test
    void casTestConflitPrioriteEntreAventuriers() {
        matrice = new ArrayList<>();

        for (int i = 0 ; i < 2 ; i++) {
            final ArrayList<Case> ligne = new ArrayList<>();

            for (int j = 0 ; j < 2 ; j++) {
                ligne.add(new Plaine());
            }

            matrice.add(ligne);
        }

        final LinkedList<Mouvement> sequenceMouvementLaraCroft = new LinkedList<>();
        sequenceMouvementLaraCroft.add(Mouvement.DROITE);
        sequenceMouvementLaraCroft.add(Mouvement.AVANCER);
        final Aventurier laraCroft = new Aventurier("Lara", OrientationCardinale.NORD, 0, sequenceMouvementLaraCroft);
        final LinkedList<Mouvement> sequenceMouvementNathanDrake = new LinkedList<>();
        // Nathan Drake sera bloqué pour son premier mouvement
        sequenceMouvementNathanDrake.add(Mouvement.AVANCER);
        // La place se libère et il peut avancer
        sequenceMouvementNathanDrake.add(Mouvement.AVANCER);
        final Aventurier nathanDrake = new Aventurier("Drake", OrientationCardinale.NORD, 1, sequenceMouvementNathanDrake);
        matrice.get(0).get(0).setAventurier(laraCroft);
        matrice.get(1).get(0).setAventurier(nathanDrake);

        carte = new Carte(2, 2, matrice);
        CarteProvider.demarrerExplorations(carte);

        assertTrue(carte.getMatrice().get(0).get(1).hasAventurier());
        assertEquals("Lara", carte.getMatrice().get(0).get(1).getAventurier().getNom());
        assertTrue(carte.getMatrice().get(0).get(0).hasAventurier());
        assertEquals("Drake", carte.getMatrice().get(0).get(0).getAventurier().getNom());
    }

    @Test
    void casNominal() {
        carte = new Carte(largeur, hauteur, matrice);
        CarteProvider.demarrerExplorations(carte);

        assertTrue(carte.getMatrice().get(1).get(0).hasAventurier());
        assertEquals("Lara", carte.getMatrice().get(1).get(0).getAventurier().getNom());
        assertTrue(carte.getMatrice().get(3).get(2).hasAventurier());
        assertEquals("Drake", carte.getMatrice().get(3).get(2).getAventurier().getNom());
    }

}
