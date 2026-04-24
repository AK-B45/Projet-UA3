package service;

import Model.Etudiant;
import Model.Note;

/**
 * Service métier responsable du calcul de la moyenne pondérée d'un étudiant.
 *
 * Rôle :
 * - Appliquer la formule de moyenne pondérée
 * - Utiliser les coefficients des matières
 * - Retourner une valeur exploitable par l'entité Etudiant
 *
 * Important :
 * - Aucune donnée n'est stockée ici (classe stateless)
 * - Respect du principe SRP (Single Responsibility Principle)
 */
public class MoyenneService {

    /**
     * Calcule la moyenne pondérée d'un étudiant
     *
     * Formule :
     * somme(note × coefficient) / somme(coefficients)
     *
     * @param e étudiant contenant les notes
     * @return moyenne pondérée (double)
     */
    public double calculer(Etudiant e) {

        // Somme pondérée des notes
        double somme = 0;

        // Somme des coefficients des matières
        double coeffTotal = 0;

        /**
         * Parcours de toutes les notes de l'étudiant
         */
        for (Note n : e.getNotes()) {

            // Coefficient de la matière associée à la note
            double coeff = n.getMatiere().getCoefficient();

            // Ajout pondéré (note × coefficient)
            somme += n.getValeur() * coeff;

            // Accumulation des coefficients
            coeffTotal += coeff;
        }

        /**
         * Gestion du cas où aucun coefficient n'existe
         * (évite une division par zéro)
         */
        return coeffTotal == 0 ? 0 : somme / coeffTotal;
    }
}