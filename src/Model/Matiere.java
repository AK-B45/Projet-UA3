package Model;

public class Matiere {
    private String nom;
    private double coefficient;

    public Matiere(String nom, double coefficient) {
        this.nom = nom;
        this.coefficient = coefficient;
    }

    public String getNom() {
        return nom;
    }

    public double getCoefficient() {
        return coefficient;
    }
}