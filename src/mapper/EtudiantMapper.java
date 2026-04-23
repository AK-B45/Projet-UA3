package mapper;

import Model.*;
import repository.MatiereRepository;

import java.util.*;

public class EtudiantMapper {

    private final MatiereRepository repo;

    public EtudiantMapper(MatiereRepository repo) {
        this.repo = repo;
    }

    public Etudiant map(String[] colonnes, String[] parties) {

        int id = Integer.parseInt(parties[0]);
        String nom = parties[1];

        List<Note> notes = new ArrayList<>();

        for (int i = 2; i < parties.length; i++) {
            try {
                double valeur = Double.parseDouble(parties[i]);

                if (valeur < 0 || valeur > 20) continue;

                Matiere matiere = repo.get(colonnes[i]);
                notes.add(new Note(matiere, valeur));

            } catch (NumberFormatException ignored) {}
        }

        return new Etudiant(id, nom, notes);
    }
}