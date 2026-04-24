import Model.Etudiant;
import io.CSVReader;
import io.DataReader;
import mapper.EtudiantMapper;
import repository.MatiereRepository;
import service.GestionNotes;
import service.MentionService;
import service.MoyenneService;
import ui.ConsoleUI;
import validation.CSVValidator;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String fichierEntree = "notes.csv";

        MatiereRepository repo = new MatiereRepository();
        CSVValidator validator = new CSVValidator(repo);
        EtudiantMapper mapper = new EtudiantMapper(repo);
        DataReader reader = new CSVReader(validator, mapper);

        List<Etudiant> etudiants = reader.lire(fichierEntree);

        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant trouvé.");
            return;
        }

        MoyenneService moyenneService = new MoyenneService();
        MentionService mentionService = new MentionService();

        for (Etudiant e : etudiants) {
            e.setMoyenne(moyenneService.calculer(e));
            e.setMention(mentionService.attribuer(e.getMoyenne()));
        }

        GestionNotes gestion = new GestionNotes();
        gestion.trierEtudiants(etudiants);

        ConsoleUI ui = new ConsoleUI(etudiants, moyenneService, mentionService);
        ui.demarrer();
    }
}