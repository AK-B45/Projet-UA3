package io;

import mapper.EtudiantMapper;
import Model.Etudiant;
import validation.CSVValidator;

import java.io.*;
import java.util.*;

/**
 * Classe responsable de la lecture d'un fichier CSV
 * et de la conversion des données en objets Etudiant.
 *
 * Rôle :
 * - Lire un fichier CSV ligne par ligne
 * - Vérifier la structure du fichier
 * - Déléguer la validation
 * - Déléguer le mapping vers les objets métier
 */
public class CSVReader implements DataReader {

    // Validation de la structure du CSV (header + cohérence)
    private final CSVValidator validator;

    // Transformation des lignes CSV en objets Etudiant
    private final EtudiantMapper mapper;

    /**
     * Injection des dépendances
     * Permet de respecter le principe de dépendance (DIP)
     */
    public CSVReader(CSVValidator validator, EtudiantMapper mapper) {
        this.validator = validator;
        this.mapper = mapper;
    }

    /**
     * Lecture complète du fichier CSV
     *
     * @param fichier chemin du fichier CSV
     * @return liste d'objets Etudiant construits à partir du fichier
     */
    @Override
    public List<Etudiant> lire(String fichier) {

        // Liste résultat contenant tous les étudiants
        List<Etudiant> etudiants = new ArrayList<>();

        // Ouverture du fichier avec fermeture automatique (try-with-resources)
        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {

            /**
             * Lecture de la première ligne du fichier (header)
             * Exemple : id,nom,Math,Physique,...
             */
            String header = br.readLine();

            // Découpage du header en colonnes
            String[] colonnes = header.split(",");

            // Validation de la structure du fichier CSV
            validator.validerHeader(colonnes);

            String ligne;

            /**
             * Lecture ligne par ligne des données étudiants
             */
            while ((ligne = br.readLine()) != null) {

                // Découpage de la ligne en cellules
                String[] parties = ligne.split(",");

                /**
                 * Vérification de cohérence :
                 * la ligne doit avoir le même nombre de colonnes que le header
                 */
                if (parties.length != colonnes.length) continue;

                /**
                 * Délégation du mapping :
                 * conversion CSV → objet Etudiant
                 */
                etudiants.add(mapper.map(colonnes, parties));
            }

        } catch (IOException e) {

            /**
             * Gestion des erreurs de lecture fichier
             * (fichier introuvable, problème disque, etc.)
             */
            e.printStackTrace();
        }

        // Retour de la liste finale d'étudiants
        return etudiants;
    }
}