import io.*;
import Model.Etudiant;
import service.GestionNotes;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DataReader reader = new CSVReader();
        DataWriter writer = new CSVWriter();
        GestionNotes service = new GestionNotes();

        List<Etudiant> etudiants = reader.lire("notes.csv");

        service.trierEtudiants(etudiants);

        writer.ecrire("resultats.csv", etudiants);

    }
}
