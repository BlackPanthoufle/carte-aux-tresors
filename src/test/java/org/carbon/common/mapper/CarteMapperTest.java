package org.carbon.common.mapper;

import org.carbon.business.domain.carte.Carte;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarteMapperTest {

    @Test
    void contenuEntreeVide() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void definitionsObjetInconnu() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("Z-1-1");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void dimensionsCarteIncorrectes() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("# Commentaire de fichier");
        listeLignes.add("C-0-0");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void largeurCarteIncorrectes() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-0-4");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void hauteurCarteIncorrectes() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-4-0");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void ligneObjetCarteParametresInsuffisants() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-0-");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void ligneObjetCarteParametresExces() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-0-0-0");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void plusieursDefinitionsObjetCarte() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-1-1");
        listeLignes.add("C-2-2");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void ligneObjetMontagneParametresInsuffisants() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("M-0-");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void ligneObjetMontagneParametresExces() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("M-0-0-0");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void ligneObjetTresorParametresInsuffisants() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("T-0-");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void ligneObjetTresorParametresExces() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("T-0-2-1-5");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void ligneObjetAventurierParametresInsuffisants() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("A-0-");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void ligneObjetAventurierParametresExces() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("A-0-2-1-5-9-6-5");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void plusieursMontagnesMemeCase() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-1-1");
        listeLignes.add("M-0-0");
        listeLignes.add("M-0-0");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void definitionMontagneHorsCarte() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-1-1");
        listeLignes.add("M-1-1");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void plusieursTresorsMemeCase() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-1-1");
        listeLignes.add("T-0-0-1");
        listeLignes.add("T-0-0-2");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void definitionTresorSurMontagne() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-1-1");
        listeLignes.add("M-0-0");
        listeLignes.add("T-0-0-1");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void definitionTresorHorsCarte() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-1-1");
        listeLignes.add("T-1-1-1");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void plusieursAventuriersMemeCase() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-1-1");
        listeLignes.add("A-Lara-0-0-E-AADADAGGA");
        listeLignes.add("A-Lara-0-0-O-AADADAGGA");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void definitionAventurierSurMontagne() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-1-1");
        listeLignes.add("M-0-0");
        listeLignes.add("A-Lara-0-0-S-AADADAGGA");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void definitionAventurierHorsCarte() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-1-1");
        listeLignes.add("A-Lara-1-1-S-AADADAGGA");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void codeOrientationCardinaleAventurierInconnu() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-1-1");
        listeLignes.add("A-Lara-0-0-X-AADADAGGA");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void codeMouvementAventurierInconnu() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-1-1");
        listeLignes.add("A-Lara-0-0-N-XADADAGGA");
        assertThrows(RuntimeException.class, () -> CarteMapper.carteMapping(listeLignes));
    }

    @Test
    void casNominal() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        listeLignes.add("C-3-4");
        listeLignes.add("M-1-0");
        listeLignes.add("M-2-1");
        listeLignes.add("T-0-3-2");
        listeLignes.add("T-1-3-3");
        listeLignes.add("A-Lara-1-1-S-AADADAGGA");
        final Carte carte = CarteMapper.carteMapping(listeLignes);

        assertEquals(3, carte.getLargeur());
        assertEquals(4, carte.getHauteur());
        assertEquals(carte.getMatrice().get(0).size(), carte.getLargeur());
        assertEquals(carte.getMatrice().size(), carte.getHauteur());
        assertFalse(carte.getMatrice().get(0).get(1).isFranchissable());
        assertFalse(carte.getMatrice().get(1).get(2).isFranchissable());
        assertTrue(carte.getMatrice().get(3).get(0).hasTresor());
        assertTrue(carte.getMatrice().get(3).get(1).hasTresor());
        assertTrue(carte.getMatrice().get(1).get(1).hasAventurier());
    }

}
