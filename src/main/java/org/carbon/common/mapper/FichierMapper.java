package org.carbon.common.mapper;

import org.carbon.business.domain.carte.Carte;
import org.carbon.common.constants.Constantes;

import java.util.ArrayList;

/**
 * Classe de mapping Carte -> Fichier
 */
public class FichierMapper {

    /**
     * Méthode de mapping Carte -> Fichier
     *
     * @param carte carte à retranscrire au format fichier
     * @return une liste de chaînes de caractères à écrire dans le fichier de sortie
     */
    public static ArrayList<String> fichierMapping(final Carte carte) {
        final ArrayList<String> fichier = new ArrayList<>();

        final String ligneCarte = genererLigneCarte(carte);
        fichier.add(ligneCarte);

        final ArrayList<String> lignesMontagnes = genererLignesMontagnes(carte);
        fichier.addAll(lignesMontagnes);

        final ArrayList<String> lignesTresors = genererLignesTresors(carte);
        fichier.addAll(lignesTresors);

        final ArrayList<String> lignesAventuriers = genererLignesAventuriers(carte);
        fichier.addAll(lignesAventuriers);

        return fichier;
    }

    /**
     * Générateur de ligne pour les informations sur la carte
     *
     * @param carte objet Carte
     * @return ligne d'informations pour une carte
     */
    private static String genererLigneCarte(final Carte carte) {
        return Constantes.IDENTIFIANT_CARTE_FICHIER + Constantes.SEPARATEUR_ELEMENTS_FICHIER + carte.getLargeur()
                + Constantes.SEPARATEUR_ELEMENTS_FICHIER + carte.getHauteur();
    }

    /**
     * Générateur de liste de lignes pour les informations sur les montagnes
     *
     * @param carte objet Carte
     * @return liste de lignes d'informations des montagnes sur la carte
     */
    private static ArrayList<String> genererLignesMontagnes(final Carte carte) {
        final ArrayList<String> lignesMontagnes = new ArrayList<>();

        for (int i = 0 ; i < carte.getHauteur() ; i++) {
            for (int j = 0 ; j < carte.getLargeur() ; j++) {
                if (!carte.getMatrice().get(i).get(j).isFranchissable()) {
                    lignesMontagnes.add(Constantes.IDENTIFIANT_MONTAGNE_FICHIER + Constantes.SEPARATEUR_ELEMENTS_FICHIER
                            + j + Constantes.SEPARATEUR_ELEMENTS_FICHIER + i);
                }
            }
        }

        return lignesMontagnes;
    }

    /**
     * Générateur de liste de lignes pour les informations sur les trésors
     *
     * @param carte objet Carte
     * @return liste de lignes d'informations des trésors sur la carte
     */
    private static ArrayList<String> genererLignesTresors(final Carte carte) {
        final ArrayList<String> lignesTresors = new ArrayList<>();

        for (int i = 0 ; i < carte.getHauteur() ; i++) {
            for (int j = 0 ; j < carte.getLargeur() ; j++) {
                if (carte.getMatrice().get(i).get(j).isFranchissable()
                        && carte.getMatrice().get(i).get(j).hasTresor()) {
                    lignesTresors.add(Constantes.IDENTIFIANT_TRESOR_FICHIER + Constantes.SEPARATEUR_ELEMENTS_FICHIER + j
                            + Constantes.SEPARATEUR_ELEMENTS_FICHIER + i + Constantes.SEPARATEUR_ELEMENTS_FICHIER
                            + carte.getMatrice().get(i).get(j).getNbTresor());
                }
            }
        }

        return lignesTresors;
    }

    /**
     * Générateur de liste de lignes pour les informations sur les aventuriers
     *
     * @param carte objet Carte
     * @return liste de lignes d'informations des aventuriers sur la carte
     */
    private static ArrayList<String> genererLignesAventuriers(final Carte carte) {
        final ArrayList<String> lignesAventuriers = new ArrayList<>();

        for (int i = 0 ; i < carte.getHauteur() ; i++) {
            for (int j = 0 ; j < carte.getLargeur() ; j++) {
                if (carte.getMatrice().get(i).get(j).isFranchissable()
                        && carte.getMatrice().get(i).get(j).hasAventurier()) {
                    lignesAventuriers.add(Constantes.IDENTIFIANT_AVENTURIER_FICHIER
                            + Constantes.SEPARATEUR_ELEMENTS_FICHIER
                            + carte.getMatrice().get(i).get(j).getAventurier().getNom()
                            + Constantes.SEPARATEUR_ELEMENTS_FICHIER  + j + Constantes.SEPARATEUR_ELEMENTS_FICHIER + i
                            + Constantes.SEPARATEUR_ELEMENTS_FICHIER
                            + carte.getMatrice().get(i).get(j).getAventurier().getOrientationCardinale().getCodeOrientationCardinale()
                            + Constantes.SEPARATEUR_ELEMENTS_FICHIER
                            + carte.getMatrice().get(i).get(j).getAventurier().getTresorsRamasses());
                }
            }
        }

        return lignesAventuriers;
    }

}
