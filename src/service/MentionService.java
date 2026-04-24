package service;

/**
 * Service métier responsable de l’attribution des mentions
 * en fonction de la moyenne d’un étudiant.
 *
 * Rôle :
 * - Convertir une moyenne numérique en mention qualitative
 * - Centraliser les règles de notation
 *
 * Important :
 * - Classe stateless (aucun attribut)
 * - Facilement modifiable si les seuils changent (OCP)
 */
public class MentionService {

    /**
     * Attribue une mention en fonction de la moyenne
     *
     * Règles :
     * - >= 16 : Très bien
     * - >= 14 : Bien
     * - >= 12 : Assez bien
     * - >= 10 : Passable
     * - < 10  : Insuffisant
     *
     * @param moyenne moyenne calculée de l'étudiant
     * @return mention correspondante
     */
    public String attribuer(double moyenne) {

        if (moyenne >= 16) return "Très bien";

        if (moyenne >= 14) return "Bien";

        if (moyenne >= 12) return "Assez bien";

        if (moyenne >= 10) return "Passable";

        return "Insuffisant";
    }
}