package io;

import Model.Etudiant;

import java.io.*;
import java.util.List;
import java.util.Locale;

public class CSVWriter implements DataWriter {

    @Override
    public void ecrire(String fichier, List<Etudiant> etudiants) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichier))) {

            bw.write("rang,id,nom,moyenne,mention");
            bw.newLine();

            int rang = 1;
            for (Etudiant e : etudiants) {
                bw.write(rang + "," + e.getId() + "," + e.getNom() + "," +
                        String.format(Locale.US, e.getMoyenne()) + "," +
                        e.getMention());
                bw.newLine();
                rang++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
