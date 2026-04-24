package mapper;

import Model.*;
import repository.MatiereRepository;

import java.util.*;

/**
 * Transforme une ligne CSV en objet Etudiant
 */
public class EtudiantMapper {

    // Accès central aux matières
    private final MatiereRepository repo;

    // Injection du repository
    public EtudiantMapper(MatiereRepository repo) {
        this.repo = repo;
    }

    /**
     * Convertit une ligne CSV en Etudiant
     */
    public Etudiant map(String[] colonnes, String[] parties) {

        // Données de base
        int id = Integer.parseInt(parties[0]);
        String nom = parties[1];

        // Liste des notes
        List<Note> notes = new ArrayList<>();

        // Parcours des matières (à partir de la 3e colonne)
        for (int i = 2; i < parties.length; i++) {
            try {

                // Conversion de la note
                double valeur = Double.parseDouble(parties[i]);

                // Ignore les valeurs hors [0,20]
                if (valeur < 0 || valeur > 20) continue;

                // Récupère la matière correspondante
                Matiere matiere = repo.get(colonnes[i]);

                // Ajoute la note
                notes.add(new Note(matiere, valeur));

            } catch (NumberFormatException ignored) {
                // Ignore les valeurs non numériques
            }
        }

        // Création de l'étudiant
        return new Etudiant(id, nom, notes);
    }
}