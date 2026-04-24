package validation;

import repository.MatiereRepository;

/**
 * Valide la structure d’un fichier CSV :
 * - cohérence du header
 * - existence des matières
 */
public class CSVValidator {

    // Source de vérité des matières disponibles
    private final MatiereRepository repo;

    public CSVValidator(MatiereRepository repo) {
        this.repo = repo;
    }

    /**
     * Vérifie la validité du header CSV.
     * <p>
     * Format attendu :
     * <p>
     * - colonne 0 : id
     * - colonne 1 : nom
     * - colonnes suivantes : matières existantes dans le système
     */
    public void validerHeader(String[] colonnes) {

        if (!colonnes[0].equals("id") || !colonnes[1].equals("nom")) {
            throw new RuntimeException("Header invalide");
        }

        for (int i = 2; i < colonnes.length; i++) {

            if (!repo.exists(colonnes[i])) {
                throw new RuntimeException("Matière inconnue : " + colonnes[i]);
            }
        }
    }
}