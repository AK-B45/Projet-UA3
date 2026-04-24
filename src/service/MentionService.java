package service;

/**
 * Attribue une mention selon la moyenne d’un étudiant.
 */
public class MentionService {

    /**
     * Retourne la mention associée à une moyenne.
     */
    public String attribuer(double moyenne) {

        if (moyenne >= 16) return "Très bien";
        if (moyenne >= 14) return "Bien";
        if (moyenne >= 12) return "Assez bien";
        if (moyenne >= 10) return "Passable";

        return "Insuffisant";
    }
}