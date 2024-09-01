package org.carbon.provider.fichier;

import org.carbon.business.domain.carte.Carte;
import org.carbon.common.constants.Constantes;
import org.carbon.common.mapper.CarteMapper;
import org.carbon.common.mapper.FichierMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provider de lecture et d'écriture des fichiers
 */
public class FichierProvider {

    /**
     * Fichier à lire et réécrire en sortie
     */
    private final File fichier;

    /**
     * Constructeur paramétré
     *
     * @param pFichier fichier à lire et réécrire
     * @throws FileNotFoundException si le fichier n'est pas trouvé
     */
    public FichierProvider(final File pFichier) throws FileNotFoundException {
        if (!pFichier.isFile()) {
            throw new FileNotFoundException();
        }

        fichier = pFichier;
    }

    /**
     * @return fichier
     */
    public File getFichier() {
        return fichier;
    }

    /**
     * Lecteur de fichier
     * Fait appel à un mapper pour construire une carte en sortie
     *
     * @return une carte remplie avec les conditions initiales
     */
    public Carte reader() {
        final ArrayList<String> listeLignes = new ArrayList<>();
        try {
            final Scanner scanner = new Scanner(fichier);
            while (scanner.hasNextLine()) {
                listeLignes.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return CarteMapper.carteMapping(listeLignes);
    }


    /**
     * Écrivain de fichier
     * Fait appel à un mapper pour construire les lignes du fichier de sortie
     *
     * @param carte la carte à transformer au format fichier
     */
    public void writer(final Carte carte) {
        final ArrayList<String> listeLignes = FichierMapper.fichierMapping(carte);

        try {
            final File fichierTemporaire = new File(fichier.getName() + Constantes.EXTENSION_FICHIER_TEMPORAIRE);
            final FileWriter writer = new FileWriter(fichierTemporaire);

            for (String ligne : listeLignes) {
                writer.write(ligne);
            }
            writer.close();

            final Path fichierTemporairePath = Paths.get(fichierTemporaire.toURI());
            final Path fichierPath = Paths.get(fichier.toURI());
            Files.move(fichierTemporairePath, fichierPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
