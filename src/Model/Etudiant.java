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
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public List<Note> getNotes() { return notes; }

    public double getMoyenne() { return moyenne; }
    public void setMoyenne(double moyenne) { this.moyenne = moyenne; }

    public String getMention() { return mention; }
    public void setMention(String mention) { this.mention = mention; }
}