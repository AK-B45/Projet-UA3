import io.*;
import mapper.EtudiantMapper;
import repository.MatiereRepository;
import service.GestionNotes;
import validation.CSVValidator;

import java.util.List;
import Model.Etudiant;

public class Main {

    public static void main(String[] args) {

        MatiereRepository repo = new MatiereRepository();

        CSVValidator validator = new CSVValidator(repo);
        EtudiantMapper mapper = new EtudiantMapper(repo);

        DataReader reader = new CSVReader(validator, mapper);
        DataWriter writer = new CSVWriter();

        GestionNotes service = new GestionNotes();

        List<Etudiant> etudiants = reader.lire("notes.csv");

        service.trierEtudiants(etudiants);

        writer.ecrire("resultats.csv", etudiants);
    }
}