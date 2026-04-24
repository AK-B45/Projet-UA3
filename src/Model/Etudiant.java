package Model;

import java.util.List;

/**
 * Classe métier représentant un étudiant.
 *
 * Rôle :
 * - Stocker les informations de base d’un étudiant
 * - Stocker ses notes
 * - Stocker les résultats calculés (moyenne, mention)
 *
 * Important :
 * Cette classe ne contient aucune logique de calcul
 * (respect du principe SRP - Single Responsibility Principle)
 */
public class Etudiant {

    // Identifiant unique de l'étudiant
    private int id;

    // Nom de l'étudiant
    private String nom;

    // Liste des notes associées à différentes matières
    private List<Note> notes;

    // Résultat calculé : moyenne pondérée
    private double moyenne;

    // Résultat calculé : mention associée à la moyenne
    private String mention;

    /**
     * Constructeur principal
     *
     * @param id identifiant de l'étudiant
     * @param nom nom de l'étudiant
     * @param notes liste des notes associées
     */
    public Etudiant(int id, String nom, List<Note> notes) {
        this.id = id;
        this.nom = nom;
        this.notes = notes;
    }

    // ===================== GETTERS =====================

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public String getMention() {
        return mention;
    }

    // ===================== SETTERS =====================

    /**
     * Mise à jour de la moyenne après calcul par un service externe
     */
    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    /**
     * Mise à jour de la mention après calcul par un service externe
     */
    public void setMention(String mention) {
        this.mention = mention;
    }
}