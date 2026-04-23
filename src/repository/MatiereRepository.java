package repository;

import Model.Matiere;
import java.util.*;

public class MatiereRepository {

    private final Map<String, Matiere> matieres = new HashMap<>();

    public MatiereRepository() {
        matieres.put("math", new Matiere("Math", 3));
        matieres.put("physique", new Matiere("Physique", 2));
        matieres.put("informatique", new Matiere("Informatique", 4));
    }

    public Matiere get(String nom) {
        return matieres.get(nom.toLowerCase().trim());
    }

    public boolean exists(String nom) {
        return matieres.containsKey(nom.toLowerCase().trim());
    }

    public Set<String> getAll() {
        return matieres.keySet();
    }
}