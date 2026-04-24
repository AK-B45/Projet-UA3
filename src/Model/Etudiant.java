package Model;

import java.util.List;

/**
 * Représente un étudiant avec ses notes et ses résultats.
 * Classe métier simple (aucun calcul interne).
 */
public class Etudiant {

    // Identifiant de l’étudiant
    private final int id;

    // Nom de l’étudiant
    private String nom;

    // Liste des notes (matière + valeur)
    private List<Note> notes;

    // Moyenne calculée par un service externe
    private double moyenne;

    // Mention calculée par un service externe
    private String mention;

    /**
     * Création de l’étudiant avec ses notes
     */
    public Etudiant(int id, String nom, List<Note> notes) {
        this.id = id;
        this.nom = nom;
        this.notes = notes;
    }

    // GETTERS

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

    // SETTERS (remplis après calcul)

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }
}