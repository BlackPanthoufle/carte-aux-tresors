package org.carbon.application;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationTest {

    final File fichierOriginal = new File("src/test/resources/fichier-test.txt");

    final File fichierCopie = new File("cartes/fichier-test.txt");

    final File fichierCopieSortie = new File("cartes/fichier-test-sortie.txt");

    @BeforeAll
    void initialisation() throws IOException {
        FileUtils.copyFile(fichierOriginal, fichierCopie);
    }

    @AfterAll
    void cloture() throws IOException {
        FileUtils.delete(fichierCopie);
        FileUtils.delete(fichierCopieSortie);
    }

    @Test
    void applicationTest() {
        String[] args = new String[1];
        args[0] = FilenameUtils.getName(fichierCopie.getName());
        Application.main(args);
        assertTrue(fichierCopieSortie.exists() && !fichierCopieSortie.isDirectory());
    }
}
