package Model;

import java.util.List;

public class Etudiant {
    private int id;
    private String nom;
    private List<Note> notes;
    private double moyenne;
    private String mention;

    public Etudiant(int id, String nom, List<Note> notes) {
        this.id = id;
        this.nom = nom;
        this.notes = notes;
        this.moyenne = calculerMoyennePonderee();
        this.mention = calculerMention();
    }

    private double calculerMoyennePonderee() {
        double somme = 0;
        double totalCoeff = 0;

        for (Note note : notes) {
            double coeff = note.getMatiere().getCoefficient();
            somme += note.getValeur() * coeff;
            totalCoeff += coeff;
        }

        return totalCoeff == 0 ? 0 : somme / totalCoeff;
    }

    private String calculerMention() {
        if (moyenne >= 16) return "Tres bien";
        if (moyenne >= 14) return "Bien";
        if (moyenne >= 12) return "Assez bien";
        if (moyenne >= 10) return "Passable";
        return "Insuffisant";
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public String getMention() {
        return mention;
    }

    public List<Note> getNotes() {
        return notes;
    }
}