package mapper;

import Model.*;
import repository.MatiereRepository;

import java.util.*;

/**
 * Classe responsable de la transformation des données brutes CSV
 * en objets métier Etudiant.
 *
 * Rôle principal :
 * - Convertir une ligne CSV en objet Etudiant
 * - Associer les colonnes aux matières du repository
 * - Créer les objets Note correspondants
 */
public class EtudiantMapper {

    // Référentiel central des matières (source de vérité des noms + coefficients)
    private final MatiereRepository repo;

    /**
     * Injection du repository des matières
     * Permet de respecter le principe de dépendance (DIP)
     */
    public EtudiantMapper(MatiereRepository repo) {
        this.repo = repo;
    }

    /**
     * Transforme une ligne CSV en objet Etudiant
     *
     * @param colonnes tableau des en-têtes CSV (id, nom, Math, Physique, ...)
     * @param parties  valeurs de la ligne correspondante
     *
     * @return objet Etudiant construit à partir des données CSV
     */
    public Etudiant map(String[] colonnes, String[] parties) {

        // Extraction des données de base de l'étudiant
        int id = Integer.parseInt(parties[0]);   // conversion id texte → entier
        String nom = parties[1];                  // nom de l'étudiant

        // Liste des notes associées à l'étudiant
        List<Note> notes = new ArrayList<>();

        /**
         * Parcours des colonnes à partir de l'indice 2 :
         * - 0 = id
         * - 1 = nom
         * - 2+ = matières
         */
        for (int i = 2; i < parties.length; i++) {
            try {

                // Conversion de la note (String → double)
                double valeur = Double.parseDouble(parties[i]);

                // Validation de la plage de note (0 à 20)
                // Toute valeur invalide est ignorée
                if (valeur < 0 || valeur > 20) continue;

                /**
                 * Récupération de la matière correspondante
                 * via le repository (source de vérité)
                 *
                 * Exemple :
                 * colonne "Math" → Matiere("Math", coef=3)
                 */
                Matiere matiere = repo.get(colonnes[i]);

                // Création d'une note liée à une matière
                notes.add(new Note(matiere, valeur));

            } catch (NumberFormatException ignored) {
                // Cas où la valeur n'est pas un nombre valide
                // Exemple : cellule vide, texte, erreur CSV
                // → la valeur est simplement ignorée
            }
        }

        /**
         * Création finale de l'objet Etudiant
         * avec ses notes associées
         */
        return new Etudiant(id, nom, notes);
    }
}