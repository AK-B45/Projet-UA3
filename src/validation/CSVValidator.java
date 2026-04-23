package validation;

import repository.MatiereRepository;

public class CSVValidator {

    private final MatiereRepository repo;

    public CSVValidator(MatiereRepository repo) {
        this.repo = repo;
    }

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