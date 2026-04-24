package service;

import Model.Etudiant;
import Model.Note;

/**
 * Calcule la moyenne pondérée d’un étudiant.
 */
public class MoyenneService {

    /**
     * Retourne la moyenne pondérée.
     */
    public double calculer(Etudiant e) {

        double somme = 0;
        double coeffTotal = 0;

        for (Note n : e.getNotes()) {

            double coeff = n.getMatiere().getCoefficient();

            somme += n.getValeur() * coeff;
            coeffTotal += coeff;
        }

        return coeffTotal == 0 ? 0 : somme / coeffTotal;
    }

    private String formatDouble(double value) {
        return String.format(java.util.Locale.US, "%.2f", value);
    }
}