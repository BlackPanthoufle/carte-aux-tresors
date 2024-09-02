package org.carbon.provider.fichier;

import org.apache.commons.io.FilenameUtils;
import org.carbon.business.domain.carte.Carte;
import org.carbon.common.constants.Constantes;
import org.carbon.common.mapper.CarteMapper;
import org.carbon.common.mapper.FichierMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provider de lecture et d'écriture des fichiers
 */
public class FichierProvider {

    /**
     * Lecteur de fichier
     * Fait appel à un mapper pour construire une carte en sortie
     *
     * @param fichier fichier à transformer en objet Carte
     * @return une carte remplie avec les conditions initiales
     */
    public static Carte reader(final File fichier) {
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
     * @param fichierEntree fichier d'entrée
     */
    public static void writer(final Carte carte, final File fichierEntree) {
        final ArrayList<String> listeLignes = FichierMapper.fichierMapping(carte);

        try {
            final String chemin = Paths.get(fichierEntree.toURI()).getParent().toString() + File.separator;
            final String nomBaseFichier = FilenameUtils.getBaseName(fichierEntree.getName());
            final String extensionFichier = "." + FilenameUtils.getExtension(fichierEntree.getName());
            final File fichierSortie = new File(chemin
                    + nomBaseFichier
                    + Constantes.EXTENSION_FICHIER_SORTIE
                    + extensionFichier);
            final FileWriter writer = new FileWriter(fichierSortie);

            for (String ligne : listeLignes) {
                writer.write(ligne);
                writer.write("\r");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
