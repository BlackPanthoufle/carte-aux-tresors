package org.carbon.application;

import org.carbon.business.domain.carte.Carte;
import org.carbon.common.constants.Constantes;
import org.carbon.provider.carte.CarteProvider;
import org.carbon.provider.fichier.FichierProvider;

import java.io.File;

/**
 * Classe de lancement de l'application
 */
public class Application {

    public static void main(String[] args) {
        final String cheminFichier = Constantes.REPERTOIRE_ENTREE + args[0];
        final File fichier = new File(cheminFichier);

        final Carte carte = FichierProvider.reader(fichier);

        CarteProvider.demarrerExplorations(carte);

        FichierProvider.writer(carte, fichier);
    }

}
