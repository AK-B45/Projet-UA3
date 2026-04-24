package service;

import Model.Etudiant;
import Model.Note;

public class MoyenneService {

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
}