import io.CSVReader;
import io.DataReader;

import mapper.EtudiantMapper;
import validation.CSVValidator;
import repository.MatiereRepository;

import Model.Etudiant;

import service.MoyenneService;
import service.MentionService;
import service.GestionNotes;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String fichierEntree = "notes.csv";

        // 1. Couche infrastructure (dépendances)
        MatiereRepository repo = new MatiereRepository();

        CSVValidator validator = new CSVValidator(repo);
        EtudiantMapper mapper = new EtudiantMapper(repo);

        DataReader reader = new CSVReader(validator, mapper);

        // 2. Lecture
        List<Etudiant> etudiants = reader.lire(fichierEntree);

        // 3. Calcul métier
        MoyenneService moyenneService = new MoyenneService();
        MentionService mentionService = new MentionService();

        for (Etudiant e : etudiants) {
            e.setMoyenne(moyenneService.calculer(e));
            e.setMention(mentionService.attribuer(e.getMoyenne()));
        }

        // 4. Tri
        GestionNotes gestion = new GestionNotes();
        gestion.trierEtudiants(etudiants);

        // 5. Fin
        System.out.println("Traitement terminé !");
    }
}