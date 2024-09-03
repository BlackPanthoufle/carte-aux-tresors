package org.carbon.provider.fichier;

import org.apache.commons.io.FileUtils;
import org.carbon.business.domain.carte.Carte;
import org.carbon.business.domain.cases.Case;
import org.carbon.business.domain.cases.impl.Plaine;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FichierProviderTest {

    final File fichierOriginal = new File("src/test/resources/fichier-test.txt");

    final File fichierCopie = new File("cartes/fichier-test.txt");

    final File fichierCopieSortie = new File("cartes/fichier-test-sortie.txt");

    final File fichierImpossibleCopie = new File("cartes/rep-inexistant/fichier-test.txt");

    final File fichierIntrouvable = new File("cartes/fichier-test-introuvable.txt");

    final int hauteur = 4;

    final int largeur = 3;

    Carte carte;

    @BeforeAll
    void initialisation() throws IOException {
        FileUtils.copyFile(fichierOriginal, fichierCopie);

        final ArrayList<ArrayList<Case>> matrice = new ArrayList<>();

        for (int i = 0 ; i < hauteur ; i++) {
            final ArrayList<Case> ligne = new ArrayList<>();

            for (int j = 0 ; j < largeur ; j++) {
                ligne.add(new Plaine());
            }

            matrice.add(ligne);
        }
        carte = new Carte(largeur, hauteur, matrice);
    }

    @AfterAll
    void cloture() throws IOException {
        FileUtils.delete(fichierCopie);
        FileUtils.delete(fichierCopieSortie);
    }

    @Test
    void lectureFichierExistant() {
        final Carte carte = FichierProvider.reader(fichierCopie);

        assertEquals(4, carte.getHauteur());
        assertEquals(3, carte.getLargeur());
        assertEquals(carte.getHauteur(), carte.getMatrice().size());
        assertEquals(carte.getLargeur(), carte.getMatrice().get(0).size());
    }

    @Test
    void lectureFichierInexistant() {
        assertThrows(RuntimeException.class, () -> FichierProvider.reader(fichierIntrouvable));
    }

    @Test
    void ecritureFichierSucces() {
        FichierProvider.writer(carte, fichierCopie);
        assertTrue(fichierCopieSortie.exists() && !fichierCopieSortie.isDirectory());
    }

    @Test
    void ecritureFichierEchec() {
        assertThrows(RuntimeException.class, () -> FichierProvider.writer(carte, fichierImpossibleCopie));
    }

}
