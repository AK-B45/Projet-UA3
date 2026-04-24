package repository;

import Model.Matiere;
import java.util.*;

/**
 * Repository central des matières du système.
 *
 * Rôle :
 * - Stocker les matières disponibles
 * - Fournir un accès unique et centralisé aux matières
 * - Servir de référence métier pour validation et mapping
 *
 * Importance :
 * Cette classe évite la duplication des matières dans le code
 * et garantit une cohérence globale du système.
 */
public class MatiereRepository {

    // Map interne :
    // clé   = nom de la matière (format normalisé)
    // valeur = objet Matiere (nom + coefficient)
    private final Map<String, Matiere> matieres = new HashMap<>();

    /**
     * Initialisation des matières disponibles dans le système.
     *
     * Ici, les matières sont définies en dur (approche simple projet étudiant).
     */
    public MatiereRepository() {

        matieres.put("math", new Matiere("Math", 3));
        matieres.put("physique", new Matiere("Physique", 2));
        matieres.put("informatique", new Matiere("Informatique", 4));
    }

    /**
     * Récupère une matière à partir de son nom.
     *
     * @param nom nom de la matière (insensible à la casse et espaces)
     * @return objet Matiere correspondant ou null si inexistante
     */
    public Matiere get(String nom) {

        // Normalisation de la clé pour éviter les erreurs de format
        return matieres.get(nom.toLowerCase().trim());
    }

    /**
     * Vérifie si une matière existe dans le repository.
     *
     * @param nom nom de la matière
     * @return true si la matière existe, sinon false
     */
    public boolean exists(String nom) {

        // Vérification basée sur la clé normalisée
        return matieres.containsKey(nom.toLowerCase().trim());
    }

    /**
     * Retourne toutes les clés des matières disponibles.
     *
     * @return ensemble des noms de matières
     */
    public Set<String> getAll() {
        return matieres.keySet();
    }
}