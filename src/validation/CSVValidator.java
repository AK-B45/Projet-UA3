package validation;

import repository.MatiereRepository;

/**
 * Classe responsable de la validation de la structure d'un fichier CSV.
 *
 * Rôle :
 * - Vérifier la cohérence du header
 * - Vérifier que les matières existent dans le système
 * - Garantir que les données peuvent être traitées correctement
 */
public class CSVValidator {

    // Référentiel des matières autorisées (source de vérité)
    private final MatiereRepository repo;

    /**
     * Injection du repository des matières
     * Permet de valider dynamiquement les colonnes du CSV
     */
    public CSVValidator(MatiereRepository repo) {
        this.repo = repo;
    }

    /**
     * Validation de la première ligne du CSV (header)
     *
     * @param colonnes tableau représentant les en-têtes du CSV
     */
    public void validerHeader(String[] colonnes) {

        /**
         * Vérification des colonnes obligatoires
         * - colonne 0 : id
         * - colonne 1 : nom
         */
        if (!colonnes[0].equals("id") || !colonnes[1].equals("nom")) {
            throw new RuntimeException("Header invalide");
        }

        /**
         * Vérification des matières présentes dans le CSV
         * On commence à partir de l'indice 2 car :
         * - 0 = id
         * - 1 = nom
         * - 2+ = matières
         */
        for (int i = 2; i < colonnes.length; i++) {

            /**
             * Vérifie si la matière existe dans le repository
             * (source de vérité du système)
             */
            if (!repo.exists(colonnes[i])) {

                // Erreur levée si matière inconnue
                throw new RuntimeException(
                        "Matière inconnue : " + colonnes[i]
                );
            }
        }
    }
}