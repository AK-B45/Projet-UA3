package io;

import Model.*;

import java.io.*;
import java.util.*;

public class CSVReader implements DataReader {

    private Map<String, Matiere> matieres;

    public CSVReader() {
        matieres = new HashMap<>();
        matieres.put("Math", new Matiere("Math", 3));
        matieres.put("Physique", new Matiere("Physique", 2));
        matieres.put("Informatique", new Matiere("Informatique", 4));
    }

    @Override
    public List<Etudiant> lire(String fichier) {
        List<Etudiant> etudiants = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {

            String header = br.readLine();
            if (header == null) throw new RuntimeException("Fichier vide");

            String[] colonnes = header.split(",");

            // Validation structure
            if (!colonnes[0].equals("id") || !colonnes[1].equals("nom")) {
                throw new RuntimeException("Structure CSV invalide (id,nom attendus)");
            }

            for (int i = 2; i < colonnes.length; i++) {
                if (!matieres.containsKey(colonnes[i])) {
                    throw new RuntimeException("Matière inconnue : " + colonnes[i]);
                }
            }

            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] parties = ligne.split(",");

                if (parties.length != colonnes.length) {
                    System.out.println("Ligne ignorée (format invalide)");
                    continue;
                }

                int id = Integer.parseInt(parties[0]);
                String nom = parties[1];

                List<Note> notes = new ArrayList<>();

                for (int i = 2; i < parties.length; i++) {
                    try {
                        double valeur = Double.parseDouble(parties[i]);

                        // Validation [0, 20]
                        if (valeur < 0 || valeur > 20) {
                            System.out.println("Note hors intervalle [0-20] ignorée : " + valeur);
                            continue;
                        }

                        Matiere matiere = matieres.get(colonnes[i]);
                        notes.add(new Note(matiere, valeur));

                    } catch (NumberFormatException e) {
                        System.out.println("Note invalide ignorée : " + parties[i]);
                    }
                }

                etudiants.add(new Etudiant(id, nom, notes));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return etudiants;
    }
}