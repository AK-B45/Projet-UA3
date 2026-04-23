package io;

import mapper.EtudiantMapper;
import Model.Etudiant;
import validation.CSVValidator;

import java.io.*;
import java.util.*;

public class CSVReader implements DataReader {

    private final CSVValidator validator;
    private final EtudiantMapper mapper;

    public CSVReader(CSVValidator validator, EtudiantMapper mapper) {
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public List<Etudiant> lire(String fichier) {

        List<Etudiant> etudiants = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {

            String header = br.readLine();
            String[] colonnes = header.split(",");

            validator.validerHeader(colonnes);

            String ligne;

            while ((ligne = br.readLine()) != null) {
                String[] parties = ligne.split(",");

                if (parties.length != colonnes.length) continue;

                etudiants.add(mapper.map(colonnes, parties));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return etudiants;
    }
}