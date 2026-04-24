package repository;

import Model.Matiere;
import java.util.*;

/**
 * Repository central des matières du système.
 * <p>
 * ============================
 * PATRON DE CONCEPTION UTILISÉ
 * ============================
 * Repository Pattern :
 * - Centralise l'accès aux données métier
 * - Sert de point unique pour récupérer les matières
 * - Évite la duplication de logique et de données
 * <p>
 * ============================
 * RÔLE GLOBAL
 * ============================
 * - Stocker les matières disponibles dans le système
 * - Fournir un accès cohérent et contrôlé
 * - Garantir l'unicité des données métier (source de vérité)
 * <p>
 * ============================
 * IMPACT ARCHITECTURAL
 * ============================
 * - Utilisé par CSVValidator (validation des matières)
 * - Utilisé par EtudiantMapper (création des notes)
 * - Permet de découpler le reste du système des données fixes
 */
public class MatiereRepository {

    /**
     * Structure interne de stockage :
     * - clé : nom normalisé de la matière
     * - valeur : objet Matiere (nom + coefficient)
     */
    private final Map<String, Matiere> matieres = new HashMap<>();

    /**
     * Initialisation des matières du système.
     * <p>
     * Remarque :
     * Dans un projet plus avancé, ces données pourraient venir
     * d'une base de données ou d'un fichier externe.
     */
    public MatiereRepository() {

        matieres.put("math", new Matiere("Math", 3));
        matieres.put("physique", new Matiere("Physique", 2));
        matieres.put("informatique", new Matiere("Informatique", 4));
    }

    /**
     * Récupère une matière à partir de son nom.
     * <p>
     * Normalisation appliquée :
     * - conversion en minuscules
     * - suppression des espaces inutiles
     *
     * @param nom nom de la matière
     * @return objet Matiere correspondant ou null si absent
     */
    public Matiere get(String nom) {

        return matieres.get(nom.toLowerCase().trim());
    }

    /**
     * Vérifie l'existence d'une matière dans le repository.
     *
     * @param nom nom de la matière
     * @return true si la matière existe, sinon false
     */
    public boolean exists(String nom) {

        return matieres.containsKey(nom.toLowerCase().trim());
    }

    /**
     * Retourne toutes les matières disponibles (clés uniquement).
     *
     * @return ensemble des noms de matières
     */
    public Set<String> getAll() {
        return matieres.keySet();
    }
}